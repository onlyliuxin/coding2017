package com.coderising.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testReverseArray() {
		int[] origin = new int[]{7,9,30,3};
		System.out.println(Arrays.toString(origin));
		util.reverseArray(origin);
		System.out.println("数组置换："+Arrays.toString(origin));
		
	}

	@Test
	public void testRemoveZero() {
		  int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		  System.out.println(Arrays.toString(oldArr));
		  int [] newArr = util.removeZero(oldArr);
		  System.out.println("去0后新数组："+Arrays.toString(newArr));
	}

	@Test
	public void testMerge() {
		int [] a1 = {3, 5, 7,8};
		int [] a2 = {4, 5, 6,7};
		int [] a3 = util.merge(a1, a2);
		System.out.println("合并排序后的数组："+Arrays.toString(a3));
	}

	@Test
	public void testGrow() {
		int [] oldArray = {2,3,6};
		int size = 3;
		int[] newArr = util.grow(oldArray, size);
		System.out.println("增长后的数组："+Arrays.toString(newArr));
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int[] arr = util.fibonacci(max);
		System.out.println("返回小于"+max+"的数列"+Arrays.toString(arr));
	}

	@Test
	public void testGetPrimes() {
		int max=23;
		int []arr = util.getPrimes(max);
		System.out.println("返回小于"+max+"所有素数数组"+Arrays.toString(arr));
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 6;
		int [] arr= util.getPerfectNumbers(max);
		System.out.println("返回小于"+max+"的所有完数"+Arrays.toString(arr));
	}

	@Test
	public void testJoin() {
		int[] array= {3,8,9};
		String seperator = "-";
		String str = util.join(array, seperator);
		System.out.println("返回带连接符的数组表示"+str);
	}

}
