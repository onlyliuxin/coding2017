package io.github.vxzh.jvm.constant;

import io.github.vxzh.jvm.clz.ConstantPool;

public class NameAndTypeInfo extends ConstantInfo {
    public int tag = ConstantInfo.CONSTANT_NAMEANDTYPE_INFO;

    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeInfo(ConstantPool pool) {
        super(pool);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getTag() {
        return tag;
    }


    public String getName() {
        ConstantPool pool = this.getConstantPool();
        UTF8Info utf8Info1 = (UTF8Info) pool.getConstantInfo(nameIndex);
        return utf8Info1.getValue();
    }

    public String getTypeInfo() {
        ConstantPool pool = this.getConstantPool();
        UTF8Info utf8Info2 = (UTF8Info) pool.getConstantInfo(descriptorIndex);
        return utf8Info2.getValue();
    }

    public String toString() {
        return "(" + getName() + "," + getTypeInfo() + ")";
    }
}
