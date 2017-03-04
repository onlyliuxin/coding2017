package com.ikook.array;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	
	private ArrayUtil util;
	
	@Before
	public void setUp() {
		util = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] origin = {7, 9, 30, 3, 4};
		int[] newArray = util.reverseArray(origin);
		int[] temp = {4,3, 30 , 9,7};
		
		for(int i = 0; i < newArray.length; i++) {
			assertEquals(temp[i], newArray[i]);
		}
		
	}

	@Test
	public void testRemoveZero() {
		int[] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray = util.removeZero(oldArray);
		int[] temp = {1,3,4,5,6,6,5,4,7,6,7,5};
		
		for(int i = 0; i < newArray.length; i++) {
			assertEquals(temp[i], newArray[i]);
		}
	}

	@Test
	public void testMerge() {
		int[] m1 = {3, 5, 7, 8};
		int[] m2 = {4, 5, 6, 7};
		int[] m3 = util.merge(m1, m2);
		int[] temp = {3,4,5,6,7,8};
		
		for(int i = 0; i < m3.length; i++) {
			assertEquals(temp[i], m3[i]);
		}
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		int size = 3;
		int[] newArray = util.grow(oldArray, size);
		int[] temp = {2,3,6,0,0,0};
		
		for(int i = 0; i < newArray.length; i++) {
			assertEquals(temp[i], newArray[i]);
		}
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int[] fibo = util.fibonacci(max);
		int[] temp = {1, 1, 2, 3, 5, 8, 13};
		
		for(int i = 0; i < fibo.length; i++) {
			assertEquals(temp[i], fibo[i]);
		}
	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		int[] primes = util.getPrimes(max);
		int[] temp = {2,3,5,7,11,13,17,19};
		
		for(int i = 0; i < primes.length; i++) {
			assertEquals(temp[i], primes[i]);
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 10000;
		int[] perfect = util.getPerfectNumbers(max);
		int[] temp = {6, 28, 496, 8128};
		
		for(int i = 0; i < perfect.length; i++) {
			assertEquals(temp[i], perfect[i]);
		}
	}

	@Test
	public void testJoin() {
		int[] array = {3,8,9};
		String seperator = "-";
	
		String s = util.join(array, seperator);
		
		assertEquals("3-8-9", s);
	}

}
