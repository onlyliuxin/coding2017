package com.github.ipk2015.coding2017.minijvm.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.minijvm.engine.MiniJVM;

public class MiniJVMTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MiniJVM jvm = new MiniJVM();
		String[] classPaths = new String[3];
		classPaths[0] = ClassFileloaderTest.path1;
		classPaths[1] = ClassFileloaderTest.path2;
		classPaths[2] = ClassFileloaderTest.path3;
		String className = "com.coderising.jvm.test.EmployeeV1";
		try {
			jvm.run(classPaths, className);
			Assert.assertEquals(0,0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			Assert.assertEquals(0,1);
		}
	}

}
