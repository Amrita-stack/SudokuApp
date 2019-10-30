package com.sudoku.util;

import org.springframework.stereotype.Component;

import com.sudoku.constants.SudokuConstants;

@Component
public class ValidatorUtilImpl implements SudokuConstants, ValidatorUtil{
	
	@Override
	public char[] readHorizontalGrid(char[][] board, int ix)
	{
		char[] subarray = new char[board.length];

		for(int i = 0; i < board.length; i++)
		{
			subarray[i] = board[ix][i];
		}
		return subarray;
	}

	@Override
	public boolean checkGrid(char[] array)
	{
		final int nBOARD_WIDTH = array.length;
		boolean []temp = new boolean [nBOARD_WIDTH];

		for(int i = 0; i < nBOARD_WIDTH; i++)
		{
			if((array[i] >= INIT_CHAR) && (array[i] <= (INIT_CHAR+nBOARD_WIDTH)))
			{
				int iPos = (array[i] - INIT_CHAR);
				if(!temp[iPos])
				{
					temp[iPos] = true;
				}
				else
				{
					return false;
				}
			}
			else if (array[i] == '.')
			{
				continue;
			}
			else
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public char[] readMxMGrid(char[][] board, int ix)
	{
		char[] subarray = new char[board.length];

		int cOffset = SUB_WIDTH*(ix%SUB_WIDTH);
		int rOffset = SUB_WIDTH*(ix/SUB_WIDTH);

		int i = 0;
		for(int r = 0; r < board.length/SUB_WIDTH; r++)
		{
			for(int c = 0; c < board.length/SUB_WIDTH; c++)
			{
				subarray[i] = board[rOffset+r][cOffset+c];
				i++;
			}
		}
		return subarray;
	}
	
	@Override
	public char[] readVerticalGrid(char[][] board, int ix)
	{
		char[] subarray = new char[board.length];

		for(int i = 0; i < board.length; i++)
		{
			subarray[i] = board[i][ix];
		}
		return subarray;
	}

	@Override
	public boolean isSudokuValid(char[][] board) 
	{
		if(null == board || board.length <= 0 || board[0].length != board.length)
		{
			return false;
		}


		if(SUB_WIDTH*SUB_WIDTH != board.length)
		{
			return false;
		}

		for(int i = 0; i < board.length; i++)
		{
			if(!checkGrid(readHorizontalGrid(board, i)))
			{
				return false;
			}
		}    

		for(int i = 0; i < board.length; i++)
		{
			if(!checkGrid(readVerticalGrid(board, i)))
			{
				return false;
			}
		}    
		for(int i = 0; i < board.length; i++)
		{
			if(!checkGrid(readMxMGrid(board, i)))
			{
				return false;
			}
		}    

		return true;
	}

	@Override
	public boolean isSolved(char[][] board)
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board.length; c++)
			{
				if('.' == board[r][c]) {
					return false;
				}
			}
		}
		return isSudokuValid(board);
	}

}
