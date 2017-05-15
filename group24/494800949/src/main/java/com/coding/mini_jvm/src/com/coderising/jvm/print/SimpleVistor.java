package com.coding.mini_jvm.src.com.coderising.jvm.print;

import com.coding.mini_jvm.src.com.coderising.jvm.constant.*;

import java.util.Formatter;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
public class SimpleVistor implements ConstantInfo.Visitor {
    private              Formatter formatter    = new Formatter(System.out);
    private              String    format       = " = %-20s %-20s %-100s\n";

    @Override
    public void visitClassInfo(ClassInfo info) {
        formatter.format(format, "Class",
                "#" + info.getUtf8Index(),
                "// " + info.getClassName());
    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)info.getConstantInfo(info.getNameAndTypeIndex());
        formatter.format(format, "Fieldref",
                "#" + info.getClassInfoIndex() + "." + "#" + info.getNameAndTypeIndex(),
                "// " + info.getClassName() + "." + nameAndTypeInfo.getName() + ":" + nameAndTypeInfo.getTypeInfo());
    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        formatter.format(format, "Methodref",
                "#" + info.getClassInfoIndex() + "." + "#" + info.getNameAndTypeIndex(),
                "// " + info.getClassName() + "." + info.getMethodName());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        formatter.format(format, "NameAndType",
                "#" + info.getIndex1() + ":" + "#" + info.getIndex2(),
                "// " + info.getName() + ":" + info.getTypeInfo());
    }

    @Override
    public void visitString(StringInfo info) {
        formatter.format(format, "String", "#" + info.getIndex(), "// " + info.toString());
    }

    @Override
    public void visistUTF8(UTF8Info info) {
        formatter.format(format, "Utf8", info.getValue(), "");
    }

}
