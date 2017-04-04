package main;

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
		if (null == origin) {
			return;
		}
		for (int temp = 0, i = 0; i < origin.length / 2; i++) {
			temp = origin[i];
			origin[i] = origin[origin.length - i - 1];
			origin[origin.length - i - 1] = temp;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @param newArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		if (null == oldArray) {
			return null;
		}
		int counter = 0;
		int[] newArray = new int[oldArray.length];
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[counter++] = oldArray[i];
			}
		}
		return Arrays.copyOf(newArray, counter);
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5,6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public static int[] merge(int[] array1, int[] array2) {
		if (null == array1) {
			return array2;
		}
		if (null == array2) {
			return array1;
		}
		if (0 == array1.length && 0 == array2.length) {
			return new int[0];
		}
		Arrays.sort(array1);
		Arrays.sort(array2);
		int[] array = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, array, 0, array1.length);
		System.arraycopy(array2, 0, array, array1.length, array2.length);
		int counter = 0;
		for (int i = 0; i < array.length - 1 - counter; i++) {
			for (int j = i + 1; j < array.length - counter; j++) {
				if (array[i] == array[j]) {
					int k = 0;
					for (k = i; k < array.length - 1; k++) {
						array[k] = array[k + 1];
					}
					i--;
					counter++;
					break;
				}
			}
		}
		array = Arrays.copyOf(array, array.length - counter);
		Arrays.sort(array);
		return array;
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
		if (null == oldArray) {
			return null;
		}
		int[] array = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			array[i] = oldArray[i];
		}
		return array;
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
		int[] array = new int[max];
		array[0] = 1;
		array[1] = 1;
		int i = 1;
		do {
			i++;
			array[i] = array[i - 1] + array[i - 2];
		} while (array[i] < max);
		return Arrays.copyOf(array, i);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		if (max < 3) {
			return new int[0];
		}
		int[] array = new int[max];
		int counter = 0;
		for (int i = 2; i < max; i++) {
			int j = 2;
			for (; j * j <= i; j++) {
				if (i % j == 0) {
					break;
				}
			}
			if (j * j > i) {
				array[counter] = i;
				counter++;
			}
		}
		return Arrays.copyOf(array, counter);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		if (max < 7) {
			return new int[0];
		}
		int[] array = new int[2];
		int counter = 0;
		for (int i = 6; i < max; i++) {
			int sum = 0;
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					sum = sum + j;
				}
			}
			if (sum == i) {
				if (counter == array.length) {
					array = ArrayUtil.grow(array, 2);
				}
				array[counter++] = i;
			}
		}
		return Arrays.copyOf(array, counter);
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		if (null == array) {
			return null;
		}
		if (array.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(seperator).append(array[i]);
		}
		return sb.toString().substring(seperator.length());
	}

}
