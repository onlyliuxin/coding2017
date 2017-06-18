/**
 * 问题点： 没写注释，代码比较难读。尤其 merge方法。
 */
package com.github.eulerlcs.jmr.algorithm;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		if (origin == null || origin.length < 2) {
			return;
		}

		for (int head = 0, tail = origin.length - 1; head < tail; head++, tail--) {
			origin[head] = origin[head] ^ origin[tail];
			origin[tail] = origin[head] ^ origin[tail];
			origin[head] = origin[head] ^ origin[tail];
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		if (oldArray == null) {
			return new int[0];
		}

		int count = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0)
				count++;
		}

		int[] newArray = new int[count];
		int newIndex = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[newIndex] = oldArray[i];
				newIndex++;
			}
		}

		return newArray;
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
		if (array1 == null || array1.length == 0) {
			if (array2 == null || array2.length == 0) {
				return new int[0];
			} else {
				return Arrays.copyOf(array2, array2.length);
			}
		} else if (array2 == null || array2.length == 0) {
			return Arrays.copyOf(array1, array1.length);
		}

		int[] result = new int[array1.length + array2.length];
		int idxResult = 0;
		int idx1 = 0;
		int idx2 = 0;

		for (; idx1 < array1.length; idx1++) {
			if (array1[idx1] < array2[idx2]) {
				result[idxResult] = array1[idx1];
				idxResult++;
			} else if (array1[idx1] == array2[idx2]) {
				result[idxResult] = array1[idx1];
				idxResult++;
				idx2++;
			} else {
				for (; idx2 < array2.length; idx2++) {
					if (array2[idx2] < array1[idx1]) {
						result[idxResult] = array2[idx2];
						idxResult++;
					} else {
						if (array2[idx2] == array1[idx1]) {
							idx2++;
						}

						break;
					}
				}

				if (idx2 == array2.length) {
					break;
				} else {
					idx1--;
				}
			}
		}

		if (idx1 < array1.length) {
			System.arraycopy(array1, idx1, result, idxResult, array1.length - idx1);
			idxResult += array1.length - idx1;
		}

		if (idx2 < array2.length) {
			System.arraycopy(array2, idx2, result, idxResult, array2.length - idx2);
			idxResult += array2.length - idx2;
		}

		result = removeZero(result);
		return result;
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param increaseCapacity
	 * @return
	 */
	public static int[] grow(int[] oldArray, int increaseCapacity) {
		if (oldArray == null || increaseCapacity < 0) {
			return new int[0];
		}

		int newCapacity = oldArray.length + increaseCapacity;

		int[] newArray = Arrays.copyOf(oldArray, newCapacity);

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
			return new int[0];
		}

		int[] result = new int[10];
		result[0] = 1;
		result[1] = 1;
		int idx = 2;
		int sum = 2;
		while (sum < max) {
			if (idx >= result.length) {
				grow(result, result.length * 2);
			}

			result[idx] = sum;
			sum = result[idx - 1] + result[idx];
			idx++;
		}

		result = removeZero(result);
		return result;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		if (max < 2) {
			return new int[0];
		}

		int[] all = new int[max];
		int index = 0;
		int temp = 0;

		for (int i = 0; i < max; i++) {
			all[i] = i;
		}

		all[0] = 0;
		all[1] = 0;
		index = 2;

		// 筛法
		loops: for (; index < max;) {
			for (int i = 2;; i++) {
				temp = index * i;
				if (temp >= max) {
					break;
				}
				all[temp] = 0;
			}

			for (int i = index + 1; i < max; i++) {
				if (all[i] != 0) {
					index = i;
					continue loops;
				}
			}

			break;
		}

		int[] result = removeZero(all);
		return result;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static long[] getPerfectNumbers(long max) {
		long[] perfect = new long[49];// 到2016年1月为止，共发现了49个完全数
		int idx = 0;
		long sum = 1;
		long sqrt = 0;

		for (long n = 2; n < max; n++) {
			sum = 1;
			sqrt = (long) Math.sqrt(n);
			for (long i = 2; i <= sqrt; i++) {
				if (n % i == 0)
					sum += i + n / i;
			}

			if (sum == n) {
				perfect[idx] = n;
				idx++;
			}
		}

		// return removeZero(perfect);
		return perfect;
	}

	/**
	 * 用separator 把数组 array给连接起来 例如array= [3,8,9], separator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(int[] array, String separator) {
		if (array == null || array.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length - 1; i++) {
			sb.append(array[i] + separator);
		}
		sb.append(String.valueOf(array[array.length - 1]));

		return sb.toString();
	}
}
