/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTests {

	private static Calculator calculator;

	@BeforeAll
	 static void intializeCalcualtor() {
		calculator = new Calculator();
	}

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
	//	Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@EnabledOnOs(OS.WINDOWS)
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    1,   1",
			"1,    2,   3",
			"49,  51, 100",
			"1,  100, 101"
	})
	void add(int first, int second, int expectedResult) {
	//	Calculator calculator = new Calculator();
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

	@Test
	@DisplayName("Multiplicatio Basic - 2 * 2 = 4")
	void multiplyTwoNumbers() {
	//	Calculator calculator = new Calculator();
		Assertions.assertAll("Test All",() -> Assertions.assertNotNull(calculator)
		,()-> assertEquals(4,calculator.multiply(2,2)));
	}

	@Test
	@DisplayName("Checking Divide by zero")
	@EnabledIf("testDivideByZero")
	void testException () {
		Assumptions.assumeFalse("Raja".equals("Ram"));
		assertThrows(ArithmeticException.class,() -> calculator.divide(1,0));
	}

	boolean testDivideByZero() {
		return false;
	}
}
