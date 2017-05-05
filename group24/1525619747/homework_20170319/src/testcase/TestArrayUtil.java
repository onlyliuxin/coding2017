package testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.basic.ArrayUtil;

public class TestArrayUtil
{
	private void print_r(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();

		// int index = 0;
		// while (a[index] != '\0') {
		// System.out.print(a[index] + " ");
		// ++index;
		// }
		// System.out.println();
	}

	@Test
	public void testReverseArray() {
		ArrayUtil arrayUtil = new ArrayUtil();

		int[] a = { 7, 9, 30, 3 };
		// print_r(a);

		arrayUtil.reverseArray(a);
		// print_r(a);
		assertTrue(a[0] == 3);
		assertTrue(a[1] == 30);
		assertTrue(a[3] == 7);

		int[] b = { 7, 9, 30, 3, 4 };
		// print_r(b);

		arrayUtil.reverseArray(b);
		// print_r(b);
		assertTrue(b[0] == 4);
		assertTrue(b[1] == 3);
		assertTrue(b[3] == 9);
		assertTrue(b[2] == 30);
	}

	@Test
	public void testRemoveZero() {
		ArrayUtil arrayUtil = new ArrayUtil();

		int[] oldArr = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int[] newArr = arrayUtil.removeZero(oldArr);
		// print_r(newArr);
		assertFalse(newArr[4] == 0);
		assertTrue(newArr[4] == 6);

	}

	@Test
	public void testMerge() {
		ArrayUtil arrayUtil = new ArrayUtil();

		int[] a1 = { 3, 5, 7, 8 };
		int[] a2 = { 4, 5, 6, 7 };

		int[] newArr = arrayUtil.merge(a1, a2);
		// print_r(newArr);
		assertTrue(newArr[0] == 3);
		assertTrue(newArr[2] == 5);
		assertTrue(newArr[3] == 6);
		assertTrue(newArr[5] == 8);
	}

	@Test
	public void testGrow() {
		ArrayUtil arrayUtil = new ArrayUtil();

		int[] a1 = { 3, 5, 7, 8 };
		a1 = arrayUtil.grow(a1, 3);
		// print_r(a1);
		assertTrue(a1[0] == 3);
		assertTrue(a1[2] == 7);
		assertTrue(a1[3] == 8);
		assertTrue(a1[4] == 0);
		assertTrue(a1[5] == 0);
		assertTrue(a1[6] == 0);

	}

	@Test
	public void testFibonacci() {
		ArrayUtil arrayUtil = new ArrayUtil();
		int max = 100;
		int[] arr = arrayUtil.fibonacci(max);
		// print_r(arr);

		assertNotNull(arr);
		int index = (int) (Math.random() * arr.length);
		assertTrue(arr[index] < max);

		arr = arrayUtil.fibonacci(1);
		assertNull(arr);
	}

	@Test
	public void testGetPrimes() {
		ArrayUtil arrayUtil = new ArrayUtil();
		int max = 23;
		int[] arr = arrayUtil.getPrimes(max);
		// print_r(arr);

		int index = (int) (Math.random() * arr.length);
		assertTrue(arr[index] < max);
		assertTrue(arrayUtil.isPrime(arr[index]));

	}

	@Test
	public void testGetPerfectNumbers() {
		ArrayUtil arrayUtil = new ArrayUtil();
		int max = 300;
		int[] arr = arrayUtil.getPerfectNumbers(max);
		// print_r(arr);

		int index = (int) (Math.random() * arr.length);
		assertTrue(arr[index] < max);
		assertTrue(arrayUtil.isPerfectNumber(arr[index]));

	}

	@Test
	public void testJoin() {
		ArrayUtil arrayUtil = new ArrayUtil();
		int[] a = { 3, 8, 9 };
		String str = arrayUtil.join(a, "-");
		// System.out.println(str);
		assertTrue(str.equals("3-8-9"));
	}

}
