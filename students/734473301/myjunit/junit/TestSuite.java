package com.jyz.myjunit.junit;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 测试套件
 *  测试套件通过反射获取一个 测试用例类 的所有test开头的方法；每个方法对应一个实例
 *  遍历这些实例
 * Created by jyz on 2017/9/16.
 */
public  class TestSuite  extends Assert implements Test{
    private List<Test> tests= new ArrayList<>(10);
    private String name;


    /**
     * 反射获取该类所有测试方法
     * @param theClass
     */
    public TestSuite(final Class<?> theClass) {
        this.name= theClass.getName();
        Constructor<?> constructor = null;
        try {
            constructor= theClass.getConstructor(String.class);
            Method[] methods= theClass.getDeclaredMethods();
            for (Method method : methods) {
                if(isPublicTestMethod(method)){
                    System.out.println("添加测试方法："+method.getName());
                    addTest((Test) constructor.newInstance(method.getName()));
                }
            }
        } catch (Exception e) {
            addTest(warning("在获取测试方法时出现异常!"));
            e.printStackTrace();
        }
        if (tests.size() == 0){
            addTest(warning("没有发现可以使用的测试用例！"));
        }

    }


    private boolean isPublicTestMethod(Method m) {
        return isTestMethod(m) && Modifier.isPublic(m.getModifiers());
    }
    private boolean isTestMethod(Method m) {
        String name= m.getName();
        Class<?>[] parameters= m.getParameterTypes();
        Class<?> returnType= m.getReturnType();
        return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    private Test warning(final String message) {
        return new TestCase("warning") {
            @Override
            public void doRun() {
                fail(message);
            }
        };
    }

    @Override
    public void run(TestResult result) {

        for (Iterator<Test> e = tests(); e.hasNext(); ) {
            if (result.shouldStop() ){
                break;
            }
            Test test= (Test)e.next();
            test.run(result);
           // result.run(test);
        }

    }

    public int countTestCases() {
        int count= 0;
        for (Iterator<Test> e= tests(); e.hasNext(); ) {
            Test test= e.next();
            count= count + test.countTestCases();
        }
        return count;
    }
    public Iterator<Test> tests() {
        return tests.iterator();
    }
}
