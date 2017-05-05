package com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	
	ArrayUtil arrayUtil;

	@Before
	public void setUp() {
		arrayUtil = new ArrayUtil();
	}
	
	@After
	public void release() {
		arrayUtil = null;
	}
	
	@Test
	public void testReverseArray() {
		int[] origin = new int[] {1,2,3,4,5};
		int[] result = new int[] {5,4,3,2,1};
		arrayUtil.reverseArray(origin);
		Assert.assertArrayEquals(origin, result);
	}
	
	@Test
	public void testRemoveZero() {
		int[] origin = new int[] {0,1,2,0,0,3,0,4,5,0,0};
		int[] result = new int[] {1,2,3,4,5};
		Assert.assertArrayEquals(result, arrayUtil.removeZero(origin));
	}
	
	@Test
	public void testMerge() {
		int[] origin1 = new int[] {3,4,7,11};
		int[] origin2 = new int[] {2,3,5,8,12};
		int[] result = new int[] {2,3,4,5,7,8,11,12};
		Assert.assertArrayEquals(result, arrayUtil.merge(origin1, origin2));
	}
	
	@Test
	public void testGrow() {
		int[] origin = new int[] {1,2,3};
		int[] result = new int[] {1,2,3,0,0,0,0};
		Assert.assertArrayEquals(result, arrayUtil.grow(origin, 4));
	}
	
	@Test
	public void testFibonacci() {
		Assert.assertArrayEquals(new int[] {1,1,2,3,5,8}, arrayUtil.fibonacci(9));
		Assert.assertArrayEquals(new int[] {}, arrayUtil.fibonacci(1));
	}
	
	@Test 
	public void testGetPrimes() {
		Assert.assertArrayEquals(new int[] {2,3,5,7,11,13,17,19}, arrayUtil.getPrimes(23));
	}
	
	@Test 
	public void testGetPerfectNumbers() {
		Assert.assertArrayEquals(new int[] {}, arrayUtil.getPerfectNumbers(6));
		Assert.assertArrayEquals(new int[] {6,28}, arrayUtil.getPerfectNumbers(29));
		
	}

	@Test 
	public void testJoin() {
		Assert.assertEquals("1-2-3-4-5", arrayUtil.join(new int[] {1,2,3,4,5}, "-"));
	}
}
