package com.pxshuo.se02.litestruts;

public class Tools {
	/**
	 * 将第一个字母转为大写
	 * @param string
	 * @return
	 */
	public static String upperFirst(String string) {
		if (string == null || ("").equals(string)) {
			return "";
		}		
		string = string.substring(0,1).toUpperCase() + string.substring(1);
		return string;
	}
	
	/**
	 * 将第一个字母转为大写
	 * @param string
	 * @return
	 */
	public static String lowerFirst(String string) {
		if (string == null || ("").equals(string)) {
			return "";
		}		
		string = string.substring(0,1).toLowerCase() + string.substring(1);
		return string;
	}
	
	public static void main(String[] args) {
		upperFirst("1ame");
	}
}
