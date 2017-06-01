package me.lzb.jvm.engine;


import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.MethodRefInfo;
import me.lzb.jvm.loader.ClassFileLoader;
import me.lzb.jvm.method.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZB
 */
public class MethodArea {

    public static final MethodArea instance = new MethodArea();

    /**
     * 这里ClassLoader 只有一个， 实际JVM中的ClassLoader,是一个双亲委托的模型，类装载器有命名空间，和被装载的类是关联的
     */
    private ClassFileLoader clzLoader = null;

    /**
     * 保存load过的class，key：me/lzb/jvm/HourlyEmployee, value：ClassFile对象
     */
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
        // 看来该class 文件还没有load过
        ClassFile clzFile = this.clzLoader.loadClass(className);

        map.put(className, clzFile);

        return clzFile;

    }


    public Method getMethod(MethodRefInfo methodRef) {

        ClassFile clz = this.findClassFile(methodRef.getClassName());

        Method m = clz.getMethod(methodRef.getMethodName(), methodRef.getParamAndReturnType());

        if (m == null) {
            throw new RuntimeException("method can't be found : " + methodRef.toString());
        }

        return m;

    }
}
