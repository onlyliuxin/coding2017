package com.coderising.array;


import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayUtilTest {
	
	@Test
	public void testReverseArray() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test reverseArray(int[] array)");
		int[] src = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		System.out.println("values of arrays:" + Arrays.toString(src));
		ArrayUtil.reverseArray(src);
		System.out.println("after reversing" + Arrays.toString(src));
	}

	@Test
	public void testRemoveZero() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test removeZero(int[] array)");
		int[] src = { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0 };
		System.out.println("values of arrays:" + Arrays.toString(src));
		int[] target = ArrayUtil.removeZero(src);
		System.out.println("after removings:" + Arrays.toString(target));
	}

	@Test
	public void testMerge() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test merge(int[] array1,int[] array2)");
		int[] merge1 = { 1, 3, 4, 6, 8, };
		int[] merge2 = { 0, 2, 3, 4, 5, 7, 9 };
		System.out.println("sorted array1:" + Arrays.toString(merge1));
		System.out.println("sorted array2:" + Arrays.toString(merge2));
		int[] target = new int[merge1.length + merge2.length];
		target = ArrayUtil.merge(merge1, merge2);
		System.out.println("after merging and sorting:" + Arrays.toString(target));
	}

	@Test
	public void testGrow() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test grow(int[] array,int size)");
		int[] oldArrays = { 1, 3, 4, 6, 8, };
		int size = 10;
		System.out.println("size of oldArray:" + oldArrays.length);
		System.out.println("size grows " + size);
		oldArrays = ArrayUtil.grow(oldArrays, size);
		System.out.println("after growing:" + oldArrays.length);
	}

	@Test
	public void testFibonacci() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test fibonacci(int max)");
		int num=19;
		System.out.print("小于 "+num+" 的斐波那契数列:");
		System.out.println(Arrays.toString(ArrayUtil.fibonacci(15)));
	}

	@Test
	public void testGetPrimes() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test getPrimes(int max)");
		int num = 10000;
		System.out.println("小于 " + num + " 的素数有："
				+ Arrays.toString(ArrayUtil.getPrimes(num)));
	}

	@Test
	public void testGetPerfectNumbers() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test getPrimes(int max)");
		int num=33550337;
		System.out.println("小于 "+num+" 的完数有："+Arrays.toString(ArrayUtil.getPerfectNumbers(num)));
	}

	@Test
	public void testJoin() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test join(int[] array, String seperator)");
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		String seperator="+";
		System.out.println("use "+"\""+seperator+"\""+" to join array");
		System.out.println("old array:"+Arrays.toString(array));
		System.out.println("after joining:"+ArrayUtil.join(array, "-"));	
	}

}
