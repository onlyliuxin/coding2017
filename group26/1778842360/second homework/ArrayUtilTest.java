package array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {
	@Test
	public void testReverseArray() {
		int[] array={3,5,7,8};
		ArrayUtil.reverseArray(array);
		int[] newArray={8,7,5,3};
		Assert.assertArrayEquals(newArray, array);
	}

	@Test
	public void testRemoveZero() {
		int[] array={3,5,0,0,0,7,8};
		int[] array1=ArrayUtil.removeZero(array);
		int[] newArray={3,5,7,8};
		Assert.assertArrayEquals(newArray, array1);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8} ;
		int[] a2 = {4, 5, 6,7};
		int[] expecteds={3,4,5,6,7,8};
		int[] actuals=ArrayUtil.merge(a1, a2);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGrow() {
		int[] a1 = {3, 5, 7,8} ;
		int[] actuals=ArrayUtil.grow(a1, 3);
		int[] expecteds={3,5,7,8,0,0,0};
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testFibonacci() {
		int[] actuals=ArrayUtil.fibonacci(15);
		int[] expecteds={1,1,2,3,5,8,13};
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGetPrimes() {
		int[] expecteds= {2,3,5,7,11,13,17,19};
		int[] actuals=ArrayUtil.getPrimes(23);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] actuals=ArrayUtil.getPerfectNumbers(100);
		int[] expecteds={6,28};
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testJoin() {
		int[] array={3,5,7,8};
		String actuals=ArrayUtil.join(array, "-");
		String expecteds="3-5-7-8";
		Assert.assertEquals(expecteds, actuals);
	}

}
