package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class IntegerInfo implements Constant {
    private byte[] bytes;

    public IntegerInfo(byte[] bytes) {
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
