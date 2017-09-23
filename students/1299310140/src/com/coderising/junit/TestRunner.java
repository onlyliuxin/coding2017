package com.coderising.junit;

import java.io.PrintStream;

public class TestRunner extends BaseTestRunner {
	
	PrintStream write = System.out;
	int column = 0;

	@Override
	public void addError(Test test, Throwable t) {
		write.print("E");
	}

	@Override
	public void addFailure(Test test, AssertionFailedError t) {
		write.print("F");
	}

	@Override
	public void startTest(Test test) {
		write.print(".");
		if(column++ >= 40){
			write.println();
			column = 0;
		}
	}

	@Override
	public void endTest(Test test) {
		
	}

	public static void main(String[] args) {
		TestRunner testRunner = new TestRunner();
		try {
			TestResult testResult = testRunner.start(args);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private TestResult start(String[] args) throws Exception {
		if(args.length == 0){
			throw new Exception("Usage : TestRunner TestCaseName");
		}
		
		String testCase = args[0];
		Test suite = getTest(testCase);
		
		return doRun(suite);
	}

	private TestResult doRun(Test suite) {
		TestResult testResult = new TestResult();
		testResult.addListener(this);
		suite.run(testResult);
		return testResult;
	}
	
}
