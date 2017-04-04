package org.comm.util;

import java.util.Arrays;

public class StringUtil {

	public static void printStr(Object obj){
		System.out.print(obj);
	}
	
	public static void printlnStr(Object obj){
		System.out.println(obj.toString());
	}
	public static void printArr(int[] arr){
		printlnStr(Arrays.toString(arr));
	}
	public static boolean isEmpty(Object str){
		boolean flag = false;
		if(str == null || "".equals(str)) flag = true;
		return flag;
	}
	public static String objToStr(Object obj){
		if(isEmpty(obj)){
			return "";
		}else{
			return obj.toString();
		}
	}
	
}
