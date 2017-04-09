package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.ArrayUtil;

public class ArrayUtilTest {
	@Test
	public void testReverseArray() {
		int[] origin = null;
		ArrayUtil.reverseArray(origin);
		assertEquals(null, origin);

		int[] origin1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ArrayUtil.reverseArray(origin1);
		int[] expected1 = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		for (int i = 0; i < 4; i++) {
			assertEquals(expected1[i], origin1[i]);
		}

		int[] origin2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		ArrayUtil.reverseArray(origin2);
		int[] expected2 = { 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		for (int i = 0; i < 4; i++) {
			assertEquals(expected2[i], origin2[i]);
		}
	}

	@Test
	public void testRemoveZero() {
		int oldArr[] = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int excepted[] = { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 };
		oldArr = ArrayUtil.removeZero(oldArr);
		System.out.println(oldArr.length);
		for (int i = 0; i < oldArr.length; i++) {
			assertEquals(excepted[i], oldArr[i]);
		}
	}

	@Test
	public void testMerge() {
		int[] array1 = { 3, 4, 4, 5, 7, 8 };
		int[] array2 = { 4, 5, 6, 7 };
		int[] expected = { 3, 4, 5, 6, 7, 8 };
		int[] array = ArrayUtil.merge(array1, array2);
		for (int i = 0; i < array.length; i++) {
			assertEquals(expected[i], array[i]);
		}
	}

	@Test
	public void testGrow() {
		int[] oldArray = { 2, 3, 6 };
		int[] array = ArrayUtil.grow(oldArray, 3);
		int[] expected = { 2, 3, 6, 0, 0, 0 };
		for (int i = 0; i < array.length; i++) {
			assertEquals(expected[i], array[i]);
		}
	}

	@Test
	public void testFibonacci() {
		int[] fibonacci = ArrayUtil.fibonacci(1);
		assertEquals(0, fibonacci.length);

		int[] fibonacci1 = ArrayUtil.fibonacci(16);
		int[] excepted1 = { 1, 1, 2, 3, 5, 8, 13, 15 };
		for (int i = 0; i < fibonacci1.length; i++) {
			assertEquals(excepted1[i], fibonacci1[i]);
		}
	}

	@Test
	public void testGetPrimes() {
		int[] primes = ArrayUtil.getPrimes(23);
		int[] excepted = { 2, 3, 5, 7, 11, 13, 17, 19 };
		for (int i = 0; i < primes.length; i++) {
			assertEquals(excepted[i], primes[i]);
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] PerfectNumbers = ArrayUtil.getPerfectNumbers(497);
		int[] excepted = { 6, 28, 496 };
		for (int i = 0; i < PerfectNumbers.length; i++) {
			assertEquals(excepted[i], PerfectNumbers[i]);
		}
	}

	@Test
	public void testJoin() {
		int[] array1 = { 3 };
		assertEquals("3", ArrayUtil.join(array1, "-%"));
		int[] array2 = { 3, 8, 9, 7 };
		assertEquals("3-%8-%9-%7", ArrayUtil.join(array2, "-%"));
	}

}
