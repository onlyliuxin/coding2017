package mini_jvm.test;

import mini_jvm.engine.MiniJVM;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MiniJVMTest {
	
	static final String PATH = "C:\\Users\\zhouliang\\Desktop\\mycoding\\";
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
		jvm.run(classPaths, "EmployeeV1");
		
	}

}
