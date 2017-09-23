package org.v0_my;

public class TestFailure {

	private Test test;
	private Throwable throwable;

	public TestFailure(Test test, Throwable throwable) {
		// TODO Auto-generated constructor stub
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
