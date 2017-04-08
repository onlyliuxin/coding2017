package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest
{

	@Test
	public void revice1()
	{
		int[] a =
		{ 7, 9, 30, 3 };
		ArrayUtil au = new ArrayUtil();
		au.reverseArray(a);
		int[] expecteds =
		{ 3, 30, 9, 7 };
		Assert.assertArrayEquals(expecteds, a);
	}

	@Test
	public void revice2()
	{
		int[] a =
		{ 7, 9, 30, 3, 4 };
		ArrayUtil au = new ArrayUtil();
		au.reverseArray(a);
		int[] expecteds =
		{ 4, 3, 30, 9, 7 };
		Assert.assertArrayEquals(expecteds, a);
	}

	@Test
	public void removeZeroTest1()
	{
		ArrayUtil au = new ArrayUtil();
		int oldArr[] =
		{ 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int[] newArr = au.removeZero(oldArr);
		int[] expecteds =
		{ 1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5 };
		Assert.assertArrayEquals(expecteds, newArr);

	}

	@Test
	public void removeZeroTest2()
	{
		ArrayUtil au = new ArrayUtil();
		int oldArr[] =
		{ 1 };
		int[] newArr = au.removeZero(oldArr);
		int[] expecteds =
		{ 1 };
		Assert.assertArrayEquals(expecteds, newArr);
	}
	
	@Test
	public void mergeTest1()
	{
		ArrayUtil au = new ArrayUtil();
		int [] a1 ={3, 5, 7,8};
		int [] a2 = {4, 5, 6,7};
		int [] expecteds ={3,4,5,6,7,8};
		int [] a3 =au.merge(a1, a2);
		Assert.assertArrayEquals(expecteds, a3);
	}
	
	@Test
	public void mergeTest2()
	{
		ArrayUtil au = new ArrayUtil();
		int [] a1 ={1, 1, 4,4};
		int [] a2 = {1, 1};
		int [] expecteds ={1,4};
		int [] a3 =au.merge(a1, a2);
		Assert.assertArrayEquals(expecteds, a3);
	}
	
	@Test
	public void growTest()
	{
		ArrayUtil au = new ArrayUtil();
		int []oldArray = {2,3,6};
		int [] newArray = au.grow(oldArray, 3);
		int [] expecteds = {2,3,6,0,0,0};
		Assert.assertArrayEquals(expecteds, newArray);
	}
	
	@Test
	public void fibonacciTest1()
	{
		ArrayUtil au = new ArrayUtil();
		int []expecteds = {1,1,2,3,5,8,13};
		int []actuals =au.fibonacci(13);
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void getPrimesTest()
	{
		ArrayUtil au = new ArrayUtil();
		int []expecteds = {2,3,5,7,11,13,17,19};
		int []actuals =au.getPrimes(20);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void getPerfectNumbersTest()
	{
		ArrayUtil au = new ArrayUtil();
		int []expecteds = {6};
		int []actuals =au.getPerfectNumbers(13);
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void join()
	{
		ArrayUtil au = new ArrayUtil();
		int []array= {3};
		String expecteds = "3";
		String str =au.join(array,"-");
		Assert.assertEquals(expecteds,str);
	}
}











