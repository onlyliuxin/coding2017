package com.coding.basic.array;

import java.util.Arrays;

public class ArrayUtil {

	private ArrayUtil() {
	}

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		// length = 4, i~~[0,3]

		int length = origin.length;
		// 取中值
		int mid = length / 2;

		// 交换
		for (int i = 0; i < mid; i++) {
			swap3(origin, i, length - 1 - i);
		}

	}

	/**
	 * 交换数组数据
	 * 
	 * @param origin
	 * @param i
	 * @param j
	 */
	private static void swap(int[] origin, int i, int j) {
		int temp = origin[i];
		origin[i] = origin[j];
		origin[j] = temp;
	}

	/**
	 * 交换数组数据
	 * 
	 * @param origin
	 * @param i
	 * @param j
	 */
	private static void swap2(int[] origin, int i, int j) {
		origin[i] = origin[i] ^ origin[j];
		origin[j] = origin[i] ^ origin[j];
		origin[i] = origin[i] ^ origin[j];
	}

	private static void swap3(int[] origin, int i, int j) {
		origin[i] = origin[i] + origin[j];
		origin[j] = origin[i] - origin[j];
		origin[i] = origin[i] - origin[j];
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
		int zereNum = 0;
		// 记录zero个数
		for (int i = 0; i < oldArray.length; i++) {
			int temp = oldArray[i];
			if (temp == 0) {
				zereNum++;
			} else {
				newArray[i - zereNum] = oldArray[i];
			}
		}
		// decrise
		return decrease(newArray, zereNum);
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
		return null;
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
		int length = oldArray.length;
		int[] newArray = new int[length + size];
		for (int i = 0; i < length + size; i++) {
			if (i < length) {
				newArray[i] = oldArray[i];
			} else {
				newArray[i] = 0;
			}
			
		}
		return newArray;
	}

	/**
	 * 把一个已经存满的数组oldArray的容量进行缩减，缩减后的大小为 oldArray.length - size
	 *  例如 oldArray = [2,3,6] , size =1,则返回的新数组为[2,3]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] decrease(int[] oldArray, int size) {
		
		int length = oldArray.length;
		if (size > length) {
			throw new IndexOutOfBoundsException("size overflow!");
		}
		
		int[] newArray = new int[length - size];
		
		for (int i = 0; i < length - size; i++) {
			newArray[i] = oldArray[i];
		}
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
		
		if (max == 1) {
			return new int[]{};
		}
		
		
		
		return null;
	}

	/**
	 * 计算斐波那契数列第n个数值，n从0开始
	 * @param index
	 * @return
	 */
	private int calFibonacciNum(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("fibonacc index must over zere");
		}
		if (index == 0 || index == 1) {
			return 1;
		}
		return calFibonacciNum(index - 1) + calFibonacciNum(index - 2);
	}
	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		return null;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		return null;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		return null;
	}

	// -------------------------------//

}
