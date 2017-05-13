package com.coderising.jvm.engine;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MiniJVMTest {

	static final String PATH = "D:\\";
	
	@Test
	public void test() throws IOException {
		String []classPaths = {PATH};
		MiniJVM jvm = new MiniJVM();
		jvm.run(classPaths, "EmployeeV1");
		
	}

}
