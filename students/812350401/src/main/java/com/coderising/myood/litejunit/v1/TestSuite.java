package com.coderising.myood.litejunit.v1;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by thomas_young on 21/8/2017.
 */
public class TestSuite implements Test {
    private String name;
    private List<Test> tests = new ArrayList<>(10);

    @Override
    public int countTestCases() {
        int totalCount = 0;
        for (Test test: tests) {
            totalCount += test.countTestCases();
        }
        return totalCount;
    }

    public TestSuite(String name) {
        this.name = name;
    }

    // 把某个测试类中的所有pulic的test方法构造成对象，塞入tests
    public TestSuite(final Class<?> theClass) {
        this.name = theClass.getName();
        Constructor<?> constructor = null;
        try {
            constructor = getConstructor(theClass);
        } catch (NoSuchMethodException e) {
            addTest(warning("Class "+theClass.getName()+" has no public constructor TestCase(String name)"));
        }

        if (!Modifier.isPublic(theClass.getModifiers())) {
            addTest(warning("Class "+theClass.getName()+" is not public"));
            return;
        }

        Method[] methods = theClass.getDeclaredMethods();
        Set<String> names = new TreeSet<>();
        for (Method method: methods) {
            addTestInstance(method, names, constructor);
        }

        if (tests.size() == 0)
            addTest(warning("No tests found in "+theClass.getName()));
    }

    private Constructor<?> getConstructor(Class<?> theClass) throws NoSuchMethodException {
        Class<?>[] args = {String.class};
        return theClass.getConstructor(args);
    }

    @Override
    public void run(TestResult tr) {
        for (Test test: tests) {
            if (tr.shouldStop()) break;
            test.run(tr);
        }
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public Iterator<Test> tests() {
        return tests.iterator();
    }

    // --------------------- private ---------------------------
    private void addTestInstance(Method method, Set<String> names, Constructor<?> constructor) {
        String name = method.getName();
        if (names.contains(name)) {
            return;
        }
        if (!isTestMethod(method)) {
            return;
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            addTest(warning("Test method isn't public: "+name));
            return;
        }

        names.add(name);
        Object[] args= new Object[]{name};
        try {
            addTest((Test)constructor.newInstance(args));
        } catch (InstantiationException e) {
            addTest(warning("Cannot instantiate test case: "+name+" ("+exceptionToString(e)+")"));
        } catch (InvocationTargetException e) {
            addTest(warning("Exception in constructor: "+name+" ("+exceptionToString(e.getTargetException())+")"));
        } catch (IllegalAccessException e) {
            addTest(warning("Cannot access test case: "+name+" ("+exceptionToString(e)+")"));
        }
    }

    private boolean isTestMethod(Method method) {
        String methodName = method.getName();
        int paraCount = method.getParameterCount();
        Class<?> returnType = method.getReturnType();
        return paraCount == 0 && returnType.equals(Void.TYPE) && methodName.startsWith("test");
    }

    private Test warning(final String message) {
        return new TestCase("warning") {
            public void doRun() {
                fail(message);
            }
        };
    }

    private String exceptionToString(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        return stringWriter.toString();

    }

}
