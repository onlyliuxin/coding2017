package com.github.Ven13.coding2017.array.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.Ven13.coding2017.array.ArrayUtil;

public class ArrayUtilTest {

	@Test
	public final void testMerge() {
		ArrayUtil arrayUtil = new ArrayUtil(); 
		int[] a1 = {3, 5, 7, 8};
		int[] a2 = {4, 5, 6, 7};
		int[] a3 = {};
		a3 = arrayUtil.merge(a1, a2);
		assertEquals(3, a3[0]);
		assertEquals(4, a3[1]);
		assertEquals(5, a3[2]);
		assertEquals(6, a3[3]);
		assertEquals(7, a3[4]);
		assertEquals(8, a3[5]);

	}
	
	@Test
	public final void testgetPrimes() {
		ArrayUtil arrayUtil = new ArrayUtil(); 
		int max = 23;
		int[] a1 = {};
		a1 = arrayUtil.getPrimes(max);
		assertEquals(3, a1.length);
		assertEquals(1, a1[0]);
		assertEquals(2, a1[1]);
		assertEquals(3, a1[2]);
	}
	
	@Test
	public final void testgetPerfectNumbers() {
		ArrayUtil arrayUtil = new ArrayUtil(); 
		int max = 6;
		int[] a1 = {};
		a1 = arrayUtil.getPerfectNumbers(max);
	}
}
