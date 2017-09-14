package org.litejunit.v2;

public interface Test {
	public abstract int countTestCases();
	public void run(TestResult tr);
}
