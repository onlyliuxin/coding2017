package com.coderising.array;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayUtilTest {

	public static ArrayUtil arrayUtil;
	
	@BeforeClass
	public static void arrayUtil() throws Exception {
		arrayUtil = new ArrayUtil();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void reverseArrayTest() {
		int[] array = {1,2,3,4,5,6,7};
		int[] swapAfter = {7,6,5,4,3,2,1};
		arrayUtil.reverseArray(array);
		Assert.assertEquals(Arrays.toString(swapAfter), Arrays.toString(array));
		
	}
	
	@Test
	public void removeZeroTest(){
		int[] array = {0,1,0,0,2,3,4,5,6,0,0,7,0};
		int[] array2 = {1,2,3,4,5,6,7};
		int[] removeZero = arrayUtil.removeZero(array);
		Assert.assertEquals(Arrays.toString(array2), Arrays.toString(removeZero));
	}
	
	@Test
	public void mergeTest(){
		int[] array1 = {1,2,3,4,5};
		int[] array2 = {4,5,7,8,13,15};
		int[] array3 = {1,2,3,4,5,7,8,13,15};
		int[] merge = arrayUtil.merge(array1, array2);
		Assert.assertEquals(Arrays.toString(array3), Arrays.toString(merge));
	}
	
	@Test
	public void growTest(){
		int[] array = {1,2,3,4,5,6,7};
		int[] array2 = {1,2,3,4,5,6,7,0,0,0};
		int[] grow = arrayUtil.grow( array, 3);
		Assert.assertEquals(Arrays.toString(array2), Arrays.toString(grow));
	}
	
	@Test
	public void fibonacciTest(){
		int[] fibonacci = arrayUtil.fibonacci(1000);
		int[] array2 = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987};
		Assert.assertEquals(Arrays.toString(array2), Arrays.toString(fibonacci));
	}
	
	@Test
	public void getPrimesTest(){
		int[] primes = arrayUtil.getPrimes(20);
		int[] array2 = {2, 3, 5, 7, 11, 13, 17, 19};
		Assert.assertEquals(Arrays.toString(array2), Arrays.toString(primes));
	}

	@Test
	public void getPerfectNumbersTest(){
		int[] perfectNumbers = arrayUtil.getPerfectNumbers(1000);
		int[] array2 = {6, 28, 496};
		Assert.assertEquals(Arrays.toString(array2), Arrays.toString(perfectNumbers));
	}
	
	@Test
	public void joinTest(){
		int[] join = {2,4,5,6};
		String join2 = arrayUtil.join(join, "-");
		Assert.assertEquals("2-4-5-6", join2);
	}
}
