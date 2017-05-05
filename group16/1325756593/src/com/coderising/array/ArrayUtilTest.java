package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] input = {7, 9 , 30, 3};
		new ArrayUtil().reverseArray(input);
		int[] inputResult = {3, 30, 9,7};
		assertArrayEquals(inputResult, input);
		
		int[] input1 = {7, 9, 30, 3, 4};
		new ArrayUtil().reverseArray(input1);
		int[] inputResult1 = {4,3, 30 , 9,7};
		assertArrayEquals(inputResult1, input1);
	}
	
	@Test
	public void removeZero() {
		 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		 int retArr[] =  {1,3,4,5,6,6,5,4,7,6,7,5} ;
		assertArrayEquals(retArr, new ArrayUtil().removeZero(oldArr));
	}

	@Test
	public void grow(){
		int[] array = {2,3,6};
		
		int[] retArray = {2,3,6,0,0,0};
		assertArrayEquals(new ArrayUtil().grow(array, 3),retArray );
		
	}
	
	@Test
	public void join(){
		int[] array = {3,8,9};
		
		
		assertEquals(new ArrayUtil().join(array, "-"), "3-8-9");
		
	}
	
	@Test
	public void getPrimes(){
		// 23, 返回的数组为[2,3,5,7,11,13,17,19]
		int[] array = {2,3,5,7,11,13,17,19};
		
		
		assertArrayEquals(new ArrayUtil().getPrimes(23), array);
		
	}
	
	@Test
	public void getPerfectNumbers(){
		// 23, 返回的数组为[2,3,5,7,11,13,17,19]
		int[] array = {1,6};
		
		
		assertArrayEquals(new ArrayUtil().getPerfectNumbers(7), array);
		
	}
	
	@Test
	public void fibonacci(){
		// 23, 返回的数组为[2,3,5,7,11,13,17,19]
		int[] array =  {1,1,2,3,5,8,13,21};
		
		
		assertArrayEquals(new ArrayUtil().fibonacci(23), array);
		
	}
	
	
	
	
}