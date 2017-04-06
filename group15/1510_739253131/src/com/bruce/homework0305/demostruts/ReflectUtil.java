package com.bruce.homework0305.demostruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectUtil {

    public static List<Method> getSetterMethods(Class<?> clz){
        return getMethods(clz, "set");
    }

    public static List<Method> getGetterMethods(Class<?> clz){
        return getMethods(clz, "get");
    }

    public static void setParameters(Object o, Map<String, String> params){
        List<Method> setterMethods = getSetterMethods(o.getClass());
        for(Method method: setterMethods) {
            for(String name: params.keySet()) {
                if(method.getName().equalsIgnoreCase("set"+name)) {
                    try {
                        method.invoke(o, params.get(name));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static Map<String, Object> getParameters(Object o) {
        Map<String,Object> parameterMap = new HashMap<>();
        List<Method> getterMethods = getGetterMethods(o.getClass());
        for(Method method : getterMethods) {
            String methodName = method.getName();
            String parameterName = methodName.replace("get","").toLowerCase();
            try {
                Object value = method.invoke(o);
                parameterMap.put(parameterName, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return parameterMap;
    }

    private static List<Method> getMethods(Class<?> clz, String methodStart){
        List<Method> methods = new ArrayList<>();
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method method: declaredMethods) {
            if(method.getName().startsWith(methodStart)) {
                methods.add(method);
            }
        }
        return methods;
    }
}
