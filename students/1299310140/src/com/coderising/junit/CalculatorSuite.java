package com.coderising.junit;

public class CalculatorSuite {
	
	public static Test suite(){
		TestSuite suite = new TestSuite("Calculator All Test");
		suite.addTestSuite(CalculatorTest.class);
		return suite;
	}
}
