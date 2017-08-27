package com.coderising.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestCase extends Assert implements Test {
	
	private String name;
	
	public TestCase(String name){
		this.name = name;
	}

	@Override
	public void run(TestResult tr){
		tr.run(this);
	}
	
	public void doRun() throws Throwable{
		setUp();
		try{
			runTest();
		}finally{
			tearDown();
		}
	}

	protected void runTest() throws Throwable{
		Method runMethod = this.getClass().getMethod(name, null);
		try {
			runMethod.invoke(this, new Class[0]);
		} catch (InvocationTargetException e) {
			e.fillInStackTrace();
			throw e.getTargetException();
		}
	}
	
	protected void setUp(){
		
	}
	
	protected void tearDown(){
		
	}
	
	@Override
	public int countTestCases() {
		return 1;
	}
}
