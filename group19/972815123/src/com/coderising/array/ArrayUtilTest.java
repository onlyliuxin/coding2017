package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class ArrayUtilTest {
	
	private ArrayUtil au; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		au = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] test = {1,2,3};
		au.reverseArray(test);
		String result = au.join(test, "-");
		Assert.assertEquals(result, "3-2-1");
	}

	@Test
	public void testRemoveZero() {
		int[] test = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		test = au.removeZero(test);
		String result = au.join(test, ",");
		Assert.assertEquals(result, "1,3,4,5,6,6,5,4,7,6,7,5");
	}

	@Test
	public void testMerge() {
		int[] arr1 = {3, 5, 7,8};
		int[] arr2 = {4, 5, 6,7};
		int[] test = au.merge(arr1, arr2);
		String result = au.join(test, ",");
		System.out.println(result);
		Assert.assertEquals(result, "3,4,5,6,7,8");
	}

	@Test
	public void testGrow() {
		int[] test = {3,5,6};
		test = au.grow(test, 2);
		String result = au.join(test, ",");
		System.out.println(result);
		System.out.println(test.length);
		Assert.assertTrue(5 == test.length);
	}

	@Test
	public void testFibonacci() {
		int[] test = au.fibonacci(250);
		String result = au.join(test, ",");
		System.out.println(result);
		Assert.assertEquals(result, "1,1,2,3,5,8,13,21,34,55,89,144,233,377");
	}

	@Test
	public void testGetPrimes() {
		//2,3,5,7,11,13,17,19
		int[] test = au.getPrimes(23);
		String result = au.join(test, ",");
		System.out.println(result);
		Assert.assertEquals(result, "2,3,5,7,11,13,17,19");
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] test = au.getPerfectNumbers(10000);
		String result = au.join(test, ",");
		System.out.println(result);
		Assert.assertEquals(result, "6,28,496,8128");
	}
	
	
	@Test
	public void testGetAllFactors(){
		int[] test = au.getAllFactors(98);
		String result = au.join(test, ",");
		System.out.println(result);
		Assert.fail();
	}
	
	@Test
	public void testGetPrimeFactors(){
		int[] test = au.getPrimeFactors(98);
		String result = au.join(test, ",");
		System.out.println(result);
		Assert.fail();
	}

	@Test
	public void testJoin() {
		int[] test = {1,2,3};
		String result = au.join(test, ",");
		
		Assert.assertEquals(result, "1,2,3");
	}

}
