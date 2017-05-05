package tong.java.two;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil util = new ArrayUtil();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a = { 7, 9, 30, 3, 70 };
		util.reverseArray(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	@Test
	public void testRemoveZero() {
		fail("Not yet implemented");
	}

	@Test
	public void testMerge() {
		int[] a = { 2, 4, 7 };
		int[] b = { 4, 8, 9 };
		int[] c = util.merge(a, b);
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
		}
	}

	@Test
	public void testGrow() {
		int[] oldArray = { 2, 4, 7 };
		int[] newArray = util.grow(oldArray, 3);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
	}

	@Test
	public void testFibonacci() {
		int[] result = util.fibonacci(1);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	@Test
	public void testGetPrimes() {
		int[] result = util.getPrimes(15);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] result = util.getPerfectNumbers(100);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	@Test
	public void testJoin() {
		int[] array = { 3, 2 };
		System.out.println(util.join(array, "~"));
	}

	@Test
	public void testFibo() {
		System.out.println(util.fibo(5));
	}

	@Test
	public void testGetParas() throws Exception {
		int[] paras = util.getPara(20);
		for (int i = 0; i < paras.length; i++) {
			System.out.println(paras[i]);
		}
	}

}
