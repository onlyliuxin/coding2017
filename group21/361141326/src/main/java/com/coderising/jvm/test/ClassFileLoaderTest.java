package com.coderising.jvm.test;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.util.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassFileLoaderTest {


    private static final String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/test/EmployeeV1";

    static String path1 = "/Users/mortimer/tmp/jvm";
    static String path2 = "/Users/mortimer/tmp/jvm2";

    static ClassFile clzFile = null;

    static {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "com.coderising.jvm.test.EmployeeV1";

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

        String className = "com.coderising.jvm.test.EmployeeV1";

        byte[] byteCodes = loader.readBinaryCode(className);

        // 注意：这个字节数可能和你的JVM版本有关系， 你可以看看编译好的类到底有多大
        Assert.assertEquals(1156, byteCodes.length);

    }


    @Test
    public void testMagicNumber() {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "com.coderising.jvm.test.EmployeeV1";
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[]{byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};

        String actualValue = Util.byteToHexString(codes);

        Assert.assertEquals("cafebabe", actualValue);
    }

    /**
     * ----------------------------------------------------------------------
     */


    @Test
    public void testVersion() {

        Assert.assertEquals(0, clzFile.getMinorVersion());
        Assert.assertEquals(52, clzFile.getMajorVersion());

    }

    @Test
    public void testConstantPool() {
        ConstantPool pool = clzFile.getConstantPool();

        Assert.assertEquals(57, pool.getSize());

        {
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(11);
            Assert.assertEquals(48, clzInfo.getUtf8Index());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(48);
            Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
        }
        {
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(12);
            Assert.assertEquals(49, clzInfo.getUtf8Index());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(49);
            Assert.assertEquals("java/lang/Object", utf8Info.getValue());
        }
        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(13);
            Assert.assertEquals("name", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(14);
            Assert.assertEquals("Ljava/lang/String;", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(15);
            Assert.assertEquals("age", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(16);
            Assert.assertEquals("I", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(17);
            Assert.assertEquals("<init>", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(18);
            Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(19);
            Assert.assertEquals("Code", utf8Info.getValue());
        }

        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(1);
            Assert.assertEquals(12, methodRef.getClassInfoIndex());
            Assert.assertEquals(35, methodRef.getNameAndTypeIndex());
        }

        {
            NameAndTypeInfo nameAndType = (NameAndTypeInfo) pool.getConstantInfo(35);
            Assert.assertEquals(17, nameAndType.getIndex1());
            Assert.assertEquals(26, nameAndType.getIndex2());
        }
        //抽查几个吧
        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(6);
            Assert.assertEquals(41, methodRef.getClassInfoIndex());
            Assert.assertEquals(42, methodRef.getNameAndTypeIndex());
        }

        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(34);
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
