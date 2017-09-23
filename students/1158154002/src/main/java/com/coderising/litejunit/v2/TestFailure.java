package com.coderising.litejunit.v2;

/**
 * A <code>TestFailure</code> collects a failed test together with
 * the caught exception.
 * @see TestResult
 */
public class TestFailure {
	protected Test failedTest;
	protected Throwable thrownException;

	/**
	 * Constructs a TestFailure with the given test and exception.
	 */
	public TestFailure(Test failedTest, Throwable thrownException) {
		this.failedTest= failedTest;
		this.thrownException= thrownException;
	}
	/**
	 * Gets the failed test.
	 */
	public Test failedTest() {
	    return failedTest;
	}
	/**
	 * Gets the thrown exception.
	 */
	public Throwable thrownException() {
	    return thrownException;
	}
	/**
	 * Returns a short description of the failure.
	 */
	public String toString() {
	    StringBuffer buffer= new StringBuffer();
	    buffer.append(failedTest+": "+thrownException.getMessage());
	    return buffer.toString();
	}
}