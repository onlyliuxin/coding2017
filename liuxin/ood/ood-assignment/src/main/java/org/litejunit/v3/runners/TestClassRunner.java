package org.litejunit.v3.runners;

import org.litejunit.v3.AfterClass;
import org.litejunit.v3.BeforeClass;
import org.litejunit.v3.notification.Failure;
import org.litejunit.v3.notification.RunNotifier;
import org.litejunit.v3.runner.Description;
import org.litejunit.v3.runner.Runner;

public class TestClassRunner extends Runner  {
	protected final Runner enclosedRunner;
	private final Class<?> testClass;

	public TestClassRunner(Class<?> klass) throws InitializationError {
		this(klass, new TestClassMethodsRunner(klass));
	}
	
	public TestClassRunner(Class<?> klass, Runner runner) throws InitializationError {
		testClass= klass;
		enclosedRunner= runner;
		
	}

	

	@Override
	public void run(final RunNotifier notifier) {
		BeforeAndAfterRunner runner = new BeforeAndAfterRunner(getTestClass(),
				BeforeClass.class, AfterClass.class, null) {		
			@Override
			protected void runUnprotected() {
				enclosedRunner.run(notifier);
			}
		
			// TODO: looks very similar to other method of BeforeAfter, now
			@Override
			protected void addFailure(Throwable targetException) {
				notifier.fireTestFailure(new Failure(getDescription(), targetException));
			}
		};

		runner.runProtected();
	}

	@Override
	public Description getDescription() {
		return enclosedRunner.getDescription();
	}

	protected Class<?> getTestClass() {
		return testClass;
	}
}
