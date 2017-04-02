package org.xukai.jvm.test;

import com.google.common.base.Splitter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xukai.coderising.util.FileUtil;
import org.xukai.jvm.loader.ClassFileLoader;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class ClassFileloaderTest {

	
	static String path1 = "D:\\java\\IDEA-Workspace\\coding2017\\group19\\527220084\\xukai_coding\\coding-common\\target\\classes";
	static String path2 = "C:\temp";
	

	
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
	public void testClassFileLength() throws IOException, ClassNotFoundException {
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		
		String className = "org.xukai.jvm.test.EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1046, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber() throws IOException, ClassNotFoundException {
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "org.xukai.jvm.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};


		String acctualValue = this.byteToHexString(codes);
		
		Assert.assertEquals("cafebabe", acctualValue);
	}
    
    private File findClassFile(String classPath, String className){
		className = className.replaceAll("\\.", "/");
		System.out.println(classPath + "\\" + className + ".class");
		return new File(classPath + "\\" + className + ".class");
	}

	@Test
	public void testFile() throws IOException {
		File file = findClassFile(path1, "org.xukai.jvm.test.EmployeeV1");
		Assert.assertTrue(file.exists());
		byte[] bytes = FileUtil.toByteArray(file);
		System.out.println(bytes.length);
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
