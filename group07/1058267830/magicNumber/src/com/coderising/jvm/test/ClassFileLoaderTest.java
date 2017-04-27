package com.coderising.jvm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coderising.jvm.loader.ClassFileLoader;

public class ClassFileLoaderTest {

	static String path1 = "C:\\Users\\Administrator\\JavaStudy\\magicNumber\\bin";
	static String path2 = "C:\\Users";
	
	@Test
	public void testClassPath(){		
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
//		loader.addClassPath(path2);
		
		String clzPath = loader.getClassPath();
		
//		assertEquals(path1+";"+path2, clzPath);
		assertEquals(path1, clzPath);
	}
	
	@Test
	public void testClassFileLength() {		
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		
		String className = "com.coderising.jvm.test.EmployeeV1";
//		String className = "\\com\\coderising\\jvm\\test\\EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		assertEquals(1055, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.coderising.jvm.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};
		
		String acctualValue = this.byteToHexString(codes);
		
		assertEquals("cafebabe", acctualValue);
	}
    
   	private String byteToHexString(byte[] codes ){
   		StringBuffer buffer = new StringBuffer();
   		for(int i=0;i<codes.length;i++){
   			byte b = codes[i];
   			int value = b & 0xFF;
   			String strHex = Integer.toHexString(value);
   			if(strHex.length()< 2){
   				strHex = "0" + strHex;
   			}		
   			buffer.append(strHex);
   		}
   		return buffer.toString();
   	}
}
