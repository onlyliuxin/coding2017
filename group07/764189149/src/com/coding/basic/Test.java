package com.coding.basic;

import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		 Set<String> anchorWhiteList4Coupon = new HashSet<String>();
		 //anchorWhiteList4Coupon.add("123");
		 String[] array = {"123","456","345"};
		 for (int i = 0; i < array.length; i++) {
			if(!anchorWhiteList4Coupon.contains(array[i])){
				System.out.println(array[i]);
			}
		}
		 
	}

}
