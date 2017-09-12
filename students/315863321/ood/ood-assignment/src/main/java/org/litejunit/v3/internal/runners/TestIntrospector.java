package org.litejunit.v3.internal.runners;

import org.litejunit.v3.Before;
import org.litejunit.v3.BeforeClass;
import org.litejunit.v3.Ignore;
import org.litejunit.v3.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestIntrospector {
	private final Class< ?> fTestClass;
	
	public TestIntrospector(Class<?> testClass) {
		fTestClass= testClass;
	}

	public List<Method> getTestMethods(Class<? extends Annotation> annotationClass) {
		List<Method> results= new ArrayList<Method>();
		for (Class eachClass : getSuperClasses(fTestClass)) {
			Method[] methods= eachClass.getDeclaredMethods();
			for (Method eachMethod : methods) {
				Annotation annotation= eachMethod.getAnnotation(annotationClass);
				if (annotation != null && ! isShadowed(eachMethod, results)) 
					results.add(eachMethod);
			}
		}
		if (runsTopToBottom(annotationClass))
			Collections.reverse(results);
		return results;
	}

	public boolean isIgnored(Method eachMethod) {
		return eachMethod.getAnnotation(Ignore.class) != null;
	}

	private boolean runsTopToBottom(Class< ? extends Annotation> annotation) {
		return annotation.equals(Before.class) || annotation.equals(BeforeClass.class);
	}
	
	private boolean isShadowed(Method method, List<Method> results) {
		for (Method each : results) {
			if (each.getName().equals(method.getName()))
				return true;
		}
		return false;
	}

	private List<Class> getSuperClasses(Class< ?> testClass) {
		ArrayList<Class> results= new ArrayList<Class>();
		Class<?> current= testClass;
		while (current != null) {
			results.add(current);
			current= current.getSuperclass();
		}
		return results;
	}

	long getTimeout(Method method) {
		Test annotation= method.getAnnotation(Test.class);
		long timeout= annotation.timeout();
		return timeout;
	}

	Class<? extends Throwable> expectedException(Method method) {
		Test annotation= method.getAnnotation(Test.class);
		if (annotation.expected() == Test.None.class)
			return null;
		else
			return annotation.expected();
	}

}

