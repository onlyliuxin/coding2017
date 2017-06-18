/**
 * 问题点： 没有全分支覆盖。只简单的测了关键或者关心的case
 */
package com.github.eulerlcs.jmr.algorithm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void reverseArray_null() {
		int[] actuals = null;
		int[] expecteds = null;

		ArrayUtil.reverseArray(actuals);

		assertArrayEquals(actuals, expecteds);
	}

	@Test
	public void reverseArray_0() {
		int[] actuals = {};
		int[] expecteds = {};

		ArrayUtil.reverseArray(actuals);

		assertArrayEquals(actuals, expecteds);
	}

	@Test
	public void reverseArray_1() {
		int[] actuals = { 2 };
		int[] expecteds = { 2 };

		ArrayUtil.reverseArray(actuals);

		assertArrayEquals(actuals, expecteds);
	}

	@Test
	public void reverseArray_2() {
		int[] actuals = { 7, 9 };
		int[] expecteds = { 9, 7 };

		ArrayUtil.reverseArray(actuals);

		assertArrayEquals(actuals, expecteds);
	}

	@Test
	public void reverseArray_4() {
		int[] actuals = { 7, 9, 30, 3 };
		int[] expecteds = { 3, 30, 9, 7 };

		ArrayUtil.reverseArray(actuals);

		assertArrayEquals(actuals, expecteds);
	}

	@Test
	public void reverseArray_5() {
		int[] actuals = { 7, 9, 30, 3, 4 };
		int[] expecteds = { 4, 3, 30, 9, 7 };

		ArrayUtil.reverseArray(actuals);

		assertArrayEquals(actuals, expecteds);
	}

	@Test
	public void removeZero_null() {
		int oldArr[] = null;
		int[] expecteds = {};

		int[] newArr = ArrayUtil.removeZero(oldArr);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void removeZero_0() {
		int oldArr[] = {};
		int[] expecteds = {};

		int[] newArr = ArrayUtil.removeZero(oldArr);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void removeZero_1() {
		int oldArr[] = { 0 };
		int[] expecteds = {};

		int[] newArr = ArrayUtil.removeZero(oldArr);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void removeZero_2() {
		int oldArr[] = { 3, 5 };
		int[] expecteds = { 3, 5 };

		int[] newArr = ArrayUtil.removeZero(oldArr);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void removeZero_3() {
		int oldArr[] = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int[] expecteds = { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 };

		int[] newArr = ArrayUtil.removeZero(oldArr);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void merge_1() {
		int[] a1 = { 3, 5, 7, 8 };
		int[] a2 = { 4, 5, 6, 7 };
		int[] expecteds = { 3, 4, 5, 6, 7, 8 };

		int[] newArr = ArrayUtil.merge(a1, a2);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void merge_2() {
		int[] a1 = { 4, 5, 6, 7 };
		int[] a2 = { 3, 5, 7, 8 };
		int[] expecteds = { 3, 4, 5, 6, 7, 8 };

		int[] newArr = ArrayUtil.merge(a1, a2);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void grow_1() {
		int[] oldArray = { 2, 3, 6 };
		int increaseCapacity = 3;
		int[] expecteds = { 2, 3, 6, 0, 0, 0 };

		int[] newArr = ArrayUtil.grow(oldArray, increaseCapacity);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void fibonacci_1() {
		int max = 1;
		int[] expecteds = {};

		int[] newArr = ArrayUtil.fibonacci(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void fibonacci_2() {
		int max = 2;
		int[] expecteds = { 1, 1 };

		int[] newArr = ArrayUtil.fibonacci(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void fibonacci_15() {
		int max = 15;
		int[] expecteds = { 1, 1, 2, 3, 5, 8, 13 };

		int[] newArr = ArrayUtil.fibonacci(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPrimes_1() {
		int max = 1;
		int[] expecteds = {};

		int[] newArr = ArrayUtil.getPrimes(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPrimes_2() {
		int max = 2;
		int[] expecteds = {};

		int[] newArr = ArrayUtil.getPrimes(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPrimes_3() {
		int max = 3;
		int[] expecteds = { 2 };

		int[] newArr = ArrayUtil.getPrimes(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPrimes_4() {
		int max = 4;
		int[] expecteds = { 2, 3 };

		int[] newArr = ArrayUtil.getPrimes(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPrimes_23() {
		int max = 23;
		int[] expecteds = { 2, 3, 5, 7, 11, 13, 17, 19 };

		int[] newArr = ArrayUtil.getPrimes(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPrimes_24() {
		int max = 24;
		int[] expecteds = { 2, 3, 5, 7, 11, 13, 17, 19, 23 };

		int[] newArr = ArrayUtil.getPrimes(max);

		assertArrayEquals(newArr, expecteds);
	}

	@Test
	public void getPerfectNumbers_max() {
		long max = Long.MAX_VALUE;
		max = 10000;
		long[] expecteds = { 6, 28, 496, 8128, 33550336, 8589869056L, 137438691328L, 2305843008139952128L };

		long[] newArr = ArrayUtil.getPerfectNumbers(max);

		assertEquals(newArr[3], expecteds[3]);
	}

	@Test
	public void join_0() {
		int[] array = { 3, 8, 9 };
		String separator = "-";
		String expected = "3-8-9";

		String actual = ArrayUtil.join(array, separator);
		assertEquals(expected, actual);
	}
}
