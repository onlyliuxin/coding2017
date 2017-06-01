package com.coderising.jvm.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.print.CommandPrinter;
import com.coderising.jvm.print.CommandPrinterJavaP;
import com.coderising.jvm.print.ConstantPoolPrinter;

import junit.framework.Assert;

public class JavapTest {

	private static final String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/test/EmployeeV1";
	static String path1 = "/Users/erlisuo/Documents/workspace/codeRising2017working/1282579502/bin";
	static String path3 = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\bin";
	static String path2 = "C:\temp";
	
	static ClassFile clzFile = null;
	static ClassFileLoader loader = null;
	static {
		loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.coderising.jvm.test.EmployeeV1";
		
		clzFile = loader.loadClass(className);
		//clzFile.print();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantPoolPrinter() {
		ConstantPoolPrinter constPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
		constPoolPrinter.print();
	}
	
	@Test
	public void testCommandPrinter(){
		CommandPrinterJavaP cmdPrinter = new CommandPrinterJavaP(clzFile);
		cmdPrinter.print();
	}

}
