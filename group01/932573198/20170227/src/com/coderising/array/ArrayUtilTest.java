package com.coderising.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	
	ArrayUtil util = null;

	@Before
	public void setUp() throws Exception {
		util = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] a = {1,2,3,4};
		int[] b = {4,3,2,1};
		util.reverseArray(a);
		Assert.assertArrayEquals(b, a);
		int[] c = {1,2,3,4,5};
		int[] d = {5,4,3,2,1};
		util.reverseArray(c);
		Assert.assertArrayEquals(d, c);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArr = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray = {1,3,4,5,6,6,5,4,7,6,7,5};
		int[] newArr = util.removeZero(oldArr);
		Assert.assertArrayEquals(newArray, newArr);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7, 8};
		int[] a2 = {4, 5, 6, 7};
		int[] newArray = {3,4,5,6,7,8};
		int[] newArr = util.merge(a1, a2);
		Assert.assertArrayEquals(newArray, newArr);
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		int size = 3;
		int[] newArray = {2,3,6,0,0,0};
		int[] newArr = util.grow(oldArray, size);
		Assert.assertArrayEquals(newArray, newArr);
	}

	@Test
	public void testFibonacci() {
		int[] arr1 = {1,1,2,3,5,8,13};
		Assert.assertArrayEquals(arr1, util.fibonacci(15));
		int[] arr2 = new int[0];
		Assert.assertArrayEquals(arr2, util.fibonacci(1));
	}

	@Test
	public void testGetPrimes() {
		int[] arr1 = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(arr1, util.getPrimes(23));
		int[] arr2 = new int[0];
		Assert.assertArrayEquals(arr2, util.getPrimes(2));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] arr = {6, 28, 496};
		Assert.assertArrayEquals(arr, util.getPerfectNumbers(497));
	}

	@Test
	public void testJoin() {
		int[] arr = {1,2,3,4,5};
		String s = "1-2-3-4-5";
		Assert.assertEquals(s, util.join(arr, "-"));
	}

}
