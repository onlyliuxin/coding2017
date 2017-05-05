package com.github.ipk2015.coding2017.basic;

public class ListUtils {
	public static boolean checkIndexInRange(int index,int range){
		if(index>=0 && index<=range){
			return true;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public static void log(String msg){
		System.out.println(msg);
	}
}
