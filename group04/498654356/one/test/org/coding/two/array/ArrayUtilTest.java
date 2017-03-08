package org.coding.two.array;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArrayUtilTest {

	private ArrayUtil arrayUtil;
	
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
		int[] origin = null;
		arrayUtil.reverseArray(origin);
		Assert.assertNull(origin);
		
		origin = new int[]{};
		arrayUtil.reverseArray(origin);
		Assert.assertArrayEquals(origin, origin);
		
		origin = new int[]{1};
		arrayUtil.reverseArray(origin);
		Assert.assertArrayEquals(origin, origin);
		
		origin = new int[]{1, 2};
		arrayUtil.reverseArray(origin);
		int[] dest = new int[]{2, 1};
		Assert.assertArrayEquals(dest, origin);
		
		origin = new int[]{1, 2, 3};
		arrayUtil.reverseArray(origin);
		dest = new int[]{3, 2, 1};
		Assert.assertArrayEquals(dest, origin);
		
		origin = new int[]{1, 2, 3, 4};
		arrayUtil.reverseArray(origin);
		dest = new int[]{4, 3, 2, 1};
		Assert.assertArrayEquals(dest, origin);
		
		origin = new int[]{7, 9 , 30, 3};
		arrayUtil.reverseArray(origin);
		dest = new int[]{3, 30, 9,7};
		Assert.assertArrayEquals(dest, origin);
		
		origin = new int[]{7, 9, 30, 3, 4};
		arrayUtil.reverseArray(origin);
		dest = new int[]{4,3, 30 , 9,7};
		Assert.assertArrayEquals(dest, origin);
		
	}

	@Test
	public void testRemoveZero() {
		int[] origin = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] dest = new int[]{1,3,4,5,6,6,5,4,7,6,7,5};
		origin = arrayUtil.removeZero(origin);
		Assert.assertArrayEquals(dest, origin);
		
		origin = null;
		dest = new int[]{};
		origin = arrayUtil.removeZero(origin);
		Assert.assertArrayEquals(dest, origin);
		
		origin = new int[]{};
		dest = new int[]{};
		origin = arrayUtil.removeZero(origin);
		Assert.assertArrayEquals(dest, origin);
		
		origin = new int[]{1};
		dest = new int[]{1};
		origin = arrayUtil.removeZero(origin);
		Assert.assertArrayEquals(dest, origin);
		
	}

	@Test
	public void testMerge() {
		int[] array1 = new int[]{3, 5, 7,8};
		int[] array2 = new int[]{4, 5, 6,7};
		int[] expecteds = new int[]{3,4,5,6,7,8};
		int[] actuals = arrayUtil.merge(array1, array2);
		Assert.assertArrayEquals(expecteds, actuals);
		
		array1 = new int[]{};
		array2 = new int[]{4, 5, 6,7};
		expecteds = new int[]{4, 5, 6,7};
		actuals = arrayUtil.merge(array1, array2);
		Assert.assertArrayEquals(expecteds, actuals);
		
		array1 = new int[]{3, 5, 7,8};
		array2 = new int[]{};
		expecteds = new int[]{3, 5, 7,8};
		actuals = arrayUtil.merge(array1, array2);
		Assert.assertArrayEquals(expecteds, actuals);
		
		array1 = new int[]{};
		array2 = new int[]{};
		expecteds = new int[]{};
		actuals = arrayUtil.merge(array1, array2);
		Assert.assertArrayEquals(expecteds, actuals);
		
		array1 = new int[]{1, 2, 8,9};
		array2 = new int[]{4, 5, 6,7};
		expecteds = new int[]{1, 2, 4, 5, 6, 7, 8, 9};
		actuals = arrayUtil.merge(array1, array2);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGrow() {
		int[] oldArray = new int[]{2,3,6};
		int size = 3;
		int[] expecteds = new int[]{2,3,6,0,0,0};
		int[] actuals = arrayUtil.grow(oldArray, size);
		Assert.assertArrayEquals(expecteds, actuals);
		
		oldArray = new int[]{};
		size = 3;
		expecteds = new int[]{};
		actuals = arrayUtil.grow(oldArray, size);
		Assert.assertArrayEquals(expecteds, actuals);
		
		oldArray = new int[]{};
		size = 0;
		expecteds = new int[]{};
		actuals = arrayUtil.grow(oldArray, size);
		Assert.assertArrayEquals(expecteds, actuals);
		
		oldArray = new int[]{2,3,6};
		size = 0;
		expecteds = new int[]{2,3,6};
		actuals = arrayUtil.grow(oldArray, size);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int[] expecteds = new int[]{1,1,2,3,5,8,13};
		int[] actuals = arrayUtil.fibonacci(max);
		Assert.assertArrayEquals(expecteds, actuals);
		
		max = 1;
		expecteds = new int[]{};
		actuals = arrayUtil.fibonacci(max);
		Assert.assertArrayEquals(expecteds, actuals);
		
		max = 2;
		expecteds = new int[]{1,1};
		actuals = arrayUtil.fibonacci(max);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGetPrimes() {
		int max = 23;
		int[] expecteds = new int[]{2,3,5,7,11,13,17,19};
		int[] actuals = arrayUtil.getPrimes(max);
		Assert.assertArrayEquals(expecteds, actuals);
		
		max = 2;
		expecteds = new int[]{};
		actuals = arrayUtil.getPrimes(max);
		Assert.assertArrayEquals(expecteds, actuals);
		
		max = 3;
		expecteds = new int[]{2};
		actuals = arrayUtil.getPrimes(max);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGetPerfectNumbers() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoin() {
		int[] array = new int[]{3,8,9};
		String seperator = "-";
		String expected = "3-8-9";
		String actual = arrayUtil.join(array, seperator);
		Assert.assertEquals(expected, actual);
		
		array = null;
		seperator = "-";
		expected = null;
		actual = arrayUtil.join(array, seperator);
		Assert.assertNull(actual);
		
		array = new int[]{};
		seperator = "-";
		expected = "";
		actual = arrayUtil.join(array, seperator);
		Assert.assertEquals(expected, actual);
		
		array = new int[]{1,2,3};
		seperator = "@-@";
		expected = "1@-@2@-@3";
		actual = arrayUtil.join(array, seperator);
		Assert.assertEquals(expected, actual);
	}

}
