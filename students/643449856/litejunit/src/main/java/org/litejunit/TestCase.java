package org.litejunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class TestCase extends Assert implements Test {
	private String name;

	public TestCase(String name) {
		this.name = name;
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
			// e1.printStackTrace();
		}
		
		if(!Modifier.isPublic(method.getModifiers())){
			  fail("method "+name+" is not a public menthod!");
		}
		
		try {
			method.invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// e.printStackTrace();

			Throwable ta = e.getCause();
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
