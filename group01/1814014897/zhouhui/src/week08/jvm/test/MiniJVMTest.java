package week08.jvm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week08.jvm.engine.MiniJVM;

public class MiniJVMTest {
	
	static final String PATH = ("" + ClassLoader.getSystemResource("")).replaceAll("file:/", "");
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
		jvm.run(classPaths, "week08.jvm.test.EmployeeV1");
		
	}

}
