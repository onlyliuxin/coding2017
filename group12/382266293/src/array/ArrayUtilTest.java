package array;

import static org.junit.Assert.*;
import static util.TestUtil.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	private int[] actual;
	ArrayUtil au = new ArrayUtil();

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		actual = null;
	}

	@Test
	public void testReverseArray() {

		int size = getRandomNumber();
		int[] expected = getRandomIntArray(size);
		actual = Arrays.copyOf(expected, size);

		au.reverseArray(actual);

		for (int i = 0; i < size; i++) {
			assertEquals(expected[i], actual[size - 1 - i]);
		}

	}

	@Test
	public void testRemoveZero() {

		int size = getRandomNumber(10000);
		int[] expected = getRandomIntArray(size);

		int zeros = getRandomNumber(size - 1);
		TreeSet<Integer> t = new TreeSet<Integer>();
		while (t.size() != zeros) {
			t.add(getRandomNumber(size));
		}

		for (Integer i : t) {
			expected[i] = 0;
		}

		int expectedSize = size - zeros;
		actual = au.removeZero(expected);
		assertEquals(expectedSize, actual.length);

		for (int i = 0, j = 0; i < size; i++) {
			if (expected[i] != 0)
				assertEquals(expected[i], actual[j++]);
		}

	}

	@Test
	public void testMerge() {
		int[] arr1 = getRandomIntArray(getRandomNumber());
		int[] arr2 = getRandomIntArray(getRandomNumber());
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		TreeSet t = new TreeSet();
		for (int i = 0; i < arr1.length; i++) {
			t.add(arr1[i]);
		}
		for (int i = 0; i < arr2.length; i++) {
			t.add(arr2[i]);
		}
		int[] actual = new int[arr1.length + arr2.length];
		actual = au.merge(arr1, arr2);

		assertEquals(t.size(), actual.length);

		Iterator it = t.iterator();
		for (int i = 0; it.hasNext(); i++) {
			assertEquals((int) it.next(), actual[i]);
		}

	}

	@Test
	public void testGrow() {
		int[] expected = getRandomIntArray(getRandomNumber());
		int growSize = getRandomNumber();
		int[] actual = au.grow(expected, growSize);

		assertEquals(expected.length + growSize, actual.length);

		for (int i = 0; i < actual.length; i++) {
			if (i < expected.length) {
				assertEquals(expected[i], actual[i]);
			} else {
				assertEquals(0, actual[i]);
			}
		}

	}

	@Test
	public void testFibonacci() {
		int[] expected = new int[] { 1, 1, 2, 3, 5, 8, 13 };
		int[] acutal = new int[expected.length];
		actual = au.fibonacci(15);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testGetPrimes() {

		int[] expected = new int[] { 2, 3, 5, 7, 11, 13, 17, 19 };
		int[] acutal = new int[expected.length];
		actual = au.getPrimes(23);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testGetPerfectNumbers() {

		int[] expected = new int[] { 6, 28, 496, 8128 };
		int[] acutal = new int[expected.length];
		actual = au.getPerfectNumbers(10000);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testJoin() {

		int[] expected = getRandomIntArray(getRandomNumber());
		String seperator = "-";
		String joinedString = au.join(expected, seperator);

		String[] actual = joinedString.split(seperator);

		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], Integer.parseInt(actual[i]));
		}

	}

}
