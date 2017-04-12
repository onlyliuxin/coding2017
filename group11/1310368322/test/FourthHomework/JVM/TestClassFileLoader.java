package com.coderising.jvm.loader;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

public class TestClassFileLoader {
	static String path1 = "D:/ProgramWorld";
	static String path2 = "D:/ProgramWorld/Java";
	@Test
	public void test() {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);
		String clzPath = loader.getClassPath();
		Assert.assertEquals(path1 + ";" + path2, clzPath);
	}
	@Test
	public void testClassFileLength() throws IOException{
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		
		String className = "D:/ProgramWorld/Java/Practice/LangSi/2017编程提高群/bin/com/coderising/jvm/loader/EmployeeV1.class";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意： 这个字节数可能和你的JVM版本有关系，你可以看看编译好的类到底有多大
		Assert.assertEquals(1058,byteCodes.length);
	}
	@Test
	public void testMagicNumber() throws IOException{
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "D:/ProgramWorld/Java/Practice/LangSi/2017编程提高群/bin/com/coderising/jvm/loader/EmployeeV1.class";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{ 
				byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]
		};
		String actualValue = this.byteToHexString(codes);
		Assert.assertEquals("cafebabe",actualValue);
		
	}
	
	private String byteToHexString(byte[] codes){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < codes.length; i++){
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length() < 2){
				strHex = "0" + strHex;
			}
			buffer.append(strHex);
		}
		return buffer.toString();
	}
	
	
	
	
	
	
	

}
