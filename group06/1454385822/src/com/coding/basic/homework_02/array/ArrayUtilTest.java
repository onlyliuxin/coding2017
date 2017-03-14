package com.coding.basic.homework_02.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArrayUtilTest {
	int[] array;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		array = new int[]{7, 9, 30, 3};
//		array = new int[]{7, 9, 30, 3, 4};
		ArrayUtil.reverseArray(array);
		Assert.assertArrayEquals(new int[]{3, 30, 9, 7}, array);
//		Assert.assertArrayEquals(new int[]{4, 3, 30, 9, 7}, array);
	}
	
	@Test
	public void testRemoveZero(){
		array = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, ArrayUtil.removeZero(array));
	}
	
	@Test
	public void testMerge(){
		array = new int[]{3,5,7,8};
		int [] array2 = new int[]{4,5,6,7};
		Assert.assertArrayEquals(new int[]{3,4,5,6,7,8}, ArrayUtil.merge(array, array2));
	}
	
	@Test
	public void testGrow(){
		array = new int[]{2, 3, 6};
		Assert.assertArrayEquals(new int[]{2, 3, 6, 0, 0, 0} ,ArrayUtil.grow(array, 3));
	}
	
	@Test
	public void testFibonacci(){
		Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, ArrayUtil.fibonacci(15));
//		array = ArrayUtil.fibonacci(15);
//		for(int i: array)
//			System.out.println(i);
	}
	
	
	@Test
	public void testPrimes(){
		Assert.assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, ArrayUtil.getPrimes(23));
//		array = ArrayUtil.getPrimes(23);
//		for(int i : array){
//			System.out.print(i + " ");
//		}
		
	}
	
	@Test
	public void testPerfectNumbers(){
//		Assert.assertArrayEquals(new int[]{6}, ArrayUtil.getPerfectNumbers(7));
		array = ArrayUtil.getPerfectNumbers(100);
		for(int i : array){
			System.out.print(i + " ");
		}
	}
	
	@Test
	public void testJoin(){
		Assert.assertEquals("3-8-9", ArrayUtil.join(new int[]{3,8,9}, "-"));
	}
	
}













