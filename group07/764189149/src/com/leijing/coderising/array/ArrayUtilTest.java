package com.leijing.coderising.array;

import org.junit.Test;

public class ArrayUtilTest {
	private ArrayUtil arrayUtil = new ArrayUtil();

	@Test
	public void testReverseArray() {
		int[] origin = {7, 9 , 30, 3 , 18 , 21};
		arrayUtil.printArray(origin,"before:");
		
		try {
			arrayUtil.reverseArray(origin);
			arrayUtil.printArray(origin , "after:");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testRemoveZero() {
		 int[] oldArray={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};  
		 arrayUtil.printArray(oldArray , "before:");
		 int[] newArray = arrayUtil.removeZero(oldArray);
		 arrayUtil.printArray(newArray , "after:");
	}

	@Test
	public void testMerge() {
		int[] array1 = {3, 5, 7,8};
		int[] array2 = {4, 5, 6,7}; 
		arrayUtil.printArray(array1 , "array1:");
		arrayUtil.printArray(array2 , "array2:");
		int[] newArray = arrayUtil.merge(array1, array2);
		arrayUtil.printArray(newArray , "mergre after:");
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6}; 
		int size = 3;
		arrayUtil.printArray(oldArray , "grow before:");
		int[] newArray = arrayUtil.grow(oldArray, size);
		arrayUtil.printArray(newArray , "grow after:");
	}

	@Test
	public void testFibonacci() {
		int max = 20;
		int[] array = arrayUtil.fibonacci(max);
		arrayUtil.printArray(array , max + "以内的所有斐波那契数列:");
	}

	@Test
	public void testGetPrimes() {
		int max = 100;
		int[] array = arrayUtil.getPrimes(max);
		arrayUtil.printArray(array , max + "以内的所有素数:");
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 1000;
		int[] array = arrayUtil.getPerfectNumbers(max);
		arrayUtil.printArray(array , max + "以内的所有完数:");
	}

	@Test
	public void testJoin() {
		int[] array = {3,8,9};
		String seperator = "-";
		String result = arrayUtil.join(array, seperator);
		System.out.println(result);
	}

}
