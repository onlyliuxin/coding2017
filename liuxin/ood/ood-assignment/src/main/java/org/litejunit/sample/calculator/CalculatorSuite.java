package org.litejunit.sample.calculator;

import org.litejunit.v2.Test;
import org.litejunit.v2.TestSuite;

public class CalculatorSuite {
	public static Test suite(){
		TestSuite suite= new TestSuite("Calculator All Test");
		suite.addTestSuite(CalculatorTest.class);		
		return suite;
	}
}
