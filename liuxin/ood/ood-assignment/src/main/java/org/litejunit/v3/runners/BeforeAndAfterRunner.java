package org.litejunit.v3.runners;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BeforeAndAfterRunner {
	private static class FailedBefore extends Exception {
		private static final long serialVersionUID= 1L;
	}

	private final Class<? extends Annotation> beforeAnnotation;

	private final Class<? extends Annotation> afterAnnotation;

	private TestIntrospector testIntrospector;

	private Object test;

	public BeforeAndAfterRunner(Class<?> testClass,
			Class<? extends Annotation> beforeAnnotation,
			Class<? extends Annotation> afterAnnotation, 
			Object test) {
		this.beforeAnnotation= beforeAnnotation;
		this.afterAnnotation= afterAnnotation;
		this.testIntrospector= new TestIntrospector(testClass);
		this.test= test;
	}

	public void runProtected() {
		try {
			runBefores();
			runUnprotected();
		} catch (FailedBefore e) {
		} finally {
			runAfters();
		}
	}

	protected abstract void runUnprotected();

	protected abstract void addFailure(Throwable targetException);

	// Stop after first failed @Before
	private void runBefores() throws FailedBefore {
		try {
			List<Method> befores= testIntrospector.getTestMethods(beforeAnnotation);
			for (Method before : befores)
				invokeMethod(before);
		} catch (InvocationTargetException e) {
			addFailure(e.getTargetException());
			throw new FailedBefore();
		} catch (Throwable e) {
			addFailure(e);
			throw new FailedBefore();
		}
	}

	// Try to run all @Afters regardless
	private void runAfters() {
		List<Method> afters= testIntrospector.getTestMethods(afterAnnotation);
		for (Method after : afters)
			try {
				invokeMethod(after);
			} catch (InvocationTargetException e) {
				addFailure(e.getTargetException());
			} catch (Throwable e) {
				addFailure(e); // Untested, but seems impossible
			}
	}
	
	private void invokeMethod(Method method) throws Exception {
		method.invoke(test);
	}
}
