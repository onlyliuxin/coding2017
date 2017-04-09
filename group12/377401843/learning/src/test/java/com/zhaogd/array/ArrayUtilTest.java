package com.zhaogd.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	private ArrayUtil arrayUtil = null;

	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] origin = new int[] { 7, 9, 30, 3 };
		arrayUtil.reverseArray(origin);
		assertArrayEquals(origin, new int[] { 3, 30, 9, 7 });
	}

	@Test
	public void testRemoveZero() {
		int oldArr[] = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int[] newArr = arrayUtil.removeZero(oldArr);
		assertArrayEquals(newArr, new int[] { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 });
	}

	@Test
	public void testMerge() {
		int arr1[] = { 3, 5, 7, 8 };
		int arr2[] = { 4, 5, 6, 7 };
		int[] arr3 = arrayUtil.merge(arr1, arr2);

		assertArrayEquals(arr3, new int[] { 3, 4, 5, 6, 7, 8 });
	}

	@Test
	public void testGrow() {
		int[] arr = { 2, 3, 6 };
		int[] newArr = arrayUtil.grow(arr, 3);
		assertArrayEquals(newArr, new int[] { 2, 3, 6, 0, 0, 0 });
	}

	@Test
	public void testFibonacci() {
		assertArrayEquals(new int[] {}, arrayUtil.fibonacci(1));
		assertArrayEquals(new int[] { 1, 1, 2, 3, 5, 8, 13 }, arrayUtil.fibonacci(15));
	}

	@Test
	public void testGetPrimes() {
		assertArrayEquals(new int[] { 2, 3, 5, 7, 11, 13, 17, 19 }, arrayUtil.getPrimes(23));
	}

}
