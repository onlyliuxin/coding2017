package assignment2_26;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil arrayUtil = new ArrayUtil();

	@Test
	public void testReverseArray() {
		int[] array = new int[] {};
		arrayUtil.reverseArray(array);
		assertArrayEquals(new int[] {}, array);

		array = new int[] { 1 };
		arrayUtil.reverseArray(array);
		assertArrayEquals(new int[] { 1 }, array);

		array = new int[] { 1, 2, 3 };
		arrayUtil.reverseArray(array);
		assertArrayEquals(new int[] { 3, 2, 1 }, array);
	}

	@Test
	public void testRemoveZero() {
		int[] array = new int[] {};
		assertArrayEquals(new int[] {}, arrayUtil.removeZero(array));

		array = new int[] { 0 };
		assertArrayEquals(new int[] {}, arrayUtil.removeZero(array));

		array = new int[] { 1 };
		assertArrayEquals(new int[] { 1 }, arrayUtil.removeZero(array));

		array = new int[] { 1, 2, 0, 0, 3 };
		assertArrayEquals(new int[] { 1, 2, 3 }, arrayUtil.removeZero(array));

		array = new int[] { 1, 2, 3 };
		assertArrayEquals(new int[] { 1, 2, 3 }, arrayUtil.removeZero(array));
	}

	@Test
	public void testMerge() {
		int[] array1 = { 3, 5, 7, 8 };
		int[] array2 = { 4, 5, 6, 7 };
		assertArrayEquals(new int[] { 3, 4, 5, 6, 7, 8 }, arrayUtil.merge(array1, array2));
	}

	@Test
	public void testGrow() {
		int[] array = { 3, 5, 7 };
		assertArrayEquals(new int[] { 3, 5, 7, 0, 0 }, arrayUtil.grow(array, 5));
		assertArrayEquals(new int[] { 3, 5, 7 }, arrayUtil.grow(array, 3));
	}

	@Test
	public void testFibonacci() {
		assertArrayEquals(new int[] {}, arrayUtil.fibonacci(1));

		assertArrayEquals(new int[] { 1, 1 }, arrayUtil.fibonacci(2));

		assertArrayEquals(new int[] { 1, 1, 2, 3, 5, 8, 13 }, arrayUtil.fibonacci(15));
	}

	@Test
	public void testGetPrimes() {
		assertArrayEquals(new int[] {}, arrayUtil.getPrimes(1));

		assertArrayEquals(new int[] {}, arrayUtil.getPrimes(2));

		assertArrayEquals(new int[] { 2 }, arrayUtil.getPrimes(3));

		assertArrayEquals(new int[] { 2, 3, 5, 7, 11, 13, 17, 19 }, arrayUtil.getPrimes(20));
	}

	@Test
	public void testGetPerfectNumbers() {
		assertArrayEquals(new int[] { 6 }, arrayUtil.getPerfectNumbers(10));

		assertArrayEquals(new int[] { 6, 28 }, arrayUtil.getPerfectNumbers(100));

		assertArrayEquals(new int[] { 6, 28, 496 }, arrayUtil.getPerfectNumbers(1000));

		assertArrayEquals(new int[] { 6, 28, 496, 8128 }, arrayUtil.getPerfectNumbers(10000));
	}

	@Test
	public void testJoin() {
		assertEquals("3-4-5", arrayUtil.join(new int[] { 3, 4, 5 }, "-"));

		assertEquals("345", arrayUtil.join(new int[] { 3, 4, 5 }, ""));

		assertEquals("3", arrayUtil.join(new int[] { 3 }, ""));

		assertEquals("3--4--5", arrayUtil.join(new int[] { 3, 4, 5 }, "--"));
	}

}
