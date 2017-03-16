package com.coderising.array;


import java.util.Arrays;

import org.junit.Test;

public class ArrayUtilTest {
	
	private ArrayUtil util = new ArrayUtil();

	@Test
	public void testReverseArray() {
		int[] a = new int[]{7, 9 , 30, 3};
		int[] b = new int[]{7, 9, 30, 3, 4};
		util.reverseArray(a);
		util.reverseArray(b);
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
	}
	
	@Test
	public void testRemoveZero(){
		int[] a = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		System.out.println(Arrays.toString(util.removeZero(a)));
	}

	@Test
	public void testMerge(){
		int[] a = new int[]{3, 5, 7,8};
		int[] b = new int[]{4, 5, 6,7};
		System.out.println(Arrays.toString(util.merge(a, b)));
	}
	
	@Test
	public void testGrow(){
		int[] a = new int[]{2,3,6};
		System.out.println(Arrays.toString(util.grow(a, 3)));
	}
	
	@Test
	public void testFibonacci(){
		System.out.println(Arrays.toString(util.fibonacci(15)));
	}
	
	@Test
	public void tsetGetPrimes(){
		System.out.println(Arrays.toString(util.getPrimes(23)));
	}
	
	@Test
	public void testGetPerfectNumbers(){
		System.out.println(Arrays.toString(util.getPerfectNumbers(25)));
		
	}
	
	@Test
	public void testJoin(){
		int[] a = new int[]{3,8,9};
		System.out.println(util.join(a, "-"));
	}
}
