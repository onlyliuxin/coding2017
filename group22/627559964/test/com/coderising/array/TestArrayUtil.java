package com.coderising.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.sun.scenario.effect.Merge;

public class TestArrayUtil {

	@Test
	public void testReverseArray() {
		try {
			int[] number = {1,2,3};
			Assert.assertEquals("[3, 2, 1]", Arrays.toString(ArrayUtil.reverseArray(number)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveZero() {
		int[] oldArr = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		Assert.assertEquals("[1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5]", Arrays.toString(ArrayUtil.removeZero(oldArr)));
	}
	
	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7, 8};
		int[] a2 = {4, 5, 6, 7};
		Assert.assertEquals("[3, 4, 5, 5, 6, 7, 7, 8]", Arrays.toString(ArrayUtil.merge(a1, a2)));
	}
	
	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		int size = 3;
		Assert.assertEquals("[2, 3, 6, 0, 0, 0]", Arrays.toString(ArrayUtil.grow(oldArray, size)));
	}
	
	@Test
	public void testFibonacci() {
		int max = 15;
		Assert.assertEquals("[1, 1, 2, 3, 5, 8, 13]", Arrays.toString(ArrayUtil.fibonacci(max)));
	}
	
	@Test
	public void testGetPrimes() {
		int max = 23;
		System.out.println(Arrays.toString(ArrayUtil.getPrimes(max)));
		Assert.assertEquals("[2, 3, 5, 7, 11, 13, 17, 19]", Arrays.toString(ArrayUtil.getPrimes(max)));
	}

	@Test
	public void testGetPerfectNumbers() {
		Assert.assertEquals("[6, 28, 496]", Arrays.toString(ArrayUtil.getPerfectNumbers(1000)));
	}

	@Test
	public void testJoin() {
		int[] array= {3,8,9};
		String seperator = "-";
		Assert.assertEquals("3-8-9", ArrayUtil.join(array, seperator));
	}
}
