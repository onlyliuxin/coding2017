package com.coderising.litestruts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zt on 2017/3/11.
 */
public final class ReflectionUtil {

    public static Map<String, Method> getMethodsMap(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }
        return methodMap;
    }

    public static Map<String, Method> getMethodsByStartName(Class<?> clazz, String startWithName) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith(startWithName)) {
                methodMap.put(method.getName(), method);
            }
        }
        return methodMap;
    }

    public static Map<String, Method> getGetterMethods(Class<?> clazz) {
        return getMethodsByStartName(clazz, "get");
    }

    public static Map<String, Method> getSetterMethods(Class<?> clazz) {
        return getMethodsByStartName(clazz, "set");
    }

    public static String getMethodName(String fieldName, String startWithName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startWithName);
        stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
        stringBuilder.append(fieldName.substring(1));
        return stringBuilder.toString();
    }

    public static String getGettterMethodName(String fieldName) {
        return getMethodName(fieldName, "get");
    }

    public static String getSettterMethodName(String fieldName) {
        return getMethodName(fieldName, "set");
    }

    public static void setParameters(Map<String, String> parameters, Object object) {
        try {
            Map<String, Method> methodMap = getSetterMethods(object.getClass());
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Method setterMethod = methodMap.get(getSettterMethodName(key));
                setterMethod.invoke(object, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Map<Object, Object> getParametersMap(Class<?> clazz, Object newInstance) {
        try {
            Map<Object, Object> map = new HashMap<>();
            Map<String, Method> methodMap = getMethodsMap(clazz);
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    String fieldName = field.getName();
                    Method getterMethod = methodMap.get(getGettterMethodName(fieldName));
                    Object value = getterMethod.invoke(newInstance);
                    map.put(fieldName, value);
                }
            }
            return map;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
