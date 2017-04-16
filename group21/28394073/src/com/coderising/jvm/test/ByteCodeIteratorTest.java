package com.coderising.jvm.test;

import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.loader.ByteCodeIterator;

public class ByteCodeIteratorTest {
	
	
	static String path1 = "D:\\RTC_workspace\\Java_Excersice\\bin";
	static byte[] byteCode;
	static ByteCodeIterator iterator;
	static{
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.coderising.jvm.test.EmployeeV1";
		try {
			byteCode = loader.readBinaryCode(className);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iterator = new ByteCodeIterator(byteCode);
	}
	


//	@Test
//	public void testByteCodeIterator() {
//		fail("Not yet implemented");
//	}



	

//	@Test
//	public void testNextU2ToHexString() {
//		
//	}

	@Test
	public void testNextU4ToHexString() {
		Assert.assertEquals("cafebabe", iterator.nextU4ToHexString());
	}
	
	@Test
	public void testNextU2ToInt() {
		Assert.assertEquals(0, iterator.nextU2ToInt());
		Assert.assertEquals(51, iterator.nextU2ToInt());
		Assert.assertEquals(54, iterator.nextU2ToInt());
	}
	
	@Test
	public void testNextU1ToInt() {
		Assert.assertEquals(7, iterator.nextU1ToInt());
		Assert.assertEquals(2,iterator.nextU2ToInt());
		Assert.assertEquals(1, iterator.nextU1ToInt());
	}
	
	
	@Test
	public void testGetByteToString(){
		int len = iterator.nextU2ToInt();
		Assert.assertEquals("com/coderising/jvm/test/EmployeeV1", iterator.getBytesToString(len));
		Assert.assertEquals(7, iterator.nextU1ToInt());
		Assert.assertEquals(4,iterator.nextU2ToInt());
	}

}
