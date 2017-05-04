package me.lzb.jvm.print;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.*;

/**
 * Created by LZB on 2017/4/23.
 */
public interface PrintVisitor {
    void visitBasicMsg(ClassFile info);

    void visitClassInfo(ClassInfo info);

    void visitFieldRef(FieldRefInfo info);

    void visitMethodRef(MethodRefInfo info);

    void visitNameAndType(NameAndTypeInfo info);

    void visitString(StringInfo info);

    void visistUTF8(UTF8Info info);
}
