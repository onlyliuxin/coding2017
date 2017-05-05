package com.coderising.litestruts;

import static org.junit.Assert.*;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.coderising.array.ArrayUtil;

public class ArrayUtilTest {

	private ArrayUtil arrayUtil = null;

	@Before
	public void init() {
		arrayUtil = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] origin = { 7, 9, 30, 3 };
		arrayUtil.reverseArray(origin);
		origin = new int[] { 7, 9, 30, 3, 4 };
		arrayUtil.reverseArray(origin);
	}

	@Test
	public void testRemoveZero() {
		int oldArr[] = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		assertArrayEquals(new int[] { 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 },
				arrayUtil.removeZero(oldArr));
	}

	@Test
	public void testMerge() {
		int[] array1 = { 3, 5, 7, 8 };
		int[] array2 = { 4, 5, 6, 7 };
		int[] array3 = { 3, 4, 5, 6, 7, 8 };
		assertArrayEquals(array3, arrayUtil.merge(array1, array2));
		assertArrayEquals(array3, arrayUtil.mergeMethod(array1, array2));
	}

	@Test
	public void testGrow() {
		int[] oldArray = { 2, 3, 6 };
		int[] newArray = arrayUtil.grow(oldArray, 3);
		assertArrayEquals(new int[] { 2, 3, 6, 0, 0, 0 }, newArray);
	}

	@Test
	public void testFibonacci() {
		assertArrayEquals(new int[] { 1, 1, 2, 3, 5, 8, 13 }, arrayUtil.fibonacci(15));
		assertArrayEquals(new int[0], arrayUtil.fibonacci(1));
	}

	@Test
	public void testGetPrimes() {
		System.out.println(Arrays.toString(arrayUtil.getPrimes(23)));
	}

	@Test
	public void testGetPerfectNumbers() {
		assertArrayEquals(new int[]{6}, arrayUtil.getPerfectNumbers(6));
	}

	@Test
	public void testJoin() {
		int[] array = { 3, 8, 9 };
		Assert.assertEquals("3-8-9", arrayUtil.join(array, "-"));
	}
	@Test
	public void main(){
		System.out.println(3 / 2 + 1);
	}
}
