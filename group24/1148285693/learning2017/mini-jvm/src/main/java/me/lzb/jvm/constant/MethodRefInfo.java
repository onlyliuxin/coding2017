package me.lzb.jvm.constant;

import me.lzb.jvm.print.PrintVisitor;

/**
 * @author LZB
 */
public class MethodRefInfo extends ConstantInfo {
    private int type = ConstantInfo.Methodref_info;

    private int classInfoIndex;
    private int nameAndTypeIndex;

    public MethodRefInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void print(PrintVisitor visitor) {
        visitor.visitMethodRef(this);
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public String getClassName() {
        ConstantPool pool = this.getConstantPool();
        ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(this.getClassInfoIndex());
        return clzInfo.getClassName();
    }

    public String getMethodName() {
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName();
    }

    public String getParamAndReturnType() {
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getTypeInfo();
    }

    public String toString() {
        return this.getMethodName() + ":" + this.getParamAndReturnType();
    }
}
