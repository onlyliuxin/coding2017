package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {
	
	ArrayUtil au = new ArrayUtil();
	
	@Test
	public void testReverseArray() {
		int[] a = new int[]{7, 9 , 30, 3};
		au.reverseArray(a);
		Assert.assertArrayEquals(new int[]{3, 30, 9,7}, a);
		int[] b = new int[]{7, 9, 30, 3, 4};
		au.reverseArray(b);
		Assert.assertArrayEquals(new int[]{4,3, 30 , 9,7}, b);
	}

	@Test
	public void testRemoveZero() {
		Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, au.removeZero(new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}));
	}

	@Test
	public void testMerge() {
		Assert.assertArrayEquals(new int[]{3,4,5,6,7,8}, au.merge(new int[]{3, 5, 7,8}, new int[]{4, 5, 6,7}));
	}

	@Test
	public void testGrow() {
		Assert.assertArrayEquals(new int[]{2,3,6,0,0,0},au.grow(new int[]{2,3,6},3));
	}

	@Test
	public void testFibonacci() {
		Assert.assertArrayEquals(new int[]{1,1,2,3,5,8,13}, au.fibonacci(15));
		Assert.assertArrayEquals(new int[]{}, au.fibonacci(1));
	}

	@Test
	public void testGetPrimes() {
		Assert.assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, au.getPrimes(23));
	}

	@Test
	public void testGetPerfectNumbers() {
		Assert.assertArrayEquals(new int[]{6,28}, au.getPerfectNumbers(100));
	}

	@Test
	public void testJoin() {
		Assert.assertEquals("3-8-9", au.join(new int[]{3,8,9}, "-"));
	}

}
