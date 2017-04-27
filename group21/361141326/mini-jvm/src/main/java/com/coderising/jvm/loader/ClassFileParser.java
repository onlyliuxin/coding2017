package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {

    /**
     * @param codes 对class进行解析,已经验证前4个字节的二进制数组
     * @return ClassFile实例
     */
    public ClassFile parse(byte[] codes) {
        ClassFile classFile = new ClassFile();

        ByteCodeIterator iterator = new ByteCodeIterator(codes);
        iterator.getBytes(4);

        // 版本号
        classFile.setMinorVersion(iterator.nextU2ToInt());
        classFile.setMajorVersion(iterator.nextU2ToInt());

        // 常量池
        classFile.setConstPool(parseConstantPool(iterator));
        // 访问标识
        classFile.setAccessFlag(parseAccessFlag(iterator));
        // 类信息
        classFile.setClassIndex(parseClassIndex(iterator));
        // 解析接口
        parseInterfaces(iterator);
        // 解析字段
        parseFields(classFile, iterator);
        // 解析方法
        parseMethods(classFile, iterator);

        return classFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
        return new AccessFlag(iterator.nextU2ToInt());
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iterator) {
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(iterator.nextU2ToInt());
        classIndex.setSuperClassIndex(iterator.nextU2ToInt());

        return classIndex;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
        ConstantPool constantPool = new ConstantPool();
        int poolSize = iterator.nextU2ToInt();
        for (int i = 1; i < poolSize; i++) {
            ConstantInfo constantInfo = parseConstantInfo(iterator, constantPool);
            constantPool.addConstantInfo(constantInfo);
        }

        return constantPool;
    }

    private ConstantInfo parseConstantInfo(ByteCodeIterator iterator, ConstantPool constantPool) {
        int tag = iterator.nextU1toInt();
        ConstantInfo constantInfo;
        switch (tag) {
            case ConstantInfo.UTF8_INFO:
                int length = iterator.nextU2ToInt();
                String value = new String(iterator.getBytes(length));

                UTF8Info utf8Info = new UTF8Info(constantPool);
                utf8Info.setLength(length);
                utf8Info.setValue(value);

                constantInfo = utf8Info;
                break;
            case ConstantInfo.FLOAT_INFO:
                constantInfo = null;
                break;
            case ConstantInfo.CLASS_INFO:
                int index = iterator.nextU2ToInt();

                ClassInfo classInfo = new ClassInfo(constantPool);
                classInfo.setUtf8Index(index);
                constantInfo = classInfo;
                break;
            case ConstantInfo.STRING_INFO:
                int nameIndex = iterator.nextU2ToInt();

                StringInfo stringInfo = new StringInfo(constantPool);
                stringInfo.setIndex(nameIndex);

                constantInfo = stringInfo;
                break;
            case ConstantInfo.FIELD_INFO:
                int classIndex = iterator.nextU2ToInt();
                int nameAndTypeIndex = iterator.nextU2ToInt();

                FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                fieldRefInfo.setClassInfoIndex(classIndex);
                fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);

                constantInfo = fieldRefInfo;
                break;
            case ConstantInfo.METHOD_INFO:
                MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);

                methodRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
                methodRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());

                constantInfo = methodRefInfo;
                break;
            case ConstantInfo.NAME_AND_TYPE_INFO:
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);

                nameAndTypeInfo.setIndex1(iterator.nextU2ToInt());
                nameAndTypeInfo.setIndex2(iterator.nextU2ToInt());

                constantInfo = nameAndTypeInfo;
                break;
            default:
                return new NullConstantInfo();
        }

        return constantInfo;
    }

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();

        System.out.println("interfaceCount:" + interfaceCount);

        // TODO : 如果实现了interface, 这里需要解析
    }

    private void parseFields(ClassFile clzFile, ByteCodeIterator iterator) {
        int fieldCount = iterator.nextU2ToInt();
        ConstantPool constantPool = clzFile.getConstantPool();
        for (int i = 0; i < fieldCount; i++) {
            clzFile.addField(Field.parse(constantPool, iterator));
        }
    }

    private void parseMethods(ClassFile clzFile, ByteCodeIterator iterator) {
        int methodCount = iterator.nextU2ToInt();

        for (int i = 0; i < methodCount; i++) {
            clzFile.addMethod(Method.parse(clzFile, iterator));
        }
    }
}
