package com.jyz.myjunit.junit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 用来保存测试结果
 */
public class TestResult {
	protected List<TestFailure> failures;
	protected List<TestFailure> errors;
	
	protected int testCount;
	private boolean stop;
	
	public TestResult() {
		failures= new ArrayList<>();
		errors= new ArrayList<>();	
		
		testCount= 0;
		stop= false;
	}
	
	public  void addError(Test test, Throwable t) {
		errors.add(new TestFailure(test, t));		
	}
	
	public  void addFailure(Test test, AssertionFailedError t) {
		failures.add(new TestFailure(test, t));		
	}
	
	public void startTest(Test test) {
		int count= test.countTestCases();
		testCount+= count;		
	}
	public void endTest(Test test) {		
	}
	
	/**
	 * Runs a TestCase.
	 */
	protected void run(final TestCase test) {
		startTest(test);		
		try {
			test.doRun();
		} 
		catch (AssertionFailedError e) {
			addFailure(test, e);
		}
		catch (Throwable e) {
			addError(test, e);
		}

		endTest(test);
	}
	/**
	 * Gets the number of run tests.
	 */
	public  int runCount() {
		return testCount;
	}
	
	
	public  boolean shouldStop() {
		return stop;
	}
	
	public  void stop() {
		stop= true;
	}
	
	public  int errorCount() {
		return errors.size();
	}
	
	public  Iterator errors() {
		return errors.iterator();
	}
	
	public  int failureCount() {
		return failures.size();
	}
	
	public  Iterator<TestFailure> failures() {
		return failures.iterator();
	}
	/**
	 * Returns whether the entire test was successful or not.
	 */
	public  boolean wasSuccessful() {
		return this.failureCount() == 0 && this.errorCount() == 0;
	}

	@Override
	public String toString() {
		return "TestResult{" +
				"\n failures=" + failures +
				",\n errors=" + errors +
				",\n testCount=" + testCount +
				",\n success ="+wasSuccessful()+"\n}";
	}
}