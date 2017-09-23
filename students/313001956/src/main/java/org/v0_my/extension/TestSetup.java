package org.v0_my.extension;

import org.v0_my.Test;
import org.v0_my.TestResult;

public class TestSetup extends TestDecorator {

	public TestSetup(Test t) {
		super(t);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(TestResult testResult) {
		// TODO Auto-generated method stub
		setup();
		super.run(testResult);
		teardown();
	}

	private void teardown() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("this is alltest  teardown.");
	}

	private void setup() {
		// TODO Auto-generated method stub

		System.out.println("this is alltest setup");
	}

}
