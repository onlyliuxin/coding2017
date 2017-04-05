package zavier.week04.coderising.jvm.test;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import zavier.week04.coderising.jvm.loader.ClassFileLoader;



public class ClassFileloaderTest {


    static String path1 = new File(".", "bin").getAbsolutePath();
    static String path2 = "C:\\temp";

    static String className = EmployeeV1.class.getName();

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testClassPath() {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        loader.addClassPath(path2);

        String clzPath = loader.getClassPath();

        Assert.assertEquals(path1 + ";" + path2, clzPath);

    }

    @Test
    public void testClassFileLength() throws ClassNotFoundException {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1076, byteCodes.length);

    }

    @Test(expected = ClassNotFoundException.class)
    public void testClassNotFindException() throws ClassNotFoundException {
        ClassFileLoader loader = new ClassFileLoader();
        loader.readBinaryCode(className);
    }


    @Test
    public void testMagicNumber() throws ClassNotFoundException {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[] {byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};


        String acctualValue = this.byteToHexString(codes);

        Assert.assertEquals("cafebabe", acctualValue);
    }



    private String byteToHexString(byte[] codes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < codes.length; i++) {
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            buffer.append(strHex);
        }
        return buffer.toString();
    }

}
