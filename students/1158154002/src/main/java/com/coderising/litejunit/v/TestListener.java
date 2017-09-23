package com.coderising.litejunit.v;

public interface TestListener {
	
	void addError(Test test,Throwable t);
	
	void addFailure(Test test,AssertionFailedError t);
	
	void endTest(Test test);
	
	void startTest(Test test);
}
