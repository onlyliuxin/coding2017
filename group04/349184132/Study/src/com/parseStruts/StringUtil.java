package com.parseStruts;

public class StringUtil {
	public static String nameTogetName(String name){
		String str = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "get");
		return sb.toString();
	}
	
	
	public static String getNameToName(String getName){
		String str = getName.substring(3, getName.length());
		String s = str.substring(0, 1).toLowerCase()+str.substring(1, str.length());
		
		return s;
	}
	
	public static String nameTosetName(String name){
		String str = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		StringBuilder sb = new StringBuilder(str);
		sb.insert(0, "set");
		return sb.toString();
	}
	
	
	public static String setnameToname(String getName){
		
		String s = getNameToName(getName);
		
		return s;
	}
}
