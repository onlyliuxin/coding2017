package com.coderising.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	private ArrayUtil arrayUtil;
	@Before
	public void init() {
		arrayUtil = new ArrayUtil();
	}
	@After
	public void finish() {
		arrayUtil = null;
	}
	
	@Test
	public void testReverseArray() {
		int[] origin = {1,2,3,4,5,6};
		arrayUtil.reverseArray(origin);
		System.out.println(Arrays.toString(origin));
	}

	@Test
	public void testRemoveZero() {
		int[] origin = {0,1,0,2,3,4,5,6,0,0};
		int[] newArr = arrayUtil.removeZero(origin);
		System.out.println(Arrays.toString(newArr));
	}

	@Test
	public void testMerge() {
		int[] array1 = {3,4,5,6,9,12};
		int[] array2 = {1,3,5,6,7,8,9};
		int[] merge = arrayUtil.merge(array1, array2);
		System.out.println(Arrays.toString(merge));
	}

	@Test
	public void testGrow() {
		int[] arr = {1,2,3,4};
		System.out.println(Arrays.toString(arrayUtil.grow(arr, 3)));
	}

	@Test
	public void testFibonacci() {
		int[] fibonacci = arrayUtil.fibonacci(12);
		System.out.println(Arrays.toString(fibonacci));
	}

	@Test
	public void testGetPrimes() {
		int[] primes = arrayUtil.getPrimes(23);
		System.out.println(Arrays.toString(primes));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] perfectNumbers = arrayUtil.getPerfectNumbers(100);
		System.out.println(Arrays.toString(perfectNumbers));
	}

	@Test
	public void testJoin() {
		int[] arr = {1,2,3};
		String join = arrayUtil.join(arr, "-");
		System.out.println(join);
	}

}
