package com.coding.jvm.engine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MiniJVMTest {
	
	static final String PATH = System.getProperty("user.dir")+"/bin";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() throws Exception{
		String[] classPaths = {PATH};
		MiniJVM jvm = new MiniJVM();
		jvm.run(classPaths, "EmployeeV2");
	}

}
