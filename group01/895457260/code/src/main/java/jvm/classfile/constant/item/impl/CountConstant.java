package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class CountConstant implements Constant {
    private int count;

    public CountConstant(int count) {
        this.count = count;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public Map<Integer, String> printableMap() {
        return null;
    }

    public int getCount() {
        return count;
    }
}
