package com.coderising.myood.litejunit.liuxinv2.sample.calculator;


import com.coderising.myood.litejunit.liuxinv2.Test;
import com.coderising.myood.litejunit.liuxinv2.TestSuite;

public class CalculatorSuite {
	public static Test suite(){
		TestSuite suite= new TestSuite("Calculator All Test");
		suite.addTestSuite(CalculatorTest.class);		
		return suite;
	}
}
