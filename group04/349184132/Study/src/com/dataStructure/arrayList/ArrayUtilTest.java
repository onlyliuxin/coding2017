package com.dataStructure.arrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	
	@Before
	public void init(){

	}
	@Test
	public void testReverseArray() {
		int[] origin = {7,9,30,3};
		int[] end = {3,30,9,7};
		
		int[] origin1 = {1,2,3,4,5};
		int[] end1 = {5,4,3,2,1};
		
		
		Assert.assertArrayEquals(end,ArrayUtil.reverseArray(origin));
		Assert.assertArrayEquals(end1,ArrayUtil.reverseArray(origin1));
	}

	@Test
	public void testRemoveZero() {
		
		int[] oldArr = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArr = {1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(newArr, ArrayUtil.removeZero(oldArr));
	}

	@Test
	public void testMerge() {
		int[] a1 = {3,5,7,8};
		int[] a2 = {4,5,6,7};
		int[] a3 ={3,4,5,6,7,8};
		System.out.println(ArrayUtil.merge(a1, a2).length);
		Assert.assertArrayEquals(a3, ArrayUtil.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		int[] oldArr = {2,3,6};
		int[] newArr = {2,3,6,0,0,0};
		Assert.assertArrayEquals(newArr, ArrayUtil.grow(oldArr, 3));
	}

	@Test
	public void testFibonacci() {
		int[] fibonacci ={1,1,2,3,5,8,13,21};
		int max = 22;
		Assert.assertArrayEquals(fibonacci, ArrayUtil.fibonacci(max));
		int max1 = 1;
		int[] fibonacci1 = {};
		Assert.assertArrayEquals(fibonacci1, ArrayUtil.fibonacci(max1));
		
	}

	@Test
	public void testGetPrimes() {
		int[] primes = {2,3,5,7,11,13,17,19};
		int max = 23;
		Assert.assertArrayEquals(primes, ArrayUtil.getPrimes(27));
	}

	@Test
	public void testGetPerfectNumbers() {
		int perfectNumber = 6;
		int[] perfectArr = {1,2,3};
		Assert.assertArrayEquals(perfectArr, ArrayUtil.getPerfectNumbers(perfectNumber));
	}

	@Test
	public void testJoin() {
		String str = "1-2-4";
		int[] array = {1,2,4};
		Assert.assertEquals(str,ArrayUtil.join(array, "-"));
	}

}
