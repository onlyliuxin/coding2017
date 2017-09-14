package com.coderising.litejunit;

public interface Test {
	public abstract int countTestCases();
	public void run(TestResult tr);
}
