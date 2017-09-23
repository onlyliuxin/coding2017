package org.v3.runners;

import java.lang.reflect.Method;
import java.util.List;

import org.v3.Test;
import org.v3.notification.Failure;
import org.v3.notification.RunNotifier;
import org.v3.runner.Description;
import org.v3.runner.Runner;



public class TestClassMethodsRunner extends Runner  {
	private final List<Method> testMethods;
	private final Class<?> testClass;

	public TestClassMethodsRunner(Class<?> klass) {
		testClass= klass;
		testMethods= new TestIntrospector(testClass).getTestMethods(Test.class);
	}
	
	@Override
	public void run(RunNotifier notifier) {
		/*if (testMethods.isEmpty())
			testAborted(notifier, getDescription());*/
		for (Method method : testMethods)
			invokeTestMethod(method, notifier);
	}



	@Override
	public Description getDescription() {
		Description spec= Description.createSuiteDescription(getName());
		List<Method> testMethods= this.testMethods;
		for (Method method : testMethods)
				spec.addChild(methodDescription(method));
		return spec;
	}

	protected String getName() {
		return getTestClass().getName();
	}
	
	protected Object createTest() throws Exception {
		return getTestClass().getConstructor().newInstance();
	}

	protected void invokeTestMethod(Method method, RunNotifier notifier) {
		Object test;
		try {
			test= createTest();
		} catch (Exception e) {
			//testAborted(notifier, methodDescription(method));
			return;
		}
		createMethodRunner(test, method, notifier).run();
	}

	protected TestMethodRunner createMethodRunner(Object test, Method method, RunNotifier notifier) {
		return new TestMethodRunner(test, method, notifier, methodDescription(method));
	}

	protected String testName(Method method) {
		return method.getName();
	}

	protected Description methodDescription(Method method) {
		return Description.createTestDescription(getTestClass(), testName(method));
	}



	protected Class<?> getTestClass() {
		return testClass;
	}
}