package com.tiaozaoj;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	public ArrayUtil util = new ArrayUtil();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] array1 = {7,9,30,3};
		int[] array2 = {3,30,9,7};
		Assert.assertArrayEquals(array2, util.reverseArray(array1));
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int except[] = {1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(except,util.removeZero(oldArr));
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] a3 = {3,4,5,6,7,8};
		Assert.assertArrayEquals(a3, util.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		int[] a = {2,3,6};
		int[] b = {2,3,6,0,0,0};
		int size = 3;
		Assert.assertArrayEquals(b,util.grow(a,size));
	}

	@Test
	public void testFibonacci() {
		int[] b = {1,1,2,3,5,8,13};
		Assert.assertArrayEquals(b,util.fibonacci(15));
	}

	@Test
	public void testGetPrimes() {
		int[] b = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(b,util.getPrimes(23));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] b = {6,28,496};
		Assert.assertArrayEquals(b,util.getPerfectNumbers(1000));
	}

	@Test
	public void testJoin() {
		int[] b = {6,28,496};
		System.out.println(util.join(b, "-"));
		Assert.assertEquals("6-28-496", util.join(b, "-"));
	}

}
