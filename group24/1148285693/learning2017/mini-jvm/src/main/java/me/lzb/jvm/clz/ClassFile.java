package me.lzb.jvm.clz;

import me.lzb.jvm.constant.ConstantPool;

/**
 * Created by LZB on 2017/4/14.
 */
public class ClassFile {

    private String magicNumber;

    private int minorVersion;

    private int majorVersion;

    private AccessFlag accessFlag;

    private ClassIndex clzIndex;

    private int constantPoolCount;

    private ConstantPool constantPool;


    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }


    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(AccessFlag accessFlag) {
        this.accessFlag = accessFlag;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }


    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }


    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }


    public void print(){

        if(this.accessFlag.isPublicClass()){
            System.out.println("Access flag : public  ");
        }
//        System.out.println("Class Name:"+ getClassName());
//
//        System.out.println("Super Class Name:"+ getSuperClassName());

    }

}
