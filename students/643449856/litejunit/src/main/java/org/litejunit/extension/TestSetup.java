package org.litejunit.extension;

import org.litejunit.Test;
import org.litejunit.TestResult;

public class TestSetup extends TestDecorator {

	public TestSetup(Test t) {
		super(t);

	}

	@Override
	public void run(TestResult testResult) {
		setup();
		super.run(testResult);
		teardown();
	}

	private void teardown() {
	}

	private void setup() {

	}

}
