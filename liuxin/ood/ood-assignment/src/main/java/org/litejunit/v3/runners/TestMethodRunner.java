package org.litejunit.v3.runners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.litejunit.v3.After;
import org.litejunit.v3.Before;
import org.litejunit.v3.notification.Failure;
import org.litejunit.v3.notification.RunNotifier;
import org.litejunit.v3.runner.Description;



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

	/*private void runWithTimeout(long timeout) {
		ExecutorService service= Executors.newSingleThreadExecutor();
		Callable<Object> callable= new Callable<Object>() {
			public Object call() throws Exception {
				runMethod();
				return null;
			}
		};
		Future<Object> result= service.submit(callable);
		service.shutdown();
		try {
			boolean terminated= service.awaitTermination(timeout,
					TimeUnit.MILLISECONDS);
			if (!terminated)
				service.shutdownNow();
			result.get(timeout, TimeUnit.MILLISECONDS); // throws the exception if one occurred during the invocation
		} catch (TimeoutException e) {
			addFailure(new Exception(String.format("test timed out after %d milliseconds", timeout)));
		} catch (Exception e) {
			addFailure(e);
		}		
	}*/
	
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
	
	/*private boolean expectsException() {
		return expectedException() != null;
	}

	private Class<? extends Throwable> expectedException() {
		return testIntrospector.expectedException(method);
	}*/

	/*private boolean isUnexpected(Throwable exception) {
		return ! expectedException().isAssignableFrom(exception.getClass());
	}*/
}

