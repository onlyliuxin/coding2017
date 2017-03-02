package com.coderising.litestruts;

/**
 * javaBean 工具
 * @author ren
 *
 */
public class BeanUtil {
	/**
	 * 返回set方法名
	 * @param filedName 属性名
	 * @return set方法名
	 */
	public static String setter(String filedName){
		return "set"+filedName.substring(0, 1).toUpperCase()+filedName.substring(1);
	}
	
	/**
	 * 返回get方法名
	 * @param filedName 属性名
	 * @return get方法名
	 */
	public static String getter(String filedName){
		return "get"+filedName.substring(0, 1).toUpperCase()+filedName.substring(1);
	}
	
}
