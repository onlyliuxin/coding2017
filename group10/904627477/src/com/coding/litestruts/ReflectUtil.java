package com.coding.litestruts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class ReflectUtil {
	
	
    public static Object exectue(Object o,String methodName){
    	Object result = null;
    	try {
			Method m = o.getClass().getMethod(methodName);
			result = m.invoke(o);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
	public static Object getObject(String className, Map<String, String> parameters){
		Object action = null;
		try {
			action = Class.forName(className).newInstance();
			if(parameters==null){
				return action;
			}
			Iterator<String> ite = parameters.keySet().iterator();
			while(ite.hasNext()){
				String name = ite.next();
				String value = parameters.get(name);
				setAttribute(action, name, value);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return action;
	}    
    
    /**
     * 
     * @param o
     * @param name
     * @param value
     */
    public static void setAttribute(Object o,String name,String value){
    	try {
    		Class<?> c = o.getClass();
    		String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    		Class<?> attType = c.getDeclaredField(name).getType();
			Method m = c.getMethod(methodName, attType);
			m.invoke(o, typeCase(value, attType));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }
    
    public static Map<String,Object> getAttributes(Object o){
    	Map<String,Object> param = new HashMap<String, Object>();
    	Class<?> c = o.getClass();
    	Field[] attrs = c.getDeclaredFields();
    	for (Field att : attrs) {
    		String name = att.getName();
    		String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Object reslut = exectue(o, methodName);
			if(reslut!=null){
				param.put(name, reslut);
			}
		}
    	return param;
    }
    
    /**
	 * 将Sting类型数据转换成指定类型数据,暂支持简单和常见类型
	 * @param oldValue 需要转换的数据值
	 * @param type 目标类型
	 * @return 返回转换后的数据
	 */
	public static Object typeCase(String oldValue,Class<?> type){
		Object value = null;
		String typeName = type.getName();
		if("int".equals(typeName)){
			value = Integer.parseInt(oldValue);
		}else if("float".equals(typeName)){
			value = Float.parseFloat(oldValue);
		}else if("boolean".equals(typeName)){
			value = Boolean.parseBoolean(oldValue);
		}else if("long".equals(typeName)){
			value = Long.parseLong(oldValue);
		}else if("byte".equals(typeName)){
			value = Byte.parseByte(oldValue);
		}else if("double".equals(typeName)){
			value = Double.parseDouble(oldValue);
		}else if("short".equals(typeName)){
			value = Short.parseShort(oldValue);
		}else if("char".equals(typeName)){
			value = oldValue.charAt(0);
		}else if("java.util.Date".equals(typeName)){
			try {
				if(Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{3}", oldValue)){
					value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse(oldValue);
				}else if(Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", oldValue)){
					value = new SimpleDateFormat("yyyy-MM-dd").parse(oldValue);
				}else{
					value = null;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("java.lang.String".equals(typeName)){
			value = oldValue;
		}else{
			throw new ClassCastException();
		}
		return value;
	}
}
