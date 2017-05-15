package task8.jvm.print;

import task8.jvm.constant.*;

public interface ConstantInfoVisitor {

    void visitClassInfo(ClassInfo classInfo);

    void visitFieldRefInfo(FieldRefInfo fieldRefInfo);

    void visitMethodRefInfo(MethodRefInfo methodRefInfo);

    void visitNameAndTypeInfo(NameAndTypeInfo nameAndTypeInfo);

    void visitStringInfo(StringInfo stringInfo);

    void visitUtf8Info(UTF8Info utf8Info);
}
