package jvm;

import jvm.classfile.ClassFile;
import jvm.classfile.ClassIndex;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.classfile.constant.item.impl.MethodRefInfo;
import jvm.classfile.constant.item.impl.NameAndTypeInfo;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.exception.ReadClassException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileLoaderTest {
	private static final String FULL_QUALIFIED_CLASS_NAME = "jvm/EmployeeV1";
	private static final String LOAD_CLASS_NAME = "jvm.EmployeeV1";

	private static ClassFileLoader loader;
	private static String path1 = "target/classes";
	private static String path2 = "target/test-classes";

	private static ClassFile clzFile = null;
	
	@Before
	public void setUp() throws Exception {
		loader = new ClassFileLoader();
		loader.addClassPath(path1);
		loader.addClassPath(path2);

		if (clzFile == null) {
			clzFile = loader.load(LOAD_CLASS_NAME);
		}
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
		byte[] byteCodes = loader.readBinaryCode(LOAD_CLASS_NAME);
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1016, byteCodes.length);
	}

    @Test	
	public void testMagicNumber() throws ReadClassException {
		byte[] byteCodes = loader.readBinaryCode(LOAD_CLASS_NAME);
		byte[] codes = new byte[] {byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};

		boolean check = loader.checkMagicNumber(codes);
		Assert.assertTrue(check);
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





	@Test
	public void testVersion() {
		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(52, clzFile.getMajorVersion());
	}

	@Test
	public void testConstantPool() {

		ConstantPool pool = clzFile.getConstantPool();

		Assert.assertEquals(53, pool.getSize());

		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(7);
			Assert.assertEquals(44, clzInfo.getUtf8Index());

			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(44);
			Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
		}
		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(11);
			Assert.assertEquals(48, clzInfo.getUtf8Index());

			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(48);
			Assert.assertEquals("java/lang/Object", utf8Info.getValue());
		}
		{
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(12);
			Assert.assertEquals("name", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(13);
			Assert.assertEquals("Ljava/lang/String;", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(14);
			Assert.assertEquals("age", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(15);
			Assert.assertEquals("I", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(16);
			Assert.assertEquals("<init>", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(17);
			Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(18);
			Assert.assertEquals("Code", utf8Info.getValue());
		}

		{
			MethodRefInfo methodRef = (MethodRefInfo)pool.getConstantInfo(1);
			Assert.assertEquals(11, methodRef.getClassInfoIndex());
			Assert.assertEquals(36, methodRef.getNameAndTypeIndex());
		}

		{
			NameAndTypeInfo nameAndType = (NameAndTypeInfo) pool.getConstantInfo(36);
			Assert.assertEquals(16, nameAndType.getIndex1());
			Assert.assertEquals(28, nameAndType.getIndex2());
		}
		//抽查几个吧
		{
			MethodRefInfo methodRef = (MethodRefInfo)pool.getConstantInfo(9);
			Assert.assertEquals(7, methodRef.getClassInfoIndex());
			Assert.assertEquals(46, methodRef.getNameAndTypeIndex());
		}

		{
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(35);
			Assert.assertEquals("EmployeeV1.java", utf8Info.getValue());
		}
	}
	@Test
	public void testClassIndex() {

		ClassIndex clzIndex = clzFile.getClzIndex();
		ClassInfo thisClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getThisClassIndex());
		ClassInfo superClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getSuperClassIndex());

		Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, thisClassInfo.getClassName());
		Assert.assertEquals("java/lang/Object", superClassInfo.getClassName());
	}
}
