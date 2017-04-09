package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ClassInfo implements Constant {
    private int nameIndex;

    public ClassInfo(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public int length() {
        return 3;
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
