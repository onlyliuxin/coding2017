package me.lzb.jvm.print;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.*;

/**
 * Created by LZB on 2017/4/23.
 */
public interface PrintVisitor {
    public void visitBasicMsg(ClassFile info);

    public void visitClassInfo(ClassInfo info);

    public void visitFieldRef(FieldRefInfo info);

    public void visitMethodRef(MethodRefInfo info);

    public void visitNameAndType(NameAndTypeInfo info);

    public void visitString(StringInfo info);

    public void visistUTF8(UTF8Info info);
}
