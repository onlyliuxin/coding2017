package me.lzb.jvm.print;

import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.*;

/**
 * @author LZB
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
        System.out.println("Class              #" + StringUtils.appendSpace(FOURTEENTH, info.getUtf8Index() + "") + "// " + info.getClassName());
    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        System.out.println("FieldRef           #" + StringUtils.appendSpace(FOURTEENTH, info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex()) + "// " + info.getClassName() + ". " + info.toString());
    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        System.out.println("MethodRef          #" + StringUtils.appendSpace(FOURTEENTH, info.getClassInfoIndex() + ".#" + info.getNameAndTypeIndex()) + "// " + info.getClassName() + ". " + info.toString());
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        //  //
        System.out.println("NameAndType        #" + StringUtils.appendSpace(FOURTEENTH, info.getIndex1() + ":#" + info.getIndex2()) + "// " + info.toString());
    }

    @Override
    public void visitString(StringInfo info) {
        System.out.println("String             #" + StringUtils.appendSpace(FOURTEENTH, info.getIndex() + "") + "// " + info.toString());
    }


}
