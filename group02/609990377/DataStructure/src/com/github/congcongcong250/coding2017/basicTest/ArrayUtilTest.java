package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.congcongcong250.coding2017.basic.ArrayUtil;

public class ArrayUtilTest {
	private ArrayUtil myArray;

	@Before
	public void setUp() throws Exception {
		myArray = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] origin = { 1, 2, 1, 3, 5, 6 };
		int[] reverse = { 6, 5, 3, 1, 2, 1 };

		myArray.reverseArray(origin);
		assertArrayEquals(origin, reverse);

		int[] empty = new int[0];
		myArray.reverseArray(empty);
		assertArrayEquals(empty, new int[0]);
	}

	@Test
	public void testRemoveZero() {
		int[] oldArray = { 1, 5, 0, 0, 6, 6, 0, 5, 4, 0, 7, 6, 7, 1, 2, 0 };
		int[] newArray = { 1, 5, 6, 6, 5, 4, 7, 6, 7, 1, 2 };
		int[] res = myArray.removeZero(oldArray);
		assertArrayEquals(newArray, res);

		int[] nl = null;
		int[] nll = myArray.removeZero(nl);
		assertNull(nll);
	}

	@Test
	public void testMerge() {
		int a1[] = { 1, 2, 3, 4, 5 };
		int b1[] = { 3, 4, 5, 6, 7, 8 };
		int c1[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] newArray1 = myArray.merge(a1, b1);
		assertArrayEquals(c1, newArray1);

		int a2[] = new int[0];
		int b2[] = { 0, 2, 3, 6, 7, 8 };
		int c2[] = { 0, 2, 3, 6, 7, 8 };
		int[] newArray2 = myArray.merge(a2, b2);
		assertArrayEquals(c2, newArray2);

		int a3[] = { 0, 2, 3, 6, 7, 8 };
		int b3[] = new int[0];
		int c3[] = { 0, 2, 3, 6, 7, 8 };
		int[] newArray3 = myArray.merge(a3, b3);
		assertArrayEquals(c3, newArray3);

		int[] a4 = null;
		int[] b4 = null;
		int[] newArray4 = myArray.merge(a4, b4);
		assertNull(newArray4);
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testGrow() {
		int[] a = { 3, 5, 7, 8, 9 };
		int[] b = { 3, 5, 7, 8, 9, 0, 0, 0 };
		int[] newArray = myArray.grow(a, 3);
		assertArrayEquals(b, newArray);

		int[] c = null;
		int[] newArray1 = myArray.grow(c, 3);
		assertNull(newArray1);

		// size < 0 
		expectedEx.expect(Exception.class);
		myArray.grow(a, -3);
	}

	@Test
	public void testFibonacci() {
		
		int[] array1 = myArray.fibonacci(1);
		int[] b = new int[0];
		assertArrayEquals(array1, b);

		int[] array2 = myArray.fibonacci(35);
		int[] c = { 1, 1, 2, 3, 5, 8, 13, 21, 34 };
		assertArrayEquals(c, array2);
	}

	@Test
	public void testGetPrimes() {
		int[] a = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
		int[] array1 = myArray.getPrimes(35);
		assertArrayEquals(a, array1);

		int[] array2 = myArray.getPrimes(1);
		int[] b = new int[0];
		assertArrayEquals(array2, b);
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] array = myArray.getPerfectNumbers(10000);
		int[] a = { 6, 28, 496, 8128 };
		assertArrayEquals(a, array);
	}

	@Test
	public void testJoin() {
		int[] a = { 3, 5, 7, 8, 9 };
		String s0 = myArray.join(a, "-");
		String s1 = "3-5-7-8-9";
		assertEquals(s1, s0);

		int[] a1 = { 3 };
		String s2 = myArray.join(a1, "-");
		String s3 = "3";
		assertEquals(s2, s3);

		int[] a0 = new int[0];
		String s4 = myArray.join(a0, "-");
		String s5 = "";
		assertEquals(s4, s5);
	}

}
