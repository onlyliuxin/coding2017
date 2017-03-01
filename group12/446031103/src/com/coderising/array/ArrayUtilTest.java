package com.coderising.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ArrayUtilTest {
	ArrayUtil au = new ArrayUtil();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReverseArray() {
		int [] origin = {4,7, 9 , 30, 3};
		au.reverseArray(origin);
		Assert.assertEquals(3, origin[0]);
		Assert.assertEquals(30, origin[1]);
		Assert.assertEquals(9, origin[2]);
		Assert.assertEquals(7, origin[3]);
		Assert.assertEquals(4, origin[4]);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};		
		int [] result=au.removeZero(oldArr);
		Assert.assertEquals(false, Arrays.asList(result).contains(0));
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
