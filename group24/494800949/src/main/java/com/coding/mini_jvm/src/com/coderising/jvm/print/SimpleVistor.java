package com.coding.mini_jvm.src.com.coderising.jvm.print;

import com.coding.mini_jvm.src.com.coderising.jvm.constant.*;

import java.util.Formatter;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
public class SimpleVistor implements ConstantInfo.Visitor {
    private              Formatter formatter    = new Formatter(System.out);
    private              String    format       = " = %-20s %-20s %-100s\n";
    private static final String    HASH_KEY     = "#";
    private static final String    DOUBLE_SLASH = "// ";
    private static final String    DOT          = ".";
    private static final String    COLON        = ":";

    @Override
    public void visitClassInfo(ClassInfo info) {
        formatter.format(format, "Class",
                HASH_KEY + info.getUtf8Index(),
                DOUBLE_SLASH + info.getClassName());
    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)info.getConstantInfo(info.getNameAndTypeIndex());
        formatter.format(format, "Fieldref",
                HASH_KEY + info.getClassInfoIndex() + DOT + HASH_KEY + info.getNameAndTypeIndex(),
                DOUBLE_SLASH + info.getClassName() + DOT + nameAndTypeInfo.getName() + COLON + nameAndTypeInfo.getTypeInfo());
    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        formatter.format(format, "Methodref",
                HASH_KEY + info.getClassInfoIndex() + DOT + HASH_KEY + info.getNameAndTypeIndex(),
                DOUBLE_SLASH + info.getClassName() + DOT + info.getMethodName());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        formatter.format(format, "NameAndType",
                HASH_KEY + info.getIndex1() + COLON + HASH_KEY + info.getIndex2(),
                DOUBLE_SLASH + info.getName() + COLON + info.getTypeInfo());
    }

    @Override
    public void visitString(StringInfo info) {
        formatter.format(format, "String", HASH_KEY + info.getIndex(), DOUBLE_SLASH + info.toString());
    }

    @Override
    public void visistUTF8(UTF8Info info) {
        formatter.format(format, "Utf8", info.getValue(), "");
    }
}
