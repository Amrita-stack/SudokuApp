package com.sudoku.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sudoku.service.SudokuService;
import com.sudoku.util.ValidatorUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SudokuServiceImplTest {
	
	@MockBean
	private ValidatorUtil validatorUtilImpl;
	
	@Autowired
	private SudokuService sudokuServiceImpl;
	
	@BeforeEach
	public void setUp() {
		Mockito.reset(validatorUtilImpl);
	}
	
	@Test
	public void testGetRandomSudokuBoard () {
		Mockito.when(validatorUtilImpl.isSudokuValid(Mockito.any())).thenReturn(Boolean.TRUE);
		char[][] returnedBoard = sudokuServiceImpl.getRandomBoard(9, '1', -1, -1, false);
		assertEquals(9, returnedBoard.length);
		boolean isAllDots = true;
		for (char[] cs : returnedBoard) {
			for (char cs2 : cs) {
				if(cs2 == ' ') {
					isAllDots = false;
					break;
				}
			}
		}
		assertTrue(isAllDots);
		Mockito.verify(validatorUtilImpl).isSudokuValid(Mockito.any());
	}
	
	@Test
	public void testGetRandomSudokuBoard1 () {
		Mockito.when(validatorUtilImpl.isSudokuValid(Mockito.any())).thenReturn(Boolean.FALSE, Boolean.TRUE);
		char[][] returnedBoard = sudokuServiceImpl.getRandomBoard(9, '1', -1, -1, false);
		assertEquals(9, returnedBoard.length);
		boolean atLeastOneNumeric = false;
		for (char[] cs : returnedBoard) {
			for (char cs2 : cs) {
				if(cs2 == '1' || cs2 == '2' || cs2 == '3'|| cs2 == '4'|| cs2 == '5'|| cs2 == '6'|| cs2 == '7' || cs2 == '8' || cs2 == '9') {
					atLeastOneNumeric = true;
					break;
				}
			}
		}
		assertTrue(atLeastOneNumeric);
		Mockito.verify(validatorUtilImpl, Mockito.times(2)).isSudokuValid(Mockito.any());
	}

}
