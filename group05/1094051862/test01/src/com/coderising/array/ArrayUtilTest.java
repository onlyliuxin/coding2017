package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	@Before
	public void setUp() throws Exception {
		ArrayUtil u;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		ArrayUtil util = new ArrayUtil();
		int[] a = {7,9,30,3,5};
		util.reverseArray(a);
		System.out.println();
	}

	@Test
	public void testRemoveZero() {
		fail("Not yet implemented");
	}

	@Test
	public void testMerge() {
		fail("Not yet implemented");
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
