package com.coderising.array;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil arrayUtil;
	@Before
	public void setUp() throws Exception {
		arrayUtil =new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a1={7, 9, 30, 3,4};
		int[] a2={7,9,30,3};
		arrayUtil.reverseArray(a1);
		arrayUtil.reverseArray(a2);
		System.out.println("testReverseArray");
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray = arrayUtil.removeZero(oldArr);
		System.out.println("testRemoveZero");
		System.out.println(Arrays.toString(newArray));
	}

/*	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8};   
		int[] a2 = {4, 5, 6,7};
		int[] a3 = arrayUtil.merge(a1, a2);
		System.out.println("testMerge");
		System.out.println(Arrays.toString(a3));
	}*/

	@Test
	public void testGrow() {
		int[] a1 = {3, 5, 7,8}; 
		int size=3;
		int[] a2 = arrayUtil.grow(a1, size);
		System.out.println("testGrow");
		System.out.println(Arrays.toString(a2));
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int[] a = arrayUtil.fibonacci(max);
		System.out.println("testFibonacci");
		System.out.println(Arrays.toString(a));
	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		int[] a = arrayUtil.getPrimes(max);
		System.out.println("testGetPrimes");
		System.out.println(Arrays.toString(a));
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 80;
		int[] a = arrayUtil.getPerfectNumbers(max);
		System.out.println("testGetPerfectNumbers");
		System.out.println(Arrays.toString(a));
	}

	@Test
	public void testJoin() {
		int[] a1 = {3, 5, 7,8};  
		String seperator = "-";
		String a = arrayUtil.join(a1, seperator);
		System.out.println("testJoin");
		System.out.println(a);
	}

}
