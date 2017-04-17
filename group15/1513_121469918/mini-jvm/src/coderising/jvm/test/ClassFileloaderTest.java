package coderising.jvm.test;


import java.util.List;

import org.junit.After;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import coderising.jvm.clz.ClassFile;
import coderising.jvm.clz.ClassIndex;
import coderising.jvm.constant.ClassInfo;
import coderising.jvm.constant.ConstantPool;
import coderising.jvm.constant.MethodRefInfo;
import coderising.jvm.constant.NameAndTypeInfo;
import coderising.jvm.constant.UTF8Info;
import coderising.jvm.field.Field;
import coderising.jvm.loader.ClassFileLoader;
import coderising.jvm.method.Method;


public class ClassFileloaderTest {	
	private static final String FULL_QUALIFIED_CLASS_NAME = "coderising/jvm/test/EmployeeV1";
	
	static String path1 = "D:\\develop\\GitHub\\coding2017\\group15\\1513_121469918\\mini-jvm\\bin";
	static String path2 = "C:\temp";
	
	static ClassFile clzFile = null;
	static {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "coderising.jvm.test.EmployeeV1";
		
		clzFile = loader.loadClass(className);
		clzFile.print();
	}
	
	
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
		
		String className = "coderising.jvm.test.EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1048, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "coderising.jvm.test.EmployeeV1";
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
		
		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(52, clzFile.getMajorVersion());
		
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
   

}
