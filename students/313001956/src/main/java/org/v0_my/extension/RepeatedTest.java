package org.v0_my.extension;

import org.v0_my.Test;
import org.v0_my.TestResult;

public class RepeatedTest extends TestDecorator {

	private int num;

	public RepeatedTest(Test t, int num) {
		super(t);
		if (num < 0) {
			throw new IllegalArgumentException("num must be >0");
		}
		this.num = num;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(TestResult testResult) {
		// TODO Auto-generated method stub
		for (int i = 0; i < num; i++) {
			super.run(testResult);
		}

	}

}
