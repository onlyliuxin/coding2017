package com.coding.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class ReflectionUtil {

    public static List<Method> getSetterMethods(Class clz) {

        return getMethods(clz, "set");
    }

    public static void setParameters(Object o, Map<String, String> params) {

        List<Method> methods = getSetterMethods(o.getClass());

        for (String name : params.keySet()) {

            String methodName = "set" + name;

            for (Method method : methods) {

                if (method.getName().equalsIgnoreCase(methodName)){
                    try {
                        method.invoke(o,params.get(name));






                    } catch (IllegalAccessException | InvocationTargetException e) {

                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static List<Method> getGetterMethods(Class clz) {
        return getMethods(clz, "get");
    }

    private static List<Method> getMethods(Class clz, String startWithName) {
        List<Method> methods = new ArrayList<>();

        for (Method method : clz.getDeclaredMethods()) {

            if (method.getName().startsWith(startWithName)) {
                methods.add(method);
            }
        }
        return methods;
    }

    public static Map<String,Object> getParamterMap(Object o) {
        Map<String, Object> params = new HashMap<>();

        List<Method> methods = getGetterMethods(o.getClass()); //获得"getXXX"方法

        for (Method method : methods) {

            String methodName = method.getName();
            String name = methodName.replaceFirst("get", "").toLowerCase();  //获得属性名

            try {
                params.put(name,method.invoke(o));       //将属性名 和属性值添加进去
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return params;

    }
}
