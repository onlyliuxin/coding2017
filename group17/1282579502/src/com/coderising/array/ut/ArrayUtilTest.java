package com.coderising.array.ut;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.array.ArrayUtil;

public class ArrayUtilTest {

	ArrayUtil au= null;
	
	@Before
	public void setUp() throws Exception {
		au=new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] target = null;
		au.reverseArray(target);
		assertArrayEquals(null, target);
		
		
		target = new int[]{7, 9 , 30, 3};
		au.reverseArray(target);
		int[] result = new int[]{3, 30 , 9, 7};
		Assert.assertArrayEquals(result, target);
		
		int[] target1 = new int[]{7, 9 ,100, 30, 3};
		au.reverseArray(target1);
		int[] result1 = new int[]{3, 30 ,100, 9, 7};
		Assert.assertArrayEquals(result1, target1);
		
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] result = {1,3,4,5,6,6,5,4,7,6,7,5};
		int[] ret = au.removeZero(oldArr);
		Assert.assertArrayEquals(result, ret);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8}; int[] a2 = {4, 5, 6,7};
		int[] ret = au.merge(a1, a2);
		//System.out.println(Arrays.toString(ret));
		int[] expected = {3,4,5,6,7,8};
		Assert.assertArrayEquals(expected, ret);
	}

	@Test
	public void testGrow() {
		int[] oldArray = new int[]{2,3,6}; int size = 3;
		int[] newArray = new int[]{2,3,6,0,0,0};
		Assert.assertArrayEquals(newArray, au.grow(oldArray, size));
	}

	@Test
	public void testFibonacci() {
		int max = 15; int[] expected = new int[]{1,1,2,3,5,8,13};
		assertArrayEquals(expected, au.fibonacci(max));
		assertArrayEquals(new int[0], au.fibonacci(1));
		assertArrayEquals(new int[]{1,1,2}, au.fibonacci(3));
		assertArrayEquals(new int[]{1,1}, au.fibonacci(2));
	}

	@Test
	public void testGetPrimes() {
		assertArrayEquals(new int[]{1,2,3,5,7,11}, au.getPrimes(11));
		assertArrayEquals(new int[]{1}, au.getPrimes(1));
	}

	@Test
	public void testGetPerfectNumbers() {
		
		assertArrayEquals(new int[]{6,28,496,8128}, au.getPerfectNumbers(9000));
		assertArrayEquals(new int[]{}, au.getPerfectNumbers(1));
		assertArrayEquals(new int[]{}, au.getPerfectNumbers(5));
		assertArrayEquals(new int[]{}, au.getPerfectNumbers(-1));
		assertArrayEquals(new int[]{}, au.getPerfectNumbers(0));
	}

	@Test
	public void testJoin() {
		String sep = "-";
		int[] array = {1,3,6};
		assertEquals("1-3-6", au.join(array, sep));
	}

}
