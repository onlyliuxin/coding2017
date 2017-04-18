package com.github.wdn.coding2017.jvm.constant;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
public class UTF8Info extends ConstantInfo{
    private String value;
    public UTF8Info(ConstantPool constantPool){
        super(constantPool);
    }
    @Override
    public int getType() {
        return UTF8_INFO;
    }
    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
