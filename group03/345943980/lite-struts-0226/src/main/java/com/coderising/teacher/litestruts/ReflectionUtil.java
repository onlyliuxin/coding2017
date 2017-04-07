package com.coderising.teacher.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReflectionUtil {

	public static List<Method> getSetterMethods(Class<?> clz) {
		/*List<Method> listMethods = new ArrayList<>();
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				listMethods.add(method);
			}
		}
		return listMethods;*/
		return getMethod(clz,"set");
	}

	public static void setParameters(Object o, Map<String, String> params) {
		List<Method> methods = ReflectionUtil.getSetterMethods(o.getClass());
		for (String name : params.keySet()) {
			String methodName = "set" + name;
			for (Method method : methods) {
				if (method.getName().equalsIgnoreCase(methodName)) {
					try {
						method.invoke(o, params.get(name));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					} 
				}
			}

		}
	}

	public static List<Method> getGetterMethods(Class<?> clz) {
		return getMethod(clz,"get");
	}

	public static List<Method> getMethod(Class<?> clz,String startWithName) {
		List<Method> methods  = new ArrayList<>();
		for(Method m:clz.getDeclaredMethods()){
			if(m.getName().startsWith(startWithName)){
				methods.add(m);
			}
		}
		return methods;
	}

	public static Map<String, Object> getParamterMap(Object obj) {
		Map<String,Object> params = new HashMap<String, Object>();
		List<Method> methods =  ReflectionUtil.getGetterMethods(obj.getClass());
		for(Method m:methods){
			try {
				Object o = m.invoke(obj);
				params.put(m.getName().replace("get","").toLowerCase(), o);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return params;
	}

	

}
