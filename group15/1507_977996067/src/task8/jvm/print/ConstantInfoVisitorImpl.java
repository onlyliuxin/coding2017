package task8.jvm.print;

import task8.jvm.constant.*;

public class ConstantInfoVisitorImpl implements ConstantInfoVisitor {
    @Override
    public void visitClassInfo(ClassInfo classInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Class\t#").append(classInfo.getUtf8Index()).append("\t").append(classInfo.getClassName());
        System.out.println(sb);
    }

    @Override
    public void visitFieldRefInfo(FieldRefInfo fieldRefInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("FieldRef\t#").append(fieldRefInfo.getClassInfoIndex()).append("\t").append(fieldRefInfo.getFieldName());
        System.out.println(sb);

    }

    @Override
    public void visitMethodRefInfo(MethodRefInfo methodRefInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("MethodRef\t#").append(methodRefInfo.getMethodName());
        System.out.println(sb);
    }

    @Override
    public void visitNameAndTypeInfo(NameAndTypeInfo nameAndTypeInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("NameAndType\t#").append(nameAndTypeInfo.getName()).append("\t")
                .append(nameAndTypeInfo.getIndex1()).append("\t")
                .append(nameAndTypeInfo.getIndex2()).append("\t")
                .append(nameAndTypeInfo.getTypeInfo());
        System.out.println(sb);
    }

    @Override
    public void visitStringInfo(StringInfo stringInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("String\t#").append(stringInfo.getIndex());
        System.out.println(sb);
    }

    @Override
    public void visitUtf8Info(UTF8Info utf8Info) {
        StringBuilder sb = new StringBuilder();
        sb.append("UTF8\t#").append(utf8Info.getValue());
        System.out.println(sb);
    }
}
