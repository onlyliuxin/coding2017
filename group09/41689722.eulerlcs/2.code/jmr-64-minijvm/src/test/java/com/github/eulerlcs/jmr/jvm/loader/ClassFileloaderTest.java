package com.github.eulerlcs.jmr.jvm.loader;

import java.io.File;

import javax.xml.bind.DatatypeConverter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileloaderTest {
	private static String userDir = System.getProperty("user.dir");
	private static String path1 = "C:\temp";
	private static String path2 = userDir + File.separator + "target" + File.separator + "test-classes";
	private static String className = EmployeeV1.class.getName();
	private ClassFileLoader loader = null;

	@Before
	public void setUp() throws Exception {
		loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);
	}

	@After
	public void tearDown() throws Exception {
		loader = null;
	}

	@Test
	public void testClassPath() {
		String clzPath = loader.getClassPath();
		Assert.assertEquals(path1 + ";" + path2, clzPath);
	}

	@Test
	public void testClassFileLength() {
		byte[] byteCodes = loader.readBinaryCode(className);
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1078, byteCodes.length);

	}

	@Test
	public void testMagicNumber() {
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[] { byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3] };
		String acctualValue = DatatypeConverter.printHexBinary(codes);

		Assert.assertEquals("CAFEBABE", acctualValue);
	}
}
