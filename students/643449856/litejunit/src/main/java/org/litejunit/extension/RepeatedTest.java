package org.litejunit.extension;

import org.litejunit.Test;
import org.litejunit.TestResult;

public class RepeatedTest extends TestDecorator {

	private int num;

	public RepeatedTest(Test t, int num) {
		super(t);
		if (num < 0) {
			throw new IllegalArgumentException("num must>0");
		}
		this.num = num;
	}

	@Override
	public void run(TestResult testResult) {
		for (int i = 0; i < num; i++) {
			super.run(testResult);
		}

	}

}
