package week2.struts2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtil {

	/**
	 * 反射赋值
	 * @param o:类对象
	 * @param params:用户信息
	 */
	public static void setParameters(Object o, Map<String,String> params){
		
		List<Method> Methods=getSetterMethods(o.getClass());
		
		for(String name:params.keySet()){
			
			String methodName="set"+name;
			
			for(Method method:Methods){
				
				if(method.getName().equalsIgnoreCase(methodName)){					
					try {
						method.invoke(o, params.get(name));
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static Map<String,Object> getParamterMap(Object o){
		
		 Map<String,Object> params=new HashMap<>();
		 
	     List<Method> methods=getGetterMethods(o.getClass());
	     
	     for(Method method:methods){
	    	 
	    	 String name=method.getName().replaceFirst("get", "").toLowerCase(); 
	    	 
	    	 try {
				Object object=method.invoke(o);
				params.put(name, object);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
	     }
	     
	     return params;
	}
	
	public static List<Method> getSetterMethods(Class<?> clazz){
	     return getMethod(clazz,"set");
	}

	public static List<Method> getGetterMethods(Class<?> clazz){
		return getMethod(clazz,"get");
	}
	
	/**
	 *反射获取给定名开始的方法
	 * @param clazz
	 * @param startWithName
	 * @return
	 */
	private static List<Method> getMethod(Class<?> clazz, String startWithName) {
		
		List<Method> methods=new ArrayList<Method>();
		
		for(Method method:clazz.getDeclaredMethods()){
			
			if(method.getName().startsWith(startWithName)){
				methods.add(method);
			}
		}
		
		return methods;
	}
}
