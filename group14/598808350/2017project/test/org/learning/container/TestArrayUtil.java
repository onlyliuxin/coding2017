package org.learning.container;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestArrayUtil extends TestCase{
	
	private ArrayUtil au = null;
	
	@Override
	public void setUp(){
		//首先执行
		//生成实例
		au = new ArrayUtil();
		
	}
	public void testReverseArray(){
		int [] array1 = {7,9,30,3};
		int[] result1 = au.reverseArray(array1);
		int[] exp1 = {3,30,9,7};
		Assert.assertArrayEquals(exp1, result1);
		
	}
	public void testRemoveZero(){
		 int[] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		 int[] newArray = au.removeZero(oldArray);
		 int[] exp1 = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
		 Assert.assertArrayEquals(exp1, newArray);
	}
	public void testMerge(){
		int[] array1 = {3, 5, 7,8};
		int[] array2 = {4, 5, 6,7};
		int[] newArray = au.merge(array1, array2);
		int[] exp1 = {3,4,5,6,7,8};
		Assert.assertArrayEquals(exp1, newArray);
	}
	
	public void testGrow(){
		int[] array1 = {3, 5, 7,8};
		int[] newArray = au.grow(array1, 1);
		int[] exp1= {3, 5, 7,8,0};
		Assert.assertArrayEquals(exp1, newArray);
	}
	
	public void testFibonacci(){
		int  max = 22;
		int[] newArray = au.fibonacci(max);
		int[] exp1 = {1,1,2,3,5,8,13,21};
		Assert.assertArrayEquals(exp1, newArray);
	}
	public void testGetPrimes(){
		int max =23;
		int[] newArray = au.getPrimes(max);
		int[] exp1 = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(exp1, newArray);
	}
	public void testGetPerfectNumbers(){
		int max = 497;
		int[] newArray = au.getPerfectNumbers(max);
		int[] exp1 = {6, 28, 496};
		Assert.assertArrayEquals(exp1, newArray);
	}
	public void testJoin(){
		//join(int[] array, String seperator)
		int[] array = {3,8,9};
		String seperator = "-";
		String result = au.join(array, seperator);
		String exp1 = "3-8-9";
		Assert.assertEquals("相等", exp1, result);
	}
	
	
	@Override
	public void tearDown(){
		//最后执行
	}
}
