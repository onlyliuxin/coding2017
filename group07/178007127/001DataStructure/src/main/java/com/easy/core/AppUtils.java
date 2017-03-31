package com.easy.core;

public class AppUtils {
	public static String fistLetterToUpper(String words){
		String letter = words.substring(0,1).toUpperCase();
		return letter+words.substring(1,words.length());
	}
	
	/*public static void main(String[] args) {
		String s="abc";
		System.out.println(fistLetterToUpper(s));
	}*/
	
	public static String packageName2Path(String packageName){
		String[] arr=packageName.split("[.]");
		StringBuilder sb=new StringBuilder();
		for (String s : arr) {
			sb.append("\\"+s);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String packageName="com.easy.mini.jvm.test.EmployeeV1";
		String result =packageName2Path(packageName);
		System.out.println(result+".class");
	}
}
