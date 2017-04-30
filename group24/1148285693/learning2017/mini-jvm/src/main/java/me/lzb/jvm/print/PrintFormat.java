package me.lzb.jvm.print;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.*;

/**
 * Created by LZB on 2017/4/23.
 */
public class PrintFormat implements PrintVisitor {

    private int FOURTEENTH = 14;

    @Override
    public void visitBasicMsg(ClassFile info) {
        System.out.println("Access flag : " + info.getAccessFlag().getFlagString());

        System.out.println("Class name:" + info.getClassName());

        System.out.println("Super Class name:" + info.getSuperClassName());

        System.out.println("minor version:" + info.getMinorVersion());

        System.out.println("major version:" + info.getMajorVersion());

        System.out.println();
    }


    @Override
    public void visistUTF8(UTF8Info info) {
        System.out.println("UTF8               " + info.getValue());
    }

    @Override
    public void visitClassInfo(ClassInfo info) {
        System.out.println("Class              #" + getSpace(info.getUtf8Index() + "") + "// " + info.getClassName());
    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        System.out.println("FieldRef           #" + getSpace(info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex()) + "// " + info.getClassName() + ". " + info.toString());
    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        System.out.println("MethodRef          #" + getSpace(info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex()) + "// " + info.getClassName() + ". " + info.toString());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        //  //
        System.out.println("NameAndType        #" + getSpace(info.getIndex1() + ":#" + info.getIndex2()) + "// " + info.toString());
    }

    @Override
    public void visitString(StringInfo info) {
        System.out.println("String             #" + getSpace(info.getIndex() + "") + "// " + info.toString());
    }


    private String getSpace(String str) {
        int spaceCount = FOURTEENTH - str.length();

        StringBuffer spaceBuffer = new StringBuffer();
        for (int i = 0; i < spaceCount; i++) {
            spaceBuffer.append(" ");
        }
        return str + spaceBuffer.toString();
    }
}
