package jvm;

import jvm.classfile.attribute.item.impl.CodeAttr;
import jvm.classfile.ClassFile;
import jvm.classfile.ClassIndex;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.classfile.constant.item.impl.MethodRefInfo;
import jvm.classfile.constant.item.impl.NameAndTypeInfo;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.command.item.impl.BiPushCmd;
import jvm.command.item.ByteCodeCommand;
import jvm.command.item.OneOperandCmd;
import jvm.command.item.TwoOperandCmd;
import jvm.exception.ReadClassException;
import jvm.classfile.field.Field;
import jvm.classfile.method.Method;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
		Assert.assertEquals(981, byteCodes.length);
	}

    @Test	
	public void testMagicNumber() throws ReadClassException {
		byte[] byteCodes = loader.readBinaryCode(LOAD_CLASS_NAME);
		byte[] codes = new byte[] {byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};

		boolean check = loader.checkMagicNumber(codes);
		Assert.assertTrue(check);
	}

	@Test
	public void testVersion() {
		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(52, clzFile.getMajorVersion());
	}

	@Test
	public void testConstantPool() {

		ConstantPool pool = clzFile.getConstantPool();

		Assert.assertEquals(48, pool.getSize());

		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(6);
			Assert.assertEquals(41, clzInfo.getUtf8Index());

			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(41);
			Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
		}
		{
			ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(10);
			Assert.assertEquals(45, clzInfo.getUtf8Index());

			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(45);
			Assert.assertEquals("java/lang/Object", utf8Info.getValue());
		}
		{
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(11);
			Assert.assertEquals("name", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(12);
			Assert.assertEquals("Ljava/lang/String;", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(13);
			Assert.assertEquals("age", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(14);
			Assert.assertEquals("I", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(15);
			Assert.assertEquals("<init>", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(16);
			Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getValue());

			utf8Info = (UTF8Info) pool.getConstantInfo(17);
			Assert.assertEquals("Code", utf8Info.getValue());
		}

		{
			MethodRefInfo methodRef = (MethodRefInfo)pool.getConstantInfo(1);
			Assert.assertEquals(10, methodRef.getClassInfoIndex());
			Assert.assertEquals(35, methodRef.getNameAndTypeIndex());
		}

		{
			NameAndTypeInfo nameAndType = (NameAndTypeInfo) pool.getConstantInfo(35);
			Assert.assertEquals(15, nameAndType.getIndex1());
			Assert.assertEquals(27, nameAndType.getIndex2());
		}
		//抽查几个吧
		{
			MethodRefInfo methodRef = (MethodRefInfo)pool.getConstantInfo(9);
			Assert.assertEquals(6, methodRef.getClassInfoIndex());
			Assert.assertEquals(44, methodRef.getNameAndTypeIndex());
		}

		{
			UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(34);
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

	/**
	 * 下面是第三次JVM课应实现的测试用例
	 */
	@Test
	public void testReadFields() {

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
	public void testMethods() {

		List<Method> methods = clzFile.getMethods();
		ConstantPool pool = clzFile.getConstantPool();

		{
			Method m = methods.get(0);
			assertMethodEquals(pool,m,
					"<init>",
					"(Ljava/lang/String;I)V",
					"2ab700012a2bb500022a1cb50003b1");

		}
		{
			Method m = methods.get(1);
			assertMethodEquals(pool,m,
					"setName",
					"(Ljava/lang/String;)V",
					"2a2bb50002b1");

		}
		{
			Method m = methods.get(2);
			assertMethodEquals(pool,m,
					"setAge",
					"(I)V",
					"2a1bb50003b1");
		}
		{
			Method m = methods.get(3);
			assertMethodEquals(pool,m,
					"sayHello",
					"()V",
					"1204b80005b1");

		}
		{
			Method m = methods.get(4);
			assertMethodEquals(pool,m,
					"main",
					"([Ljava/lang/String;)V",
					"bb0006591207101db700084c2bb60009b1");
		}
	}

	private void assertMethodEquals(ConstantPool pool, Method m,
									String expectedName, String expectedDesc,String expectedCode) {
		String methodName = ((UTF8Info) pool.getConstantInfo(m.getNameIndex())).getValue();
		String methodDesc = ((UTF8Info) pool.getConstantInfo(m.getDescriptorIndex())).getValue();
		String code = ((CodeAttr) m.getAttributes().get(0)).getCode();
		Assert.assertEquals(expectedName, methodName);
		Assert.assertEquals(expectedDesc, methodDesc);
		Assert.assertEquals(expectedCode, code);
	}
	@Test
	public void testByteCodeCommand(){
		{
			Method initMethod = clzFile.getMethod("<init>",
					"(Ljava/lang/String;I)V");
			ByteCodeCommand [] cmds = initMethod.getCommands();

			assertOpCodeEquals("0: aload_0", cmds[0]);
			assertOpCodeEquals("1: invokespecial #1", cmds[1]);
			assertOpCodeEquals("4: aload_0", cmds[2]);
			assertOpCodeEquals("5: aload_1", cmds[3]);
			assertOpCodeEquals("6: putfield #2", cmds[4]);
			assertOpCodeEquals("9: aload_0", cmds[5]);
			assertOpCodeEquals("10: iload_2", cmds[6]);
			assertOpCodeEquals("11: putfield #3", cmds[7]);
			assertOpCodeEquals("14: return", cmds[8]);
		}

		{
			Method setNameMethod = clzFile.getMethod("setName",
					"(Ljava/lang/String;)V");
			ByteCodeCommand [] cmds = setNameMethod.getCommands();

			assertOpCodeEquals("0: aload_0", cmds[0]);
			assertOpCodeEquals("1: aload_1", cmds[1]);
			assertOpCodeEquals("2: putfield #2", cmds[2]);
			assertOpCodeEquals("5: return", cmds[3]);

		}

		{
			Method sayHelloMethod = clzFile.getMethod("sayHello",
					"()V");
			ByteCodeCommand [] cmds = sayHelloMethod.getCommands();

			assertOpCodeEquals("0: ldc #4", cmds[0]);
			assertOpCodeEquals("2: invokestatic #5", cmds[1]);
			assertOpCodeEquals("5: return", cmds[2]);

		}

		{
			Method mainMethod = clzFile.getMainMethod();

			ByteCodeCommand [] cmds = mainMethod.getCommands();

			assertOpCodeEquals("0: new #6", cmds[0]);
			assertOpCodeEquals("3: dup", cmds[1]);
			assertOpCodeEquals("4: ldc #7", cmds[2]);
			assertOpCodeEquals("6: bipush 29", cmds[3]);
			assertOpCodeEquals("8: invokespecial #8", cmds[4]);
			assertOpCodeEquals("11: astore_1", cmds[5]);
			assertOpCodeEquals("12: aload_1", cmds[6]);
			assertOpCodeEquals("13: invokevirtual #9", cmds[7]);
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
