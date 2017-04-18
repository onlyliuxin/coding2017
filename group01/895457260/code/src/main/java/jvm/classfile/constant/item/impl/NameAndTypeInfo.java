package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class NameAndTypeInfo implements Constant {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeInfo(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public int size() {
        return 5;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getIndex1() {
        return nameIndex;
    }

    public int getIndex2() {
        return descriptorIndex;
    }
}
