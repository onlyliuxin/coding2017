package com.coderising.jvm.constant;

import com.coderising.jvm.loader.ByteCodeIterator;
import com.coderising.jvm.util.ByteUtil;

/**
 * Created by haibo on 2017/4/9.
 */
public class ConstantFactory {

    public static ConstantInfo build(ConstantPool constantPool, ByteCodeIterator iterator) {
        int type = ByteUtil.byteToInt(iterator.getNext(1));
        switch (type) {
        case ConstantInfo.CLASS_INFO:
            return buildClassInfo(constantPool, iterator);
        case ConstantInfo.FIELD_INFO:
            return buildFiledInfo(constantPool, iterator);
        case ConstantInfo.FLOAT_INFO:
            return buildFloatInfo(iterator);
        case ConstantInfo.METHOD_INFO:
            return buildMethodInfo(constantPool, iterator);
        case ConstantInfo.NAME_AND_TYPE_INFO:
            return buildNameAndTypeInfo(constantPool, iterator);
        case ConstantInfo.STRING_INFO:
            return buildStringInfo(constantPool, iterator);
        case ConstantInfo.UTF8_INFO:
            return buildUTF8Info(constantPool, iterator);
        default:
            return new NullConstantInfo();
        }
    }

    private static ConstantInfo buildClassInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        ClassInfo classInfo = new ClassInfo(constantPool);
        classInfo.setUtf8Index(ByteUtil.byteToInt(iterator.getNext(2)));
        return classInfo;
    }

    private static ConstantInfo buildFiledInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
        fieldRefInfo.setClassInfoIndex(ByteUtil.byteToInt(iterator.getNext(2)));
        fieldRefInfo.setNameAndTypeIndex(ByteUtil.byteToInt(iterator.getNext(2)));
        return fieldRefInfo;
    }

    private static ConstantInfo buildFloatInfo(ByteCodeIterator iterator) {
        return null;
    }

    private static ConstantInfo buildMethodInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
        methodRefInfo.setClassInfoIndex(ByteUtil.byteToInt(iterator.getNext(2)));
        methodRefInfo.setNameAndTypeIndex(ByteUtil.byteToInt(iterator.getNext(2)));
        return methodRefInfo;
    }

    private static ConstantInfo buildNameAndTypeInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
        nameAndTypeInfo.setIndex1(ByteUtil.byteToInt(iterator.getNext(2)));
        nameAndTypeInfo.setIndex2(ByteUtil.byteToInt(iterator.getNext(2)));
        return nameAndTypeInfo;
    }

    private static ConstantInfo buildStringInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        StringInfo stringInfo = new StringInfo(constantPool);
        stringInfo.setIndex(ByteUtil.byteToInt(iterator.getNext(2)));
        return stringInfo;
    }

    private static ConstantInfo buildUTF8Info(ConstantPool constantPool, ByteCodeIterator iterator) {
        UTF8Info utf8Info = new UTF8Info(constantPool);
        int length = ByteUtil.byteToInt(iterator.getNext(2));
        utf8Info.setLength(length);
        utf8Info.setValue(ByteUtil.byteToHexString(iterator.getNext(length)));
        return utf8Info;
    }

}
