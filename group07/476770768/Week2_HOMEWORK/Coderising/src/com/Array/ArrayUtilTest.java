package com.Array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil au;

	@Before
	public void setUp() throws Exception {
		au = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] origin1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] origin2 = { 1, 2, 3, 4, 5 };
		au.reverseArray(origin1);
		au.reverseArray(origin2);
		assertArrayEquals(new int[] { 8, 7, 6, 5, 4, 3, 2, 1 }, origin1);
		assertArrayEquals(new int[] { 5, 4, 3, 2, 1 }, origin2);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArray = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		assertArrayEquals(new int[] { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 }, au.removeZero(oldArray));
	}

	@Test
	public void testMerge() {
		int[] array1 = {0, 5, 7, 8};
		int[] array2 = {4, 5, 8, 9};
		assertArrayEquals(new int[]{0,4,5,7,8, 9}, au.merge(array1, array2));
				
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		assertArrayEquals(new int[]{2,3,6,0,0,0}, au.grow(oldArray, 3));
	}

	@Test
	public void testFibonacci() {
		assertArrayEquals(new int[]{1,1,2,3,5,8,13}, au.fibonacci(15));
		assertEquals(null, au.fibonacci(1));
	}

	@Test
	public void testGetPrimes() {
		assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, au.getPrimes(23));
		assertEquals(null, au.getPrimes(1));
	}

	@Test
	public void testGetPerfectNumbers() {
		assertArrayEquals(new int[]{6, 28, 496}, au.getPerfectNumbers(1000));
	}

	@Test
	public void testJoin() {
		assertEquals("3-4-5", au.join(new int[]{3,4,5}, "-"));
		assertEquals("3|4|5", au.join(new int[]{3,4,5}, "|"));
	}

}
