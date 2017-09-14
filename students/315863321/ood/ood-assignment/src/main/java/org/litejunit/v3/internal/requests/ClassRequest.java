/**
 *
 */
package org.litejunit.v3.internal.requests;


import org.litejunit.v3.internal.runners.TestClassRunner;
import org.litejunit.v3.runner.Request;
import org.litejunit.v3.runner.RunWith;
import org.litejunit.v3.runner.Runner;

import java.lang.reflect.Constructor;

public class ClassRequest extends Request {
    private final Class<?> fTestClass;

    public ClassRequest(Class<?> each) {
        fTestClass = each;
    }

    @Override
    public Runner getRunner() {
        Class runnerClass = getRunnerClass(fTestClass);
        try {
            Constructor constructor = runnerClass.getConstructor(Class.class); // TODO good error message if no such constructor
            Runner runner = (Runner) constructor
                    .newInstance(new Object[]{fTestClass});
            return runner;
        } catch (Exception e) {
            return Request.errorReport(fTestClass, e).getRunner();
        }
    }

    Class getRunnerClass(Class<?> testClass) {
        RunWith annotation = testClass.getAnnotation(RunWith.class);
        if (annotation != null) {
            return annotation.value();
        } else if (isPre4Test(testClass)) {
            return null;
//			return OldTestClassRunner.class;
        } else {
            return TestClassRunner.class;
        }
    }

    boolean isPre4Test(Class<?> testClass) {
        return false;
//        return junit.framework.TestCase.class.isAssignableFrom(testClass);
    }
}