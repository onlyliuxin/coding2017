package com.github.miniyk2012.coding2017.coderising.jvm.loader;

import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantPool;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.UTF8Info;
import com.github.miniyk2012.coding2017.coderising.jvm.test.ClassFileloaderTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 9/4/2017.
 */
public class ClassFileParserTest {
    private static ClassFileParser parser;
    private static byte[] byteCodes;

    @BeforeClass
    public static void setUp() {
        String path = ClassFileloaderTest.class.getClassLoader().getResource("jvm").getPath();
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path);
        String className = "com.github.miniyk2012.coding2017.jvm.test.EmployeeV1";
        byteCodes = loader.readBinaryCode(className);
        parser = new ClassFileParser();
    }

    @Test
    public void parse() throws Exception {

    }

    @Test
    public void parseAccessFlag() throws Exception {

    }

    @Test
    public void parseClassInfex() throws Exception {

    }

    @Test
    public void parseConstantPool() throws Exception {
        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(byteCodes);
        byteCodeIterator.skip(8);  // skip magic,minor_version,major_version
        ConstantPool constantPool = parser.parseConstantPool(byteCodeIterator);
        UTF8Info utf8Info = (UTF8Info)constantPool.getConstantInfo(5);
        assertEquals("name", utf8Info.getValue());
    }

}