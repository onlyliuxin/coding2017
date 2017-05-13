package com.github.HarryHook.coding2017.jvm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.HarryHook.coding2017.jvm.engine.MiniJVM;;

public class MiniJVMTest {

    static final String PATH = "F:\\Coding2017\\group02\\727171008\\bin";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
	String[] classPaths = { PATH };
	MiniJVM jvm = new MiniJVM();
	jvm.run(classPaths, "com.github.HarryHook.coding2017.jvm.test.EmployeeV1");

    }

}