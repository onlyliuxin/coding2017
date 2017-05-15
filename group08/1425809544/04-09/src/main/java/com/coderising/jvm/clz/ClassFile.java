package com.coderising.jvm.clz;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;

/**
 * 类文件结构
 */
public class ClassFile {


    private String magic;//魔数
    private int minorVersion;//副版本号
    private int majorVersion;//主版本号

    private AccessFlag accessFlag;//修饰符
    private ClassIndex clzIndex;//索引类:定义了当前类和父类
    private ConstantPool constantPool;//常量池大小为常量数量减一

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(AccessFlag accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void print() {

        if (this.accessFlag.isPublicClass()) {
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name:" + getClassName());

        System.out.println("Super Class Name:" + getSuperClassName());


    }

    private String getClassName() {
        int thisClassIndex = this.clzIndex.getThisClassIndex();//获得类索引
        ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }

    private String getSuperClassName() {
        int superClassIndex = this.clzIndex.getSuperClassIndex();//获得父类索引
        ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(superClassIndex);
        return superClass.getClassName();
    }
}
