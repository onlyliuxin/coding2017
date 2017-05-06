package assignment0226;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] array = new int[] {};
		ArrayUtil.reverseArray(array);
		assertArrayEquals(new int[] {}, array);

		array = new int[] { 1 };
		ArrayUtil.reverseArray(array);
		assertArrayEquals(new int[] { 1 }, array);

		array = new int[] { 1, 2, 3 };
		ArrayUtil.reverseArray(array);
		assertArrayEquals(new int[] { 3, 2, 1 }, array);
	}

	@Test
	public void testRemoveZero() {
		int[] array = new int[] {};
		assertArrayEquals(new int[] {}, ArrayUtil.removeZero(array));

		array = new int[] { 0 };
		assertArrayEquals(new int[] {}, ArrayUtil.removeZero(array));

		array = new int[] { 1 };
		assertArrayEquals(new int[] { 1 }, ArrayUtil.removeZero(array));

		array = new int[] { 1, 2, 0, 0, 3 };
		assertArrayEquals(new int[] { 1, 2, 3 }, ArrayUtil.removeZero(array));

		array = new int[] { 1, 2, 3 };
		assertArrayEquals(new int[] { 1, 2, 3 }, ArrayUtil.removeZero(array));
	}

	@Test
	public void testMerge() {
		int[] array1 = { 3, 5, 7, 8 };
		int[] array2 = { 4, 5, 6, 7 };
		assertArrayEquals(new int[] { 3, 4, 5, 6, 7, 8 }, ArrayUtil.merge(array1, array2));
	}

	@Test
	public void testGrow() {
		int[] array = { 3, 5, 7 };
		assertArrayEquals(new int[] { 3, 5, 7, 0, 0 }, ArrayUtil.grow(array, 5));
		assertArrayEquals(new int[] { 3, 5, 7 }, ArrayUtil.grow(array, 3));
	}

	@Test
	public void testFibonacci() {
		assertArrayEquals(new int[] {}, ArrayUtil.fibonacci(1));

		assertArrayEquals(new int[] { 1, 1 }, ArrayUtil.fibonacci(2));

		assertArrayEquals(new int[] { 1, 1, 2, 3, 5, 8, 13 }, ArrayUtil.fibonacci(15));
	}

	@Test
	public void testGetPrimes() {
		assertArrayEquals(new int[] {}, ArrayUtil.getPrimes(1));

		assertArrayEquals(new int[] {}, ArrayUtil.getPrimes(2));

		assertArrayEquals(new int[] { 2 }, ArrayUtil.getPrimes(3));

		assertArrayEquals(new int[] { 2, 3, 5, 7, 11, 13, 17, 19 }, ArrayUtil.getPrimes(20));
	}

	@Test
	public void testGetPerfectNumbers() {
		assertArrayEquals(new int[] { 6 }, ArrayUtil.getPerfectNumbers(10));

		assertArrayEquals(new int[] { 6, 28 }, ArrayUtil.getPerfectNumbers(100));

		assertArrayEquals(new int[] { 6, 28, 496 }, ArrayUtil.getPerfectNumbers(1000));

		assertArrayEquals(new int[] { 6, 28, 496, 8128 }, ArrayUtil.getPerfectNumbers(10000));

	}

	@Test
	public void testJoin() {
		assertEquals("3-4-5", ArrayUtil.join(new int[] { 3, 4, 5 }, "-"));

		assertEquals("345", ArrayUtil.join(new int[] { 3, 4, 5 }, ""));

		assertEquals("3", ArrayUtil.join(new int[] { 3 }, ""));

		assertEquals("3--4--5", ArrayUtil.join(new int[] { 3, 4, 5 }, "--"));
	}

}
