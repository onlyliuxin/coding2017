package com.coderising.jvm.test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.loader.ClassFileLoaderException;

public class ClassFileLoaderTest {

	static String path1 = "D:\\MyTest\\mini-jvm\\bin";
	static String path2 = "C:\\temp";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);

		String clzPath = loader.getClassPath();

		Assert.assertEquals(path1 + ";" + path2, clzPath);
	}

	@Test
	public void ClassFileLengthTest() throws ClassFileLoaderException {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);

		String className = "com.coderising.jvm.test.EmployeeV1";
		byte[] bytes = loader.readBinaryCode(className);

		Assert.assertEquals(1056, bytes.length);
	}

	@Test
	public void MagicNumberTest() throws ClassFileLoaderException {

		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);

		String className = "com.coderising.jvm.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);

		byte[] bytes = { byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3] };
		String actualString = byteToHexString(bytes);
		Assert.assertEquals("cafebabe", actualString);
	}

	private String byteToHexString(byte[] bytes) {

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if (strHex.length() < 2) {
				strHex = "0" + strHex;
			}
			buffer.append(strHex);
		}
		return buffer.toString();
	}

}
