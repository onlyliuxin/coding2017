package jvm.classfile;


import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.field.Field;
import jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

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
    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    public void addField(Field f){
        this.fields.add(f);
    }
    public List<Field> getFields(){
        return this.fields;
    }
    public void addMethod(Method m){
        this.methods.add(m);
    }
    public List<Method> getMethods() {
        return methods;
    }

    public void print(){
        if(this.accessFlag.isPublicClass()){
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name:"+ getClassName());
        System.out.println("Super Class Name:"+ getSuperClassName());
    }

    private String getClassName(){
        int thisClassIndex = this.classIndex.getThisClassIndex();
        ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }

    private String getSuperClassName(){
        ClassInfo superClass = (ClassInfo)this.getConstantPool()
                .getConstantInfo(this.classIndex.getSuperClassIndex());
        return superClass.getClassName();
    }

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
