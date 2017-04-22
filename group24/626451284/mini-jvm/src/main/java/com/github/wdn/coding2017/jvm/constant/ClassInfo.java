package com.github.wdn.coding2017.jvm.constant;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class ClassInfo extends ConstantInfo{
    private int nameIndex;
    public ClassInfo(ConstantPool constantPool){
        super(constantPool);
    }
    @Override
    public int getType() {
        return CLASS_INFO;
    }
    @Override
    public String getValue() {
        return getConstantPool().getConstantInfo(nameIndex).getValue();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getUtf8Index() {
        return nameIndex;
    }

    public String getClassName() {
        return getConstantPool().getConstantInfo(nameIndex).getValue();
    }
}
