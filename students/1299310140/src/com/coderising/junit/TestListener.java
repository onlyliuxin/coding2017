package com.coderising.junit;

public interface TestListener {
	
	public void addError(Test test, Throwable t);
	
	public void addFailure(Test test, AssertionFailedError t);
	
	public void startTest(Test test);
	
	public void endTest(Test test);
}
