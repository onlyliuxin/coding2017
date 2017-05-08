package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class LongInfo implements Constant {
    private byte[] highBytes;
    private byte[] lowBytes;

    public LongInfo(byte[] highBytes, byte[] lowBytes) {
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    @Override
    public int size() {
        return 9;
    }

    public byte[] getHighBytes() {
        return highBytes;
    }

    public byte[] getLowBytes() {
        return lowBytes;
    }
}
