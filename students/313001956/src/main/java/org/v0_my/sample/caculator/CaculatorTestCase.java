package org.v0_my.sample.caculator;

import org.junit.experimental.theories.Theories;
import org.v0_my.TestCase;

public class CaculatorTestCase extends TestCase {

	Calculator calculator = null;

	public CaculatorTestCase(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void setUp() {
		// TODO Auto-generated method stub
		calculator = new Calculator();
	}

	public void tearDown() {
		// TODO Auto-generated method stub
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

	public static void main(String[] args) {
		// TestCase testCase = new CaculatorTestCase("testAdd");
		// testCase.run();
//		Test tt = new TestTuite(CaculatorTestCase.class);
//		TestResult testResult=new TestResult();
//		tt.run(testResult);
//		System.out.println(testResult.getFailtestCount());
//		System.out.println(testResult.getErrtestCount());
	}

}
