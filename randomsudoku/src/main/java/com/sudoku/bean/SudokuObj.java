package com.sudoku.bean;

import java.io.Serializable;

public class SudokuObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Character val;
	public Integer row;
	public Integer column;
	
	
	
	public Character getVal() {
		return val;
	}
	public void setVal(Character val) {
		this.val = val;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getColumn() {
		return column;
	}
	public void setColumn(Integer column) {
		this.column = column;
	}

}
