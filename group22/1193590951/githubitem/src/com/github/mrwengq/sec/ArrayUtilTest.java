package com.github.mrwengq.sec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil au = null;
	@Before
	public void setUp() throws Exception {
		au = new ArrayUtil();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] origin = new int[]{7, 9 , 30, 3,17};
		au.reverseArray(origin);
		int[] b = new int[]{17,3, 30, 9,7};
		assertArrayEquals(origin, b);
		
	}

	@Test
	public void testRemoveZero() {
		int[] oldArray = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] a = au.removeZero(oldArray);
		int[] b = new int[]{1,3,4,5,6,6,5,4,7,6,7,5};
		assertArrayEquals(a, b);
	}

	@Test
	public void testMerge() {
		int[] array1 = {3, 5, 7,8};
		int[] array2 = {4, 5, 6,7};
		int[] a =  au.merge(array1, array2);
		int[] b = new int[]{3,4,5,6,7,8};
		assertArrayEquals(a, b);
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		int[] a = au.grow(oldArray, 3);
		int[] b = new int[]{2,3,6,0,0,0};
		assertArrayEquals(a, b);
	}

	@Test
	public void testFibonacci() {
		int[] a = au.fibonacci(15);
		int[] b = {1,1,2,3,5,8,13};
		assertArrayEquals(a, b);
		int[] c = au.fibonacci(22);
		int[] d = {1,1,2,3,5,8,13,21};
		assertArrayEquals(c, d);
		int[] e = au.fibonacci(12);
		int[] f = {1,1,2,3,5,8};
		assertArrayEquals(e, f);
	}

	@Test
	public void testGetPrimes() {
		int[] a = au.getPrimes(23);
		int[] b = {2,3,5,7,11,13,17,19};
		assertArrayEquals(a, b);
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] a = au.getPerfectNumbers(7);
		int[] b = {6};
		assertArrayEquals(a, b);
		int[] c = au.getPerfectNumbers(50);
		int[] d = {6,28};
		assertArrayEquals(c, d);
	}

	@Test
	public void testJoin() {
		int[] array = {3, 5, 7,8}; 
		String a = au.join(array, "**");
		String b = "3**5**7**8";
		assertEquals(a,b);
		
	}

}
