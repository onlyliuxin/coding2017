package org.xukai.coderising.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xukai
 * @desc
 * @date 2017-02-27-下午 4:19
 */
public class ReflectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }


    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Method getMethod(Class<?> cls, String methodName){
        Method result = null;
        try {
            Method[] methods = cls.getDeclaredMethods();
            for(Method method : methods){
                if (method.getName().equals(methodName)) {
                    result = method;
                }
            }
        } catch (Exception e) {
            logger.error("get method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Method> getMethodBeginWith(Class<?> cls, String methodName){
        ArrayList<Method> methodsList = new ArrayList<Method>();
        try {
            Method[] methods = cls.getDeclaredMethods();
            for(Method method : methods){
                if (method.getName().startsWith(methodName)) {
                    methodsList.add(method);
                }
            }
        } catch (Exception e) {
            logger.error("get methods failure",e);
            throw new RuntimeException(e);
        }
        return methodsList;
    }

    public static void setField(Object obj, Field field, Object values){
        try {
            field.setAccessible(true);
            field.set(obj,values);
        } catch (Exception e) {
            logger.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过反射取对象指定字段(属性)的值
     * @param target 目标对象
     * @param fieldName 字段的名字
     * @throws RuntimeException 如果取不到对象指定字段的值则抛出异常
     * @return 字段的值
     */
    public static Object getValue(Object target, String fieldName) {
        Class<?> clazz = target.getClass();
        String[] fs = fieldName.split("\\.");

        try {
            for(int i = 0; i < fs.length - 1; i++) {
                Field f = clazz.getDeclaredField(fs[i]);
                f.setAccessible(true);
                target = f.get(target);
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);
            return f.get(target);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过反射给对象的指定字段赋值
     * @param target 目标对象
     * @param fieldName 字段的名称
     * @param value 值
     */
    public static void setValue(Object target, String fieldName, Object value) {
        Class<?> clazz = target.getClass();
        String[] fs = fieldName.split("\\.");
        try {
            for(int i = 0; i < fs.length - 1; i++) {
                Field f = clazz.getDeclaredField(fs[i]);
                f.setAccessible(true);
                Object val = f.get(target);
                if(val == null) {
                    Constructor<?> c = f.getType().getDeclaredConstructor();
                    c.setAccessible(true);
                    val = c.newInstance();
                    f.set(target, val);
                }
                target = val;
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);
            f.set(target, value);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
