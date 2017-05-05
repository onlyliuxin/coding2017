package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayTest {
	ArrayUtil util = new ArrayUtil();
	
	@Test
	public void testReverseArray() {
		int[] origin = {1,2,3,4,53,52,4,2,423};
		int[] target = util.reverseArray(origin);
		int[] expecteds = {423, 2, 4, 52, 53, 4, 3, 2, 1};
		assertArrayEquals(expecteds, target);
	}
	
	@Test
	public void testRemoveZero(){
		int[] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] target = util.removeZero(oldArray);
		int[] expecteds = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 };
		assertArrayEquals(expecteds, target);
	}
	
	@Test
	public void testMerger(){
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] target = util.merge(a1, a2);
		int[] expecteds = {3, 4, 5, 6, 7, 8 };
		assertArrayEquals(expecteds, target);
	}
	
	@Test
	public void testGrow(){
		int[] oldArray = {3, 5,7,8};
		int[] target = util.grow(oldArray, 4);
		int[] expecteds = {3, 5,7,8, 0, 0, 0,0 };
		assertArrayEquals(expecteds, target);
	}
	
	@Test
	public void testFibonacci(){
		int[] target = util.fibonacci(15);
		
		//int[] expecteds = {1,1};
		int[] expecteds = {1, 1, 2, 3, 5, 8, 13 };
		assertArrayEquals(expecteds, target);
	}
	
	@Test
	public void testGetPrimes(){
		int[] target = util.getPrimes(25);
		int[] expecteds = {2, 3, 5, 7, 11, 13, 17, 19, 23 };
		assertArrayEquals(expecteds, target);
//		for(int i=0; i<target.length; i++)
//			System.out.print(target[i] + " ");
	}
	
	@Test
	public void testGetPerfectNumbers(){
		int[] target = util.getPerfectNumbers(3000);
		int[] expecteds = {6, 28, 496 };
		assertArrayEquals(expecteds, target);
//		for(int i=0; i<target.length; i++)
//			System.out.print(target[i] + " ");
	}
	
	@Test
	public void testJoin(){
		int[] array = {1,2,3};
		String target = util.join(array, "-");
		String expecteds = "1-2-3";
		assertEquals(expecteds, target);
	}
}
