package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class Utf8Info implements Constant {
    private int length;
    private byte[] bytes;

    public Utf8Info(int length, byte[] bytes) {
        this.length = length;
        this.bytes = bytes;
    }

    @Override
    public int length() {
        return 3 + length;
    }

    public int getLength() {
        return length;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
