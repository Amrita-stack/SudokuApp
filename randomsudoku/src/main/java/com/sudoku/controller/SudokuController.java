package com.sudoku.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku.bean.SudokuObj;
import com.sudoku.constants.SudokuConstants;
import com.sudoku.service.SudokuService;

@RestController
@RequestMapping("/sudoku/board")
@CrossOrigin(origins = "*")
public class SudokuController implements SudokuConstants{

	private static final Logger log= LoggerFactory.getLogger(SudokuController.class);

	@Autowired
	private SudokuService sudokuServiceImpl;	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<String>> createAndSolveBoard(@RequestBody SudokuObj obj){
		log.info("Inside the api createAndSolveBoard");
		if(obj == null || obj.getVal() == null || obj.getColumn() == null || obj.getRow() == null) {
			List<String> myList = new ArrayList<String>();
			myList.add("Invalid request");
			return ResponseEntity.badRequest().body(myList);
		}
		
		if(obj.getColumn() < 0  || obj.getColumn() > 8) {
			List<String> myList = new ArrayList<String>();
			myList.add("Invalid column index");
			return ResponseEntity.badRequest().body(myList);
		}
		
		if(obj.getRow() < 0  || obj.getRow() > 8) {
			List<String> myList = new ArrayList<String>();
			myList.add("Invalid row index");
			return ResponseEntity.badRequest().body(myList);
		}
		
		Integer value = Integer.valueOf(obj.getVal().toString());
		if(value < 0 || value >9) {
			List<String> myList = new ArrayList<String>();
			myList.add("Invalid value - Range is between 0 to 9");
			return ResponseEntity.badRequest().body(myList);
		}
		
		
		log.info("Called the api for board["+obj.getRow()+"]["+obj.getColumn()+"] with value "+ obj.getVal());
		char[][] board = sudokuServiceImpl.getRandomBoard(BOARD_LENGTH, obj.getVal(), obj.getRow(), obj.getColumn(), true);

		if(!sudokuServiceImpl.solveBoard(board, 0, 0)) {
			sudokuServiceImpl.solveBoard(board, 0, 0);
		}

		List<String> myList = new ArrayList<String>();
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j<board.length; j++) {
				myList.add(String.valueOf(board[i][j]));
			}
		}

		return ResponseEntity.ok(myList);

	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<String>> getSolvedSudokuBoard() {

		log.info("Inside the api getSudokuBoard");

		char[][] board = sudokuServiceImpl.getRandomBoard(BOARD_LENGTH, '1', -1, -1 , false);

		if(!sudokuServiceImpl.solveBoard(board, 0, 0)) {
			sudokuServiceImpl.solveBoard(board, 0, 0);
		}
		
		

		List<String> myList = new ArrayList<String>();
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j<board.length; j++) {
				myList.add(String.valueOf(board[i][j]));
			}
		}

		return ResponseEntity.ok(myList);
	}

}
