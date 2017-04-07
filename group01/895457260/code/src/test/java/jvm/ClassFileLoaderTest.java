package jvm;

import jvm.exception.ReadClassException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileLoaderTest {

	private static ClassFileLoader loader;
	private static String path1 = "target/classes";
	private static String path2 = "target/test-classes";
	
	@Before
	public void setUp() throws Exception {
		loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);
	}

	@After
	public void tearDown() throws Exception {}
	
	@Test
	public void testClassPath() {
		String clzPath = loader.getClassPath();
		Assert.assertEquals(path1+";"+path2,clzPath);
	}
	
	@Test
	public void testClassFileLength() throws ReadClassException {
		String className = "jvm.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1016, byteCodes.length);
	}

    @Test	
	public void testMagicNumber() throws ReadClassException {
		String className = "jvm.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[] {byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};
		
		String actualValue = this.byteToHexString(codes);
		Assert.assertEquals("cafebabe", actualValue);
	}

	private String byteToHexString(byte[] codes) {
		StringBuilder buffer = new StringBuilder();
		for (byte b : codes) {
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
