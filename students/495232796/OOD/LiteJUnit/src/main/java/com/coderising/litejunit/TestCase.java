package com.coderising.litejunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestCase extends Assert implements Test{
	private String name;
	
	public TestCase(String name) {
		this.name = name;
	}

	@Override
	public int countTestCases() {
		return 1;
	}

	@Override
	public void run(TestResult tr) {
		tr.run(this);
	}
	
	public void doRun() throws Throwable {
		setUp();
		
		try {
			runTest();
		} finally {
			tearDown();
		}
		
	}
	
	protected void runTest() throws Throwable {
		Method method = null;
		
		try {
			method = getClass().getMethod(name, null);
		} catch (NoSuchMethodException e) {
			fail("method " + name + "is not found.");
		}
		
		if (!Modifier.isPublic(method.getModifiers())) {
			fail("method " + name + " is not public.");
		}
		
		try {
			method.invoke(this, new Class[0]);
		} catch (IllegalArgumentException e) {
			e.fillInStackTrace();
			throw e;
		} catch (InvocationTargetException e) {
			e.fillInStackTrace();
			throw e.getTargetException();
		} catch (IllegalAccessException e) {
			e.fillInStackTrace();
			throw e;
		}

	}
	
	protected void setUp() {
		
	}
	
	protected void tearDown() {
		
	}

}
