package com.basic;

import java.util.Collections;

public class ArrayUtil
{

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int length = origin.length;
		int left = 0;
		int right = length - 1;
		int a = 0;
		int b = 0;
		while (left < right) {
			swap(origin, left++, right--);
		}
	}

	private void swap(int[] origin, int i, int j) {
		// TODO Auto-generated method stub
		int tmp = origin[i];
		origin[i] = origin[j];
		origin[j] = tmp;
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int length = oldArray.length;
		int[] newArray = new int[length];
		int[] zeroArray = new int[length];

		int zIndex = 0;
		int nzIndex = 0;
		for (int i = 0; i < length; i++) {
			if (oldArray[i] == 0) {
				zeroArray[zIndex++] = oldArray[i];
			} else {
				newArray[nzIndex++] = oldArray[i];
			}
		}

		int[] newArray2 = new int[nzIndex];
		for (int i = 0; i < nzIndex; i++) {
			newArray2[i] = newArray[i];
		}

		return newArray2;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		int[] array3 = new int[length1 + length2];

		int index1 = 0;
		int index2 = 0;
		int index3 = 0;

		while (index1 < length1 && index2 < length2) {

			if (index3 > 0) {
				if (array3[index3 - 1] == array1[index1]) {
					++index1;
					continue;
				}
				if (array3[index3 - 1] == array2[index2]) {
					++index2;
					continue;
				}
			}

			if (array1[index1] == array2[index2]) {
				array3[index3++] = array1[index1];
				++index1;
				++index2;
				continue;
			}
			if (array1[index1] < array2[index2]) {
				array3[index3++] = array1[index1];
				++index1;
				continue;
			}
			if (array1[index1] > array2[index2]) {
				array3[index3++] = array2[index2];
				++index2;
				continue;
			}
		}

		while (index1 < length1) {
			array3[index3++] = array1[index1++];
		}
		while (index2 < length2) {
			array3[index3++] = array1[index2++];
		}

		int[] newArray = new int[index3];
		for (int i = 0; i < index3; i++) {
			newArray[i] = array3[i];
		}

		return newArray;
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int[] oldArray, int size) {
		int length = oldArray.length;
		int[] newArr = new int[length + size];
		for (int i = 0; i < length; i++) {
			newArr[i] = oldArray[i];
		}
		for (int i = length; i < length + size; i++) {
			newArr[i] = 0;
		}
		return newArr;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if (max == 1) {
			return null;
		}
		int[] arr = new int[max / 2];
		int a = 1;
		int b = 1;
		int c = 0;

		arr[0] = 1;
		arr[1] = 1;

		int index = 2;
		while (a + b < max) {
			arr[index++] = a + b;
			c = b;
			b = a + b;
			a = c;
		}

		int[] newArr = new int[index];
		for (int i = 0; i < index; i++) {
			newArr[i] = arr[i];
		}

		return newArr;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int size = max / 2 + 1;
		int[] arr = new int[size];
		int index = 0;
		for (int i = 2; i < max; i++) {
			if (isPrime(i)) {
				arr[index++] = i;
			}
		}

		int[] newArr = new int[index];
		for (int i = 0; i < index; i++) {
			newArr[i] = arr[i];
		}

		return newArr;
	}

	public boolean isPrime(int i) {
		for (int j = 2; j < i / 2; j++) {
			if (i % j == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int size = max / 2 + 1;
		int[] arr = new int[size];
		int index = 0;
		for (int i = 2; i < max; i++) {
			if (isPerfectNumber(i)) {
				arr[index++] = i;
			}
		}

		int[] newArr = new int[index];
		for (int i = 0; i < index; i++) {
			newArr[i] = arr[i];
		}

		return newArr;
	}

	public boolean isPerfectNumber(int num) {
		int sum = 0;
		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0) {
				sum += i;
			}
		}
		// System.out.println("num: " + num + ", sum:" + sum);
		return (num == sum);
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String str = "";
		int length = array.length;
		for (int a : array) {
			str += a + seperator;
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

}
