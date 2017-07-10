package com.coding.basic.array;

import java.util.Arrays;

public class Fibonacci {

	/**
	 * 0 1 2 3 4 5 6  7  8  9  10
	 * 1 1 2 3 5 8 13 21 34 55 89
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i <= 10; i++) {
			System.out.println(calFibonacciNum_2(i));
		}
		System.out.println("-----------------------");
		System.out.println(Arrays.toString(fibonacci(10)));
	}
	//---------------求通项值----------------//
	/**
	 * 递归法
	 * 计算斐波那契数列第n个数值，n从0开始
	 * @param index
	 * @return
	 */
	public static int calFibonacciNum1(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("fibonacc index must over zero");
		}
		if (index == 0 || index == 1) {
			return 1;
		}
		return calFibonacciNum1(index - 1) + calFibonacciNum1(index - 2);
	}
	
	/**
	 * 1,1,2,3,5
	 * 迭代法
	 * @param index
	 * @return
	 */
	public static int calFibonacciNum2(int index) {
		
		if (index == 0 || index == 1) {
			return 1;
		}
		
		int fib0 = 1;
		int fib1 = 1;
		int current = 0;
		for (int i = 1; i < index; i++) {
			current = fib0 + fib1;
			fib0 = fib1;
			fib1 = current;
		}
		return current;
	}
	/**
	 * 1,1,2,3,5
	 * 迭代法
	 * @param index
	 * @return
	 */
	public static int calFibonacciNum_2(int index) {
		
		if (index == 0 || index == 1) {
			return 1;
		}
		
		int[] arr = new int[index + 1];
		arr[0] = 1;
		arr[1] = 1;
		for (int i = 2; i <= index; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[index];
	}
	/**
	 * 通项公式法
	 * fib(n) = [(1＋√5)/2]^n /√5 － [(1－√5)/2]^n /√5 (n=1,2,3.....）
	 * @param index
	 * @return
	 */
	public static int calFibonacciNum3(int index) {
		if (index == 0 || index == 1) {
			return 1;
		}
		int n = index + 1;
		return  (int) (Math.pow(((1 + Math.sqrt(5)) / 2.0), n) / Math.sqrt(5) - Math.pow(((1 - Math.sqrt(5)) / 2.0),n) / Math.sqrt(5));
	}
	
	//-------------- 求数组----------------//
	
	public static int[] fibonacci(int index) {
		
		if (index == 0) {
			return new int[]{1};
		}
		if (index == 1) {
			return new int[]{1, 2};
		}
		
		//size = index + 1
		int[] arr = new int[index + 1];
		arr[0] = 1;
		arr[1] = 1;
		for (int i = 2; i <= index; i++) {
			arr[i] = arr[i-2] + arr[i-1];
		}
		
		return arr;
	}
	
	
	
}
