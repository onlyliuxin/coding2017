package com.coderising.jvm.test;

import java.util.List;

import org.junit.After;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.method.Method;





public class ClassFileloaderTest2 {
	
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

	@After
	public void tearDown() throws Exception {
	}
	

}
