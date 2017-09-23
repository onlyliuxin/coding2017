package org.litejunit.extension;

import org.litejunit.Test;
import org.litejunit.TestResult;

public class TestDecorator implements Test {

	Test test;

	public TestDecorator(Test test) {
		this.test = test;
	}

	
	
	@Override
	public void run(TestResult testResult) {
		test.run(testResult);
	}

	@Override
	public Integer getCaseCount() {
		return test.getCaseCount();
	}

}
