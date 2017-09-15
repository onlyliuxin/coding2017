package com.coderising.litejunit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestResult {
	private boolean stop;
	protected List<TestFailure> failures;
	protected List<TestFailure> errors;
	protected int testCount;
	
	public TestResult() {
		failures= new ArrayList<>();
		errors= new ArrayList<>();	
		
		testCount= 0;
		stop= false;
	}
	public void startTest(Test test) {
		int count= test.countTestCases();
		testCount+= count;		
	}
	
	public void endTest(Test test) {		
	}
	
	public void run(TestCase tc) {
		startTest(tc);
		
		try {
			tc.doRun();
		} catch (AssertionFailedError e) {
			addFailure(tc, e);
		} catch (Throwable e) {
			addError(tc, e);
		}
		
		endTest(tc);
	}
	
	public boolean shouldStop() {
		return this.stop;
	}
	
	public  void addError(Test test, Throwable t) {
		errors.add(new TestFailure(test, t));		
	}
	
	public  void addFailure(Test test, AssertionFailedError t) {
		failures.add(new TestFailure(test, t));		
	}
	
	public  Iterator<TestFailure> failures() {
		return failures.iterator();
	}

	public  boolean wasSuccessful() {
		return failures.size() == 0 && errors.size() == 0;
	}
}
