package com.leijing.coderising.array;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {
	private ArrayUtil arrayUtil = new ArrayUtil();

	@Test
	public void testReverseArray() {
		int[] origin = {7, 9 , 30, 3 , 18 , 21};
		int[] expecteds = {21,18,3,30,9,7};
		arrayUtil.printArray(origin,"before:");
		
		try {
			arrayUtil.reverseArray(origin);
			arrayUtil.printArray(origin , "after:");
			Assert.assertArrayEquals(expecteds, origin);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testRemoveZero() {
		 int[] oldArray={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};  
		 int[] expecteds = {1,3,4,5,6,6,5,4,7,6,7,5};
		 arrayUtil.printArray(oldArray , "before:");
		 int[] newArray = arrayUtil.removeZero(oldArray);
		 arrayUtil.printArray(newArray , "after:");
		 Assert.assertArrayEquals(expecteds, newArray);
	}

	@Test
	public void testMerge() {
		int[] array1 = {3, 5, 7,8};
		int[] array2 = {4, 5, 6,7}; 
		int[] expecteds = {3,4,5,6,7,8};
		arrayUtil.printArray(array1 , "array1:");
		arrayUtil.printArray(array2 , "array2:");
		int[] newArray = arrayUtil.merge(array1, array2);
		arrayUtil.printArray(newArray , "mergre after:");
		Assert.assertArrayEquals(expecteds, newArray);
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6}; 
		int[] expecteds ={2,3,6,0,0,0}; 
		int size = 3;
		arrayUtil.printArray(oldArray , "grow before:");
		int[] newArray = arrayUtil.grow(oldArray, size);
		arrayUtil.printArray(newArray , "grow after:");
		Assert.assertArrayEquals(expecteds, newArray);
	}

	@Test
	public void testFibonacci() {
		int max = 20;
		int[] expecteds = {1,1,2,3,5,8,13,21};
		int[] array = arrayUtil.fibonacci(max);
		arrayUtil.printArray(array , max + "以内的所有斐波那契数列:");
		Assert.assertArrayEquals(expecteds, array);
	}

	@Test
	public void testGetPrimes() {
		int max = 100;
		int[] expecteds = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
		int[] array = arrayUtil.getPrimes(max);
		arrayUtil.printArray(array , max + "以内的所有素数:");
		Assert.assertArrayEquals(expecteds, array);
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 1000;
		int[] expecteds = {6,28,496};
		int[] array = arrayUtil.getPerfectNumbers(max);
		arrayUtil.printArray(array , max + "以内的所有完数:");
		Assert.assertArrayEquals(expecteds, array);
	}

	@Test
	public void testJoin() {
		int[] array = {3,8,9};
		String seperator = "-";
		String expectedString = "3-8-9";
		String result = arrayUtil.join(array, seperator);
		System.out.println(result);
		Assert.assertEquals(expectedString, result);
	}
}
