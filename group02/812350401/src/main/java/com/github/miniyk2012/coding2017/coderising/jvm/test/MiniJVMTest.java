package com.github.miniyk2012.coding2017.coderising.jvm.test;

import com.github.miniyk2012.coding2017.coderising.jvm.engine.MiniJVM;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MiniJVMTest {
	
	static final String PATH = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\answer\\bin";
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
		jvm.run(classPaths, "com.coderising.jvm.test.EmployeeV1");
		
	}

}
