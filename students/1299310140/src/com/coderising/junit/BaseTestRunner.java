package com.coderising.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseTestRunner implements TestListener {
	
	public static final String SUITE_METHODNAME = "suite";
	
	public Test getTest(String suiteClassName) {
		Class<?> testClass = null;
		try {
			testClass = loadSuiteClass(suiteClassName);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		Method suiteMethod = null;
		try {
			suiteMethod = testClass.getMethod(SUITE_METHODNAME, new Class[0]);
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
		
		Test test = null;
		try {
			test = (Test) suiteMethod.invoke(null, new Class[0]);
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		return test;
	}

	private Class<?> loadSuiteClass(String suiteClassName) throws ClassNotFoundException {
		
		return Class.forName(suiteClassName);
	}
}
