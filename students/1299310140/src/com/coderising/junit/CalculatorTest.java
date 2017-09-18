package com.coderising.junit;

public class CalculatorTest extends TestCase {

	public CalculatorTest(String name) {
		super(name);
	}
	
	Calculator calculator = null;
	
	public void setUp(){
		calculator = new Calculator();
	}
	
	public void tearDown(){
		calculator = null;
	}
	
	public void testAdd(){
		calculator.add(10);
		Assert.assertEquals(10, calculator.getResult());
	}
	
	public void testSubtract(){
		calculator.add(10);
		calculator.subtract(5);
		Assert.assertEquals(10, calculator.getResult());
	}
}
