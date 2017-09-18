package com.coderising.litejunit.sample.calculator;

import com.coderising.litejunit.v2.TestCase;

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
		assertEquals(10,calculator.getResult());
	}	
	public void testSubtract(){
		calculator.add(10);
		calculator.subtract(5);
		//throw new RuntimeException("this is a test");
		assertEquals(5,calculator.getResult());
	}
	
	public static void main(String[] args){
		/*{
		TestCase tc1 = new CalculatorTest("testAdd"){
			protected void runTest() {
				testAdd();
			}
		};
		
		TestCase tc2 = new CalculatorTest("testSubtract"){
			protected void runTest() {
				testSubtract();
			}
		};
		tc1.run();
		tc2.run();
		}
		
		
		TestSuite ts = new TestSuite();
		ts.addTest(new CalculatorTest("testAdd"));
		ts.addTest(new CalculatorTest("testSubtract"));
		
		
		{
			TestCase tc1 = new CalculatorTest("test1");
			TestCase tc2 = new CalculatorTest("test2");
			tc1.run();
			tc2.run();
		}*/
	}
}