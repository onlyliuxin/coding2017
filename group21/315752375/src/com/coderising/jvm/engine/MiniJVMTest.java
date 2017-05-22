package com.coderising.jvm.engine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MiniJVMTest {
	static final String PATH="H:\\课件\\编程提高相关\\coding2017\\group21\\315752375\\bin";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() throws Exception {
		String[] classPaths={PATH};
		MiniJVM jvm=new MiniJVM();
		jvm.run(classPaths, "com.coderising.jvm.test.EmployeeV1");
	}

}
