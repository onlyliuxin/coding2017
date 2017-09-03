package com.coderising.litejunit;

public class CalculatorTest extends TestCase{
	private Calculator cal = null;
	
	public CalculatorTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void setUp() {
		cal = new Calculator();
	}
	
	public void tearDown() {
		cal = null;
	}
	
	public void testAdd() {
		cal.add(10);
		Assert.assertEquals(cal.getResult(), 10);
	}
	
	public void testSubstract() {
		cal.add(10);
		cal.substract(5);
		assertEquals(cal.getResult(), 50);	
	}
	
	public static void main(String[] args){
		TestSuite ts = new TestSuite(CalculatorTest.class);
		TestResult tr = new TestResult();
		ts.run(tr);
		System.out.println(tr.wasSuccessful());
		for(TestFailure failure : tr.failures){
			System.err.println(failure);
		}
	}
}
