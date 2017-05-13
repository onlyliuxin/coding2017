package com.donaldy.jvm.test;

import com.donaldy.jvm.engine.MiniJVM;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/4/27.
 */
public class MinJVMTest {

    static final String PATH = "D:\\tools\\Code\\Y_Repository\\coding2017\\group24\\448641125\\out\\production" +
        "\\448641125\\";

    @Test
    public void testMain() throws Exception {
        /**
         * 1.加载类
         *  -工具：ClassFileLoader
         *  -目的地：方法区(MethodArea)
         * 2.获取类的main方法
         *  -从方法区寻找
         * 3.执行main方法的字节码
         *  -字节码指令(Command对象)
         *  -栈帧(StackFrame)
         *  -堆(Heap)
         */
        String [] classPaths = {PATH};
        MiniJVM jvm = new MiniJVM();
        jvm.run(classPaths, "com.coderising.jvm.test.EmployeeV1");
    }
}
