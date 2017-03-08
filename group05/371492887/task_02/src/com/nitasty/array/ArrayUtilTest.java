package com.nitasty.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	ArrayUtil util;
	@Before
	public void setUp() throws Exception {
		
		util=new ArrayUtil();
		
		
		int[] a1={3, 5,7,9,10};
		int[] a2={4,6,100,111,132}; 
		
//		System.out.println(Arrays.toString(origin));
//		System.out.println(Arrays.toString(util.removeZero(oldArray)));
//		System.out.println(Arrays.toString(util.merge(a1,a2)));
//		System.out.println(Arrays.toString(util.grow(a1,3)));
//		System.out.println(Arrays.toString(util.fibonacci(100)));
//		System.out.println(Arrays.toString(util.getPrimes(100)));
//		System.out.println(Arrays.toString(util.getPerfectNumbers(1000)));
//		System.out.println(util.join(oldArray,"--"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] origin={7, 9 ,10, 30, 3};
		int[] later={3,30,10,9,7};
		util.reverseArray(origin);
		assertArrayEquals(later, origin);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArray={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray={1,3,4,5,6,6,5,4,7,6,7,5};
		assertArrayEquals(newArray, util.removeZero(oldArray));
	}

	@Test
	public void testMerge() {
		int[] a1={3, 5,7,9,10};
		int[] a2={4,6,100,111,132}; 
		int[] result={3,4,5,6,7,9,10,100,111,132};
		assertArrayEquals(result, util.merge(a1,a2));
	}

	@Test
	public void testGrow() {
		int[] a1={3, 5,7,9,10};
		int[] result={3,5,7,9,10,0,0,0};
		assertArrayEquals(result, util.grow(a1,3));
	}

	@Test
	public void testFibonacci() {
		int[] result={1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
		assertArrayEquals(result, util.fibonacci(100));
	}

	@Test
	public void testGetPrimes() {
		int[] result={1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
		assertArrayEquals(result, util.getPrimes(100));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] result={6, 28, 496};
		assertArrayEquals(result, util.getPerfectNumbers(1000));
	}

	@Test
	public void testJoin() {
		int[] oldArray={1,2,3,4,5};
		String result="1-2-3-4-5";
		assertEquals(result,util.join(oldArray, "-"));
	}

}
