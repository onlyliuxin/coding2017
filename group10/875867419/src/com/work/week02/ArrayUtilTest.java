package com.work.week02;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] a = {7, 9 , 30, 3};
		int[] b = {3, 30, 9,7};
		Assert.assertArrayEquals(b, ArrayUtil.reverseArray(a));
	}

	@Test
	public void testRemoveZero() {
		 int[] oldArr={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		 int[] newArr={1,3,4,5,6,6,5,4,7,6,7,5};
		 Assert.assertArrayEquals(newArr, ArrayUtil.removeZero(oldArr));
	}

	@Test
	public void testMerge() {
		int[] a = {3, 5, 7,8};
		int[] b = {4, 5, 6,7};
		int[] c = {3,4,5,6,7,8};
		Assert.assertArrayEquals(c, ArrayUtil.merge(a, b));
	}

	@Test
	public void testGrow() {
		int[] oldArr = {2,3,6};
		int size = 3;
		int[] newArr = {2,3,6,0,0,0};
		Assert.assertArrayEquals(newArr, ArrayUtil.grow(oldArr, size));
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int[] expecteds = {1,1,2,3,5,8,13};
		Assert.assertArrayEquals(expecteds, ArrayUtil.fibonacci(max));
	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		int[] expecteds = {2,3,5,7,11,13,17,19,23};
		Assert.assertArrayEquals(expecteds, ArrayUtil.getPrimes(max));
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 1000;
		int[] expecteds = {6,28,496};
		Assert.assertArrayEquals(expecteds, ArrayUtil.getPerfectNumbers(max));
	}

	@Test
	public void testJoin() {
		int[] array = {3,8,9};
		String seperator = "-";
		String expecteds = "3-8-9";
		Assert.assertEquals(expecteds, ArrayUtil.join(array, seperator));
	}

}
