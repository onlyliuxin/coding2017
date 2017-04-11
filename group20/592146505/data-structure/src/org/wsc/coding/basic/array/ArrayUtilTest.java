package org.wsc.coding.basic.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] nums = new int[]{7, 9 , 30, 3, 5};
		ArrayUtil.reverseArray(nums);
		assertArrayEquals(nums, new int[]{5, 3 ,30, 9, 7});
	}
	
	/**
	 * 删除0
	 */
	@Test
	public void testRemoveZero() {
		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5,0};
		nums = ArrayUtil.removeZero(nums);
		assertArrayEquals(nums, new int[]{7, 9 ,30, 3, 5});
	}
	
	/**
	 * 删除0
	 */
	@Test
	public void testRemoveZero2() {
		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5,0};
		nums = ArrayUtil.removeZero2(nums);
		assertArrayEquals(nums, new int[]{7, 9 ,30, 3, 5});
	}
	
	/**
	 * 拼接
	 */
	@Test
	public void testJoin() {
		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5,0};
		String str = ArrayUtil.join(nums,"-");
		assertEquals(str, "0-7-9-0-0-30-0-3-5-0");
	}
	
	/**
	 * 扩容
	 */
	@Test
	public void testGrow() {
		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5};
		nums = ArrayUtil.grow(nums,3);
		assertTrue(nums.length==12);
	}

	/**
	 * 合并
	 */
	@Test
	public void testMerge() {
		int[] nums = new int[]{3, 5, 7,8};
		int[] nums2 = new int[]{4, 5, 6,7,8};
		nums = ArrayUtil.merge(nums,nums2);
		assertTrue(nums.length==6);
		assertArrayEquals(nums, new int[]{3, 4 ,5, 6, 7,8});
	}
	
	/**
	 *斐波那契数列
	 */
	@Test
	public void testFibonacci() {
		int[] nums = ArrayUtil.fibonacci(15);
		assertTrue(nums.length==7);
		assertArrayEquals(nums, new int[]{1,1,2,3,5,8,13});
	}
	
	/**
	 * 素数
	 */
	@Test
	public void testGetPrimes() {
		int[] nums = ArrayUtil.getPrimes(23);
		assertTrue(nums.length==8);
		assertArrayEquals(nums, new int[]{2,3,5,7,11,13,17,19});
	}
	
	/**
	 * 完数
	 */
	@Test
	public void testGetPerfectNumbers() {
		int[] nums = ArrayUtil.getPerfectNumbers(10000);
		assertTrue(nums.length==4);
		assertArrayEquals(nums, new int[]{6,28,496,8128});
	}
	
}
