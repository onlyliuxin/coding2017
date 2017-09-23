package com.coderising.litejunit.v.runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.coderising.litejunit.v.AssertionFailedError;
import com.coderising.litejunit.v.Test;
import com.coderising.litejunit.v.TestListener;

public abstract class BaseTestRunner implements TestListener {
	public static final String METHOD_NAME = "suite";

	public Test getTest(String testCase) {
		if (testCase.length() <= 0) {
			return null;
		}
		Class clazz = null;
		try {
			clazz = loadSuitClass(testCase);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Method method = clazz.getMethod(METHOD_NAME, new Class[0]);
			return	(Test) method.invoke(null, null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Class<?> loadSuitClass(String suitClassName) throws ClassNotFoundException {
		return Class.forName(suitClassName);
	}
}
