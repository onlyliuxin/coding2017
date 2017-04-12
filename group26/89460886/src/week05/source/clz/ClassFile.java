package jvm.clz;

import jvm.constant.ClassInfo;
import jvm.constant.ConstantPool;

/**
 * @author jiaxun
 */
public class ClassFile {

    private String magicNumber;

    private int minorVersion;
    private int majorVersion;

    private AccessFlag accessFlag;
    private ClassIndex clzIndex;
    private ConstantPool pool;

    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(AccessFlag accessFlag) {
        this.accessFlag = accessFlag;
    }

    public ConstantPool getConstantPool() {
        return pool;
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

    public void setConstantPool(ConstantPool pool) {
        this.pool = pool;
    }

    public void setClassIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public void print() {
        if (this.accessFlag.isPublicClass()) {
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name:" + getClassName());
        System.out.println("Super Class Name:" + getSuperClassName());
    }

    private String getClassName() {
        int thisClassIndex = this.clzIndex.getThisClassIndex();
        ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }

    private String getSuperClassName() {
        ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
        return superClass.getClassName();
    }
}
