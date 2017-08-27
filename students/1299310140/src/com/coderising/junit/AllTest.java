package com.coderising.junit;

public class AllTest {
	
	public static Test suite(){
		TestSuite suite = new TestSuite("All Test");
		suite.addTestSuite(CalculatorTest.class);
		suite.addTestSuite(CalculatorTest.class);
		return suite;
	}
}
