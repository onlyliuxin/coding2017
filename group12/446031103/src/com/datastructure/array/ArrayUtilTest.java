package com.datastructure.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings({ "deprecation", "unused" })
public class ArrayUtilTest {
	ArrayUtil au = new ArrayUtil();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

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

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};		
		int [] result=au.removeZero(oldArr);
		Assert.assertEquals(false, Arrays.asList(result).contains(0));
	}

	@Test
	public void testMerge() {
		int [] array1 = {3,5,7,8};
		int [] array2 = {4,5,6,7};    
		int [] array3 =	au.merge(array1, array2);
		Assert.assertEquals(3, array3[0]);
		Assert.assertEquals(4, array3[1]);
		Assert.assertEquals(5, array3[2]);
		Assert.assertEquals(6, array3[3]);
		Assert.assertEquals(7, array3[4]);
		Assert.assertEquals(8, array3[5]);
	}

	@Test
	public void testGrow() {
		int []oldArray = {2,3,6}; 
		int [] result=au.grow(oldArray, 3);
		Assert.assertEquals(2, result[0]);
		Assert.assertEquals(3, result[1]);
		Assert.assertEquals(6, result[2]);
		Assert.assertEquals(0, result[3]);
		Assert.assertEquals(0, result[4]);
		Assert.assertEquals(0, result[5]);
	}

	@Test
	public void testFibonacci() {
		int [] result= au.fibonacci(22);
		Assert.assertEquals(1, result[0]);
		Assert.assertEquals(1, result[1]);
		Assert.assertEquals(2, result[2]);
		Assert.assertEquals(3, result[3]);
		Assert.assertEquals(5, result[4]);
		Assert.assertEquals(8, result[5]);
		Assert.assertEquals(13, result[6]);
		Assert.assertEquals(21, result[7]);
	}

	@Test
	public void testGetPrimes() {
		int [] result=au.getPrimes(23);
		Assert.assertEquals(2, result[0]);
		Assert.assertEquals(3, result[1]);
		Assert.assertEquals(5, result[2]);
		Assert.assertEquals(7, result[3]);
		Assert.assertEquals(11, result[4]);
		Assert.assertEquals(13, result[5]);
		Assert.assertEquals(17, result[6]);
		Assert.assertEquals(19, result[7]);
	}

	@Test
	public void testGetPerfectNumbers() {
		int [] result=au.getPerfectNumbers(100);
		Assert.assertEquals(6, result[0]);
		Assert.assertEquals(28, result[1]);
	}

	@Test
	public void testJoin() {
		int [] array = {3,8,9};
		String result=au.join(array, "-");
		Assert.assertEquals("3-8-9",result);
	}

}
