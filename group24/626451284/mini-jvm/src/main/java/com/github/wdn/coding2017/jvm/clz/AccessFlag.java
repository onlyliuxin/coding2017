package com.github.wdn.coding2017.jvm.clz;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class AccessFlag {
    /*
        enum {
            ACC_PUBLIC,ACC_FINAL,ACC_SUPER,ACC_INTERFACE,ACC_ABSTRACT,ACC_SYNTHETIC
        }
        private int ACC_PUBLIC =0x0001; //可以被包的类外访问。
        private int ACC_FINAL =0x0010; //不允许有子类。
        private int ACC_SUPER =0x0020;//当用到invokespecial指令时，需要特殊处理③的父类方法。
        private int ACC_INTERFACE= 0x0200; //标识定义的是接口而不是类。
        private int ACC_ABSTRACT= 0x0400; //不能被实例化。
        private int ACC_SYNTHETIC= 0x1000; //标识并非Java源码生成的代码
    */
    private int flagValue;
    public AccessFlag(int value) {
        this.flagValue = value;
    }

    public int getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(int flag) {
        this.flagValue = flag;
    }

    public boolean isPublicClass(){
        return (this.flagValue & 0x0001) != 0;
    }
    public boolean isFinalClass(){
        return (this.flagValue & 0x0010) != 0;
    }
}
