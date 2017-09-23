package org.litejunit.sample.caculator;

import org.litejunit.TestCase;

public class CaculatorTestCase extends TestCase {

	Calculator calculator = null;

	public CaculatorTestCase(String name) {
		super(name);
	}

	public void setUp() {
		calculator = new Calculator();
	}

	public void tearDown() {
		calculator = null;
	}

	public void testAdd() {

		calculator.add(10);
		int a=0;
		int b=1/a;
		assertEquals(10, calculator.getResult());
		System.out.println("testadd");
	}

	public void testSubtract() {
		calculator.add(10);
		calculator.subtract(5);
		assertEquals(4, calculator.getResult());
		System.out.println("testSubtract");
	}



}
