package com.coderising.jvm.loader;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;

public class TestClassFileLoader {
	static String path1 = "D:/ProgramWorld";
	static String path2 = "D:/ProgramWorld/Java";
	private static final String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/loader/EmployeeV1";
	static String path = "D:/ProgramWorld/Java/Practice/LangSi/2017������Ⱥ/bin/com/coderising/jvm/loader/EmployeeV1.class";
	static ClassFile clzFile = null;
	
	
	
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
		
		String className = "D:/ProgramWorld/Java/Practice/LangSi/2017������Ⱥ/bin/com/coderising/jvm/loader/EmployeeV1.class";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// ע�⣺ ����ֽ������ܺ����JVM�汾�й�ϵ������Կ�������õ��ൽ���ж��
		Assert.assertEquals(1058,byteCodes.length);
	}
	@Test
	public void testMagicNumber() throws IOException{
		
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "D:/ProgramWorld/Java/Practice/LangSi/2017������Ⱥ/bin/com/coderising/jvm/loader/EmployeeV1.class";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{ 
				byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]
		};
		System.out.println("ddd");
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
	
	//---------------------------------------------
	
	
	 @Test
	 public void testVersion() throws IOException{    		
		ClassFileLoader loader = new ClassFileLoader();
		String className = "D:/TestJVM.class";
		clzFile = loader.loadClass(className);
		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(51, clzFile.getMajorVersion());
			
	 }
	    
	 @Test
	 public void testConstantPool() throws IOException{
    	
		ClassFileLoader loader = new ClassFileLoader();
		clzFile = loader.loadClass(path);
		ConstantPool pool = clzFile.getConstantPool();
		
		Assert.assertEquals(53, pool.getSize());
	
		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(1);
			Assert.assertEquals(2, clzInfo.getUtf8Index());
			
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(2);
			Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
		}
		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(3);
			Assert.assertEquals(4, clzInfo.getUtf8Index());
			
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(4);
			Assert.assertEquals("java/lang/Object", utf8Info.getValue());
		}
		{
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(5);
			Assert.assertEquals("name", utf8Info.getValue());
			
			utf8Info = (UTF8Info) pool.getConstantInfo(6);
			Assert.assertEquals("Ljava/lang/String;", utf8Info.getValue());
			
			utf8Info = (UTF8Info) pool.getConstantInfo(7);
			Assert.assertEquals("age", utf8Info.getValue());
			
			utf8Info = (UTF8Info) pool.getConstantInfo(8);
			Assert.assertEquals("I", utf8Info.getValue());
			
			utf8Info = (UTF8Info) pool.getConstantInfo(9);
			Assert.assertEquals("<init>", utf8Info.getValue());
			
			utf8Info = (UTF8Info) pool.getConstantInfo(10);
			Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getValue());
			
			utf8Info = (UTF8Info) pool.getConstantInfo(11);
			Assert.assertEquals("Code", utf8Info.getValue());
		}
		
		{
			MethodRefInfo methodRef = (MethodRefInfo)pool.getConstantInfo(12);
			Assert.assertEquals(3, methodRef.getClassInfoIndex());
			Assert.assertEquals(13, methodRef.getNameAndTypeIndex());
		}
		
		{
			NameAndTypeInfo nameAndType = (NameAndTypeInfo) pool.getConstantInfo(13);
			Assert.assertEquals(9, nameAndType.getIndex1());
			Assert.assertEquals(14, nameAndType.getIndex2());
		}
		//��鼸����
		{
			MethodRefInfo methodRef = (MethodRefInfo)pool.getConstantInfo(45);
			Assert.assertEquals(1, methodRef.getClassInfoIndex());
			Assert.assertEquals(46, methodRef.getNameAndTypeIndex());
		}
		
		{
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(53);
			Assert.assertEquals("EmployeeV1.java", utf8Info.getValue());
		}
    }
    @Test
    public void testClassIndex() throws IOException{
    	
    	ClassFileLoader loader = new ClassFileLoader();
		clzFile = loader.loadClass(path);
    	ClassIndex clzIndex = clzFile.getClzIndex();
    	System.out.println("clzIndex="+clzIndex);
    	ClassInfo thisClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getThisClassIndex());
    	ClassInfo superClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getSuperClassIndex());
    	System.out.println(thisClassInfo.getClassName());
    	System.out.println(superClassInfo.getClassName());
    	Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, thisClassInfo.getClassName());
    	Assert.assertEquals("java/lang/Object", superClassInfo.getClassName());
    }

	
	
	
	

}
