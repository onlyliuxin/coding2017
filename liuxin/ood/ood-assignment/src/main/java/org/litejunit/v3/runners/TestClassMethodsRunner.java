package org.litejunit.v3.runners;

import java.lang.reflect.Method;
import java.util.List;

import org.litejunit.v3.Test;
import org.litejunit.v3.notification.Failure;
import org.litejunit.v3.notification.RunNotifier;
import org.litejunit.v3.runner.Description;
import org.litejunit.v3.runner.Runner;



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

	/*private void testAborted(RunNotifier notifier, Description description) {
		// TODO: duped!
		// TODO: envious
		notifier.fireTestStarted(description);
		notifier.fireTestFailure(new Failure(description, new Exception("No runnable methods")));
		notifier.fireTestFinished(description);
	}*/

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

	/*public void filter(Filter filter) throws NoTestsRemainException {
		for (Iterator iter= fTestMethods.iterator(); iter.hasNext();) {
			Method method= (Method) iter.next();
			if (!filter.shouldRun(methodDescription(method)))
				iter.remove();
		}
		if (fTestMethods.isEmpty())
			throw new NoTestsRemainException();
	}

	public void sort(final Sorter sorter) {
		Collections.sort(fTestMethods, new Comparator<Method>() {
			public int compare(Method o1, Method o2) {
				return sorter.compare(methodDescription(o1), methodDescription(o2));
			}
		});
	}*/

	protected Class<?> getTestClass() {
		return testClass;
	}
}