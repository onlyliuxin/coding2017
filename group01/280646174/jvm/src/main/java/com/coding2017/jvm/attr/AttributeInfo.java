package com.coding2017.jvm.attr;

import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.loader.ByteCodeIterator;

public abstract class AttributeInfo {
    public static final String CODE = "Code";
    public static final String CONST_VALUE = "ConstantValue";
    public static final String EXCEPTIONS = "Exceptions";
    public static final String LINE_NUM_TABLE = "LineNumberTable";
    public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
    public static final String STACK_MAP_TABLE = "StackMapTable";
    int attrNameIndex;
    int attrLen;

    public AttributeInfo(int attrNameIndex, int attrLen) {

        this.attrNameIndex = attrNameIndex;
        this.attrLen = attrLen;
    }

    public static AttributeInfo parse(ClassFile clzFile, ByteCodeIterator iter) {
        int nameIndex = iter.nextU2ToInt();
        String name = clzFile.getConstantPool().getUTF8String(nameIndex);
        int length = iter.nextU4ToInt();
        if (AttributeInfo.CODE.equals(name)) {
            return CodeAttr.parse(clzFile, iter, nameIndex, length);
        } else if (AttributeInfo.LINE_NUM_TABLE.equals(name)) {
            return LineNumberTable.parse(clzFile, iter, nameIndex, length);
        } else if (AttributeInfo.LOCAL_VAR_TABLE.equals(name)) {
            return LocalVariableTable.parse(clzFile, iter, nameIndex, length);
        } else if (AttributeInfo.STACK_MAP_TABLE.equals(name)) {
            return StackMapTable.parse(clzFile, iter, nameIndex, length);
        } else {
            throw new RuntimeException("not support attribute " + name);
        }
    }

}
