package com.m0305.array;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil util;
	@Before
	public void setUp() throws Exception {
		util=new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		/**
		 * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
		 */
		int[] origin={7, 9 , 30, 3};
		int[] origin2={7, 9, 30, 3, 4};
		util.reverseArray(origin2);
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		for(int i:util.removeZero(oldArr)){
			System.out.print(i+",");
		}
		fail("Not yet implemented");
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8,9,10},   a2 = {4, 5, 6,7,9,11,12};
		printArr(util.merge(a1, a2));
		fail("Not yet implemented");
	}
	
	@Test
	public void testGrow() {
		int[]  oldArray = {2,3,6};
		printArr(util.grow(oldArray,4));
		fail("Not yet implemented");
	}

	@Test
	public void testFibonacci() {
		//printArr(util.fibonacci(35));
		printArr(util.fibonacci(1));
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrimes() {
		printArr(util.getPrimes(26));
		fail("Not yet implemented");
	}

	@Test
	public void testGetPerfectNumbers() {
		printArr(util.getPerfectNumbers(1000));
		//6,28,496
		fail("Not yet implemented");
	}

	@Test
	public void testJoin() {
		int[] a={};
		System.out.println(util.join(a, "#"));
		fail("Not yet implemented");
	}
	public static void printArr(int[] arr){
		for(int obj:arr){
			System.out.print(obj+",");
		}
		System.out.println();
	}

}
