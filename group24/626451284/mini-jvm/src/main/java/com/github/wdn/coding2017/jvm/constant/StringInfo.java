package com.github.wdn.coding2017.jvm.constant;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
public class StringInfo extends ConstantInfo {
    public StringInfo(ConstantPool constantPool) {
        super(constantPool);
    }

    private int stringIndex;

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public String getValue() {
        return getConstantPool().getConstantInfo(stringIndex).getValue();
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }
}
