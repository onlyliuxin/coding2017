package com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArrayUtilTest {

	private ArrayUtil arrayUtil;
	
	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] oldarray = {2, 0, 9, 6, 5, 23, 12, 9, 78};
		
		int[] newarray = {78, 9, 12, 23, 5, 6, 9, 0, 2};
		int[] result = arrayUtil.reverseArray(oldarray);
		
		Assert.assertEquals(newarray, result);
		
	}

	@Test
	public void testRemoveZero() {
		int[] oldarray = {0, 0, 1, 0, 8, 0, 0, 6, 10, 0, 0, 0};
		
		int[] newarray = {1, 8, 6, 10};
		int[] result = arrayUtil.removeZero(oldarray);
		Assert.assertEquals(newarray, result);
	}

	
	@Test
	public void testMerge() {
		int[] array1 = {3, 5, 7, 8, 9, 10, 12};
		int[] array2 = {4, 5, 6, 7, 12, 13};
		
		int[] newarray = {3, 4, 5, 6, 7, 8, 9, 10, 12, 13};
		int[] result = arrayUtil.merge(array1, array2);
		for(int i = 0; i < result.length; i++)
			Assert.assertEquals(newarray[i], result[i]);
	}

	@Test
	public void testGrow() {
		int[] array = {2, 3, 6, 8, 9};
		int size = 3;
		
		int[] newarray = {2, 3, 6, 8, 9, 0, 0, 0};
		int[] result = arrayUtil.grow(array, size);
		for(int i = 0; i < result.length; i++)
			Assert.assertEquals(newarray[i], result[i]);
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		
		int[] newarray = {1, 1, 2, 3, 5, 8, 13};
		int[] result = arrayUtil.fibonacci(max);
		Assert.assertEquals(newarray, result);
	}

	@Test
	public void testFib() {
		int n = 7;
		
		int newnum = 21;
		int result = arrayUtil.fib(n);
		Assert.assertEquals(newnum, result);
		
	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		
		int[] newarray = {2, 3, 5, 7, 11, 13, 17, 19};
		int[] result = arrayUtil.getPrimes(max);
		Assert.assertEquals(newarray, result);
	}

	@Test
	public void testPrimes() {
		int n = 17;
		
		
		boolean result = arrayUtil.Primes(n);
		Assert.assertTrue(result);
	}

	@Test
	public void testGetPerfectNumbers() {
		
		int max = 6;
		int[] result = arrayUtil.getPerfectNumbers(max);
		
		int[] newarray = {1, 6};
		Assert.assertEquals(newarray, result);
	}

	@Test
	public void testPerfectNumber() {
		int n = 6;
		boolean result = arrayUtil.PerfectNumber(n);
		Assert.assertTrue(result);
	}

	@Test
	public void testJoin() {
		int[] array = {3, 8, 9, 10, 21};
		String str = "3-8-9-10-21";
		String result = arrayUtil.join(array, "-");
		Assert.assertEquals(str, result);

	}

}
