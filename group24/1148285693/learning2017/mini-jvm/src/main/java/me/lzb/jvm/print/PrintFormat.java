package me.lzb.jvm.print;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.*;

/**
 * Created by LZB on 2017/4/23.
 */
public class PrintFormat implements PrintVisitor {


    @Override
    public void visitBasicMsg(ClassFile info) {
        System.out.println("Access flag : " + info.getAccessFlag().getFlagString());

        System.out.println("Class Name:" + info.getClassName());

        System.out.println("Super Class Name:" + info.getSuperClassName());

        System.out.println("minor version:" + info.getMinorVersion());

        System.out.println("major version:" + info.getMajorVersion());

        System.out.println();
    }


    @Override
    public void visistUTF8(UTF8Info info) {
        System.out.println("UTF8    " + info.getValue());
    }

    @Override
    public void visitClassInfo(ClassInfo info) {
        System.out.println("Class    #" + info.getUtf8Index() + "  " + info.getClassName());
    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        System.out.println("FieldRef    #" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex());
    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        System.out.println("MethodRef    #" + info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        System.out.println("NameAndType    #" + info.getIndex1() + ":#" + info.getIndex2());
    }

    @Override
    public void visitString(StringInfo info) {
        System.out.println("String    #" + info.getIndex());
    }
}
