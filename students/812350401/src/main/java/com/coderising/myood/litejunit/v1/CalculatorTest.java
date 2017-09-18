package com.coderising.myood.litejunit.v1;


public class CalculatorTest extends TestCase {
	public CalculatorTest(String name) {
		super(name);

	}
	private Calculator calculator =null;

	public void setUp(){
		System.out.println("init a calculator instance");
		calculator = new Calculator();
	}
	public void tearDown(){
		System.out.println("destroy a calculator instance");
		calculator = null;
	}
	public void testAdd(){

		calculator.add(10);
		assertEquals(10,calculator.getResult());
	}
	public void testSubtract(){
		calculator.add(10);
		calculator.subtract(5);
		assertEquals(4, calculator.getResult());
	}

	public void haha() {
        System.out.println("haha is not test case");
    }

	private void testXX() {
    }

}
