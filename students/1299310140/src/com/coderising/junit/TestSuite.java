package com.coderising.junit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSuite implements Test {

	private List<Test> tests = new ArrayList<Test>();
	
	@Override
	public void run(TestResult tr) {
		for(Iterator<Test> iterator = tests.iterator();iterator.hasNext();){
			Test test = iterator.next();
			test.run(tr);
		}
	}

	public void addTest(Test test){
		tests.add(test);
	}
	
	public TestSuite(){
		
	}
	
    public TestSuite(final Class<?> theClass){
		Constructor<?> constructor = null;
		try {
			constructor = theClass.getConstructor(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> names = new ArrayList<String>();
		Method[] method = theClass.getDeclaredMethods();
		for(int i = 0;i < method.length;i++){
			addTestMethod(method[i],names,constructor);
		}
		
		if(tests.size() == 0){
			System.out.println("No tests found in" + theClass.getName());
		}
	}

	private void addTestMethod(Method method, List<String> names,
			Constructor<?> constructor) {
		String name = method.getName();
		if(names.contains(name)){
			return;
		}
		
		if(isPublicTestMethod(method)){
			names.add(name);
			
			try {
				addTest((Test)constructor.newInstance(name));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	private boolean isPublicTestMethod(Method method) {
		String name = method.getName();
		Class<?>[] parameters = method.getParameterTypes();
		Class<?> returnType = method.getReturnType();
		return Modifier.isPublic(method.getModifiers()) && name.startsWith("test") 
				&& parameters.length == 0 && returnType.equals(Void.TYPE);
	}
}
