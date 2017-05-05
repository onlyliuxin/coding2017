package com.java.xiaoqin.litestruts.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoqin on 17-3-5.
 */
public class ReflectUtils {

    public static Object newInstance(String className) {
        Object obj = null;
        try {
            Class<?> aClass = Class.forName(className);
            obj = aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void setMethod(Object obj, String methodName, String paramter) {
        try {
            StringBuilder methodNameBuilder = new StringBuilder();
            methodNameBuilder.append("set").append(methodName.substring(0, 1).toUpperCase()).append(methodName.substring(1));
            Method method = obj.getClass().getMethod(methodNameBuilder.toString(), String.class);
            method.invoke(obj, paramter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Object executeMethod(Object obj, String methodName) {
        Object resultObj = null;
        try {
            Method method = obj.getClass().getMethod(methodName);
            resultObj = method.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultObj;
    }

    public static Map<String, Object> executeGets(Object obj) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Method[] methods = obj.getClass().getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get")) {
                    resultMap.put(methodName.substring(3, 4).toLowerCase() + methodName.substring(4), method.invoke(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
