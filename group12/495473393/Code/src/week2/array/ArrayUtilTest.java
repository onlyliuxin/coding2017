/**
 * 
 */
package week2.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author TangHaoJie
 *
 */
public class ArrayUtilTest {

	private ArrayUtil au;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		au = new ArrayUtil();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * {@link week2.array.ArrayUtil#reverseArray(int[])} 的测试方法。
	 */
	@Test
	public void testReverseArray() {
		int[] a1 = null;
		au.reverseArray(a1);
		assertArrayEquals(null, a1);

		int[] a2 = new int[0];
		au.reverseArray(a2);
		assertArrayEquals(new int[0], a2);

		int[] a3 = new int[] { 1, 2, 3, 4, 5, 6 };
		au.reverseArray(a3);
		assertArrayEquals(new int[] { 6, 5, 4, 3, 2, 1 }, a3);
	}

	/**
	 * {@link week2.array.ArrayUtil#removeZero(int[])} 的测试方法。
	 */
	@Test
	public void testRemoveZero() {
		int[] a1 = null;
		int[] b1 = au.removeZero(a1);
		assertArrayEquals(b1, a1);

		int[] a2 = new int[0];
		int[] b2 = au.removeZero(a2);
		assertArrayEquals(b2, a2);

		int[] a3 = new int[] { 1, 2, 3, 4, 5, 6 };
		int[] b3 = au.removeZero(a3);
		assertArrayEquals(b3, a3);

		int[] a4 = new int[] { 0, 0, 1, 2, 0, 3, 4, 0, 5, 6 };
		int[] b4 = au.removeZero(a4);
		assertArrayEquals(b4, new int[] { 1, 2, 3, 4, 5, 6 });

		int[] a5 = new int[] { 1, 2, 0, 3, 4, 0, 5, 6, 0, 0, 0 };
		int[] b5 = au.removeZero(a5);
		assertArrayEquals(b5, new int[] { 1, 2, 3, 4, 5, 6 });
	}

	/**
	 * {@link week2.array.ArrayUtil#merge(int[], int[])} 的测试方法。
	 */
	@Test
	public void testMerge() {
		int[] a1 = null;
		int[] b1 = null;
		int[] c1 = au.merge(a1, b1);
		assertArrayEquals(c1, null);

		int[] a2 = new int[0];
		int[] b2 = new int[0];
		int[] c2 = au.merge(a2, b2);
		assertArrayEquals(c2, new int[0]);

		int[] a3 = new int[] { 1, 3, 5, 7, 9 };
		int[] b3 = new int[] { 2, 4, 6, 8, 10 };
		int[] c3 = au.merge(a3, b3);
		assertArrayEquals(c3, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
	}

	/**
	 * {@link week2.array.ArrayUtil#grow(int[], int)} 的测试方法。
	 */
	@Test
	public void testGrow() {
		int[] a1 = null;
		int[] b1 = au.grow(a1, 0);
		assertArrayEquals(b1, a1);

		int[] a2 = new int[0];
		int[] b2 = au.grow(a2, 0);
		assertArrayEquals(b2, a2);

		int[] a3 = new int[] { 1, 2 };
		int[] b3 = au.grow(a3, -10);
		assertArrayEquals(b3, a3);

		int[] a4 = new int[] { 1, 2 };
		int[] b4 = au.grow(a4, 5);
		assertArrayEquals(b4, new int[] { 1, 2, 0, 0, 0, 0, 0 });
	}

	/**
	 * {@link week2.array.ArrayUtil#fibonacci(int)} 的测试方法。
	 */
	@Test
	public void testFibonacci() {
	}

	/**
	 * {@link week2.array.ArrayUtil#getPrimes(int)} 的测试方法。
	 */
	@Test
	public void testGetPrimes() {
	}

	/**
	 * {@link week2.array.ArrayUtil#getPerfectNumbers(int)} 的测试方法。
	 */
	@Test
	public void testGetPerfectNumbers() {
	}

	/**
	 * {@link week2.array.ArrayUtil#join(int[], java.lang.String)} 的测试方法。
	 */
	@Test
	public void testJoin() {
	}

}
