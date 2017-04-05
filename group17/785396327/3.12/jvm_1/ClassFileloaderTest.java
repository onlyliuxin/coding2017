package jvm_1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by william on 2017/4/5.
 */
public class ClassFileloaderTest {
    static String path1 = "D:\\workspace\\IDEA\\homework\\coding2017\\group17\\785396327\\out\\production\\785396327";
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

        String className = "jvm_1.EmployeeV1";

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1020, byteCodes.length);

    }


    @Test
    public void testMagicNumber(){
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "jvm_1.EmployeeV1";
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
