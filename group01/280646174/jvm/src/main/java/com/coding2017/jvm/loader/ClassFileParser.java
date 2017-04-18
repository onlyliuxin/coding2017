package com.coding2017.jvm.loader;

import com.coding2017.jvm.clz.AccessFlag;
import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.clz.ClassIndex;
import com.coding2017.jvm.constant.*;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        boolean check = checkMagicNumber(iterator);
        if (!check) {
            System.out.println("不是标准class文件, magic number不正确");
            return null;
        }

        // 版本号
        classFile.setMinorVersion(iterator.nextU2ToInt());
        classFile.setMajorVersion(iterator.nextU2ToInt());

        // 常量池
        classFile.setConstPool(parseConstantPool(iterator));

        // 访问标志
        classFile.setAccessFlag(parseAccessFlag(iterator));

        // this class and super class
        classFile.setClassIndex(parseClassInfex(iterator));

        return classFile;
    }

    private boolean checkMagicNumber(ByteCodeIterator iterator) {
        String magicNumber = iterator.nextU4ToString();
        return "cafebabe".equals(magicNumber);
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
        return accessFlag;
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iter.nextU2ToInt());
        classIndex.setSuperClassIndex(iter.nextU2ToInt());
        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        ConstantPool constantPool = new ConstantPool();
        int constantCount = iter.nextU2ToInt();
        constantPool.addConstantInfo(new NullConstantInfo());
        for (int i = 1; i < constantCount; i++) {
            constantPool.addConstantInfo(parseConstantInfo(constantPool, iter));
        }
        return constantPool;
    }

    private ConstantInfo parseConstantInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        int tag = iterator.nextU1ToInt();
        if (tag == ConstantInfo.UTF8_INFO) {
            UTF8Info utf8Info = new UTF8Info(constantPool);
            utf8Info.setLength(iterator.nextU2ToInt());
            utf8Info.setValue(new String(iterator.nextByteN(utf8Info.getLength())));
            return utf8Info;
        } else if (tag == ConstantInfo.CLASS_INFO) {
            ClassInfo classInfo = new ClassInfo(constantPool);
            classInfo.setUtf8Index(iterator.nextU2ToInt());
            return classInfo;
        } else if (tag == ConstantInfo.STRING_INFO) {
            StringInfo stringInfo = new StringInfo(constantPool);
            stringInfo.setIndex(iterator.nextU2ToInt());
            return stringInfo;
        } else if (tag == ConstantInfo.FIELD_INFO) {
            FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
            fieldRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
            fieldRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
            return fieldRefInfo;
        } else if (tag == ConstantInfo.METHOD_INFO) {
            MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
            methodRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
            methodRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
            return methodRefInfo;
        } else if (tag == ConstantInfo.NAME_AND_TYPE_INFO) {
            NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
            nameAndTypeInfo.setIndex1(iterator.nextU2ToInt());
            nameAndTypeInfo.setIndex2(iterator.nextU2ToInt());
            return nameAndTypeInfo;
        } else {
            throw new RuntimeException("not support tag " + tag);
        }
    }

}
