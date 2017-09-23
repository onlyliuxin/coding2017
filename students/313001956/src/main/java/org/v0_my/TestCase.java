package org.v0_my;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public abstract class TestCase extends Assert implements Test {
	private String name;

	public TestCase(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(TestResult testResult) {
		testResult.run(this);
	}

	public void doRun(TestResult testResult) throws Throwable {
		setUp();
		try {
			runTest();
		
		} finally {
			tearDown();
		}
	}

	protected void setUp() {

	}

	protected void runTest() throws Throwable {
		Class<?> clazz = this.getClass();
		Method method=null;
		try {
			method = clazz.getDeclaredMethod(name);
			
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		
		if(!Modifier.isPublic(method.getModifiers())){
			  fail("method "+name+" is not a public menthod!");
		}
		
		try {
			method.invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			Throwable ta = e.getCause();
			// if (ta instanceof AssertionFailedError) {
			throw ta;
			// }

		}

	}

	protected void tearDown() {

	}

	@Override
	public Integer getCaseCount() {
		return 1;
	}

}
