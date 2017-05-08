package com.coderising.jvm.test;

import org.junit.After;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.loader.ClassFileLoader;





public class ClassFileloaderTest {

	
	static String path1 = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\bin";
	static String path2 = "C:\temp";
	static String path3 = "/Users/erlisuo/Documents/workspace/codeRising2017working/1282579502/bin";
	
	
	@Before
	public void setUp() throws Exception {		 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClassPath(){		
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);
		
		String clzPath = loader.getClassPath();
		
		Assert.assertEquals(path1+";"+path2,clzPath);
		
	}
	
	@Test
	public void testClassFileLength() {		
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);
		loader.addClassPath(path3);
		String className = "com.coderising.jvm.test.EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1056, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path3);
		String className = "com.coderising.jvm.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};
		
		
		String acctualValue = this.byteToHexString(codes);
		
		Assert.assertEquals("cafebabe", acctualValue);
	}
    
    
    @Test
    public void testToHexString(){
    	byte b1 = 'a';
    	byte b2 = 'b';
    	System.out.println("Binary: " + Integer.toBinaryString(b1) + " decimal: " + Integer.toString(b1) + " hex: " + Integer.toHexString(b1));
    	System.out.println("Binary: " + Integer.toBinaryString(b2) + " decimal: " + Integer.toString(b2) + " hex: " + Integer.toHexString(b2));

    	byte[] bArray = new byte[]{b1, b2};
    	System.out.println(byteToHexString(bArray));
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