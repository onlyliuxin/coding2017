package com.coderising.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFile {

    private int minorVersion;
    private int majorVersion;

    private int intefaceCount;

    private AccessFlag accessFlag;
    private ClassIndex clzIndex;
    private ConstantPool pool;
    private List<Field> fields = new ArrayList<Field>();
    private List<Method> methods = new ArrayList<Method>();

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

    public void setConstPool(ConstantPool pool) {
        this.pool = pool;

    }

    public void setClassIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public void addField(Field f) {
        this.fields.add(f);
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public void addMethod(Method m) {
        this.methods.add(m);
    }

    public List<Method> getMethods() {
        return methods;
    }

    public int getIntefaceCount() {
        return intefaceCount;
    }

    public void setIntefaceCount(int intefaceCount) {
        this.intefaceCount = intefaceCount;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
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
