package org.wsc.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil arrayUtil;
	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
		arrayUtil = null;
	}

	@Test
	public void testReverseArray() {
		int[] nums = new int[]{7, 9 , 30, 3, 5};
		arrayUtil.reverseArray(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemoveZero() {
		int[] nums = new int[]{0,7, 9 , 0,0,30,0, 3, 5,0};
		nums = arrayUtil.removeZero(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		fail("Not yet implemented");
	}

}
