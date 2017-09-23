package com.coderising.litejunit.v;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestCase extends Assert implements Test {
	private String name;
	public TestCase(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void run(TestResult tr) {
		tr.run(this);
	}

	public void doRun() {
		setUp();
		try {
			runTest();
		} finally {
			tearDown();
		}
	}
	
	protected void setUp(){
		
	}
	
	private void runTest(){
		try {
			Method method=this.getClass().getMethod(this.getName(),new Class[0]);
			method.invoke(this, null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public int countTestCases() {
		return 1;
	}

	protected void tearDown(){
		
	}

}