package com.coderising.litejunit.sample.calculator;

import com.coderising.litejunit.v2.Test;
import com.coderising.litejunit.v2.TestSuite;

public class CalculatorSuite {
	public static Test suite(){
		TestSuite suite= new TestSuite("Calculator All Test");
		suite.addTestSuite(CalculatorTest.class);		
		return suite;
	}
}