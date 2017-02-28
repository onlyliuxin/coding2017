package org.wsc.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilTest {

//	@Test
//	public void testReverseArray() {
//		int[] nums = new int[]{7, 9 , 30, 3, 5};
//		ArrayUtil.reverseArray(nums);
//		for (int i = 0; i < nums.length; i++) {
//			System.out.println(nums[i]);
//		}
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testRemoveZero() {
//		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5,0};
//		nums = ArrayUtil.removeZero2(nums);
//		for (int i = 0; i < nums.length; i++) {
//			System.out.println(nums[i]);
//		}
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testJoin() {
//		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5,0};
//		String str = ArrayUtil.join(nums,"-");
//		System.out.println(str);
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testGrow() {
		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5};
		nums = ArrayUtil.grow(nums,3);
		assertTrue(nums.length==12);
	}

}
