package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class MethodTypeInfo implements Constant {
    private int descriptorIndex;

    public MethodTypeInfo(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public int size() {
        return 3;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
