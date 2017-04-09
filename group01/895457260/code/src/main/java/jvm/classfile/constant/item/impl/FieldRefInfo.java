package jvm.classfile.constant.item.impl;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class FieldRefInfo implements Constant {
    private int classIndex;
    private int nameAndTypeindex;

    public FieldRefInfo(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeindex = nameAndTypeIndex;
    }

    @Override
    public int length() {
        return 5;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeindex() {
        return nameAndTypeindex;
    }
}
