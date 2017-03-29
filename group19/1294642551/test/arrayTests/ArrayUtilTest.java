package arrayTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		ArrayUtil au = new ArrayUtil();
		int[] origin = {7, 9, 30, 3, 4};
		int[] expecteds ={4, 3, 30, 9, 7};
		int[] actuals = au.reverseArray(origin);
		
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testRemoveZero() {
		ArrayUtil au = new ArrayUtil();
		int[] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] expecteds = {1,3,4,5,6,6,5,4,7,6,7,5};
		int[] actuals = au.removeZero(oldArray);
		
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testMerge() {
		
		ArrayUtil au = new ArrayUtil();
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] expecteds = {3,4,5,6,7,8};
		int[] actuals = au.merge(a1, a2);
		
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGrow() {
		
		ArrayUtil au = new ArrayUtil();
		int[] oldArray = {2,3,6};
		int size = 3;
		int[] expecteds = {2,3,6,0,0,0};
		int[] actuals = au.grow(oldArray, size);
		
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testFibonacci() {
		
		ArrayUtil au = new ArrayUtil();
		int max = 15;
		int[] expecteds = {1, 1, 2, 3, 5, 8, 13};
		int[] actuals = au.fibonacci(max);
		
		Assert.assertArrayEquals(expecteds, actuals);
	}
		
		

	@Test
	public void testGetPrimes() {
		ArrayUtil au = new ArrayUtil();
		int max = 23;
		int[] expecteds = {2,3,5,7,11,13,17,19};
		int[] actuals = au.getPrimes(max);
		
		Assert.assertArrayEquals(expecteds, actuals);
		
	}

	@Test
	public void testGetPerfectNumbers() {
		
		ArrayUtil au = new ArrayUtil();
		int max = 30;
		int[] expecteds = {6, 28};
		int[] actuals = au.getPerfectNumbers(max);
		
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testJoin() {
		
		ArrayUtil au = new ArrayUtil();
		int[] array = {3, 8, 9};
		String seperator = "-";
		String expecteds = "3-8-9";
		String actuals = au.join(array, seperator);
		
		Assert.assertEquals(expecteds, actuals);
	}

}
