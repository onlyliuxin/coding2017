package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] test1 = {12,3,1,5,6,4};
		int[] test2 = {2,43,6,8,90};
		int[] test3 = {1,2,3,4,5,6,7,8,9};
		int[] test1Expect = {4,6,5,1,3,12};
		int[] test2Expect = {90,8,6,43,2};
		int[] test3Expect = {9,8,7,6,5,4,3,2,1};
		Assert.assertArrayEquals(test1Expect, new ArrayUtil().reverseArray(test1));
		Assert.assertArrayEquals(test2Expect, new ArrayUtil().reverseArray(test2));
		Assert.assertArrayEquals(test3Expect, new ArrayUtil().reverseArray(test3));
	}

	@Test
	public void testRemoveZero() {
		int[] test1 ={1,0,2,0,3,0,19999,0,32323,2356};
		int[] test1Expect ={1,2,3,19999,32323,2356};
		Assert.assertArrayEquals(test1Expect, new ArrayUtil().removeZero(test1));
	}

	@Test
	public void testMerge() {
		int[] a0 ={};
		int[] a1 ={3,5,7,8};
		int[] a2 ={4,5,6,7};
		int[] a3 ={1,2,3,4,5,6,7,8,9};
		int[] expectTest1 = {3,4,5,6,7,8};
		int[] expectTest2 = {1,2,3,4,5,6,7,8,9};
		int[] expectTest3 = {3,5,7,8};
		Assert.assertArrayEquals(expectTest1, new ArrayUtil().merge(a1, a2));
		Assert.assertArrayEquals(expectTest2, new ArrayUtil().merge(a1, a3));
		Assert.assertArrayEquals(expectTest2, new ArrayUtil().merge(a2, a3));
		Assert.assertArrayEquals(expectTest3, new ArrayUtil().merge(a0, a1));
		}

	@Test
	public void testGrow() {
		int[] test1 = {4,6,5,1,3,12};
		int size1 = 5;
		int[] expectTest1 = {4,6,5,1,3,12,0,0,0,0,0};
		Assert.assertArrayEquals(expectTest1, new ArrayUtil().grow(test1, size1));
	}

	@Test
	public void testFibonacci() {
		int[] expectTest1 = {1,1,2,3,5,8};
		int[] expectTest2 = {1,1,2,3,5,8,13,21};
		Assert.assertArrayEquals(expectTest1, new ArrayUtil().fibonacci(12));
		Assert.assertArrayEquals(expectTest2, new ArrayUtil().fibonacci(21));
		Assert.assertArrayEquals(expectTest2, new ArrayUtil().fibonacci(22));
	}

	@Test
	public void testGetPrimes() {
		int[] expectTest1={2,3,5,7};
		int[] expectTest2={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
		int[] expectTest3=null;
		int[] expectTest4={2};
		int[] expectTest5={2,3};
		Assert.assertArrayEquals(expectTest1, new ArrayUtil().getPrimes(10));
		Assert.assertArrayEquals(expectTest2, new ArrayUtil().getPrimes(100));
		Assert.assertArrayEquals(expectTest3, new ArrayUtil().getPrimes(1));
		Assert.assertArrayEquals(expectTest4, new ArrayUtil().getPrimes(2));
		Assert.assertArrayEquals(expectTest5, new ArrayUtil().getPrimes(3));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] expectTest1={6};
		int[] expectTest2={6,28};
		int[] expectTest3={6,28,496};
		int[] expectTest4={};
		Assert.assertArrayEquals(expectTest1, new ArrayUtil().getPerfectNumbers(6));
		Assert.assertArrayEquals(expectTest1, new ArrayUtil().getPerfectNumbers(10));
		Assert.assertArrayEquals(expectTest2, new ArrayUtil().getPerfectNumbers(28));
		Assert.assertArrayEquals(expectTest3, new ArrayUtil().getPerfectNumbers(1000));
		Assert.assertArrayEquals(expectTest4, new ArrayUtil().getPerfectNumbers(1));
		Assert.assertArrayEquals(expectTest4, new ArrayUtil().getPerfectNumbers(2));
		Assert.assertArrayEquals(expectTest4, new ArrayUtil().getPerfectNumbers(3));
		Assert.assertArrayEquals(expectTest4, new ArrayUtil().getPerfectNumbers(0));
	}
	
	@Test
	public void testJoin() {
		int[] test1={1,2,3,4,6};
		String expected="1-2-3-4-6";
		Assert.assertEquals(expected, new ArrayUtil().join(test1, "-"));
	}

}
