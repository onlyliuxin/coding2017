package common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 * @author William on 2017-03-05 18:00:43
 */
public class BeanHelper {
	
	
	/**
	 * 调用实例中methodName的方法
	 * @param bean  		实例
	 * @param methodName	方法名称
	 * @param fieldName		字段名称
	 * @return
	 */
	public static Object excuteBeanMethod(Object bean, String methodName, String fieldName) {
		Class<? extends Object> clazz = bean.getClass();
		String method = getMethodName(fieldName, methodName);
		try {
			Method getMethod = clazz.getMethod(method);
			return getMethod.invoke(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static<T> T invokeClassFromMap(Class<T> clazz, Map<String,String> map) throws InstantiationException, IllegalAccessException {
		T bean = clazz.newInstance();
		return invokeBeanFromMap(bean, map);
	}
	
	public static<T> T invokeBeanFromMap(T bean, Map<String,String> map) throws InstantiationException, IllegalAccessException {
		
		Class<? extends Object> clazz = bean.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field: fields) {
			String fieldName = field.getName();
			
			// 从m里找相应的信息
			String value = map.get(fieldName);
			if (value == null) {
				continue;
			}
			
			String methodName = getMethodName(fieldName, "set");
			Object methodParam = null;
			try {
				// 定位set方法
				Method setMethod = clazz.getDeclaredMethod(methodName, field.getType());
				
				if (field.getType().equals(String.class)) {
					methodParam = value;
					
				} else if (field.getType().equals(Long.class)) {
					methodParam = Long.parseLong(value);
					
				} else if (field.getType().equals(int.class)) {
					methodParam = Integer.parseInt(value);
					
				} else if (field.getType().equals(float.class)) {
					methodParam = Float.parseFloat(value);					
					
				} else if (field.getType().equals(Float.class)) {
					methodParam = Float.parseFloat(value);
					
				} else if (field.getType().equals(Date.class)) {
					if (value == null || value.equals("")) {
						continue;
					}
					value = value.replaceAll("/", "-");
					String formate="";
					if (value.length()>11) {
						// 年-月-日 时:分:秒
						formate = "yyyy-MM-dd HH:mm:ss";
					} else {
						// 年-月-日
						formate = "yyyy-MM-dd";
					}
					SimpleDateFormat sdf = new SimpleDateFormat(formate);
					methodParam = sdf.parse(value);
				}
				
				if (value.equals("")) {
					setMethod.invoke(bean, new Object[]{null});
				} else {
					if (methodParam == null) {
						System.err.println("未支持的反射类型：" + field.getType().getName() + "，已忽略反射。");
						continue;
					} else {
						setMethod.invoke(bean, methodParam);
					}
				}
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bean;
	}

	private static String getMethodName(String fieldName,String name) {
		// 找方法名字
		if (fieldName == null || fieldName.equals("")){
			return name;
		}
		String methodName = name + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return methodName;
	}
	
	//调用所有get方法返回属性map
	public static<T> Map<String, Object> getBeanFields (T bean){
		Class<? extends Object> clazz = bean.getClass();
		Map<String,Object> result = new HashMap<String,Object>();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field: fields) {
			String fieldName = field.getName();
			String methodName = getMethodName(fieldName, "get");
				// 定位set方法
			Method getMethod;
			try {
				getMethod = clazz.getMethod(methodName);
				result.put(fieldName, getMethod.invoke(bean));
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
