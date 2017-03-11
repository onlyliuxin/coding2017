package com.coderising.array;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		ArrayUtil util = new ArrayUtil();
		
		int[] test1 = {7, 9 , 30, 3};
		util.reverseArray(test1);
		Assert.assertArrayEquals(new int[]{3, 30, 9,7}, test1);
		
		int[] test2 = {7, 9, 30, 3, 4};
		util.reverseArray(test2);
		Assert.assertArrayEquals(new int[]{4,3, 30 , 9,7}, test2);
		
		int[] test3 = {};
		util.reverseArray(test3);
		Assert.assertArrayEquals(new int[]{}, test3);
	}

	@Test
	public void testRemoveZero() {
		ArrayUtil util = new ArrayUtil();
		
		int oldArray[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, util.removeZero(oldArray));
		
		int oldArray1[]={1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, util.removeZero(oldArray1));
		
		int oldArray2[]={};
		Assert.assertArrayEquals(new int[]{}, util.removeZero(oldArray2));
	}

	@Test
	public void testMerge() {
		ArrayUtil util = new ArrayUtil();
		
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7, 9}; 
		int[] a3 =  util.merge(a1, a2);
		
		Assert.assertArrayEquals(new int[] {3,4,5,6,7,8,9},a3);
		
	}

	@Test
	public void testGrow() {
		ArrayUtil util = new ArrayUtil();
		
		int[] a = new int[]{2,3,6};
		Assert.assertArrayEquals(new int[] {2,3,6,0,0,0}, util.grow(a, 3));
	}

	@Test
	public void testFibonacci() {
		ArrayUtil util = new ArrayUtil();
		int[] res = util.fibonacci(32);
		Assert.assertArrayEquals(new int[] {1, 1, 2, 3, 5, 8, 13, 21}, res);
	}

	@Test
	public void testGetPrimes() {
		ArrayUtil util = new ArrayUtil();
		int[] res = util.getPrimes(23);
		Assert.assertArrayEquals(new int[] {2,3,5,7,11,13,17,19}, res);
		
		int[] res2 = util.getPrimes(6);
		Assert.assertArrayEquals(new int[] {2, 3, 5}, res2);
	}

	@Test
	public void testGetPerfectNumbers() {
		ArrayUtil util = new ArrayUtil();
		int[] res = util.getPerfectNumbers(6);
		Assert.assertArrayEquals(new int[] {6}, res);
	}

	@Test
	public void testJoin() {
		ArrayUtil util = new ArrayUtil();
		String ans1 = util.join(new int[]{3,8,9}, "-");
		Assert.assertEquals("3-8-9", ans1);
		
		String ans2 = util.join(null,"-");
		Assert.assertEquals(null, ans2);
		
		String ans3 = util.join(new int[]{3,8,9},null);
		Assert.assertEquals("389", ans3);
	}

}
