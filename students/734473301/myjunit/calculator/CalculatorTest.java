package com.jyz.myjunit.calculator;


import com.jyz.myjunit.junit.TestCase;
import com.jyz.myjunit.junit.TestResult;
import com.jyz.myjunit.junit.TestSuite;

public class CalculatorTest extends TestCase {
	public CalculatorTest(String name) {
		super(name);

	}
	Calculator calculator =null;

	public void setUp(){
		calculator = new Calculator();
	}
	public void tearDown(){
		calculator = null;
	}

	public void testAdd(){
		
		calculator.add(10);
		assertEquals(1,calculator.getResult());
	}	
	public void testSubtract(){
		calculator.add(10);
		calculator.subtract(5);
		//throw new RuntimeException("this is a test");
		assertEquals(5,calculator.getResult());
	}

	public static void main(String[] args) throws ClassNotFoundException {

		TestSuite ts = new TestSuite(Class.forName("com.jyz.myjunit.calculator.CalculatorTest"));
		TestResult result = new TestResult();
		ts.run(result);
		System.out.println(result);
	}
}
