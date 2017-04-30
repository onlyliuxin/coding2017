package jvm.engine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MiniJVMTest {
	
	private static final String PATH = "target/test-classes";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() throws Exception{
		String[] classPaths = {PATH};
		MiniJVM jvm = new MiniJVM();
		jvm.run(classPaths, "jvm.EmployeeV1");
	}

}
