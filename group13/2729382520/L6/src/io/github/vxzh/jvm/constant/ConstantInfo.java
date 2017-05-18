package io.github.vxzh.jvm.constant;

import io.github.vxzh.jvm.clz.ConstantPool;

public abstract class ConstantInfo {

    public static final int CONSTANT_UTF8_INFO = 1;//
    public static final int CONSTANT_INTEGER_INFO = 3;
    public static final int CONSTANT_FLOAT_INFO = 4;//
    public static final int CONSTANT_LONG_INFO = 5;
    public static final int CONSTANT_DOUBLE_INFO = 6;
    public static final int CONSTANT_CLASS_INFO = 7;//
    public static final int CONSTANT_STRING_INFO = 8;//
    public static final int CONSTANT_FIELDREF_INFO = 9;//
    public static final int CONSTANT_METHODREF_INFO = 10;//
    public static final int CONSTANT_INTERFACEMETHODREF_INFO = 11;
    public static final int CONSTANT_NAMEANDTYPE_INFO = 12;//
    public static final int CONSTANT_METHODHANDLE_INFO = 15;
    public static final int CONSTANT_METHODTYPE_INFO = 16;
    public static final int CONSTANT_INVOKEDYNAMIC_INFO = 18;

    protected ConstantPool constantPool;

    public ConstantInfo() {

    }

    public ConstantInfo(ConstantPool pool) {
        this.constantPool = pool;
    }

    public abstract int getTag();

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ConstantInfo getConstantInfo(int index) {
        return this.constantPool.getConstantInfo(index);
    }

}
