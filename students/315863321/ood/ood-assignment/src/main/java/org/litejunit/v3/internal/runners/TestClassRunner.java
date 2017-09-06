package org.litejunit.v3.internal.runners;

import org.litejunit.v3.AfterClass;
import org.litejunit.v3.BeforeClass;
import org.litejunit.v3.runner.Description;
import org.litejunit.v3.runner.Runner;
import org.litejunit.v3.runner.manipulation.*;
import org.litejunit.v3.runner.notification.Failure;
import org.litejunit.v3.runner.notification.RunNotifier;

public class TestClassRunner extends Runner implements Filterable, Sortable {
	protected final Runner fEnclosedRunner;
	private final Class<?> fTestClass;

	public TestClassRunner(Class<?> klass) throws InitializationError {
		this(klass, new TestClassMethodsRunner(klass));
	}

	public TestClassRunner(Class<?> klass, Runner runner) throws InitializationError {
		fTestClass= klass;
		fEnclosedRunner= runner;
		MethodValidator methodValidator= new MethodValidator(klass);
		validate(methodValidator);
		methodValidator.assertValid();
	}

	// TODO: this is parallel to passed-in runner
	protected void validate(MethodValidator methodValidator) {
		methodValidator.validateAllMethods();
	}

	@Override
	public void run(final RunNotifier notifier) {
		BeforeAndAfterRunner runner = new BeforeAndAfterRunner(getTestClass(),
				BeforeClass.class, AfterClass.class, null) {
			@Override
			protected void runUnprotected() {
				fEnclosedRunner.run(notifier);
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
		return fEnclosedRunner.getDescription();
	}
	
	// TODO: good behavior when createTest fails
	
	// TODO: dup?
	public void filter(Filter filter) throws NoTestsRemainException {
		filter.apply(fEnclosedRunner);
	}

	public void sort(Sorter sorter) {
		sorter.apply(fEnclosedRunner);
	}

	protected Class<?> getTestClass() {
		return fTestClass;
	}
}
