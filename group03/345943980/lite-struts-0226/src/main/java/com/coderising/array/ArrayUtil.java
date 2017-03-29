package com.coderising.array;

import java.util.Arrays;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int[] target = new int[origin.length];
		for (int i = origin.length - 1, j = 0; i >= 0; i--, j++) {
			target[j] = origin[i];
		}
		System.out.println(Arrays.toString(target));
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
	 * {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int[] newArr = new int[5]; // 数组初始化，默认给10个位置
		int size = 0;

		for (int i = 0; i < oldArray.length; i++) {
			if (size >= newArr.length) { // 大于初始长度，对新数组进行扩容
				newArr = this.grow(newArr, 5);
			}
			if (oldArray[i] != 0) {
				newArr[size] = oldArray[i];
				size++;
			}
		}
		// 对结果数组进行排0处理
		int[] newArrary = new int[size];
		for (int i = 0, j = 0; i < newArr.length; i++) {
			if (newArr[i] != 0) {
				newArrary[j] = newArr[i];
				j++;
			}
		}
		return newArrary;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 = [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3
	 * 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		int[] array3 = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, array3, 0, array1.length);
		System.arraycopy(array2, 0, array3, array1.length, array2.length);
		int temp = 0;
		// 冒泡排序
		for (int i = array3.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array3[j] > array3[j + 1]) {
					temp = array3[j + 1];
					array3[j + 1] = array3[j];
					array3[j] = temp;
				}
			}
		}
		// Arrays.sort(array3);
		// System.out.println(Arrays.toString(array3));
		// 消除重复
		for (int i = array3.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array3[j] == array3[j + 1]) {
					array3[j + 1] = 0;
				}
			}
		}
		// 去掉0值
		return removeZero(array3);
	}

	/**
	 * 方法实现二
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] mergeMethod(int[] array1, int[] array2) {
		int[] array3 = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, array3, 0, array1.length);
		System.arraycopy(array2, 0, array3, array1.length, array2.length);
		int counts = 0; // 找出重复元素个数
		for (int i = 0; i < array3.length - 1; i++) {
			for (int j = i + 1; j < array3.length; j++) {
				if (array3[i] == array3[j]) {
					counts++;
					break;
				}
			}
		}
		// 消除重复
		int[] newArr = new int[array3.length - counts];
		int index = 0;
		for (int i = 0; i < array3.length; i++) {
			boolean flag = false;
			for (int j = 0; j < newArr.length; j++) {
				if (array3[i] == newArr[j]) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				newArr[index++] = array3[i];
			}
		}
		// 排序
		Arrays.sort(newArr);
		return newArr;
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size =
	 * 3,则返回的新数组为 [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int[] oldArray, int size) {
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		int a = 1, b = 1, c = 0, size = 2;
		int[] array = new int[0];
		if (max <= 1)
			return array;
		array = new int[] { a, b };
		while (true) {
			c = a + b;
			a = b;
			b = c;
			if (c > max)
				break;
			// 对数组进行扩容
			array = ensureCapacity(size + 1, array);
			array[size] = c;
			size++;
		}
		return removeZero(array);
	}

	private int[] ensureCapacity(int minCapacity, int[] array) {
		if (minCapacity > array.length) {
			int newCapacity = Math.max(minCapacity, array.length * 2);
			int[] newDataArray = new int[newCapacity];
			System.arraycopy(array, 0, newDataArray, 0, array.length);
			return newDataArray;
		}
		return array;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int size = 0;
		int[] array = new int[0];
		if (max < 2) {
			return array;
		}
		for (int i = 2; i < max; i++) {
			for (int j = 2; j <= i; j++) {
				if (i % j == 0 && i != j) {
					break;
				}
				if (i % j == 0 && i == j) {
					array = this.ensureCapacity(size + 1, array);
					array[size] = i;
					size++;
				}
			}
		}
		return this.removeZero(array);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int size = 0;
		int[] array = new int[0];
		if (max < 1) {
			return array;
		}
		for (int i = 1; i <= max; i++) {
			int sum = 0;
			for (int j = 1; j < i / 2 + 1; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (i == sum) {
				array = this.ensureCapacity(size + 1, array);
				array[size] = i;
				size++;
			}
		}
		return this.removeZero(array);
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuilder sb = new StringBuilder();
		if (array == null || array.length == 0) {
			return null;
		}
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				sb.append(array[i]);
			} else {
				sb.append(array[i]).append(seperator);
			}
		}
		return sb.toString();
	}

}
