package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;
import jvm.util.ByteUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class DoubleInfo implements Constant {
    private byte[] highBytes;
    private byte[] lowBytes;

    public DoubleInfo(byte[] highBytes, byte[] lowBytes) {
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    @Override
    public int size() {
        return 9;
    }

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "Double");
        map.put(PRINT_PARAM, ByteUtils.toHexString(highBytes) + ByteUtils.toHexString(lowBytes));
        map.put(PRINT_COMMENT, "");
        return map;
    }

    public byte[] getHighBytes() {
        return highBytes;
    }

    public byte[] getLowBytes() {
        return lowBytes;
    }
}
