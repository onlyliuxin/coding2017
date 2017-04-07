package com;

import java.util.*;
import com.coderising.litestruts.*;
import com.coderising.array.*;
public class Main {
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5};
		System.out.print("reverseArray测试:");
		ArrayUtil.reverseArray(array);
		for (int i : array)
			System.out.print(i + " ");
		System.out.print("\nremoveZero测试:");

		int[] oldArray = {1, 3, 4, 5, 0, 0, 8 , 0, 9};
		oldArray = ArrayUtil.removeZero(oldArray);
		for (int i : oldArray) {
			System.out.print(i + " ");
		}
		
		System.out.print("\nmerge测试:");
	 	int[] a1 = {3, 5,8};
		int[] a2 = {4, 5, 6,7};
		int[] arrays = ArrayUtil.merge(a1, a2);		
		for (int i : arrays)
			System.out.print(i + " ");
		
		System.out.print("\ngrow测试:");
	
		int[] growArray = ArrayUtil.grow(a1, 5);
		for (int i : growArray)
			System.out.print(i + " ");
		
		System.out.print("\nfibonacci测试");
		int[] fArray = ArrayUtil.fibonacci(1);
		System.out.print(fArray);
		System.out.println();
		fArray = ArrayUtil.fibonacci(15);
		for (int i : fArray)
			System.out.print(i + " ");
		System.out.print("\ngetPrimes测试:");
		int[] primesArray = ArrayUtil.getPrimes(23);
		for (int i : primesArray)
			System.out.print(i + " ");
		System.out.print("\ngetPerfectNumbers测试:");
		int[] pArray = ArrayUtil.getPerfectNumbers(100);
		for (int i : pArray)
			System.out.print(i + " ");
		System.out.print("\njoin测试:");
		int[] jArray = new int[]{2, 3, 8};
		System.out.print(ArrayUtil.join(jArray, "-"));
		Map<String, String> map = new HashMap<>();
		Struts.runAction("login", map);

	}
}
