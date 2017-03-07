package com.github.chaoswang.learning.java.array;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
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
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
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
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
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
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
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
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
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
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
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
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
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
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
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
