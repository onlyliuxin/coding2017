package org.v3.runners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import org.v3.After;
import org.v3.Before;
import org.v3.notification.Failure;
import org.v3.notification.RunNotifier;
import org.v3.runner.Description;



public class TestMethodRunner extends BeforeAndAfterRunner {
	private final Object test;
	private final Method method;
	private final RunNotifier notifier;
	private final TestIntrospector testIntrospector;
	private final Description description;

	public TestMethodRunner(Object test, Method method, RunNotifier notifier, Description description) {
		super(test.getClass(), Before.class, After.class, test);
		this.test= test;
		this.method= method;
		this.notifier= notifier;
		testIntrospector= new TestIntrospector(test.getClass());
		this.description= description;
	}

	public void run() {
		/*if (testIntrospector.isIgnored(method)) {
			notifier.fireTestIgnored(description);
			return;
		}*/
		notifier.fireTestStarted(description);
		try {
			/*long timeout= testIntrospector.getTimeout(method);
			if (timeout > 0)
				runWithTimeout(timeout);
			else*/
				runMethod();
		} finally {
			notifier.fireTestFinished(description);
		}
	}


	
	private void runMethod() {
		runProtected();
	}
	
	@Override
	protected void runUnprotected() {
		try {
			executeMethodBody();
			/*if (expectsException())
				addFailure(new AssertionError("Expected exception: " + expectedException().getName()));*/
		} catch (InvocationTargetException e) {
			addFailure(e);
			/*Throwable actual= e.getTargetException();
			if (!expectsException())
				addFailure(actual);
			else if (isUnexpected(actual)) {
				String message= "Unexpected exception, expected<" + expectedException().getName() + "> but was<"
					+ actual.getClass().getName() + ">";
				addFailure(new Exception(message, actual));
			}*/
		} catch (Throwable e) {
			addFailure(e);
		}
	}

	protected void executeMethodBody() throws IllegalAccessException, InvocationTargetException {
		method.invoke(test);
	}

	@Override
	protected void addFailure(Throwable e) {
		notifier.fireTestFailure(new Failure(description, e));
	}
	

}

