package com.coderising.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil arrayUtil = null;
	@Before
	public void setUp() {
		arrayUtil = new ArrayUtil();
	}
	
	@Test
	public void testReverseArray() {
		int  origin[] = {7, 9, 30, 3, 4};
		int  expecteds[] = {4,3, 30 , 9,7};
		arrayUtil.reverseArray(origin);
		
		Assert.assertArrayEquals(expecteds, origin);
	}
	
	@Test
	public void testRemoveZero() {
		int oldArray[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int  expecteds[] = {1,3,4,5,6,6,5,4,7,6,7,5};
		int newArray[] = arrayUtil.removeZero(oldArray);
		
		Assert.assertArrayEquals(expecteds, newArray);
	}
	
	@Test
	public void testMerge() {
		int array1[] = {3, 5, 7,8};
		int  array2[] = {4, 5, 6,7};
		Integer  expecteds[] = {3,4,5,6,7,8};
		Integer newArray[] = arrayUtil.merge(array1, array2);
		Assert.assertArrayEquals(expecteds, newArray);
	}
	
	@Test
	public void testGrow() {
		int oldArray[] = {2,3,6};
		int  expecteds[] = {2,3,6,0,0,0};
		int newArray[] = arrayUtil.grow(oldArray,3);
		
		Assert.assertArrayEquals(expecteds, newArray);
	}
	
	@Test
	public void testFibonacci() {
		Integer expecteds[] = {1,1,2,3,5,8,13};
		Integer newArray[] = arrayUtil.fibonacci(18);
		
		Assert.assertArrayEquals(arrayUtil.fibonacci(1), new Integer[0]);
		Assert.assertArrayEquals(expecteds, newArray);
	}
	
	@Test
	public void testGetPrimes() {
		Integer expecteds[] = {2,3,5,7,11,13,17,19};
		Integer newArray[] = arrayUtil.getPrimes(23);
		
		Assert.assertArrayEquals(expecteds, newArray);
	}
	
	@Test
	public void testGetPerfectNumbers() {
		Integer array[] = arrayUtil.getPerfectNumbers(100);
		System.out.println(Arrays.toString(array));
	}
	
	@Test
	public void testJoin() {
		int dealedArray[] = {3,8,9};
		String expectedStr = "3-8-9";
		String resultStr = arrayUtil.join(dealedArray, "-");
		Assert.assertEquals("", expectedStr, resultStr);
	}

}
