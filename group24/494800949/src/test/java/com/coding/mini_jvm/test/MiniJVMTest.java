package com.coding.mini_jvm.test;

import com.coding.mini_jvm.src.com.coderising.jvm.engine.MiniJVM;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MiniJVMTest {

	//	static final String PATH = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\answer\\bin";
//	static String PATH = "H:\\sourceCode\\coding2017\\group24\\494800949";
	static String PATH  = "H:\\sourceCode\\coding2017\\group24\\494800949\\build\\classes\\main";
	static String PATH1 = "D:\\Java\\jdk1.8.0_25\\lib\\rt.jar";
	static String PATH2 = "D:\\Java\\jdk1.8.0_25\\jre\\lib\\rt";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() throws Exception {
		String[] classPaths = {PATH, PATH1, PATH2};
		MiniJVM jvm = new MiniJVM();
		jvm.run(classPaths, "com.coding.mini_jvm.src.com.coderising.jvm.EmployeeV1");
		
	}

}
