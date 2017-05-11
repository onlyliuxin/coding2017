package com.github.wdn.coding2017.jvm.constant;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
public class NameAndTypeInfo extends ConstantInfo{
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeInfo(ConstantPool constantPool) {
        super(constantPool);
    }

    @Override
    public int getType() {
        return 0;
    }
    @Override
    public String getValue() {
        return getConstantPool().getConstantInfo(nameIndex).getValue()+getConstantPool().getConstantInfo(descriptorIndex).getValue();
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
}
