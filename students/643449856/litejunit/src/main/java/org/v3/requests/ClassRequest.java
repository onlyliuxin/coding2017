/**
 * 
 */
package org.v3.requests;

import java.lang.reflect.Constructor;

import org.v3.runner.Request;
import org.v3.runner.Runner;
import org.v3.runners.TestClassRunner;


public class ClassRequest extends Request {
	private final Class<?> fTestClass;

	public ClassRequest(Class<?> each) {
		fTestClass= each;
	}
	
	@Override
	public Runner getRunner() {
		Class runnerClass= getRunnerClass(fTestClass);
		try {
			Constructor constructor= runnerClass.getConstructor(Class.class); // TODO good error message if no such constructor
			Runner runner= (Runner) constructor
					.newInstance(new Object[] { fTestClass });
			return runner;
		} catch (Exception e) {
			return null;
			//return Request.errorReport(fTestClass, e).getRunner();
		}
	}

	Class getRunnerClass(Class<?> testClass) {
		/*RunWith annotation= testClass.getAnnotation(RunWith.class);
		if (annotation != null) {
			return annotation.value();
		} else if (isPre4Test(testClass)) {
			return OldTestClassRunner.class; 
		} else {*/
			return TestClassRunner.class;
		/*}*/
	}
	
	/*boolean isPre4Test(Class<?> testClass) {
		return junit.framework.TestCase.class.isAssignableFrom(testClass);
	}*/
}