package com.coderising.junit;

public class AllTest {
	
//	public static Test suite(){
//		TestSuite suite = new TestSuite("All Test");
//		suite.addTestSuite(CalculatorTest.class);
//		suite.addTestSuite(CalculatorTest.class);
//		return suite;
//	}
	
	public static Test suite(){
		TestSuite suite = new TestSuite("All Test");
		suite.addTest(CalculatorSuite.suite());
//		suite.addTestSuite(CalculatorTest.class);
		suite.addTest(new RepeatedTest(new TestSuite(CalculatorTest.class),3));
		return new OverallTestSetup(suite);
	}
	
	static class OverallTestSetup extends TestSetUp{

		public OverallTestSetup(Test test) {
			super(test);
		}
		
		@Override
		public void setUp(){
			System.out.println("this is overall testsetup");
		}
		
		@Override
		public void tearDown(){
			System.out.println("this is overall testteardown");
		}
	}
}
