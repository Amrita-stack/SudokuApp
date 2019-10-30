package com.sudoku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sudoku.constants.SudokuConstants;
import com.sudoku.util.ValidatorUtil;

@Component
public class SudokuServiceImpl implements SudokuConstants, SudokuService{

	@Autowired
	private ValidatorUtil validatorUtilImpl;
	
	long nSolutionTracker = 0;
	
	
	private boolean isValidCharAddition(char[][] board, int r, int c, char digit)
	{
		if((r >= 0) && (r < board.length))
		{
			if((c >= 0) && (c < board.length))
			{
				if('.' == board[r][c])
				{
					board[r][c] = digit;
					if(validatorUtilImpl.checkGrid(validatorUtilImpl.readHorizontalGrid(board, r)) &&
							validatorUtilImpl.checkGrid(validatorUtilImpl.readVerticalGrid(board, c)) &&
							validatorUtilImpl.checkGrid(validatorUtilImpl.readMxMGrid(board, SUB_WIDTH*(r/SUB_WIDTH) + c/SUB_WIDTH)) &&
							solveBoard(board, r, c+1))
					{
						return true;
					}
					else
					{
						board[r][c] = '.';
						return false;
					}
				}
				else
				{
					// already contains a potentially valid digit
					return true;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean solveBoard(char[][] board, int rStart, int cStart)
	{
		
		if(cStart >= board.length)
		{
			// roll over to the next row
			cStart = 0;
			rStart++;
		}

		long currentTime = System.currentTimeMillis();
		if(0 == nSolutionTracker) nSolutionTracker = currentTime;
		if((currentTime-nSolutionTracker) > 5000)
		{
			nSolutionTracker = currentTime;
		}

		boolean bPutChar = false;
		for(int r = rStart; r < board.length; r++)
		{
			for(int c = cStart; c < board.length; c++)
			{
				for(char i = 0; i < board.length; i++)
				{
					bPutChar = isValidCharAddition(board, r, c, (char)(INIT_CHAR+i));
					if(bPutChar) break; // potentially solved !
				}
				if(false == bPutChar) {
					return false; // exhausted all possibilities
				}
			}
			cStart = 0; // for next cycle cStart starts from zero.
		}
		return validatorUtilImpl.isSolved(board);
	}

	

	@Override
	public char[][] getRandomBoard(int N, Character value, int row, int column, boolean isUpdate)
	{
		char[][] board = new char[N][N];
		char [] aNums = new char[N];

		while(!validatorUtilImpl.isSudokuValid(board))
		{
			for(int ix = 0; ix < board.length; ix++)
			{
				for(char i = 0; i < board.length; i++)
				{
					aNums[i] = (char)(INIT_CHAR + i);
				}

				int cOffset = SUB_WIDTH*(ix%SUB_WIDTH);
				int rOffset = SUB_WIDTH*(ix/SUB_WIDTH);

				int i = 0;
				for(int r = 0; r < board.length/SUB_WIDTH; r++)
				{
					for(int c = 0; c < board.length/SUB_WIDTH; c++)
					{
						//Randomize
						int iRandom = (int)(Math.random()*(board.length-i)+i); 

						//Sparseness
						if((Math.random()*BOARD_LENGTH)>=((BOARD_LENGTH*7/9))) 
						{
							if(isUpdate) {
								if(rOffset+r == (row) && cOffset+c == (column)) {
									board[rOffset+r][cOffset+c] = value;
								}
								
							}else {
								board[rOffset+r][cOffset+c] = aNums[iRandom];
							}
							
						}
						else
						{
							board[rOffset+r][cOffset+c] = '.';
						}
						aNums[iRandom] = aNums[i];
 						i++;
					}
				}
			}
		}	 
		if(isUpdate) {
			board[row][column] = value;
		}
		return board;
	}


}
