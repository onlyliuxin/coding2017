package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayUtilsTest {
	
	int[] array = {7, 9, 30, 3, 4};

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testReverseArray() {
		int[] reverse = {4, 3, 30 , 9, 7};
		MyArrayUtils.reverseArray(array);
		Assert.assertArrayEquals(reverse, array);
	}

	@Test
	public void testRemoveZero() {
		int[] includeZero = {0, 3, 0 , 9, 0};
		int[] result = {3, 9};
		int[] excludeZero = MyArrayUtils.removeZero(includeZero);
		Assert.assertArrayEquals(result, excludeZero);
		
		int[] allZero = {0, 0, 0, 0, 0};
		Assert.assertArrayEquals(null, MyArrayUtils.removeZero(allZero));
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8,9,10};
		int[] a2 = {4, 5, 6,7};  
		int[] a3 = {3,4,5,6,7,8,9,10};
		Assert.assertArrayEquals(a3, MyArrayUtils.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		fail("Not yet implemented");
	}

	@Test
	public void testFibonacci() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrimes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPerfectNumbers() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoin() {
		fail("Not yet implemented");
	}

}
