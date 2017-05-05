package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReverseArray() {
			int[] a = { 7, 9, 30, 3 };
			int[] b = ArrayUtil.reverseArray(a);
			int[] c = ArrayUtil.reverseArray(b);
			assertArrayEquals(a, c);
	}

	@Test
	public void testRemoveZero() {
		 int[] arrOld={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		 int[] arrTarget={1,3,4,5,6,6,5,4,7,6,7,5};
		 int[] arrResult =ArrayUtil.removeZero(arrOld);
		 assertArrayEquals(arrTarget,arrResult);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] arrTarget = {3,4,5,6,7,8};
		int[] arrResult = ArrayUtil.merge(a1, a2);
		assertArrayEquals(arrTarget, arrResult);
	}

	@Test
	public void testGrow() {
		int[] arrOld = {2,3,6};
		int[] arrTarget = {2,3,6,0,0,0};
		int[] arrResult = ArrayUtil.grow(arrOld, 3);
		assertArrayEquals(arrTarget, arrResult);
	}

	@Test
	public void testFibonacci() {
		int max = 1;
		assertNull(ArrayUtil.fibonacci(max));
		max =15;
		int[] arrTarget = {1,1,2,3,5,8,13};
		int[] arrResult = ArrayUtil.fibonacci(max);
		assertArrayEquals(arrTarget,arrResult);
	}

	@Test
	public void testGetPrimes() {
		int max = 1;
		assertNull(ArrayUtil.getPrimes(max));
		max =23;
		int[] arrTarget = {2,3,5,7,11,13,17,19};
		int[] arrResult = ArrayUtil.getPrimes(max);
		assertArrayEquals(arrTarget,arrResult);
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 5;
		assertNull(ArrayUtil.getPerfectNumbers(max));
		max =8129;
		int[] arrTarget = {6,28,496,8128};
		int[] arrResult = ArrayUtil.getPerfectNumbers(max);
		assertArrayEquals(arrTarget,arrResult);
	}

	@Test
	public void testJoin() {
		int[] arrOld ={3,8,9};
		String target = "3-8-9";
		String result = ArrayUtil.join(arrOld, "-");
		assertEquals(target, result);
	}

}
