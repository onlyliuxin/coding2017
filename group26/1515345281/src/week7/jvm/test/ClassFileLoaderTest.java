package week7.jvm.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import week7.jvm.attr.CodeAttr;
import week7.jvm.clz.AccessFlag;
import week7.jvm.clz.ClassFile;
import week7.jvm.clz.ClassIndex;
import week7.jvm.cmd.BiPushCmd;
import week7.jvm.cmd.ByteCodeCommand;
import week7.jvm.cmd.OneOperandCmd;
import week7.jvm.cmd.TwoOperandCmd;
import week7.jvm.constant.ClassInfo;
import week7.jvm.constant.ConstantPool;
import week7.jvm.constant.MethodRefInfo;
import week7.jvm.constant.NameAndTypeInfo;
import week7.jvm.constant.UTF8Info;
import week7.jvm.field.Field;
import week7.jvm.loader.ClassFileLoader;
import week7.jvm.loader.ClassFileParser;
import week7.jvm.method.Method;
import week7.jvm.util.Util;


public class ClassFileLoaderTest {

    
	private static final String FULL_QUALIFIED_CLASS_NAME = "week5/jvm/test/EmployeeV1";
	static String path1="E:\\JAVA\\liuxin\\coding2017\\group26\\1515345281\\bin";
    static String path2="C:\\temp";
    static String className = "week5.jvm.test.EmployeeV1";
	
	static ClassFileLoader loader=null;
	static ClassFileParser parser=null;
	static ClassFile clzFile=null;
	
	static{
		 loader=new ClassFileLoader();
		 parser=new ClassFileParser();
		 
		 loader.addClassPath(path1);
		 
		 clzFile=loader.loadClass(className);
	}
	
	@Test
	public void testClassPath(){
		ClassFileLoader loader=new ClassFileLoader();
		loader.addClassPath(path2);
		loader.addClassPath(path2);
		loader.addClassPath(path1);
		Assert.assertEquals(path2+";"+path1, loader.getClassPath());
	}
	
	@Test
	public void testClassFileLength() {		
		
		loader.addClassPath(path1);
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1038, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	
		loader.addClassPath(path1);
		byte[] byteCodes = loader.readBinaryCode(className);
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]};
		
		
		String acctualValue = Util.byteToHexString(codes);
		
		Assert.assertEquals("cafebabe", acctualValue);
	}  
    
	 /**
     * ----------------------------------------------------------------------
     */
    
    
    @Test
    public void testVersion(){    			
		
		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(51, clzFile.getMajorVersion());
		
    }
    
    @Test
    public void testConstantPool(){
    	

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
			Assert.assertEquals(9, nameAndType.getNameIndex());
			Assert.assertEquals(14, nameAndType.getTypeIndex());
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
    public void testAccessFlag(){
    	AccessFlag flag=clzFile.getAccessFlag();
    	Assert.assertEquals(true, flag.isPublicClass());
    	assertEquals(false,flag.isFinalClass());
    }
    
    
    @Test
    public void testClassIndex(){
    	
    	ClassIndex clzIndex = clzFile.getClassIndex();
    	ClassInfo thisClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getThisClassIndex());
    	ClassInfo superClassInfo = (ClassInfo)clzFile.getConstantPool().getConstantInfo(clzIndex.getSuperClassIndex());
    	
    	Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, thisClassInfo.getClassName());
    	Assert.assertEquals("java/lang/Object", superClassInfo.getClassName());
    	assertEquals(0, clzIndex.getInterfaceIndex());
    }

    @Test
    public void testReadFields(){
   
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
    	List<Method> methods=clzFile.getMethods();
    	  	     	
    	String[] str=new String[5];
    	String[] code=new String[5];
    	
    	for(int i=0;i<5;i++){
    		str[i]=methods.get(i).toString();
    		code[i]=((CodeAttr)methods.get(i).getAttrInfo()).getCode();
    	}
    	
    	assertArrayEquals(str, new String[]{
    			"<init>:(Ljava/lang/String;I)V",
    			"setName:(Ljava/lang/String;)V",
    			"setAge:(I)V",
    			"sayHello:()V",
    			"main:([Ljava/lang/String;)V"
    	});  
    	
    	assertArrayEquals(code, new String[]{
    			"2ab7000c2a2bb5000f2a1cb50011b1",
    			"2a2bb5000fb1",
    			"2a1bb50011b1",
    			"b2001c1222b60024b1",
    			"bb000159122b101db7002d4c2bb6002fb1"
    	});
    
    }
    
    @Test
    public void testByteCodeCommand(){
    	{
	    	Method initMethod = this.clzFile.getMethod("<init>", "(Ljava/lang/String;I)V");
	    	ByteCodeCommand[] cmds = ((CodeAttr)initMethod.getAttrInfo()).getCmds();
	    	
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
	    	ByteCodeCommand [] cmds = ((CodeAttr)setNameMethod.getAttrInfo()).getCmds();
	    	
	    	assertOpCodeEquals("0: aload_0", cmds[0]);
	    	assertOpCodeEquals("1: aload_1", cmds[1]);
	    	assertOpCodeEquals("2: putfield #15", cmds[2]);
	    	assertOpCodeEquals("5: return", cmds[3]);
	    	
    	}
    	
    	{
	    	Method sayHelloMethod = this.clzFile.getMethod("sayHello", "()V");
	    	ByteCodeCommand [] cmds = ((CodeAttr)sayHelloMethod.getAttrInfo()).getCmds();
	    	
	    	assertOpCodeEquals("0: getstatic #28", cmds[0]);
	    	assertOpCodeEquals("3: ldc #34", cmds[1]);
	    	assertOpCodeEquals("5: invokevirtual #36", cmds[2]);
	    	assertOpCodeEquals("8: return", cmds[3]);
	    	
    	}
    	
    	{
	    	Method mainMethod = this.clzFile.getMethod("main","([Ljava/lang/String;)V");
	    	
	    	ByteCodeCommand [] cmds = ((CodeAttr)mainMethod.getAttrInfo()).getCmds();
	    	
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