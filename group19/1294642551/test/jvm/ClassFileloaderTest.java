package jvm;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.constant.ClassInfo;
import jvm.constant.ConstantPool;
import jvm.constant.MethodRefInfo;
import jvm.constant.NameAndTypeInfo;
import jvm.constant.UTF8Info;
import jvm.loader.ClassFileLoader;



public class ClassFileloaderTest {

	
	private static final String FULL_QUALIFIED_CLASS_NAME = "jvm/EmployeeV1";
	
	static String path1 = "D:\\Git Files\\coding2017\\group19\\1294642551\\bin";
	static String path2 = "D:\\TempFiles";
	
	static ClassFile clzFile = null;
	static {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "jvm.EmployeeV1";
		
		clzFile = loader.loadClass(className);
		
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
		
		String className = "jvm.EmployeeV1";
		
		byte[] byteCodes = loader.readBinaryCode(className);
		
		// 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
		Assert.assertEquals(1016, byteCodes.length);
		
	}
	
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "jvm.EmployeeV1";
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
   	
   	//----------------常量池测试-----------------------------------------
    @Test
    public void testVersion(){    			
		
		Assert.assertEquals(0, clzFile.getMinorVersion());
		Assert.assertEquals(50, clzFile.getMajorVersion());
		
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

 
}
