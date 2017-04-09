package jvm.classfile;


/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ClassFile {
    ClassIndex classIndex;
    AccessFlag accessFlag;
    ConstantPool constantPool;
    int minorVersion;
    int majorVersion;

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ClassIndex getClzIndex() {
        return classIndex;
    }
}
