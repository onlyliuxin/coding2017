package com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a = {7, 9 , 30, 3};
		int[] ra = {3, 30, 9,7};
		int[] reverseArray = ArrayUtil.reverseArray(a);
		Assert.assertArrayEquals(ra, reverseArray);
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int newArr[]={1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(newArr, ArrayUtil.removeZero(oldArr));
	}

	@Test
	public void testMerge() {
		int[] a1 = {3,5,7,8};
		int[] a2 = {4,5,6,7};
		int[] a3 = {3,4,5,6,7,8};
		Assert.assertArrayEquals(a3, ArrayUtil.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		int[] newArray = {2,3,6,0,0,0,0};
		Assert.assertArrayEquals(newArray, ArrayUtil.grow(oldArray, 4));
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int[] fibonacci = ArrayUtil.fibonacci(max);
		int[] f = {1,1,2,3,5,8,13};
		Assert.assertArrayEquals(f, fibonacci);
	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		int[] primes = ArrayUtil.getPrimes(max);
		int[] expecteds = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(expecteds, primes);
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 8129;
		int[] perfectNumbers = ArrayUtil.getPerfectNumbers(max);
		int[] expected = {6,28,496,8128};
		Assert.assertArrayEquals(expected, perfectNumbers);
	}

	@Test
	public void testJoin() {
		int[] array = {3,8,9};
		String sep = "-";
		String join = ArrayUtil.join(array, sep);
		Assert.assertEquals("3-8-9", join);
	}

}
