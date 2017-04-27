package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

import java.text.MessageFormat;
import java.util.Formatter;

public class ConstantPoolPrinter {

    ConstantPool pool;

    ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
    }

    private Formatter formatter = new Formatter(System.out);


    public void print() {

        System.out.println("Constant Pool:");
        for (int i = 1; i < pool.getConstantInfos().size(); i++) {
            ConstantInfo constantInfo = pool.getConstantInfos().get(i);
            NameAndTypeInfo nt = null;
            String value1;
            String value2;
            switch (constantInfo.getType()) {
                case ConstantInfo.CLASS_INFO:
                    ClassInfo classInfo = (ClassInfo) constantInfo;
                    UTF8Info utf8Info = (UTF8Info) classInfo.getConstantInfo(classInfo.getUtf8Index());
                    formatter.format("%5s %s %-20s %-10s %5s %s\n",
                            "#" + i,
                            "=",
                            "Class",
                            "#" + classInfo.getUtf8Index(),
                            "//",
                            utf8Info.getValue());
                    break;
                case ConstantInfo.UTF8_INFO:
                    formatter.format("%5s %s %-20s %-20s\n", "#" + i, "=", "Utf8", ((UTF8Info) constantInfo).getValue());
                    break;
                case ConstantInfo.METHOD_INFO:
                    MethodRefInfo methodRefInfo = (MethodRefInfo) constantInfo;
                    int indexOfMethodRed = ((ClassInfo) methodRefInfo.getConstantInfo(methodRefInfo.getClassInfoIndex())).getUtf8Index();
                    nt = (NameAndTypeInfo) pool.getConstantInfo(methodRefInfo.getNameAndTypeIndex());
                    value1 = ((UTF8Info) pool.getConstantInfo(nt.getIndex1())).getValue();
                    value2 = ((UTF8Info) pool.getConstantInfo(nt.getIndex2())).getValue();
                    formatter.format("%5s %s %-20s %-10s %5s %s\n",
                            "#" + i,
                            "=",
                            "Methodref",
                            "#" + methodRefInfo.getClassInfoIndex() + "." + "#" + methodRefInfo.getNameAndTypeIndex(),
                            "//",
                            ((UTF8Info) pool.getConstantInfo(indexOfMethodRed)).getValue() + "." + value1 + ":" + value2);
                    break;
                case ConstantInfo.NAME_AND_TYPE_INFO:
                    NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) constantInfo;
                    value1 = ((UTF8Info) pool.getConstantInfo(nameAndTypeInfo.getIndex1())).getValue();
                    value2 = ((UTF8Info) pool.getConstantInfo(nameAndTypeInfo.getIndex2())).getValue();
                    formatter.format("%5s %s %-20s %-10s %5s %s\n",
                            "#" + i,
                            "=",
                            "NameAndType",
                            "#" + nameAndTypeInfo.getIndex1() + "." + "#" + nameAndTypeInfo.getIndex2(),
                            "//",
                            value1 + ":" + value2);
                    break;
                case ConstantInfo.FIELD_INFO:
                    FieldRefInfo fieldRefInfo = (FieldRefInfo) constantInfo;
                    ClassInfo ci = (ClassInfo) pool.getConstantInfo(fieldRefInfo.getClassInfoIndex());
                    nt = (NameAndTypeInfo) pool.getConstantInfo(fieldRefInfo.getNameAndTypeIndex());
                    value1 = ((UTF8Info) pool.getConstantInfo(nt.getIndex1())).getValue();
                    value2 = ((UTF8Info) pool.getConstantInfo(nt.getIndex2())).getValue();
                    formatter.format("%5s %s %-20s %-10s %5s %s\n",
                            "#" + i,
                            "=",
                            "Fieldref",
                            "#" + fieldRefInfo.getClassInfoIndex() + "." + "#" + fieldRefInfo.getNameAndTypeIndex(),
                            "//",
                            ((UTF8Info) pool.getConstantInfo(ci.getUtf8Index())).getValue() + "." + value1 + ":" + value2);
                    break;
                case ConstantInfo.STRING_INFO:
                    StringInfo stringInfo = (StringInfo) constantInfo;
                    formatter.format("%5s %s %-20s %-10s %5s %s\n",
                            "#" + i,
                            "=",
                            "String",
                            "#" + stringInfo.getIndex(),
                            "//",
                            ((UTF8Info) pool.getConstantInfo(stringInfo.getIndex())).getValue());
                    break;
                default:
                    throw new RuntimeException("没有此常量类型: " + pool.getConstantInfos().get(i).getType());
            }
        }

    }
}
