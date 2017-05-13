package jvm.classfile;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.constant.item.impl.ClassInfo;
import jvm.classfile.field.Field;
import jvm.classfile.method.Method;
import jvm.engine.JavaObject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    List<ClassInfo> interfaces = new ArrayList<>();
    List<Field> fields = new ArrayList<>();
    List<Method> methods = new ArrayList<>();
    List<AttributeInfo> attributes = new ArrayList<>();

    Map<String, JavaObject> staticFieldValues = new HashMap<>();


    public AccessFlag getAccessFlag() {
        return accessFlag;
    }
    public List<ClassInfo> getInterfaces() {
        return this.interfaces;
    }
    public List<Field> getFields() {
        return this.fields;
    }
    public List<Method> getMethods() {
        return methods;
    }
    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public void print() {
        if(this.accessFlag.isPublic()){
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name:"+ getClassName());
        System.out.println("Super Class Name:"+ getSuperClassName());
    }

    public String getClassName() {
        int thisClassIndex = this.classIndex.getThisClassIndex();
        ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
        return thisClass.getClassName();
    }

    public String getSuperClassName() {
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

    public Method getMethod(String methodName, String paramAndReturnType) {
        return methods.stream().filter(m -> methodName.equals(m.getName())
                && paramAndReturnType.equals(m.getParamAndReturnType()))
                .findFirst().orElse(null);
    }

    public Method getMainMethod() {
        return getMethod("main", "([Ljava/lang/String;)V");
    }

    public Field getField(String name) {
        return fields.stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public JavaObject getStaticFieldValue(String name) {
        return staticFieldValues.get(name);
    }

    public void putStaticFieldValue(String name, JavaObject object) {
        java.lang.reflect.Field field;
        try {
            Class<?> clazz = Class.forName(this.getClassName().replace('/', '.'));
            field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            if (object == null) {
                staticFieldValues.remove(name);
                field.set(null, null);
            } else {
                staticFieldValues.put(name, object);
                field.set(null, object);
            }
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Static field " + getClassName() + "." + name + "not found");
        }
    }
}
