package com.sudoku.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = SudokuApplication.class)
class SudokuApplicationTests {
	
	@Test
	void contextLoads() {
	}

}
