package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtil {
	public static List<Method> getSetterMethods(Class clz){
		return getMethods(clz,"set");
	}
	
	public static List<Method> getGetterMethods(Class clz){
		return getMethods(clz,"get");
	}
	
	public static void setParameters(Object o, Map<String,String> parameters){
		List<Method> methods = getSetterMethods(o.getClass());
		
		for(String name : parameters.keySet()){
			String methodName = "set" + name;
			System.out.println("methodName: "+methodName);
			
			for(Method m : methods){
				if(m.getName().equalsIgnoreCase(methodName)){					
					try {
						m.invoke(o, parameters.get(name));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}					
				}
			}
		}		
	}
	
	private static List<Method> getMethods(Class clz, String startWithName){
		List<Method> methods = new ArrayList<>();
		
		for(Method m : clz.getDeclaredMethods()){
			if(m.getName().startsWith(startWithName)){
				methods.add(m);
			}
		}
		
		return methods;
	}
	
	public static Map<String, Object> getParamterMap(Object o){
		Map<String, Object> params = new HashMap<>();
		
		return null;
	}
}
