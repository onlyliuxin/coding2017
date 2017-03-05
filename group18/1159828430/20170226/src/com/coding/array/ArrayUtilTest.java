package com.coding.array;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author Scholar
 * @Time：2017年3月4日 上午9:12:27
 * @version 1.0
 */
public class ArrayUtilTest {

	@Test
	public void testReverseArray(){ 
		int[] oldArr = {7, 9 , 30, 3};
		int[] newArr = ArrayUtil.reverseArray(oldArr);
		int[] resultArr = {3, 30 , 9, 7};
		assertArrayEquals(resultArr, newArr);
		
	}

	@Test
	public void testRemoveZero() {
		int[] oldArr = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArr = ArrayUtil.removeZero(oldArr);
		int[] resultArr = {1,3,4,5,6,6,5,4,7,6,7,5};
		assertArrayEquals(resultArr, newArr);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3,5,7,8};
		int[] a2 = {4, 5, 6,7};
		int[] newArr = ArrayUtil.merge(a1, a2);
		int[] resultArr = {3,4,5,6,7,8};
		assertArrayEquals(resultArr, newArr);
	}

	@Test
	public void testGrow() {
		int[] oldArr = {2,3,6};
		int[] newArr = ArrayUtil.grow(oldArr, 3);
		int[] resultArr = {2,3,6,0,0,0};
		assertArrayEquals(resultArr, newArr);
	}

	@Test
	public void testFibonacci() {
		int[] newArr = ArrayUtil.fibonacci(15);
		int[] resultArr = {1, 1, 2, 3, 5, 8, 13};
		assertArrayEquals(resultArr, newArr);
	}

	@Test
	public void testGetPrimes() {
		int[] newArr = ArrayUtil.getPrimes(23);
		int[] resultArr = {2,3,5,7,11,13,17,19};
		assertArrayEquals(resultArr, newArr);
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] newArr = ArrayUtil.getPerfectNumbers(500);
		int[] resultArr = {6, 28, 496};
		assertArrayEquals(resultArr, newArr);
	}

	@Test
	public void testJoin() {
		int[] oldArr = {3,8,9};
		String resultStr = ArrayUtil.join(oldArr, "-");
		String str = "3-8-9";
		assertEquals(str, resultStr);
	}

}
