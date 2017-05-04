package com.sprint.array;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] a = new int[]{7, 9, 30, 3};
		int[] expected = new int[]{3, 30, 9, 7};
		ArrayUtil.reverseArray(a);
		Assert.assertArrayEquals(a, expected);
		a = new int[]{7, 9, 30, 3, 4};
		expected = new int[]{4, 3, 30, 9, 7};
		ArrayUtil.reverseArray(a);
		Assert.assertArrayEquals(a, expected);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArr = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] expected = new int[]{1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(ArrayUtil.removeZero(oldArr), expected);
		oldArr = new int[]{1, 0, 2, 0, 3, 0};
		expected = new int[]{1, 2, 3};
		Assert.assertArrayEquals(ArrayUtil.removeZero(oldArr), expected);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7, 8};
		int[] a2 = {4, 5, 6, 7};
		int[] a3 = {3, 4, 5, 6, 7, 8};
		Assert.assertArrayEquals(ArrayUtil.merge(a1, a2), a3);
	}

	@Test
	public void testGrow() {
		int[] oldArray = new int[]{2, 3, 6};
		int[] expected = new int[]{2, 3, 6, 0, 0, 0};
		Assert.assertArrayEquals(ArrayUtil.grow(oldArray, 3), expected);
	}

	@Test
	public void testFibonacci() {
		int[] expected = new int[]{1, 1, 2, 3, 5, 8, 13};
		Assert.assertArrayEquals(ArrayUtil.fibonacci(15), expected);
		expected = new int[0];	
		Assert.assertArrayEquals(ArrayUtil.fibonacci(1), expected);
		/*GET 新技能：　[] == new int[0]*/
		System.out.println(Arrays.toString(expected));
	}
	
	@Test
	public void testGetPrimes() {
		int[] expected = new int[]{2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(ArrayUtil.getPrimes(23), expected);
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] result = new int[]{6};
		int length = ArrayUtil.getPerfectNumbers(7).length;
		System.out.println(length);
		Assert.assertArrayEquals(ArrayUtil.getPerfectNumbers(7), result);
	}

	@Test 
	public void tetJoin() {
		String result = "3-8-9";
		int[] array = {3, 8, 9};
		Assert.assertEquals(ArrayUtil.join(array, "-"), result);
	}

}
