package me.lzb.jvm.constant;

import me.lzb.jvm.print.PrintVisitor;

/**
 * @author LZB
 */
public class FieldRefInfo extends ConstantInfo {
    private int type = ConstantInfo.Fieldref_info;

    private int classInfoIndex;
    private int nameAndTypeIndex;

    public FieldRefInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void print(PrintVisitor visitor) {
        visitor.visitFieldRef(this);
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

    public String getFieldName() {
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) this.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName();
    }

    public String getFieldType() {
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) this.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getTypeInfo();
    }

    public String getClassName() {
        ClassInfo classInfo = (ClassInfo) this.getConstantInfo(this.getClassInfoIndex());
        UTF8Info utf8Info = (UTF8Info) this.getConstantInfo(classInfo.getUtf8Index());
        return utf8Info.getValue();
    }

    public String toString() {
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) this.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName() + ":" + typeInfo.getTypeInfo();
    }
}
