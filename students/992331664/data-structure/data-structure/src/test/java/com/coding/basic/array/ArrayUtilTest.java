package com.coding.basic.array;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	ArrayUtil arrayUtil;
	
	int[] resultArray ;
	
	@Before
	public void before(){
		arrayUtil = new ArrayUtil();
	}
	
	@After
	public void printArray(){
		System.out.println(Arrays.toString(resultArray));
	}
	
	@Test
	public void testReverseArray(){
		int[] arr = {12,344,5,6,0,4,65,4,};
		arrayUtil.reverseArray(arr);
		resultArray = arr;
	}
	
	@Test
	public void testRemoveZero(){
		int[] arr = {};
		resultArray = arrayUtil.removeZero(arr);
	}
	
	@Test
	public void testMerge(){
	}
	
	@Test
	public void testGrow(){
		int[] arr = {1,6,4,2,0};
		resultArray = arrayUtil.grow(arr, 2);
	}
	
	@Test
	public void testFibonacci(){
		resultArray = arrayUtil.fibonacci(15);
	}
}
