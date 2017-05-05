package com.coderising.array;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int l = origin.length, n;
		for (int i = 0; i < l / 2; i++) {
			n = origin[i];
			origin[i] = origin[l - 1 - i];
			origin[l - 1 - i] = n;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int n = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0)
				n++;
		}
		int[] newArray = new int[oldArray.length - n];
		for (int i = 0, j = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[j] = oldArray[i];
				j++;
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

	public int[] merge(int[] array1, int[] array2) {
		int n = 0; // 重复的整数个数
		for (int i = 0, j = 0; i < array1.length && j < array2.length;) {
			int z = array1[i] - array2[j];
			if (z < 0)
				i++;
			else if (z > 0)
				j++;
			else {
				i++;
				j++;
				n++;
			}
		}
		int[] newArray = new int[array1.length + array2.length - n];

		for (int i = 0, j = 0, k = 0; i < array1.length && j < array2.length;) {
			int z = array1[i] - array2[j];
			if (z < 0) {
				newArray[k] = array1[i];
				k++;
				i++;
			} else if (z > 0) {
				newArray[k] = array2[j];
				k++;
				j++;
			} else {
				newArray[k] = array1[i];
				k++;
				i++;
				j++;
			}
			// 判断循环是否即将结束,若结束则将另外一个数组未比较的元素放入新数组
			if (i >= array1.length) {
				for (int a = j; a < array2.length; a++, k++) {
					newArray[k] = array2[a];
				}
			}
			if (j >= array2.length) {
				for (int a = i; a < array1.length; a++, k++) {
					newArray[k] = array1[a];
				}
			}
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
		int[] newArray = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
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
	public int[] fibonacci(int max) {
		int n = 0;
		for (int i = 1, j = 1, k; i < max; n++) {
			k = i + j;
			i = j;
			j = k;
		}
		int[] newArray = new int[n];
		if (n > 1) {
			newArray[0] = 1;
			newArray[1] = 1;
		}
		for (int i = 2; i < n; i++) {
			newArray[i] = newArray[i - 1] + newArray[i - 2];
		}
		return newArray;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int n = 0;
		for (int i = 2; i < max; i++) {
			if (primeNumber(i))
				n++;
		}

		int[] newArray = new int[n];
		for (int i = 2, j = 0; i < max; i++) {
			if (primeNumber(i)) {
				newArray[j++] = i;
			}
		}
		return newArray;
	}

	// 判断是否为素数
	public boolean primeNumber(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
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
		int n = 0;
		for (int i = 6; i < max; i++) {
			if (perfectNumber(i))
				n++;
		}

		int[] newArray = new int[n];
		for (int i = 6, j = 0; i < max; i++) {
			if (perfectNumber(i)) {
				newArray[j++] = i;
			}
		}
		return newArray;
	}

	// 判断是否为完数
	public boolean perfectNumber(int number) {
		int n = 0;
		for (int i = 1; i <= number / 2; i++) {
			if (number % i == 0)
				n += i;
		}
		if (number != n)
			return false;
		return true;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			s.append(String.valueOf(array[i]));
			s.append("-");
		}
		String str = s.substring(0, s.length() - 1);
		return str;
	}

}
