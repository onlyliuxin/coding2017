package com.coderising.myood.litejunit.liuxinv2;

/**
 * A TestListener for test progress
 */
public interface TestListener {
	
	void addError(Test test, Throwable t);
	
 	void addFailure(Test test, AssertionFailedError t);
	
 	void endTest(Test test);
	
	void startTest(Test test);
}