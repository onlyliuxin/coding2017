package org.litejunit.v1;

public interface Test {
	public abstract int countTestCases();
	public void run(TestResult tr);
}
