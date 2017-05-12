package week4.origin.jvm.loader.test;

import  org.junit.Assert;
import org.junit.Test;

import week4.origin.jvm.loader.ClassFileLoader;


public class ClassFileLoaderTest {

	
	static String path1="E:\\JAVA\\liuxin\\coding2017\\group26\\1515345281\\bin";

	@Test
	public void testClassFileLength() {		
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		
		String className = "week4.origin.jvm.loader.test.EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1066, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "week4.origin.jvm.loader.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};
		
		
		String acctualValue = this.byteToHexString(codes);
		
		Assert.assertEquals("cafebabe", acctualValue);
	}  
    
	private String byteToHexString(byte[] codes ){
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<codes.length;i++){
			byte b=codes[i];
			int t=b & 0xFF;
			String str=Integer.toHexString(t);
			buffer.append(str);
		}
		return buffer.toString();
	}

}