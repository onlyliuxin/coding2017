package minijvm.loader;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import minijvm.engine.MiniJVM;

public class MiniJVMTest {
	
	static final String PATH = new File(".", "target\\test-classes").getAbsolutePath();
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
		jvm.run(classPaths, "minijvm.loader.EmployeeV1");
		
	}

}
