package week8.jvm.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import week8.jvm.engine.MiniJVM;

public class MiniJVMTest {

	static final String path="E:\\JAVA\\liuxin\\coding2017\\group26\\1515345281\\bin";
	
	@Test
	public void testMain() throws FileNotFoundException, IOException{
		String[] classPaths={path};
		MiniJVM miniJVM=new MiniJVM();
		miniJVM.run(classPaths,"week5.jvm.test.EmployeeV1");
	}
}
