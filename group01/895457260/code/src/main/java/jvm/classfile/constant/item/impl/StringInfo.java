package jvm.classfile.constant.item.impl;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.IReference;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class StringInfo implements Constant, IReference {
    private int stringIndex;
    private String value;

    public StringInfo(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public int size() {
        return 3;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        UTF8Info info = (UTF8Info) constantPool.getConstantInfo(stringIndex);
        value = info.getValue();
    }
}
