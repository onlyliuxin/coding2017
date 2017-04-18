package com.coderising.array;

public class Main {

	public static void main(String[] args) {
		ArrayUtil au = new ArrayUtil();
//		int[] test = {1,2};
//		au.reverseArray(test);
//		String result = au.join(test, "-");
		
		
		int[] arr1 = {3, 5, 7,8};
		int[] arr2 = {4, 5, 6,7};
		int[] test = au.merge(arr1, arr2);
		String result = au.join(test, ",");
		
		System.out.println(result);
	}

}
