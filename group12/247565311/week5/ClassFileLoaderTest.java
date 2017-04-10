package week5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileLoaderTest {
	static String path1 = "F:\\code_language\\demo\\Homework\\bin\\week5\\";
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
	public void testClassFileLength() {
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "EmployeeV1";
		byte[] byteCodes = new byte[0];
		try {
			byteCodes = loader.readBinaryCode(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ע�⣺����ֽ������ܺ����JVM�汾�й�ϵ�� ����Կ�������õ��ൽ���ж��
		Assert.assertEquals(267, byteCodes.length);
	}
	
    @Test	
	public void testMagicNumber(){
    	ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path1);
		String className = "EmployeeV1";
		byte[] byteCodes = new byte[0];
		try {
			byteCodes = loader.readBinaryCode(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
