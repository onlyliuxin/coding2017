package com.coderising.jvm.loader;

import java.beans.MethodDescriptor;
import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import org.junit.Assert;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {

        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
        String magicNum = byteCodeIterator.nextU4toHex();
        System.out.println(magicNum);
        Assert.assertEquals("cafebabe", magicNum);
        // 设置版本号
        int minorVersion = byteCodeIterator.nextU2toInt();
        int majorVersion = byteCodeIterator.nextU2toInt();

        ClassFile classFile = new ClassFile();
        classFile.setMajorVersion(minorVersion);
        classFile.setMajorVersion(majorVersion);
        classFile.setConstPool(parseConstantPool(byteCodeIterator));

        // 设置访问标记
        classFile.setAccessFlag(parseAccessFlag(byteCodeIterator));
        classFile.setClassIndex(parseClassIndex(byteCodeIterator));
        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        return new AccessFlag(iter.nextU2toInt());
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {

        int thisClass = iter.nextU2toInt();
        int superClass = iter.nextU2toInt();
        ClassIndex classIndex = new ClassIndex();
        classIndex.setSuperClassIndex(superClass);
        classIndex.setThisClassIndex(thisClass);
        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {

        // 常量池长度
        int constantPoolCount = iter.nextU2toInt();
        System.out.println("常量池长度: " + constantPoolCount);

        // 常量池
        ConstantPool constantPool = new ConstantPool();
        constantPool.addConstantInfo(new NullConstantInfo());

        for (int i = 1; i < constantPoolCount; i++) {

            int tag = iter.nextU1toInt();
            switch (tag) {
                case 7:
                    int utf8Index = iter.nextU2toInt();
                    ClassInfo classInfo = new ClassInfo(constantPool);
                    classInfo.setUtf8Index(utf8Index);
                    constantPool.addConstantInfo(classInfo);
                    break;
                case 1:
                    // UTF-8 String
                    int len = iter.nextU2toInt();
                    byte[] data = iter.getBytes(len);
                    String value = null;
                    try {
                        value = new String(data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    UTF8Info utf8Info = new UTF8Info(constantPool);
                    utf8Info.setLength(len);
                    utf8Info.setValue(value);
                    constantPool.addConstantInfo(utf8Info);
                    break;
                case 8:
                    StringInfo stringInfo = new StringInfo(constantPool);
                    stringInfo.setIndex(iter.nextU2toInt());
                    constantPool.addConstantInfo(stringInfo);
                    break;
                case 9:
                    FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                    fieldRefInfo.setClassInfoIndex(iter.nextU2toInt());
                    fieldRefInfo.setNameAndTypeIndex(iter.nextU2toInt());
                    constantPool.addConstantInfo(fieldRefInfo);
                    break;
                case 10:
                    MethodRefInfo methodInfo = new MethodRefInfo(constantPool);
                    methodInfo.setClassInfoIndex(iter.nextU2toInt());
                    methodInfo.setNameAndTypeIndex(iter.nextU2toInt());
                    constantPool.addConstantInfo(methodInfo);
                    break;
                case 12:
                    NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                    nameAndTypeInfo.setIndex1(iter.nextU2toInt());
                    nameAndTypeInfo.setIndex2(iter.nextU2toInt());
                    constantPool.addConstantInfo(nameAndTypeInfo);
                    break;
                default:
                    throw new RuntimeException("常量池标记: " + tag + "还未实现!");
            }
        }

        return constantPool;
    }


}
