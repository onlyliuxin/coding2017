package task8.jvm.engine;

import task8.jvm.clz.ClassFile;
import task8.jvm.constant.MethodRefInfo;
import task8.jvm.loader.ClassFileLoader;
import task8.jvm.method.Method;

import java.util.HashMap;
import java.util.Map;

public class MethodArea {

    private MethodArea() {
    }

    private static MethodArea INSTANCE = new MethodArea();

    private ClassFileLoader classFileLoader;

    Map<String, ClassFile> map = new HashMap<>();

    public static MethodArea getInstance() {
        return INSTANCE;
    }

    public Method getMainMethod(String className) {
        return classFileLoader.loadClass(className).getMainMethod();
    }

    public Method getMethod(MethodRefInfo info) {
        return classFileLoader.loadClass(info.getClassName()).getMethod(info.getMethodName(), info.getParamAndReturnType());
    }

    public ClassFileLoader getClassFileLoader() {
        return classFileLoader;
    }

    public void setClassFileLoader(ClassFileLoader classFileLoader) {
        this.classFileLoader = classFileLoader;
    }
}
