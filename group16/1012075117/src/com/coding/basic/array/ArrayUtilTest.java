package com.coding.basic.array;

import org.junit.Test;

public class ArrayUtilTest {

	ArrayUtil arrayUtil = new ArrayUtil();

	@Test
	public void testReverseArray() {
		int[] origin = { 7, 9, 30, 3 };
		System.out.println("原数组:");
		for (int i : origin) {
			System.out.print(i + ",");
		}

		arrayUtil.reverseArray(origin);

		System.out.println();
		System.out.println("置换后的结果:");
		for (int i : origin) {
			System.out.print(i + ",");
		}
	}

	@Test
	public void testRemoveZero() {
		int oldArr[] = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		System.out.println("原数组:");
		for (int i : oldArr) {
			System.out.print(i + ",");
		}

		arrayUtil.removeZero(oldArr);

		System.out.println();
		System.out.println("去0后的结果:");
		for (int i : arrayUtil.removeZero(oldArr)) {
			System.out.print(i + ",");
		}
	}

	@Test
	public void testMerge() {
		int[] a1 = { 3, 5, 7, 8 };
		int[] a2 = { 4, 5, 6, 7 };

		System.out.println("a1数组:");
		for (int i : a1) {
			System.out.print(i + ",");
		}
		System.out.println();
		System.out.println("a2数组:");
		for (int i : a2) {
			System.out.print(i + ",");
		}

		arrayUtil.merge(a1, a2);

		System.out.println();
		System.out.println("合并后结果:");
		for (int i : arrayUtil.merge(a1, a2)) {
			System.out.print(i + ",");
		}
	}

	@Test
	public void testGrow() {
		int[] oldArray = { 2, 3, 6 };
		int size = 3;
		int[] temp;

		System.out.println("原数组:");
		for (int i : oldArray) {
			System.out.print(i + ",");
		}

		temp = arrayUtil.grow(oldArray, size);

		System.out.println();
		System.out.println("扩展后的结果:");
		for (int i : temp) {
			System.out.print(i + ",");
		}

	}

	@Test
	public void testFibonacci() {
		int max = 15;
		System.out.println("给定一个最大值:max=" + max);

		int[] array = arrayUtil.fibonacci(max);

		System.out.println("返回的数组:");
		if (array != null) {
			for (int i : array) {
				System.out.print(i + ",");
			}
		} else {
			System.out.println("[]");
		}

	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		System.out.println("给定一个最大值:max=" + max);
		
		int[] array = arrayUtil.getPrimes(max);
		
		System.out.println("返回的数组:");
		if (array != null) {
			for (int i : array) {
				System.out.print(i + ",");
			}
		} else {
			System.out.println("[]");
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 50;
		System.out.println("给定一个最大值:max=" + max);
		
		int[] array = arrayUtil.getPerfectNumbers(max);
		
		System.out.println("返回的数组:");
		if (array != null) {
			for (int i : array) {
				System.out.print(i + ",");
			}
		} else {
			System.out.println("[]");
		}
	}

	@Test
	public void testJoin() {
		int[] array = {3,8,9};
		String seperator = "-";
		System.out.println("原数组:");
		for (int i : array) {
			System.out.print(i + ",");
		}
		System.out.println();
		System.out.println("seperator:" + seperator);
		
		String result = arrayUtil.join(array, seperator);
		
		System.out.println("返回的值:");
		if (result != null) {
			System.out.print(result);
		} else {
			System.out.println("传入的参数非法!!!");
		}
	}

}
