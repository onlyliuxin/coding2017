package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayUtilsTest {
	
	int[] myarray = {7, 9, 30, 3, 4};

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testReverseArray() {
		int[] reverse = {4, 3, 30 , 9, 7};
		MyArrayUtils.reverseArray(myarray);
		Assert.assertArrayEquals(reverse, myarray);
	}

	@Test
	public void testRemoveZero() {
		int[] includeZero = {0, 3, 0 , 9, 0};
		int[] result = {3, 9};
		int[] excludeZero = MyArrayUtils.removeZero(includeZero);
		Assert.assertArrayEquals(result, excludeZero);
		
		int[] allZero = {0, 0, 0, 0, 0};
		Assert.assertArrayEquals(new int[0], MyArrayUtils.removeZero(allZero));
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8,9,10};
		int[] a2 = {4, 5, 6,7};  
		int[] a3 = {3,4,5,6,7,8,9,10};
		Assert.assertArrayEquals(a3, MyArrayUtils.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		int[] newArray = {7, 9, 30, 3, 4, 0, 0, 0};
		Assert.assertArrayEquals(newArray, MyArrayUtils.grow(myarray, 3));
	}

	@Test
	public void testFibonacci() {
		int[] results = {1, 1, 2, 3, 5, 8, 13};
		Assert.assertArrayEquals(results, MyArrayUtils.fibonacci(15));
	}

	@Test
	public void testGetPrimes() {
		int[] results = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(results, MyArrayUtils.getPrimes(23));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] results = {6, 28, 496};
		Assert.assertArrayEquals(results, MyArrayUtils.getPerfectNumbers(500));
	}

	@Test
	public void testJoin() {
		Assert.assertEquals("7-9-30-3-4", MyArrayUtils.join(myarray, "-"));
	}

}
