package jvm.loader;

import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.constant.*;
import jvm.util.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiaxun
 */
public class TestClassFileLoader {

    private static final String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/test/EmployeeV1";

    static String path1 = "/Users/jiaxun/OpenSource/Algorithm/out/production/Algorithm";
    static String path2 = "/Users/jiaxun/temp";

    static ClassFile clzFile = null;

    static {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "jvm.loader.EmployeeV1";

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

        String className = "jvm.loader.EmployeeV1";

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1056, byteCodes.length);
    }


    @Test
    public void testMagicNumber() {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "jvm.loader.EmployeeV1";
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[]{byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};


        String actualValue = Util.byteToHexString(codes);

        Assert.assertEquals("cafebabe", actualValue);
    }

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
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(1);
            Assert.assertEquals(2, clzInfo.getNameIndex());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(2);
            Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getBytes());
        }
        {
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(3);
            Assert.assertEquals(4, clzInfo.getNameIndex());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(4);
            Assert.assertEquals("java/lang/Object", utf8Info.getBytes());
        }
        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(5);
            Assert.assertEquals("name", utf8Info.getBytes());

            utf8Info = (UTF8Info) pool.getConstantInfo(6);
            Assert.assertEquals("Ljava/lang/String;", utf8Info.getBytes());

            utf8Info = (UTF8Info) pool.getConstantInfo(7);
            Assert.assertEquals("age", utf8Info.getBytes());

            utf8Info = (UTF8Info) pool.getConstantInfo(8);
            Assert.assertEquals("I", utf8Info.getBytes());

            utf8Info = (UTF8Info) pool.getConstantInfo(9);
            Assert.assertEquals("<init>", utf8Info.getBytes());

            utf8Info = (UTF8Info) pool.getConstantInfo(10);
            Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getBytes());

            utf8Info = (UTF8Info) pool.getConstantInfo(11);
            Assert.assertEquals("Code", utf8Info.getBytes());
        }

        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(12);
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
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(45);
            Assert.assertEquals(1, methodRef.getClassInfoIndex());
            Assert.assertEquals(46, methodRef.getNameAndTypeIndex());
        }

        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(53);
            Assert.assertEquals("EmployeeV1.java", utf8Info.getBytes());
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
