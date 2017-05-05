package com.coderising.array;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin) {
		int length = origin.length;
		int[] nArr = new int[length];
		for (int i = 0; i < length; i++) {
			nArr[i] = origin[length - i - 1];
		}
		return nArr;
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		int[] newArray = new int[oldArray.length];
		int size = 0;
		for (int i : oldArray) {
			if (i != 0) {
				newArray[size] = i;
				size++;
			}
		}
		return Arrays.copyOf(newArray, size);
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public static int[] merge(int[] array1, int[] array2) {
		Set<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < array1.length; i++) {
			set.add(array1[i]);
		}
		for (int i = 0; i < array2.length; i++) {
			set.add(array2[i]);
		}
		int[] newArray = new int[set.size()];
		int j = 0;
		for (Integer i : set) {
			newArray[j++] = i;
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
	public static int[] grow(int[] oldArray, int size) {
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max) {
		if (max <= 1) {
			return null;
		}
		int[] arrFib = new int[max / 2 + 2];
		int current = 0;
		int temp = 1;
		int pre = 1;
		int next = 2;
		arrFib[current++] = 1;
		arrFib[current++] = 1;
		do {
			arrFib[current++] = next;
			temp = pre;
			pre = next;
			next = next + temp;
		} while (next <= max);
		return Arrays.copyOf(arrFib, current);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		if (max < 2) {
			return null;
		}
		int[] arrPrimes = new int[max];
		int current = 0;
		arrPrimes[current++] = 2;
		Boolean isPrime = true;
		for (int i = 3; i < max; i++) {
			isPrime = true;
			for (Integer j : arrPrimes) {
				if (j == 0) {
					break;
				}
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime == true) {
				arrPrimes[current++] = i;
			}
		}
		System.out.println(current);
		return Arrays.copyOf(arrPrimes, current);

	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		if (max < 6) {
			return null;
		}
		int[] arrPerNums = new int[max / 6 + 1];
		int current = 0;
		arrPerNums[current++] = 6;
		for (int i = 7; i < max; i++) {
			int sum = 1;
			for (int j = 2; j < i / 2+1; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				arrPerNums[current++] = i;
			}
		}
		return Arrays.copyOf(arrPerNums, current);
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append("-");
			sb.append(array[i]);
		}
		return sb.toString().substring(1);
	}

}
