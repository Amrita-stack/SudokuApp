package com.sudoku.service;

public interface SudokuService {

	public char[][] getRandomBoard(int N, Character value, int row, int column, boolean isUpdate);
	public boolean solveBoard(char[][] board, int rStart, int cStart);
	
}
