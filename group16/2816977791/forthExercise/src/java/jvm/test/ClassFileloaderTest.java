package jvm.test;

import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.constant.*;
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

    private static final String FULL_QUALIFIED_CLASS_NAME = "jvm/test/EmployeeV1";

    static String path1 = "/Users/nvarchar/Documents/github/coding2017/group16/2816977791/forthExercise/target";
    static String path2 = "/Users";

    static ClassFile clzFile = null;

    static {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "jvm.test.EmployeeV1";

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


    /**
     * --------------------------JVM second work--------------------------------------------
     */


    @Test
    public void testVersion() {

        Assert.assertEquals(0, clzFile.getMinorVersion());
        Assert.assertEquals(52, clzFile.getMajorVersion());

    }

    @Test
    public void testConstantPool() {


        ConstantPool pool = clzFile.getConstantPool();

        Assert.assertEquals(53, pool.getSize());

        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(1);
            Assert.assertEquals(11, methodRef.getClassInfoIndex());
            Assert.assertEquals(36, methodRef.getNameAndTypeIndex());
        }


        {
            FieldRefInfo fieldRefInfo = (FieldRefInfo) pool.getConstantInfo(2);
            Assert.assertEquals(7, fieldRefInfo.getClassInfoIndex());
            Assert.assertEquals(37, fieldRefInfo.getNameAndTypeIndex());
        }

        {
            FieldRefInfo fieldRefInfo = (FieldRefInfo) pool.getConstantInfo(3);
            Assert.assertEquals(7, fieldRefInfo.getClassInfoIndex());
            Assert.assertEquals(38, fieldRefInfo.getNameAndTypeIndex());
        }

        {
            FieldRefInfo fieldRefInfo = (FieldRefInfo) pool.getConstantInfo(4);
            Assert.assertEquals(39, fieldRefInfo.getClassInfoIndex());
            Assert.assertEquals(40, fieldRefInfo.getNameAndTypeIndex());
        }

        {
            StringInfo stringInfo = (StringInfo) pool.getConstantInfo(5);
            Assert.assertEquals(41, stringInfo.getIndex());
        }

        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(6);
            Assert.assertEquals(42, methodRef.getClassInfoIndex());
            Assert.assertEquals(43, methodRef.getNameAndTypeIndex());
        }

        {
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(7);
            Assert.assertEquals(44, clzInfo.getUtf8Index());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(44);
            Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
        }
        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(12);
            Assert.assertEquals("name", utf8Info.getValue());
        }

        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(35);
            Assert.assertEquals("EmployeeV1.java", utf8Info.getValue());
        }
    }

    @Test
    public void testClassIndex() {

        ClassIndex clzIndex = clzFile.getClzIndex();
        ClassInfo thisClassInfo = (ClassInfo) clzFile.getConstantPool().getConstantInfo(clzIndex.getThisClassIndex());
        ClassInfo superClassInfo = (ClassInfo) clzFile.getConstantPool().getConstantInfo(clzIndex.getSuperClassIndex());


        Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, thisClassInfo.getClassName());
        Assert.assertEquals("java/lang/Object", superClassInfo.getClassName());
    }

}
