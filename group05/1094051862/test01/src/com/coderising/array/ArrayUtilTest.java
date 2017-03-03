package com.coderising.array;

import static org.junit.Assert.*;
import junit.framework.Assert;

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
		int[] a = {7,9,30,3,5};
		int[] b = a.clone();
		ArrayUtil.reverseArray(a);
		for (int i = 0; i < b.length; i ++) {
			Assert.assertEquals(b[i], a[b.length - 1 - i]);
		}
		
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] result = {1,3,4,5,6,6,5,4,7,6,7,5};
		int[] removeZero = ArrayUtil.removeZero(oldArr);
		assertArrayEquals(result, removeZero);
	}

	@Test
	public void testMerge() {
		 int[] a1 = {3,5,7,8};
		 int[] a2 = {4,5,6,7};
		 int[] a3 = {3,4,5,6,7,8};
		 int[] merge = ArrayUtil.merge(a1, a2);
		 assertArrayEquals(a3, merge);
	}

	@Test
	public void testGrow() {
		fail("Not yet implemented");
	}

	@Test
	public void testFibonacci() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrimes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPerfectNumbers() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoin() {
		fail("Not yet implemented");
	}

}
