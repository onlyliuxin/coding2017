package com.coderising.litejunit.v;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestSuite extends Assert implements Test {
	List<Test> tests = new ArrayList<>();

	public <T> TestSuite(final Class<T> clazz) {
		Constructor<T> cons = null;
		cons = getConstructor(clazz, cons);

		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (isPublicTestMethod(method)) {
				tests.add(addTestMethod(cons, method.getName()));
			}
		}
	}

	private <T> Constructor<T> getConstructor(final Class<T> clazz, Constructor<T> cons) {
		try {
			cons = clazz.getConstructor(String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return cons;
	}

	private <T> Test addTestMethod(Constructor<T> cons, String name) {
		try {
			return (Test) cons.newInstance(name);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean isPublicTestMethod(Method method) {
		return isPublic(method) && isTest(method);
	}

	private boolean isTest(Method method) {
		return method.getName().startsWith("test") && method.getParameterCount() == 0
				&& method.getReturnType().equals(Void.TYPE);
	}

	private boolean isPublic(Method method) {
		return Modifier.isPublic(method.getModifiers());
	}

	@Override
	public int countTestCases() {
		int count=0;
		for (Test test : tests) {
			count+=test.countTestCases();
		}
		
		return count;
	}

	@Override
	public void run(TestResult tr) {
		for (Test test : tests) {
			test.run(tr);
		}

	}

}
