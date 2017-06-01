package me.lzb.jvm.clz;

import me.lzb.jvm.constant.ClassInfo;
import me.lzb.jvm.constant.ConstantPool;
import me.lzb.jvm.field.Field;
import me.lzb.jvm.method.Method;
import me.lzb.jvm.print.Print;
import me.lzb.jvm.print.PrintVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class ClassFile implements Print {

    private String magicNumber;

    private int minorVersion;

    private int majorVersion;

    private AccessFlag accessFlag;

    private ClassIndex clzIndex;


    private ConstantPool constantPool;

    private List<Field> fields = new ArrayList<>();

    private List<Method> methods = new ArrayList<>();


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

    public void setClzIndex(ClassIndex clzIndex) {
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

    public int getConstantPoolCount() {
        return constantPool.getSize();
    }

    public void addField(Field f) {
        this.fields.add(f);
    }

    public void addMethod(Method m) {
        this.methods.add(m);
    }

    //函数的 方法名+参数类型+返回值，构成函数的唯一标识
    public Method getMethod(String methodName, String paramAndReturnType) {
        for (Method m : methods) {
            int nameIndex = m.getNameIndex();
            int descIndex = m.getDescriptorIndex();

            String name = this.getConstantPool().getUTF8String(nameIndex);
            String desc = this.getConstantPool().getUTF8String(descIndex);

            if (name.equals(methodName) && desc.equals(paramAndReturnType)) {
                return m;
            }
        }

        return null;
    }

    public Method getMainMethod() {
        return getMethod("main", "([Ljava/lang/String;)V");
    }

    @Override
    public void print(PrintVisitor visitor) {
        visitor.visitBasicMsg(this);
    }
}
