package org.litejunit;

public interface Test {
	public void run(TestResult testResult);

	public Integer getCaseCount();
}
