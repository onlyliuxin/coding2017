package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;
import jvm.util.ByteUtils;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<Integer, String> printableMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(PRINT_TYPE, "Float");
        map.put(PRINT_PARAM, ByteUtils.toHexString(bytes));
        map.put(PRINT_COMMENT, "");
        return map;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public float getValue() {
        return ByteUtils.toFloat(bytes);
    }
}
