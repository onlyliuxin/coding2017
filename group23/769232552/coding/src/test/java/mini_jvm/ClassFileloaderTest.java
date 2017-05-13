package mini_jvm;

import mini_jvm.loader.ClassFileLoader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileloaderTest {


	static String path1 = "D:\\worksapce\\gitRepo\\java_coding2017\\coding2017\\group23\\769232552\\coding\\target\\classes";
	static String path2 = "D:\\worksapce\\gitRepo\\java_coding2017\\coding2017\\group23\\769232552\\coding\\target\\test-classes";


	ClassFileLoader loader = new ClassFileLoader();

	@Before
	public void setUp() throws Exception {
		loader.addClassPath(path1);
		loader.addClassPath(path2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClassPath(){
		String clzPath = loader.getClassPath();
		Assert.assertEquals(path1+";"+path2,clzPath);
	}

	@Test
	public void testClassFileLength() {
		String className = "mini_jvm.test.EmployeeV1";
		byte[] byteCodes = loader.readBinaryCode(className);
		Assert.assertEquals(1026, byteCodes.length);

	}


    @Test
	public void testMagicNumber(){
		String className = "mini_jvm.test.EmployeeV1";
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

}
