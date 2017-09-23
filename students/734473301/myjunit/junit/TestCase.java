package com.jyz.myjunit.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by jyz on 2017/9/16.
 */
public abstract class TestCase extends  Assert implements Test {
    private  String name;// 该用例的方法名字

    public TestCase(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "methond ='" + name + '\'' +
                '}';
    }

    public void doRun() throws Throwable{
        setUp();
        try{
            runTest();
        }
        finally{
            tearDown();
        }
    }

    @Override
    public int countTestCases(){
        return 1;
    }

    protected void setUp() {

    }

    protected void tearDown() {

    }

    @Override
    public void run(TestResult tr) {
        tr.run(this);
    }

    protected void runTest() throws Throwable{
        Method runMethod= null;
        try {
            runMethod= getClass().getMethod(name, null);
        } catch (NoSuchMethodException e) {
            fail("Method \""+name+"\" not found");
        }
        if (!Modifier.isPublic(runMethod.getModifiers())) {
            fail("Method \""+name+"\" should be public");
        }

        try {
            runMethod.invoke(this);
        }
        catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        }
        catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e;
        }
    }
}
