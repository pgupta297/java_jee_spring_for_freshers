package com.training.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static java.time.Duration.ofMillis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.training.Calculator;

class TestCaclulator {

	private Calculator calc = new Calculator();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		System.out.println("BEFORE ALL CALLED ONLY ONCE BEFORE ALL TESTS");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		System.out.println("AFTER ALL CALLED ONLY ONCE AFTER ALL TESTS");

	}

	@BeforeEach
	void setUp(TestInfo info) throws Exception {
		
		System.out.println(info.getDisplayName() + "Called");
	}

	@AfterEach
	void tearDown(TestInfo info) throws Exception {
		
		System.out.println(info.getDisplayName() + "Finished");

	}

	@Test
	@DisplayName("Check the Return Value of The Method")
	void testCalculateReturnValue() {
		
		double actual = calc.calculate("45","55","65");
		
		double expected = 550;
		
		assertEquals(expected, actual);
		
	}

	@Test
	@DisplayName("Check For Number Format  Exception ")
	void testCalculateForException() {
		
		
		assertAll(
				() ->assertThrows(ArrayIndexOutOfBoundsException.class,
						()-> calc.calculate("45")),
				()-> assertThrows(NumberFormatException.class,
						 ()-> calc.calculate("Five","Four"))
				
						);
				
		
	}
	
	@Test
	@DisplayName("Test Method completes in 1000ms")
	void testCalculateForTimeOut() {
		
		
		assertTimeout(ofMillis(1000), () -> calc.calculate("45","55"));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1,3,5})
	@DisplayName("Test Elements in Odd postions are Not NUll")
	void testGetNameFromList(int idxPos) {
		
		LocalDate date = LocalDate.now();
		
		Assumptions.assumeTrue(date.getDayOfWeek().getValue()==3);
		  assertNotNull(calc.getNameFromList(idxPos));
	}
	
}
