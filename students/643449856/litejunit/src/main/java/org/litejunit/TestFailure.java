package org.litejunit;

public class TestFailure {

	private Test test;
	private Throwable throwable;

	public TestFailure(Test test, Throwable throwable) {
		this.test = test;
		this.throwable = throwable;
	}

	public Test getTestCase() {
		return this.test;
	}

	public Throwable getThrowable() {
		return this.throwable;
	}
}
