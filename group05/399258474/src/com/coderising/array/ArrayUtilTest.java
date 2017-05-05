package com.coderising.array;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;

public class ArrayUtilTest {
	ArrayUtil util = new ArrayUtil();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("-----------------------------");
	}

	@Test
	public void reverseArraytest() {
		int[] origin = new int[]{7,9,30,3};
		System.out.println(Arrays.toString(origin));
		util.reverseArray(origin);
		System.out.println("数组置换："+Arrays.toString(origin));
	}
	
	@Test
	public void removeZeroTest(){
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArr = util.removeZero(oldArr);
		System.out.println(Arrays.toString(oldArr) +"数组去0后为："+ Arrays.toString(newArr));
	}
	
	@Test
	public void mergeTest(){
		int[] a1 = {3,5,7,8};
		int[] a2 = {4,5,6,7};
		int[] a3 = util.merge(a1, a2);
		System.out.println(Arrays.toString(a1)+Arrays.toString(a2)+"合并、排序、去重后为："+Arrays.toString(a3));
	}
	
	@Test
	public void growTest(){
		int[] oldArray = {2,3,6};
		int i = 3;
		int[] newArray = util.grow(oldArray, i);
		System.out.println(Arrays.toString(oldArray) +" 扩容 "+ i +"后为："+Arrays.toString(newArray));
 	}
	
	@Test
	public void fibonacciTest(){
		int i = 15;
		int[] arr = util.fibonacci(i);
		System.out.println("斐波那契数列最大数小于"+ i +"的数组是：" + Arrays.toString(arr));
	}
	
	@Test
	public void getPrimesTest(){
		int max = 23;
		int[] arr = util.getPrimes(max);
		System.out.println("最大数小于"+ max +"的素数数组是：" + Arrays.toString(arr));
	}
	
	@Test
	public void getPerfectNumbersTest(){
		int max = 1000;
		int[] arr = util.getPerfectNumbers(max);
		System.out.println("最大数小于"+ max +"的完数数组是：" + Arrays.toString(arr));
	}
	
	@Test
	public void joinTest(){
		int[] arr = {3,8,9};
		String seperator = "-";
		String str = util.join(arr, seperator);
		System.out.println(Arrays.toString(arr)+"数组用\""+seperator+"\"连接后为："+str);
 	}
	
}









