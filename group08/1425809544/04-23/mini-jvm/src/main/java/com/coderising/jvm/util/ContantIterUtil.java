package com.coderising.jvm.util;

import com.coderising.jvm.constant.*;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * Created by 14258 on 2017/4/9.
 */
public class ContantIterUtil {

    public static ConstantInfo parseType(ConstantPool constantPool, ByteCodeIterator iterator) {
        int type = BytesIterUtil.byteToInt(iterator.getNext(1));
        switch (type) {
            case ConstantInfo.CLASS_INFO:
                return parseClassInfo(constantPool, iterator);
            case ConstantInfo.FIELD_INFO:
                return parseFiledInfo(constantPool, iterator);
            case ConstantInfo.METHOD_INFO:
                return parseMethodInfo(constantPool, iterator);
            case ConstantInfo.STRING_INFO:
                return parseStringInfo(constantPool, iterator);
            case ConstantInfo.UTF8_INFO:
                return parseUTF8Info(constantPool, iterator);
            case ConstantInfo.NAME_AND_TYPE_INFO:
                return parseNameAndType(constantPool, iterator);
            case ConstantInfo.FLOAT_INFO:
                return paraseFloatInfo(constantPool, iterator);
            default:
                return new NullConstantInfo();

        }
    }

    private static ConstantInfo parseNameAndType(ConstantPool constantPool, ByteCodeIterator iterator) {
        NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
        nameAndTypeInfo.setIndex1(BytesIterUtil.byteToInt(iterator.getNext(2)));
        nameAndTypeInfo.setIndex2(BytesIterUtil.byteToInt(iterator.getNext(2)));
        return nameAndTypeInfo;
    }

    private static ConstantInfo parseUTF8Info(ConstantPool constantPool, ByteCodeIterator iterator) {
        UTF8Info utf8Info = new UTF8Info(constantPool);
        int length = BytesIterUtil.byteToInt(iterator.getNext(2));
        utf8Info.setLength(length);
        utf8Info.setValue(BytesIterUtil.byteToHexString(iterator.getNext(length)));
        return utf8Info;
    }

    private static ConstantInfo parseStringInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        StringInfo stringInfo = new StringInfo(constantPool);
        stringInfo.setIndex(BytesIterUtil.byteToInt(iterator.getNext(2)));
        return stringInfo;
    }

    private static ConstantInfo parseMethodInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
        methodRefInfo.setClassInfoIndex(BytesIterUtil.byteToInt(iterator.getNext(2)));
        methodRefInfo.setNameAndTypeIndex(BytesIterUtil.byteToInt(iterator.getNext(2)));
        return methodRefInfo;
    }

    private static ConstantInfo paraseFloatInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        return null;
    }

    private static ConstantInfo parseFiledInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
        fieldRefInfo.setClassInfoIndex(BytesIterUtil.byteToInt(iterator.getNext(2)));
        fieldRefInfo.setNameAndTypeIndex(BytesIterUtil.byteToInt(iterator.getNext(2)));
        return fieldRefInfo;
    }

    private static ConstantInfo parseClassInfo(ConstantPool constantPool, ByteCodeIterator iterator) {
        ClassInfo classInfo = new ClassInfo(constantPool);
        classInfo.setUtf8Index(BytesIterUtil.byteToInt(iterator.getNext(2)));
        return classInfo;
    }


}
