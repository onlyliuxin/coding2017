package com.sprint.jvm.loader;

import com.sprint.jvm.clz.ClassFile;
import org.junit.Test;
import org.junit.Assert;
public class ClassFileLoaderTest {

	private static final String FULL_QUALTFIED_CLASS_NAME = "com/sprint/jvm/EmployeeV1";

	static String path1 = "/home/sprint/java/code/coding2017/group11/1178243325/week06/build/classes/test";
	static ClassFile clzFile = null;
	static {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.sprint.jvm.loader.EmployeeV1"; 
		clzFile = loader.loadClass(className);
	}

	@Test
	public void test() {
		Assert.assertEquals("cafebabe", clzFile.getCafebabe());
	}
}
