package com.coderising.junit;

public class TestFailure {
	
	private Test failedTest;
	private Throwable thrownException;
	
	public TestFailure(Test failedTest, Throwable thrownException) {
		super();
		this.failedTest = failedTest;
		this.thrownException = thrownException;
	}
	
	public Test failedTest(){
		return this.failedTest;
	}
	
	public Throwable thrownException(){
		return this.thrownException;
	}
}
