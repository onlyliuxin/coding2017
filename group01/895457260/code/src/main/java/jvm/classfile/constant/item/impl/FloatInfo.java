package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class FloatInfo implements Constant {
    private byte[] bytes;

    public FloatInfo(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int size() {
        return 5;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
