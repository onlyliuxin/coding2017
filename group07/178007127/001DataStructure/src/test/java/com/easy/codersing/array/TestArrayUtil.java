package com.easy.codersing.array;


import org.junit.Assert;
import org.junit.Test;


public class TestArrayUtil {
	@Test
	public void test_reverseArray(){
		int[] origin =new int[]{1,2,3};
		int[] dest =ArrayUtil.reverseArray(origin);
		Assert.assertArrayEquals(new int[]{3,2,1}, dest);
	}
	
	@Test
	public void test_removeZero(){
		int[] origin=new int[]{1,0,2,0,3,0};
		int[] dest = ArrayUtil.removeZero(origin);
		Assert.assertArrayEquals(new int[]{1,2,3}, dest);
	}
	
	@Test
	public void test_merge(){
		int[] array1=new int[]{4,0,6,2};
		int[] array2=new int[]{7,0,9,2,3};
	    int[] array3=ArrayUtil.merge(array1, array2);
	    //System.out.println(Arrays.toString(array3));
	    Assert.assertArrayEquals(new int[]{0, 2, 3, 4, 6, 7, 9}, array3);
	}
	
	@Test
	public void test_sort(){
		int[] arr=new int[]{4,6,2};
		arr = ArrayUtil.sort(arr);
		//System.out.println(Arrays.toString(arr));
		Assert.assertArrayEquals(new int[]{2,4,6},arr);
	}
	
	
	@Test
	public void test_getfabonacci(){
		int max=15;
		int[] intArr=ArrayUtil.fibonacci(max);
		//System.out.println(Arrays.toString(intArr));
		Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, intArr);
	}
	
	@Test
	public void test_getPrimes(){
		int[] intArr =ArrayUtil.getPrimes(16);
		Assert.assertArrayEquals(new int[]{2,3,5,7,11,13}, intArr);
	}
	
	@Test
	public void test_getPerfectNumbers(){
		int[] intArr =ArrayUtil.getPerfectNumbers(10);
		Assert.assertArrayEquals(new int[]{6}, intArr);
	}
	
	@Test
	public void test_join(){
		int[] intArr=new int[]{1,2,3};
		String s=ArrayUtil.join(intArr, "-");
		Assert.assertEquals("1-2-3", s);
	}
}
