package litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReflectionUtil {

	public static List<Method> getSetterMethod(Class<?> clazz) {
		return getMethods(clazz, "set");
	}


	public static void setParameters(Object o,
			Map<String, String> parameters) {
		List<Method> methods = getSetterMethod(o.getClass());
		
		for(String name : parameters.keySet()){
			String methodName = "set"+name;
			for(Method m : methods){
				if(m.getName().equalsIgnoreCase(methodName)){
					try {
						m.invoke(o, parameters.get(name));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} 
				}
			}
		}
	}


	public static List<Method> getGetterMethod(Class<?> clazz) {
		return getMethods(clazz,"get");
	}


	public static List<Method> getMethods(Class<?> clazz, String startWithName) {
		List<Method> methods = new ArrayList<Method>();
		for(Method method : clazz.getDeclaredMethods()){
			if(method.getName().startsWith(startWithName)){
				methods.add(method);
			}
		}
		return methods;
	}


	public static Map<String, Object> getParameters(Object o) {
		List<Method> methods = getGetterMethod(o.getClass());
		Map<String, Object> parameters = new HashMap<String, Object>();
		for(Method m : methods){
			try {
				String name = m.getName().replaceFirst("get", "").toLowerCase();
				Object value = m.invoke(o);
				parameters.put(name, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return parameters;
		
	}


}
