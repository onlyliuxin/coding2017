package main.coding_170430.jvm.clz;

import main.coding_170430.jvm.constant.ClassInfo;
import main.coding_170430.jvm.constant.ConstantPool;
import main.coding_170430.jvm.field.Field;
import main.coding_170430.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/4/21.
 */
public class ClassFile {
    private int minorVersion;
    private int majorVersion;

    private ConstantPool pool;
    private AccessFlag accessFlag;
    private ClassIndex clzIndex;
    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    public ClassIndex getClzIndex(){
        return clzIndex;
    }

    public AccessFlag getAccessFlag(){
        return accessFlag;
    }

    public void setAccessFlag(AccessFlag accessFlag){
        this.accessFlag = accessFlag;
    }
    public ConstantPool getConstantPool(){
        return pool;
    }

    public int getMinorVersion(){
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion){
        this.minorVersion = minorVersion;
    }
    public int getMajorVersion(){
        return majorVersion;
    }
    public void setMajorVersion(int majorVersion){
        this.majorVersion = majorVersion;
    }

    public void setConstantPool(ConstantPool pool){
        this.pool = pool;
    }
    public void setClassIndex(ClassIndex clzIndex){
        this.clzIndex = clzIndex;
    }

    public void addField(Field f){
        this.fields.add(f);
    }
    public List<Field> getFields(){
        return this.fields;
    }

    public void addMethod(Method m){
        this.methods.add(m);
    }

    public List<Method> getMethods(){
        return methods;
    }

    public void print(){
        if(this.accessFlag.isPublicClass()){
            System.out.println("Access flag : public");
        }
        System.out.println("Class Name:"+getClassName());
        System.out.println("Super Class Name:"+getSuperClassName());
    }
    public String getClassName(){
        int thisClassIndex = this.clzIndex.getThisClassIndex();
        ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }

    public String getSuperClassName(){
        ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.getClzIndex().getSuperClassIndex());
        return superClass.getClassName();
    }
    public Method getMainMethod(){

        return null;
    }
}
