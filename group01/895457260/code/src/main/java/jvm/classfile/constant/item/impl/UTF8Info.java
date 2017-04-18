package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

import java.io.UnsupportedEncodingException;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class UTF8Info implements Constant {
    private int length;
    private String value;

    public UTF8Info(int length, byte[] bytes) {
        this.length = length;
        try {
            this.value = new String(bytes, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return 3 + length;
    }

    public int getLength() {
        return length;
    }

    public String getValue() {
        return value;
    }
}
