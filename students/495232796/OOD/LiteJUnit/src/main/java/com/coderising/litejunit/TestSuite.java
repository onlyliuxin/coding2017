package com.coderising.litejunit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestSuite implements Test{
	private List<Test> tests= new ArrayList<>();
	private String name;
	public TestSuite(){
		
	}
	
	public TestSuite(final Class<?> tClass) {
		this.name = tClass.getName();
		Constructor<?> constructor = null;
		try {
			constructor = getConstructor(tClass);
		} catch (NoSuchMethodException e) {
			addTest(warning("Class " + name + " has no public constructor TestCase(String name)."));
			return ;
		}
		
		if (!Modifier.isPublic(tClass.getModifiers())) {
			addTest(warning("Class " + name + " is not public."));
			return ;
		}
		
		List<String> names = new ArrayList<>();
		Method[] methods = tClass.getDeclaredMethods();
		for (Method m : methods) {
			addTestMethod(m, names, constructor);
		}
		
		if (tests.size() == 0) {
			addTest(warning("No testcase in Class " + name));
		}
	}
	
	private void addTestMethod(Method m, List<String> names, Constructor<?> constructor) {
		String name = m.getName();
		if (names.contains(name)) {
			return ;
		}
		
		if (isPublicMethod(m)) {
			names.add(name);
			Object[] args = new Object[]{name};
			
			try {
				addTest((Test)constructor.newInstance(args));
			} catch (InstantiationException e) {
				addTest(warning("Method " + name + " InstantiationException:" + exceptionToString(e)));
			} catch (IllegalAccessException e) {
				addTest(warning("Method " + name + " IllegalAccessException:" + exceptionToString(e)));
			} catch (IllegalArgumentException e) {
				addTest(warning("Method " + name + " IllegalArgumentException:" + exceptionToString(e)));
			} catch (InvocationTargetException e) {
				addTest(warning("Method " + name + " InvocationTargetException:" + exceptionToString(e)));
			}
		} else {
			if (isTestMethod(m)) {
				addTest(warning("Test method " + name + " is not public."));
			}
		}
	}
	
	private String exceptionToString(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		return stringWriter.toString();
		
	}
	
	private boolean isPublicMethod(Method m) {
		return isTestMethod(m) && Modifier.isPublic(m.getModifiers());
	}
	private boolean isTestMethod(Method m) {
		String name = m.getName();
		Class<?>[] parameters = m.getParameterTypes();
		Class<?> retType = m.getReturnType();
		
		return parameters.length == 0 && name.startsWith("test") && retType.equals(Void.TYPE);
	}
	
	private Test warning(final String message) {
		return new TestCase("warning") {
			public void doRun() {
				fail(message);
			}
		};		
	}
	
	public void addTest(Test t) {
		this.tests.add(t);
	}
	
	private Constructor<?> getConstructor(Class<?> theClass) throws NoSuchMethodException {
		Class<?>[] args= { String.class };
		return theClass.getConstructor(args);
	}

	@Override
	public int countTestCases() {
		int cnt = 0;
		for (Test t : tests) {
			cnt += t.countTestCases();
		}
		return cnt;
	}

	@Override
	public void run(TestResult tr) {
		for (Test t : tests) {
			if (tr.shouldStop()) {
				break;
			}
			t.run(tr);;
		}
	}

}
