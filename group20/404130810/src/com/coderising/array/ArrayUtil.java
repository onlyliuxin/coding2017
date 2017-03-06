package com.coderising.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

	/**
	 * ����һ����������a , �Ը������ֵ�����û� ���磺 a = [7, 9 , 30, 3] , �û���Ϊ [3, 30, 9,7] ��� a =
	 * [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int[] reversedArray = new int[origin.length];
		for (int i = origin.length - 1, j = 0; i >= 0 && j < origin.length; i--, j++) {
			reversedArray[j] = origin[i];
		}
		origin = reversedArray;
	}

	/**
	 * ���������µ�һ�����飺 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ�� {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int zeroCount = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				zeroCount++;
			}
		}
		int[] removedZeroArray = new int[oldArray.length - zeroCount];

		for (int i = 0, j = 0; i < oldArray.length; i++, j++) {
			if (oldArray[i] == 0) {
				j--;
				continue;
			}
			removedZeroArray[j] = oldArray[i];
		}
		return removedZeroArray;
	}

	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 , ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ������� ���� a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] �� a3 Ϊ[3,4,5,6,7,8] , ע�⣺ �Ѿ��������ظ�
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		List<Integer> mergedArrayList = new ArrayList<>();
		return null;
	}

	/**
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ���� ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
	 * [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int[] oldArray, int size) {
		int[] resultArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, resultArray, 0, oldArray.length);
		return resultArray;
	}

	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21...... ������һ�����ֵ�� ����С�ڸ�ֵ������ ���磬 max = 15 ,
	 * �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13] max = 1, �򷵻ؿ����� []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		
		return null;
	}

	private static int fibonacciNum(int n) {
		if (n <= 2) {
			return 1;
		} else {
			return fibonacciNum(n - 1) + fibonacciNum(n - 2);
		}
	}

	/**
	 * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		return null;
	}

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		return null;
	}

	/**
	 * ��seperator ������ array���������� ����array= [3,8,9], seperator = "-" �򷵻�ֵΪ"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i != array.length - 1) {
				sb.append(seperator);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		/*
		 * int[] origin = { 0, 1, 2, 0, 12 }; new
		 * ArrayUtil().removeZero(origin);
		 */
		/*
		 * int[] array1 = { 3, 5, 7, 8 }; int[] array2 = { 4, 5, 6, 7 }; new
		 * ArrayUtil().merge(array1, array2);
		 */
		/*
		 * int[] array = { 3, 8, 9, 10, 12 }; new ArrayUtil().grow(array, 9);
		 */
		/*
		 * int[] array = { 3, 8, 9, 10, 12 }; String seperator = "-";
		 * System.out.println(new ArrayUtil().join(array, seperator));
		 */
	}

}