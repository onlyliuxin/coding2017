package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;
import com.coderising.jvm.util.Util;

public class ClassFileParser {

    /**
     * @param codes 对class进行解析,已经验证前4个字节的二进制数组
     * @return ClassFile实例
     */
    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        iterator.next(4);
        // 版本号
        classFile.setMinorVersion(Util.byteToInt(iterator.next(2)));
        classFile.setMajorVersion(Util.byteToInt(iterator.next(2)));

        // 常量池
        classFile.setConstPool(parseConstantPool(iterator));
        // 访问标识
        classFile.setAccessFlag(parseAccessFlag(iterator));
        // 类信息
        classFile.setClassIndex(parseClassIndex(iterator));

        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        return new AccessFlag(Util.byteToInt(iterator.next(2)));
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iterator) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(Util.byteToInt(iterator.next(2)));
        classIndex.setSuperClassIndex(Util.byteToInt(iterator.next(2)));

        return classIndex;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
        ConstantPool constantPool = new ConstantPool();
        int poolSize = Util.byteToInt(iterator.next(2));
        for (int i = 1; i < poolSize; i++) {
            ConstantInfo constantInfo = parseConstantInfo(iterator, constantPool);
            constantPool.addConstantInfo(constantInfo);
        }

        return constantPool;
    }

    private ConstantInfo parseConstantInfo(ByteCodeIterator iterator, ConstantPool constantPool) {
        int tag = iterator.next();
        ConstantInfo constantInfo;
        switch (tag) {
            case ConstantInfo.UTF8_INFO:
                int length = Util.byteToInt(iterator.next(2));
                String value = new String(iterator.next(length));

                UTF8Info utf8Info = new UTF8Info(constantPool);
                utf8Info.setLength(length);
                utf8Info.setValue(value);

                constantInfo = utf8Info;
                break;
            case ConstantInfo.FLOAT_INFO:
                constantInfo = null;
                break;
            case ConstantInfo.CLASS_INFO:
                int index = Util.byteToInt(iterator.next(2));

                ClassInfo classInfo = new ClassInfo(constantPool);
                classInfo.setUtf8Index(index);
                constantInfo = classInfo;
                break;
            case ConstantInfo.STRING_INFO:
                int nameIndex = Util.byteToInt(iterator.next(2));

                StringInfo stringInfo = new StringInfo(constantPool);
                stringInfo.setIndex(nameIndex);

                constantInfo = stringInfo;
                break;
            case ConstantInfo.FIELD_INFO:
                int classIndex = Util.byteToInt(iterator.next(2));
                int nameAndTypeIndex = Util.byteToInt(iterator.next(2));

                FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                fieldRefInfo.setClassInfoIndex(classIndex);
                fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);

                constantInfo = fieldRefInfo;
                break;
            case ConstantInfo.METHOD_INFO:
                MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);

                methodRefInfo.setClassInfoIndex(Util.byteToInt(iterator.next(2)));
                methodRefInfo.setNameAndTypeIndex(Util.byteToInt(iterator.next(2)));

                constantInfo = methodRefInfo;
                break;
            case ConstantInfo.NAME_AND_TYPE_INFO:
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);

                nameAndTypeInfo.setIndex1(Util.byteToInt(iterator.next(2)));
                nameAndTypeInfo.setIndex2(Util.byteToInt(iterator.next(2)));

                constantInfo = nameAndTypeInfo;
                break;
            default:
                return new NullConstantInfo();
        }

        return constantInfo;
    }

}
