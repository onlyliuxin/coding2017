package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtil {

	public static List<Method> getSetterMethods(Class<?> clz) {
		
		return getMethods(clz,"set");
	}

	public static List<Method> getGetterMethods(Class<?> clz) {		
		return getMethods(clz,"get");
	}

	public static List<Method> getMethods(Class<?> clz,String startsWithName) {
		List<Method> methods = new ArrayList<>();
		for (Method method : clz.getDeclaredMethods()) {
			if(method.getName().startsWith(startsWithName)){
				methods.add(method);
			}
		}
		return methods;
	}

	public static void setParams(Object o, Map<String, String> params) {
		List<Method> methods = getSetterMethods(o.getClass());
		for (String name : params.keySet()) {
			String methodName = "set"+name;
			for (Method method : methods) {
				if(methodName.equalsIgnoreCase(method.getName())){
					try {
						method.invoke(o, params.get(name));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				};
			}
			
		}
	}

	public static Map<String, Object> getParams(Object o) {
		Map<String ,Object> params = new HashMap<>();
		List<Method> methods = getGetterMethods(o.getClass());
		for (Method method : methods) {
			try {
				String name=method.getName();
				 name = name.replaceFirst("get", "").toLowerCase();
				Object value = method.invoke(o);
				params.put(name, value);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return params;
	}

}
