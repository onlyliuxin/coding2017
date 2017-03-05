package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	@Test
	public final void testReverseArray() {
		int[] intArray = new int[]{7, 9 , 30, 3};
		int[] reversedArray = new int[]{3, 30, 9, 7};
		ArrayUtil.reverseArray(intArray);
		for(int i=0; i<intArray.length; ++i)
		{
			assertEquals(intArray[i], reversedArray[i]);
		}
		
		intArray = new int[]{7, 9, 30, 3, 4};
		reversedArray = new int[]{4,3, 30 , 9,7};
		ArrayUtil.reverseArray(intArray);
		for(int i=0; i<intArray.length; ++i)
		{
			assertEquals(intArray[i], reversedArray[i]);
		}
	}

	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	@Test
	public final void testRemoveZero() {
		int[] oldArr= new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArr = ArrayUtil.removeZero(oldArr);
		int[] expectedNewArray = new int[]{1,3,4,5,6,6,5,4,7,6,7,5};
		assertNotNull(newArr);
		assertEquals(newArr.length, expectedNewArray.length);
		for(int i = newArr.length; i < newArr.length; ++i)
		{
			assertEquals(newArr[i], expectedNewArray[i]);
		}
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	@Test
	public final void testMerge() {
		int[] a1 = new int[]{3, 5, 7,8};
		int[] a2 = new int[]{4, 5, 6,7};
		int[] mergedArray = ArrayUtil.merge(a1, a2);
		int[] expectedMergedArray = new int[]{3,4,5,6,7,8};
		assertNotNull(mergedArray);
		assertEquals(expectedMergedArray.length, mergedArray.length);
		for(int i=0; i<mergedArray.length; ++i)
		{
			assertEquals(expectedMergedArray[i], mergedArray[i]);
		}
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
	public final void testGrow() {
		int[] oldArray = new int[]{2,3,6};
		int[] newArray = ArrayUtil.grow(oldArray, 3);
		assertNotNull(newArray);
		assertEquals(oldArray.length + 3, newArray.length);
		int[] expectedArray = new int[]{2,3,6,0,0,0};
		for(int i=0; i<newArray.length; ++i)
		{
			assertEquals(expectedArray[i], newArray[i]);
		}
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	@Test
	public final void testFibonacci() {
		int max = 15;
		int[] result = ArrayUtil.fibonacci(max);
		assertNotNull(result);
		assertEquals(7, result.length);
		int[] expectedResult = new int[]{1,1,2,3,5,8,13};
		for(int i=0; i<result.length; ++i)
		{
			assertEquals(expectedResult[i], result[i]);
		}
		max = 1;
		result = ArrayUtil.fibonacci(max);
		assertNotNull(result);
		assertEquals(0, result.length);
		expectedResult = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 
				233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 
				28657, 46368, 75025, 121393, 196418, 317811, 514229, 
				832040, 1346269, 2178309, 3524578, 5702887, 9227465};
		result = ArrayUtil.fibonacci(9227466);
		assertNotNull(result);
		assertEquals(expectedResult.length, result.length);
		for(int i=0; i<result.length; ++i)
		{
			assertEquals(expectedResult[i], result[i]);
		}
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	@Test
	public final void testGetPrimes() {
		int max = 23;
		int[] result = ArrayUtil.getPrimes(max);
		int[] expectedResult = new int[]{2,3,5,7,11,13,17,19};
		assertNotNull(result);
		assertEquals(expectedResult.length, result.length);
		for(int i=0; i<result.length; ++i)
		{
			System.out.println(result[i]);
			assertEquals(expectedResult[i], result[i]);
		}
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	@Test
	public final void testGetPerfectNumbers() {
		int max = 500;
		int[] result = ArrayUtil.getPerfectNumbers(max);
		int[] expectedResult = new int[]{6, 28, 496};
		assertNotNull(result);
		assertEquals(expectedResult.length, result.length);
		for(int i=0; i<result.length; ++i)
		{
			assertEquals(expectedResult[i], result[i]);
		}
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
	public final void testJoin() {
		int[] array = new int[]{3,8,9};
		String seperator = "-";
		String result = ArrayUtil.join(array, seperator);
		String expectedResult = "3-8-9";
		assertEquals(expectedResult, result);
	}

}
