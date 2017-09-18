package com.coderising.myood.litejunit.liuxinv2.sample;


import com.coderising.myood.litejunit.liuxinv2.Test;
import com.coderising.myood.litejunit.liuxinv2.TestSuite;
import com.coderising.myood.litejunit.liuxinv2.extension.RepeatedTest;
import com.coderising.myood.litejunit.liuxinv2.extension.TestSetup;
import com.coderising.myood.litejunit.liuxinv2.sample.calculator.CalculatorSuite;

public class AllTest {
	/*public static Test suite(){
		
		TestSuite suite= new TestSuite("All Test");
		suite.addTest(CalculatorSuite.suite());		
		suite.addTestSuite(PersonTest.class);
		return suite;
		
	}*/
	
	public static Test suite(){
		
		TestSuite suite= new TestSuite("All Test");
		suite.addTest(new RepeatedTest(new TestSuite(PersonTest.class), 2));  // 装饰者模式
		suite.addTest(new RepeatedTest(CalculatorSuite.suite(), 2));
		return new OverallTestSetup(suite);  // 装饰者模式
	}
	
	
	static class OverallTestSetup extends TestSetup {

		public OverallTestSetup(Test test) {
			super(test);
			
		}
		protected void setUp() throws Exception {
			System.out.println("this is overall testsetup");
		}
		protected void tearDown() throws Exception {
			System.out.println("this is overall teardown");
		}
		
	}
}
