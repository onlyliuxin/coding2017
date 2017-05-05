package com.coderising.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coderising.array.ArrayUtil;

public class ArrayUtilTest {
	
	private ArrayUtil arrayUtil;
	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] array = new int[]{7, 9, 30, 3, 4};
		arrayUtil.reverseArray(array);
		assertArrayEquals(new int[]{4,3,30,9,7}, array);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArray = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray = arrayUtil.removeZero(oldArray);
		assertEquals(12, newArray.length);
		assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, newArray);
	}

	@Test
	public void testMerge() {
		int[] array1 = new int[]{3, 5, 7,8};
		int[] array2 = new int[]{4, 5, 6,7};
		int[] merge = arrayUtil.merge(array1, array2);
		assertArrayEquals(new int[]{3,4,5,6,7,8}, merge);
		assertEquals(6, merge.length);
	}

	@Test
	public void testGrow() {
		int[] array = new int[]{2,3,6};
		int[] grow = arrayUtil.grow(array, 3);
		assertArrayEquals(new int[]{2,3,6,0,0,0}, grow);
		assertEquals(6, grow.length);
	}

	@Test
	public void testFibonacci() {
		int[] fibonacci = arrayUtil.fibonacci(15);
		assertArrayEquals(new int[]{1,1,2,3,5,8,13}, fibonacci);
		assertEquals(7, fibonacci.length);
	}

	@Test
	public void testGetPrimes() {
		int[] primes = arrayUtil.getPrimes(23);
		assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, primes);
		assertEquals(8, primes.length);
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] perfectNumbers = arrayUtil.getPerfectNumbers(1000);
		assertArrayEquals(new int[]{6,28,496}, perfectNumbers);
	}

	@Test
	public void testJoin() {
		String join = arrayUtil.join(new int[]{3,8,9}, "-");
		assertEquals("3-8-9", join);
	}

}
