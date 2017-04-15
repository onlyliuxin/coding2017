package me.lzb.jvm;

import me.lzb.common.utils.ByteUtils;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.clz.ClassIndex;
import me.lzb.jvm.constant.*;
import me.lzb.jvm.field.Field;
import me.lzb.jvm.loader.ClassFileLoader;
import me.lzb.jvm.method.Method;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class ClassFileloaderTest {


    static String path1 = EmployeeV1.class.getResource("/").getPath();
//    static String path1 = "D:\\code\\learning\\coding2017\\group24\\1148285693\\learning2017\\mini-jvm\\target\\test-classes\\";
    static String path2 = "C:\\temp";

    static String className = "me.lzb.jvm.EmployeeV1";

    private static final String FULL_QUALIFIED_CLASS_NAME = "me/lzb/jvm/EmployeeV1";
//    private static final String FULL_QUALIFIED_CLASS_NAME = "com/coderising/jvm/test/EmployeeV1";


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testPath() {

        String s = EmployeeV1.class.getResource("/").getPath();
        String s2 = EmployeeV1.class.getResource("").getPath();
        System.out.println(s);
        System.out.println(s2);

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
    public void testClassFileLength() throws Exception {

        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);


        byte[] byteCodes = loader.readBinaryCode(className);

        Assert.assertEquals(1030, byteCodes.length);

    }


    @Test
    public void testMagicNumber() throws Exception {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        byte[] byteCodes = loader.readBinaryCode(className);
        byte[] codes = new byte[]{byteCodes[0], byteCodes[1], byteCodes[2], byteCodes[3]};

        String acctualValue = ByteUtils.byteToHexString(codes);

        Assert.assertEquals("cafebabe", acctualValue);
    }


    static ClassFile clzFile = null;

    static {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);

        try {
            clzFile = loader.loadClass(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clzFile.print();
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
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(7);
            Assert.assertEquals(44, clzInfo.getUtf8Index());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(44);
            Assert.assertEquals(FULL_QUALIFIED_CLASS_NAME, utf8Info.getValue());
        }
        {
            ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(11);
            Assert.assertEquals(48, clzInfo.getUtf8Index());

            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(48);
            Assert.assertEquals("java/lang/Object", utf8Info.getValue());
        }
        {
            UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(12);
            Assert.assertEquals("name", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(13);
            Assert.assertEquals("Ljava/lang/String;", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(14);
            Assert.assertEquals("age", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(15);
            Assert.assertEquals("I", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(16);
            Assert.assertEquals("<init>", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(17);
            Assert.assertEquals("(Ljava/lang/String;I)V", utf8Info.getValue());

            utf8Info = (UTF8Info) pool.getConstantInfo(18);
            Assert.assertEquals("Code", utf8Info.getValue());
        }

        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(1);
            Assert.assertEquals(11, methodRef.getClassInfoIndex());
            Assert.assertEquals(36, methodRef.getNameAndTypeIndex());
        }

        {
            NameAndTypeInfo nameAndType = (NameAndTypeInfo) pool.getConstantInfo(36);
            Assert.assertEquals(16, nameAndType.getIndex1());
            Assert.assertEquals(28, nameAndType.getIndex2());
        }
        //抽查几个吧
        {
            MethodRefInfo methodRef = (MethodRefInfo) pool.getConstantInfo(10);
            Assert.assertEquals(7, methodRef.getClassInfoIndex());
            Assert.assertEquals(47, methodRef.getNameAndTypeIndex());
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

    /**
     * 下面是第三次JVM课应实现的测试用例
     */
    @Test
    public void testReadFields() {

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
    public void testMethods() {

        List<Method> methods = clzFile.getMethods();
        ConstantPool pool = clzFile.getConstantPool();

        {
            Method m = methods.get(0);
            assertMethodEquals(pool, m,
                "<init>",
                "(Ljava/lang/String;I)V",
                "2ab700012a2bb500022a1cb50003b1");

        }
        {
            Method m = methods.get(1);
            assertMethodEquals(pool, m,
                "setName",
                "(Ljava/lang/String;)V",
                "2a2bb50002b1");

        }
        {
            Method m = methods.get(2);
            assertMethodEquals(pool, m,
                "setAge",
                "(I)V",
                "2a1bb50003b1");
        }
        {
            Method m = methods.get(3);
            assertMethodEquals(pool, m,
                "sayHello",
                "()V",
                "b200041205b60006b1");

        }
        {
            Method m = methods.get(4);
            assertMethodEquals(pool, m,
                "main",
                "([Ljava/lang/String;)V",
                "bb0007591208101db700094c2bb6000ab1");
        }
    }

    private void assertMethodEquals(ConstantPool pool, Method m, String expectedName, String expectedDesc, String expectedCode) {
        String methodName = pool.getUTF8String(m.getNameIndex());
        String methodDesc = pool.getUTF8String(m.getDescriptorIndex());
        String code = m.getCodeAttr().getCode();
        Assert.assertEquals(expectedName, methodName);
        Assert.assertEquals(expectedDesc, methodDesc);
        Assert.assertEquals(expectedCode, code);
    }

}
