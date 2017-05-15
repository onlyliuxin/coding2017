package jvm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jvm.engine.MiniJVM;

public class MiniJVMTest {
	
	static final String PATH = "D:\\Git Files\\coding2017\\group19\\1294642551\\bin";
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
		jvm.run(classPaths, "jvm.EmployeeV1");
		
	}

}
