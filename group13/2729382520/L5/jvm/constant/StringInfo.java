package io.github.vxzh.jvm.constant;

import io.github.vxzh.jvm.clz.ConstantPool;

public class StringInfo extends ConstantInfo {
    private int tag = ConstantInfo.CONSTANT_STRING_INFO;
    private int index;

    public StringInfo(ConstantPool pool) {
        super(pool);
    }

    public int getTag() {
        return tag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public String toString() {
        return this.getConstantPool().getUTF8String(index);
    }

}
