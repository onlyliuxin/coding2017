package com.easy.core;

public class AppUtils {
	public static String fistLetterToUpper(String words){
		String letter = words.substring(0,1).toUpperCase();
		return letter+words.substring(1,words.length());
	}
	
	public static void main(String[] args) {
		String s="abc";
		System.out.println(fistLetterToUpper(s));
	}
}
