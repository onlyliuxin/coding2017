package org.litejunit.sample;

import org.litejunit.extension.RepeatedTest;
import org.litejunit.extension.TestSetup;
import org.litejunit.sample.calculator.CalculatorSuite;
import org.litejunit.v2.Test;
import org.litejunit.v2.TestSuite;


public class AllTest {
	/*public static Test suite(){
		
		TestSuite suite= new TestSuite("All Test");
		suite.addTest(CalculatorSuite.suite());		
		suite.addTestSuite(PersonTest.class);
		return suite;
		
	}*/
	
	public static Test suite(){
		
		TestSuite suite= new TestSuite("All Test");
		suite.addTest(CalculatorSuite.suite());		
		suite.addTest(new RepeatedTest(new TestSuite(PersonTest.class), 2));
		return new OverallTestSetup(suite);
	}
	
	
	static class OverallTestSetup extends TestSetup{

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
