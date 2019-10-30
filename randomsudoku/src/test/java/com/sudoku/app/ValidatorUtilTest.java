package com.sudoku.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sudoku.util.ValidatorUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidatorUtilTest {

	@Autowired
	private ValidatorUtil validatorUtil;

	@BeforeEach
	public void setUp() {

	}

	@Test
	void testReadHorizontalGrid() {
		char[][] board= {
				{'1','2'},
				{'2','1'}
		};

		char[] returnedVal = validatorUtil.readHorizontalGrid(board, 0);
		assertEquals(2,returnedVal.length);
		assertEquals('1', returnedVal[0]);
		assertEquals('2', returnedVal[1]);

	}

	@Test
	void testReadHorizontalGridFor9X9() {
		char[][] board= {
				{'1','2','3','4','5','6','7','8','9'},
				{'4','5','6','7','8','9','1','2','3'},
				{'7','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};

		char[] returnedVal = validatorUtil.readHorizontalGrid(board, 1);
		assertEquals(9,returnedVal.length);
		assertEquals('4', returnedVal[0]);
		assertEquals('5', returnedVal[1]);
		assertEquals('6', returnedVal[2]);
		assertEquals('7', returnedVal[3]);
		assertEquals('8', returnedVal[4]);
		assertEquals('9', returnedVal[5]);
		assertEquals('1', returnedVal[6]);
		assertEquals('2', returnedVal[7]);
		assertEquals('3', returnedVal[8]);

	}

	@Test
	void testCheckGrid() {
		char[]board= {'1','2','3','4','5','6','7','8','9'};
		assertTrue(validatorUtil.checkGrid(board));
	}
	
	@Test
	void testCheckGridForDots() {
		char[]board= {'1','.','.','4','5','6','7','8','9'};
		assertTrue(validatorUtil.checkGrid(board));
	}
	
	@Test
	void testCheckGridForBiggerBoardValue() {
		char[]board= {'1','.','.','4','5','6','7','8','v'};
		assertFalse(validatorUtil.checkGrid(board));
	}
	
	@Test
	void testReadMxMGrid() {
		char[][] board= {
				{'1','2'},
				{'2','1'}
		};

		char[] returnedVal = validatorUtil.readMxMGrid(board, 0);
		assertEquals(2, returnedVal.length);
	}
	
	@Test
	void testReadMxMGridFor3X3() {
		char[][] board= {
				{'1','2','3','4','5','6','7','8','9'},
				{'4','5','6','7','8','9','1','2','3'},
				{'7','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};

		char[] returnedVal = validatorUtil.readMxMGrid(board, 1);
		assertEquals(9, returnedVal.length);
		assertEquals('4', returnedVal[0]);
		assertEquals('5', returnedVal[1]);
		assertEquals('6', returnedVal[2]);
		assertEquals('7', returnedVal[3]);
		assertEquals('8', returnedVal[4]);
		assertEquals('9', returnedVal[5]);
		assertEquals('1', returnedVal[6]);
		assertEquals('2', returnedVal[7]);
		assertEquals('3', returnedVal[8]);
		
	}
	
	@Test
	void testReadMxMGridFor3X3WithDots() {
		char[][] board= {
				{'1','.','3','4','5','6','7','8','9'},
				{'.','.','6','7','8','9','1','2','3'},
				{'7','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};

		char[] returnedVal = validatorUtil.readMxMGrid(board, 0);
		assertEquals(9, returnedVal.length);
		assertEquals('1', returnedVal[0]);
		assertEquals('.', returnedVal[1]);
		assertEquals('3', returnedVal[2]);
		assertEquals('.', returnedVal[3]);
		assertEquals('.', returnedVal[4]);
		assertEquals('6', returnedVal[5]);
		assertEquals('7', returnedVal[6]);
		assertEquals('8', returnedVal[7]);
		assertEquals('9', returnedVal[8]);
		
	}
	
	
	@Test
	void testReadVerticalGridFor3X3() {
		char[][] board= {
				{'1','2','3','4','5','6','7','8','9'},
				{'4','5','6','7','8','9','1','2','3'},
				{'7','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};

		char[] returnedVal = validatorUtil.readVerticalGrid(board, 1);
		assertEquals(9, returnedVal.length);
		assertEquals('2', returnedVal[0]);
		assertEquals('5', returnedVal[1]);
		assertEquals('8', returnedVal[2]);
		assertEquals('1', returnedVal[3]);
		assertEquals('6', returnedVal[4]);
		assertEquals('9', returnedVal[5]);
		assertEquals('3', returnedVal[6]);
		assertEquals('4', returnedVal[7]);
		assertEquals('7', returnedVal[8]);
		
	}
	
	@Test
	void testIsSudokuValid() {
		char[][] board= {
				{'1','2','3','4','5','6','7','8','9'},
				{'4','5','6','7','8','9','1','2','3'},
				{'7','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};
		
		assertTrue(validatorUtil.isSudokuValid(board));

	}
	
	@Test
	void testIsSudokuValidNegative() {
		char[][] board= {
				{'.','2','3','4','5','6','7','8','9'},
				{'a','5','6','7','8','9','1','2','3'},
				{'x','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};
		
		assertFalse(validatorUtil.isSudokuValid(board));

	}
	
	@Test
	void testIsSudokuSolved() {
		char[][] board= {
				{'1','2','3','4','5','6','7','8','9'},
				{'4','5','6','7','8','9','1','2','3'},
				{'7','8','9','1','2','3','4','5','6'},
				{'2','1','4','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};
		
		assertTrue(validatorUtil.isSolved(board));

	}
	
	@Test
	void testIsSudokuSolvedNegative() {
		char[][] board= {
				{'.','2','3','4','5','6','7','8','9'},
				{'a','5','6','7','8','9','1','2','3'},
				{'x','p','n','1','2','3','4','5','6'},
				{'2','1','.','3','6','5','8','9','7'},
				{'3','6','5','8','9','7','2','1','4'},
				{'8','9','7','2','1','4','3','6','5'},
				{'5','3','1','6','4','2','9','7','8'},
				{'6','4','2','9','7','8','5','3','1'},
				{'9','7','8','5','3','1','6','4','2'}
		};
		
		assertFalse(validatorUtil.isSolved(board));

	}
}
