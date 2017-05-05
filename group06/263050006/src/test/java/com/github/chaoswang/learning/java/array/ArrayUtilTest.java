package com.github.chaoswang.learning.java.array;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	@Test
	public void testReverseArray() {
		int a[] = {7, 9, 30, 3};
		int reversedA[] = {3, 30, 9, 7};
		int b[] = {7, 9, 30, 3, 4};
		int reversedB[] = {4, 3, 30, 9, 7};
		Assert.assertArrayEquals(reversedA, ArrayUtil.reverseArray(a));
		Assert.assertArrayEquals(reversedB, ArrayUtil.reverseArray(b));
	}

	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int newArr[]={1,3,4,5,6,6,5,4,7,6,7,5}; 
		Assert.assertArrayEquals(newArr, ArrayUtil.removeZero(oldArr));
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	@Test
	public void testMerge() {
		int a1[] = {3, 5, 7,8};
		int a2[] = {4, 5, 6,7};
		int a3[] = {3,4,5,6,7,8};
		Assert.assertArrayEquals(a3, ArrayUtil.merge(a1, a2));
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	@Test
	public void testGrow() {
		int oldArray[] = {2,3,6};
		int newArray[] = {2,3,6,0,0,0};
		Assert.assertArrayEquals(newArray, ArrayUtil.grow(oldArray, 3));
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	@Test
	public void testFibonacci() {
		int returnA[] = {1,1,2,3,5,8,13};
		int returnB[] = {};
		int returnC[] = {1,1,2,3,5,8,13,21,34};
		int returnD[] = {1,1,2};
		Assert.assertArrayEquals(returnA, ArrayUtil.fibonacci(15));
		Assert.assertArrayEquals(returnB, ArrayUtil.fibonacci(1));
		Assert.assertArrayEquals(returnC, ArrayUtil.fibonacci(34));
		Assert.assertArrayEquals(returnD, ArrayUtil.fibonacci(2));
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	@Test
	public void testGetPrimes() {
		int returnC[] = {2,3,5,7,11,13,17,19};
		int returnD[] = {2,3,5,7,11};
		Assert.assertArrayEquals(returnC, ArrayUtil.getPrimes(23));
		Assert.assertArrayEquals(returnD, ArrayUtil.getPrimes(12));
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	@Test
	public void testGetPerfectNumbers() {
		int returnC[] = {6,28,496};
		int returnD[] = {6,28,496,8128};
		int returnE[] = {6,28,496,8128,33550336};
		Assert.assertArrayEquals(returnC, ArrayUtil.getPerfectNumbers(496));
		Assert.assertArrayEquals(returnD, ArrayUtil.getPerfectNumbers(8129));
		Assert.assertArrayEquals(returnE, ArrayUtil.getPerfectNumbers(33550337));
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	@Test
	public void testJoin() {
		int array[] = {3,8,9};
		Assert.assertEquals("3-8-9", ArrayUtil.join(array,"-"));
		Assert.assertEquals("3@$8@$9", ArrayUtil.join(array,"@$"));
	}

}
