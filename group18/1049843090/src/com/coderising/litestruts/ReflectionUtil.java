package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ReflectionUtil
 */
public class ReflectionUtil {

    private ReflectionUtil() {

    }

    public static void setParameters(Object o, Map<String, Object> params) {
        Class clz = o.getClass();
        Method[] methods = clz.getDeclaredMethods();
        for (String name : params.keySet()) {
            for (Method method : methods) {
                method.getName().equalsIgnoreCase("set" + name);
                try {
                    method.invoke(o,params.get(name));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



