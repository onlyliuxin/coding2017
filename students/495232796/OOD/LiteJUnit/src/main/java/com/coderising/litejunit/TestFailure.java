package com.coderising.litejunit;

public class TestFailure {
	protected Test failedTest;
	protected Throwable thrownException;
	
	public TestFailure(Test failedTest, Throwable thrownException) {
		this.failedTest= failedTest;
		this.thrownException= thrownException;
	}
	

	public Test failedTest() {
	    return failedTest;
	}

	public Throwable thrownException() {
	    return thrownException;
	}

	public String toString() {
	    StringBuffer buffer= new StringBuffer();
	    buffer.append(failedTest+": "+thrownException.getMessage());
	    return buffer.toString();
	}
}
