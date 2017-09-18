package org.v0_my.extension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.v0_my.Test;
import org.v0_my.TestResult;

public class TestDecorator implements Test {

	Test test;

	public TestDecorator(Test test) {
		// TODO Auto-generated constructor stub
		this.test = test;
	}

	
	
	@Override
	public void run(TestResult testResult) {
		test.run(testResult);
	}

	@Override
	public Integer getCaseCount() {
		// TODO Auto-generated method stub
		return test.getCaseCount();
	}

}
