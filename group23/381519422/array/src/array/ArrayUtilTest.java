package array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a = { 7, 9, 30, 3, 4 };
		int[] expecteds = { 4, 3, 30, 9, 7 };
		ArrayUtil util = new ArrayUtil();
		util.reverseArray(a);
		assertArrayEquals(expecteds, a);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArr = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int[] expecteds = { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 };
		ArrayUtil util = new ArrayUtil();
		int[] removeZero = util.removeZero(oldArr);
		assertArrayEquals(expecteds, removeZero);
	}

	@Test
	public void testMerge() {
		int[] a1 = { 3, 5, 7, 8 };
		int[] a2 = { 4, 5, 6, 7 };
		ArrayUtil util = new ArrayUtil();
		int[] merge = util.merge(a1, a2);
		int[] expecteds = { 3, 4, 5, 6, 7, 8 };
		assertArrayEquals(expecteds, merge);
	}

	@Test
	public void testGrow() {
		int[] oldArray = { 2, 3, 6 };
		int size = 3;
		int[] expecteds = { 2, 3, 6, 0, 0, 0 };
		ArrayUtil util = new ArrayUtil();
		int[] grow = util.grow(oldArray, size);
		assertArrayEquals(expecteds, grow);
	}

	@Test
	public void testFibonacci() {
		int max1 = 15;
		int[] expecteds1 = { 1, 1, 2, 3, 5, 8, 13 };
		ArrayUtil util = new ArrayUtil();
		int[] fibonacci1 = util.fibonacci(max1);
		assertArrayEquals(expecteds1, fibonacci1);

		int max2 = 1;
		int[] expecteds2 = {};
		int[] fibonacci2 = util.fibonacci(max2);
		assertArrayEquals(expecteds2, fibonacci2);
	}

	@Test
	public void testGetPrimes() {
		int max1 = 23;
		int[] expecteds1 = { 2, 3, 5, 7, 11, 13, 17, 19 };
		ArrayUtil util = new ArrayUtil();
		int[] primes1 = util.getPrimes(max1);
		assertArrayEquals(expecteds1, primes1);

		int max2 = 2;
		int[] expecteds2 = {};
		int[] primes2 = util.getPrimes(max2);
		assertArrayEquals(expecteds2, primes2);
	}

	@Test
	public void testGetPerfectNumbers() {
		int max1 = 2;
		int[] expecteds1 = {};
		ArrayUtil util = new ArrayUtil();
		int[] perfectNumbers1 = util.getPerfectNumbers(max1);
		assertArrayEquals(expecteds1, perfectNumbers1);

		// 6=1+2+3
		int max2 = 7;
		int[] expecteds2 = { 6 };
		int[] perfectNumbers2 = util.getPerfectNumbers(max2);
		assertArrayEquals(expecteds2, perfectNumbers2);

		// 28=1+2+4+7+14
		int max3 = 30;
		int[] expecteds3 = { 6, 28 };
		int[] perfectNumbers3 = util.getPerfectNumbers(max3);
		assertArrayEquals(expecteds3, perfectNumbers3);
	}

	@Test
	public void testJoin() {
		int[] array = { 3, 8, 9 };
		String seperator = "-";
		String expecteds = "3-8-9";
		ArrayUtil util = new ArrayUtil();
		String join = util.join(array, seperator);
		assertEquals(expecteds, join);
	}

}
