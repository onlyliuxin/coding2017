package jvm.test;

import jvm.loader.ClassFileLoader;
import jvm.util.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nvarchar
 *         date 2017/4/24
 */
public class ClassFileloaderTest {

    static String path1 = "/Users/nvarchar/Documents/github/coding2017/group16/2816977791/forthExercise/target";
    static String path2 = "/Users";


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClassPath() {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        loader.addClassPath(path2);

        String clzPath = loader.getClassPath();

        Assert.assertEquals(path1 + ";" + path2, clzPath);

    }

    @Test
    public void testClassFileLength() {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);

        String className = "jvm.test.EmployeeV1";

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1026, byteCodes.length);

    }


    @Test
    public void testMagicNumber() {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "jvm.test.EmployeeV1";
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[]{byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};


        String acctualValue = Util.byteToHexString(codes);

        Assert.assertEquals("cafebabe", acctualValue);
    }

}
