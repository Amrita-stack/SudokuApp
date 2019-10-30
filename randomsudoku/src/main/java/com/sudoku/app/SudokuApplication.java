package com.sudoku.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sudoku.controller","com.sudoku.service", "com.sudoku.util"})
public class SudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuApplication.class, args);
	}

}
