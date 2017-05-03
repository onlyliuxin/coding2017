package task7.jvm.clz;

import task7.jvm.constant.ClassInfo;
import task7.jvm.constant.ConstantPool;
import task7.jvm.field.Field;
import task7.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

public class ClassFile {

    private int minorVersion;
    private int majorVersion;

    private AccessFlag accessFlag;
    private ClassIndex clzIndex;
    private ConstantPool pool;

    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
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

    public void setConstPool(ConstantPool pool) {
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

    public String getClassName() {
        int thisClassIndex = this.clzIndex.getThisClassIndex();
        ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }

    public String getSuperClassName() {
        ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
        return superClass.getClassName();
    }

    public Method getMethod(String methodName, String paramAndReturnType) {

        for (Method method : methods) {
            int nameIndex = method.getNameIndex();
            int descriptorIndex = method.getDescriptorIndex();
            String name = getConstantPool().getUTF8String(nameIndex);
            String descriptor = getConstantPool().getUTF8String(descriptorIndex);
            if (methodName.equalsIgnoreCase(name) && paramAndReturnType.equalsIgnoreCase(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public Method getMainMethod() {

        return getMethod("main", "([Ljava/lang/String;)");
    }
}
