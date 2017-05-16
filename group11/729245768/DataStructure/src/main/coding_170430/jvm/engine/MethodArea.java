package main.coding_170430.jvm.engine;

import main.coding_170430.jvm.clz.ClassFile;
import main.coding_170430.jvm.constant.MethodRefInfo;
import main.coding_170430.jvm.loader.ClassFileLoader;
import main.coding_170430.jvm.method.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peterchen on 2017/5/5.
 */
public class MethodArea {
    public static final MethodArea instance = new MethodArea();

    private ClassFileLoader clzLoader = null;
    Map<String, ClassFile> map = new HashMap<>();

    private MethodArea() {
    }

    public static MethodArea getInstance() {
        return instance;
    }

    public void setClassFileLoader(ClassFileLoader clzLoader) {
        this.clzLoader = clzLoader;
    }

    public Method getMainMethod(String className) {
        ClassFile clzFile = findClassFile(className);
        return clzFile.getMainMethod();
    }

    public ClassFile findClassFile(String className) {
        if (map.get(className) != null) {
            return map.get(className);
        }
        ClassFile classFile = clzLoader.loadClass(className);
        map.put(className, classFile);
        return classFile;
    }

    public Method getMethod(String className, String methodName, String paramANDrETURNtYPE) {
        return null;
    }

    public Method getMethod(MethodRefInfo methodRefInfo) {
        return null;
    }
}
