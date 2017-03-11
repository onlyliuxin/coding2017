package com.louis.java.arrayUtil;


import com.louis.java.arrayUtil.LYArrayUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LYArrayUtilTest {

	static LYArrayUtil arrayUtil;
	@Before
	public void setUp() throws Exception {
		arrayUtil = new LYArrayUtil();		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] originList = {7, 9 ,30, 3, 4};
		int[] actualResult = arrayUtil.reverseArray(originList);
		int[] expectResult = {4, 3 ,30, 9, 7};
		Assert.assertArrayEquals(expectResult, actualResult);
	}

	@Test
	public void removeZero() {
		int[] oldArr = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] actuals = arrayUtil.removeZero(oldArr);
		int[] expecteds = {1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void merge() {
		int[] array1 = {3, 5, 7,8};
		int[] array2 = {4, 5, 6,7};
		int[] array = arrayUtil.merge(array1, array2);
		int[] expecteds = {3,4,5,6,7,8};
		Assert.assertArrayEquals(expecteds, array);
	}
	
	@Test
	public void grow() {
		int[] origin = {2, 3, 6};
		int[] actuals = arrayUtil.grow(origin, 3);
		int[] expecteds = {2, 3, 6, 0, 0, 0};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void fibonacci() {
		int[] actuals = arrayUtil.fibonacci(20);
		int[] expecteds = {1,1,2,3,5,8,13};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void getPrimes() {
		int[] actuals = arrayUtil.getPrimes(23);
		int[] expecteds = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(expecteds, actuals);
	}
}
