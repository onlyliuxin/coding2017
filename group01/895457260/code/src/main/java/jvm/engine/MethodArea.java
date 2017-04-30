package jvm.engine;

import jvm.ClassFileLoader;
import jvm.classfile.ClassFile;
import jvm.classfile.constant.item.impl.MethodRefInfo;
import jvm.classfile.method.Method;
import jvm.exception.ReadClassException;

import java.util.HashMap;
import java.util.Map;

public class MethodArea {

    private static final MethodArea instance = new MethodArea();

    /**
     * 注意：我们做了极大的简化， ClassLoader 只有一个， 实际JVM中的ClassLoader,是一个双亲委托的模型
     */

    private ClassFileLoader clzLoader = null;

    private Map<String, ClassFile> map = new HashMap<>();

    private MethodArea() {
    }

    public static MethodArea getInstance() {
        return instance;
    }

    public void setClassFileLoader(ClassFileLoader clzLoader) {
        this.clzLoader = clzLoader;
    }

    public Method getMainMethod(String className) throws ReadClassException {
        ClassFile clzFile = this.findClassFile(className);
        return clzFile.getMainMethod();
    }

    public ClassFile findClassFile(String className) throws ReadClassException {
        if (map.get(className) != null) {
            return map.get(className);
        }
        // 看来该class文件还没有load过
        ClassFile clzFile = this.clzLoader.load(className);
        map.put(className, clzFile);
        return clzFile;
    }

    public Method getMethod(String className, String methodName, String paramAndReturnType)
            throws ReadClassException {
        ClassFile classFile = findClassFile(className);
        return classFile.getMethod(methodName, paramAndReturnType);
    }

    public Method getMethod(MethodRefInfo methodRef) throws ReadClassException {
        return getMethod(methodRef.getClassName(), methodRef.getName(), methodRef.getParamAndReturnType());
    }
}
