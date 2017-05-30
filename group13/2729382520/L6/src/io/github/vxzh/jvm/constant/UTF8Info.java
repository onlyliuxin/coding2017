package io.github.vxzh.jvm.constant;

import io.github.vxzh.jvm.clz.ConstantPool;

public class UTF8Info extends ConstantInfo {
    private int tag = ConstantInfo.CONSTANT_UTF8_INFO;
    private int length;
    private String value;

    public UTF8Info(ConstantPool pool) {
        super(pool);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "UTF8Info [tag=" + tag + ", length=" + length + ", value=" + value + ")]";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
