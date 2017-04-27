package com.donaldy.jvm.test;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.clz.ClassIndex;
import com.donaldy.jvm.cmd.BiPushCmd;
import com.donaldy.jvm.cmd.ByteCodeCommand;
import com.donaldy.jvm.cmd.OneOperandCmd;
import com.donaldy.jvm.cmd.TwoOperandCmd;
import com.donaldy.jvm.constant.*;
import com.donaldy.jvm.field.Field;
import com.donaldy.jvm.method.Method;
import org.junit.After;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.donaldy.jvm.loader.ClassFileLoader;

import java.util.List;


public class ClassFileloaderTest {

	private static final String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/test/EmployeeV1";

	static String path1 = "D:\\tools\\Code\\Y_Repository\\coding2017\\group24\\448641125\\out\\production\\448641125\\";
	static String path2 = "C:\\temp";

	static ClassFile clzFile = null;
	
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
		
		String className = "com.donaldy.jvm.test.EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1050, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "com.donaldy.jvm.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};
		
		
		String acctualValue = this.byteToHexString(codes);
		
		Assert.assertEquals("cafebabe", acctualValue);
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


	/**
	 * ----------------------------------------------------------------------
	 */


	@Test
	public void testVersion(){
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(this.path1);
		clzFile = loader.loadClass("com.donaldy.jvm.test.EmployeeV1");

		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(52, clzFile.getMajorVersion());

	}

	@Test
	public void testConstantPool(){

		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(this.path1);
		clzFile = loader.loadClass("com.donaldy.jvm.test.EmployeeV1");

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
		//抽查几个吧
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
	public void testClassIndex(){

		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(this.path1);
		clzFile = loader.loadClass("com.donaldy.jvm.test.EmployeeV1");

		ClassIndex clzIndex = clzFile.getClzIndex();
		ClassInfo thisClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getThisClassIndex());
		ClassInfo superClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getSuperClassIndex());


		Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, thisClassInfo.getClassName());
		Assert.assertEquals("java/lang/Object", superClassInfo.getClassName());
	}

	/**
	 * 下面是第三次JVM课应实现的测试用例
	 */
	@Test
	public void testReadFields(){

		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(this.path1);
		clzFile = loader.loadClass("com.donaldy.jvm.test.EmployeeV1");

		List<Field> fields = clzFile.getFields();
		Assert.assertEquals(2, fields.size());
		{
			Field f = fields.get(0);
			Assert.assertEquals("name:Ljava/lang/String;", f.toString());
		}
		{
			Field f = fields.get(1);
			Assert.assertEquals("age:I", f.toString());
		}
	}
	@Test
	public void testMethods(){

		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(this.path1);
		clzFile = loader.loadClass("com.donaldy.jvm.test.EmployeeV1");


		List<Method> methods = clzFile.getMethods();
		ConstantPool pool = clzFile.getConstantPool();

		{
			Method m = methods.get(0);
			assertMethodEquals(pool,m,
				"<init>",
				"(Ljava/lang/String;I)V",
				"2ab7000c2a2bb5000f2a1cb50011b1");

		}
		{
			Method m = methods.get(1);
			assertMethodEquals(pool,m,
				"setName",
				"(Ljava/lang/String;)V",
				"2a2bb5000fb1");

		}
		{
			Method m = methods.get(2);
			assertMethodEquals(pool,m,
				"setAge",
				"(I)V",
				"2a1bb50011b1");
		}
		{
			Method m = methods.get(3);
			assertMethodEquals(pool,m,
				"sayHello",
				"()V",
				"b2001c1222b60024b1");

		}
		{
			Method m = methods.get(4);
			assertMethodEquals(pool,m,
				"main",
				"([Ljava/lang/String;)V",
				"bb000159122b101db7002d4c2bb6002fb1");
		}
	}

	private void assertMethodEquals(ConstantPool pool,Method m , String expectedName, String expectedDesc,String expectedCode){
		String methodName = pool.getUTF8String(m.getNameIndex());
		String methodDesc = pool.getUTF8String(m.getDescriptorIndex());
		String code = m.getCodeAttr().getCode();
		Assert.assertEquals(expectedName, methodName);
		Assert.assertEquals(expectedDesc, methodDesc);
		Assert.assertEquals(expectedCode, code);
	}


	/**
	 * 第四次 JVM作业
	 */

	@Test
	public void testByteCodeCommand(){
		{
			ClassFileLoader loader = new ClassFileLoader();
			loader.addClassPath(this.path1);
			this.clzFile = loader.loadClass("com.donaldy.jvm.test.EmployeeV1");

			Method initMethod = this.clzFile.getMethod("<init>", "(Ljava/lang/String;I)V");
			ByteCodeCommand [] cmds = initMethod.getCmds();

			assertOpCodeEquals("0: aload_0", cmds[0]);
			assertOpCodeEquals("1: invokespecial #12", cmds[1]);
			assertOpCodeEquals("4: aload_0", cmds[2]);
			assertOpCodeEquals("5: aload_1", cmds[3]);
			assertOpCodeEquals("6: putfield #15", cmds[4]);
			assertOpCodeEquals("9: aload_0", cmds[5]);
			assertOpCodeEquals("10: iload_2", cmds[6]);
			assertOpCodeEquals("11: putfield #17", cmds[7]);
			assertOpCodeEquals("14: return", cmds[8]);
		}

		{
			Method setNameMethod = this.clzFile.getMethod("setName", "(Ljava/lang/String;)V");
			ByteCodeCommand[] cmds = setNameMethod.getCmds();

			assertOpCodeEquals("0: aload_0", cmds[0]);
			assertOpCodeEquals("1: aload_1", cmds[1]);
			assertOpCodeEquals("2: putfield #15", cmds[2]);
			assertOpCodeEquals("5: return", cmds[3]);

		}

		{
			Method sayHelloMethod = this.clzFile.getMethod("sayHello", "()V");
			ByteCodeCommand [] cmds = sayHelloMethod.getCmds();

			assertOpCodeEquals("0: getstatic #28", cmds[0]);
			assertOpCodeEquals("3: ldc #34", cmds[1]);
			assertOpCodeEquals("5: invokevirtual #36", cmds[2]);
			assertOpCodeEquals("8: return", cmds[3]);

		}

		{
			Method mainMethod = this.clzFile.getMainMethod();

			ByteCodeCommand [] cmds = mainMethod.getCmds();

			assertOpCodeEquals("0: new #1", cmds[0]);
			assertOpCodeEquals("3: dup", cmds[1]);
			assertOpCodeEquals("4: ldc #43", cmds[2]);
			assertOpCodeEquals("6: bipush 29", cmds[3]);
			assertOpCodeEquals("8: invokespecial #45", cmds[4]);
			assertOpCodeEquals("11: astore_1", cmds[5]);
			assertOpCodeEquals("12: aload_1", cmds[6]);
			assertOpCodeEquals("13: invokevirtual #47", cmds[7]);
			assertOpCodeEquals("16: return", cmds[8]);
		}

	}

	private void assertOpCodeEquals(String expected, ByteCodeCommand cmd){

		String acctual = cmd.getOffset()+": "+cmd.getReadableCodeText();

		if(cmd instanceof OneOperandCmd){
			if(cmd instanceof BiPushCmd){
				acctual += " " + ((OneOperandCmd)cmd).getOperand();
			} else{
				acctual += " #" + ((OneOperandCmd)cmd).getOperand();
			}
		}
		if(cmd instanceof TwoOperandCmd){
			acctual += " #" + ((TwoOperandCmd)cmd).getIndex();
		}
		Assert.assertEquals(expected, acctual);
	}


}
