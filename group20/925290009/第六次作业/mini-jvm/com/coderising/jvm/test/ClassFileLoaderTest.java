package com.coderising.jvm.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.attribute.CodeAttr;
import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.clasfile.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.Utf8Info;
import com.coderising.jvm.field.JField;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.method.JMethod;

public class ClassFileLoaderTest {

	private static String path1 = "D:\\MyTest\\mini-jvm\\bin";
	private static String path2 = "C:\\temp";
	private final static String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/test/EmployeeV1";
	private static ClassFile clzFile = null;
	static {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.coderising.jvm.test.EmployeeV1";
		clzFile = loader.loadClass(className);

	}

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
	public void ClassFileLengthTest() {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);

		String className = "com.coderising.jvm.test.EmployeeV1";
		byte[] bytes = loader.readBinaryCode(className);

		Assert.assertEquals(1056, bytes.length);
	}

	@Test
	public void MagicNumberTest() {

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

	@Test
	public void testVersion() {

		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(51, clzFile.getMajorVersion());
	}

	@Test
	public void testConstantＰool() {

		ConstantPool pool = clzFile.getPool();

		Assert.assertEquals(53, pool.getConstantNumber());

		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(1);
			Assert.assertEquals(2, clzInfo.getUtf8Index());

			Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(2);
			Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
		}

		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(3);
			Assert.assertEquals(4, clzInfo.getUtf8Index());

			Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(4);
			Assert.assertEquals("java/lang/Object", utf8Info.getValue());
		}

		{
			Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(5);
			Assert.assertEquals("name", utf8Info.getValue());

			utf8Info = (Utf8Info) pool.getConstantInfo(6);
			Assert.assertEquals("Ljava/lang/String;", utf8Info.getValue());

			utf8Info = (Utf8Info) pool.getConstantInfo(7);
			Assert.assertEquals("age", utf8Info.getValue());

			utf8Info = (Utf8Info) pool.getConstantInfo(8);
			Assert.assertEquals("I", utf8Info.getValue());

			utf8Info = (Utf8Info) pool.getConstantInfo(9);
			Assert.assertEquals("<init>", utf8Info.getValue());

			utf8Info = (Utf8Info) pool.getConstantInfo(10);
			Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getValue());

			utf8Info = (Utf8Info) pool.getConstantInfo(11);
			Assert.assertEquals("Code", utf8Info.getValue());
		}

		{
			MethodInfo methodRef = (MethodInfo) pool.getConstantInfo(12);
			Assert.assertEquals(3, methodRef.getIndex_ClassInfo());
			Assert.assertEquals(13, methodRef.getIndex_NameAndType());
		}

		{
			NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) pool
					.getConstantInfo(13);
			Assert.assertEquals(9, nameAndTypeInfo.getIndex_Name());
			Assert.assertEquals(14, nameAndTypeInfo.getIndex_Describe());
		}

		{
			MethodInfo methodRef = (MethodInfo) pool.getConstantInfo(45);
			Assert.assertEquals(1, methodRef.getIndex_ClassInfo());
			Assert.assertEquals(46, methodRef.getIndex_NameAndType());
		}

		{
			Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(53);
			Assert.assertEquals("EmployeeV1.java", utf8Info.getValue());
		}

		{
			FieldRefInfo fieldRefInfo = (FieldRefInfo) pool.getConstantInfo(28);
			Assert.assertEquals(29, fieldRefInfo.getIndex_ClassInfo());
			Assert.assertEquals(31, fieldRefInfo.getIndex_NameAndType());

		}

	}

	@Test
	public void testClassIndex() {

		ClassIndex clzIndex = clzFile.getClassIndex();
		ClassInfo thisClassInfo = (ClassInfo) clzFile.getPool()
				.getConstantInfo(clzIndex.getThisClassIndex());
		ClassInfo superClassInfo = (ClassInfo) clzFile.getPool()
				.getConstantInfo(clzIndex.getSuperClassIndex());

		Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME,
				thisClassInfo.getClassName());
		Assert.assertEquals("java/lang/Object", superClassInfo.getClassName());
	}

	/**
	 * 下面是JVM第三次实现部分
	 */
	@Test
	public void testReadFields() {

		List<JField> jfields = clzFile.getJFields();

		Assert.assertEquals(2, jfields.size());
		{
			JField jfield = jfields.get(0);
			Assert.assertEquals("name:Ljava/lang/String;", jfield.toString());
		}
		{
			JField jfield = jfields.get(1);
			Assert.assertEquals("age:I", jfield.toString());
		}

	}

	
	@Test
	public void testMethod() {

		List<JMethod> jMethods = clzFile.getMethods();
		ConstantPool pool = clzFile.getPool();

		{
			JMethod jMethod = jMethods.get(0);
			assertJMethodEquals(pool, jMethod, "<init>",
					"(Ljava/lang/String;I)V", "2ab7000c2a2bb5000f2a1cb50011b1");
		}
		{
			JMethod jMethod = jMethods.get(1); 
			assertJMethodEquals(pool,jMethod,
    				"setName",
    				"(Ljava/lang/String;)V",
    				"2a2bb5000fb1");
    		
    	}
    	{
    		JMethod jMethod = jMethods.get(2); 
    		assertJMethodEquals(pool,jMethod,
    				"setAge",
    				"(I)V",
    				"2a1bb50011b1");
    	}
    	{
    		JMethod jMethod = jMethods.get(3);
    		assertJMethodEquals(pool,jMethod,
    				"sayHello",
    				"()V",
    				"b2001c1222b60024b1");
    		
    	}
    	{
    		JMethod jMethod = jMethods.get(4); 
    		assertJMethodEquals(pool,jMethod,
    				"main",
    				"([Ljava/lang/String;)V",
    				"bb000159122b101db7002d4c2bb6002fb1");
    	}
	}
	

	private void assertJMethodEquals(ConstantPool pool, JMethod jMethod,
			String expectedName, String expectedDescrib, String expectedCode) {
		String methodNameString = pool.getUtf8String(jMethod.getName_index());
		String realDescrib = pool.getUtf8String(jMethod.getDescriptor_index());
		String realCode = jMethod.getCodeAttr().getCodeString();
		
		Assert.assertEquals(expectedName, methodNameString);
		Assert.assertEquals(expectedDescrib, realDescrib);
		Assert.assertEquals(expectedCode, realCode);
	}

}
