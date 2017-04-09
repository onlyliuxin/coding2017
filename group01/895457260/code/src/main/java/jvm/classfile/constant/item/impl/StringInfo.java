package jvm.classfile.constant.item.impl;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class StringInfo implements Constant {
    private int stringIndex;

    public StringInfo(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public int size() {
        return 3;
    }

    public int getStringIndex() {
        return stringIndex;
    }
}
