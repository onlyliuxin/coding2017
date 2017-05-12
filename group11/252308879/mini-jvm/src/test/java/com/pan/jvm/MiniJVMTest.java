package com.pan.jvm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pan.jvm.engine.MiniJVM;

public class MiniJVMTest {


	private static final String FULL_QUALIFIED_CLASS_NAME = "com/pan/jvm/EmployeeV1";

	static String PATH = EmployeeV1.class.getClassLoader().getResource("").getPath()
			.replace("test-classes", "classes");

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
		jvm.run(classPaths, "com.pan.jvm.EmployeeV1");
		
	}

}
