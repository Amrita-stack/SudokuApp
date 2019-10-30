package com.sudoku.util;

public interface ValidatorUtil {

	public char[] readHorizontalGrid(char[][] board, int ix);
	public boolean checkGrid(char[] array);
	public char[] readMxMGrid(char[][] board, int ix);
	public char[] readVerticalGrid(char[][] board, int ix);
	public boolean isSudokuValid(char[][] board);
	public boolean isSolved(char[][] board);
}
