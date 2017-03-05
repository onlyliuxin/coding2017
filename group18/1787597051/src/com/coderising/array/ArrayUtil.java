package com.coderising.array;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		for (int i = origin.length - 1, j = 0; i >= origin.length / 2; i--, j++) {
			int num = origin[j];
			origin[j] = origin[i];
			origin[i] = num;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组， 生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int count = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				count++;
			}
		}
		int[] newArray = new int[count];
		for (int i = 0, j = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[j] = oldArray[i];
				j++;
			}
		}
		return newArray;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 
	 * 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 *  例如 a1 = [3, 5, 7,8] 
	 *      a2 = [4, 5, 6,7] 
	 *      则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		/*int i = 0, j = 0;
		int flagNum = 0;
		String s = new String();
		int index = array1.length;
		int length = array2.length;
		int[] arr = array2;
		if (array1.length > array2.length) {
			index = array2.length;
			length = array1.length;
			arr = array1;
		}
		while (i < array1.length && j < array2.length) {
			if (array1[i] == array2[j]) {
				flagNum = array1[i];
				s += array1[i] + "/";
				i++;
				j++;
			} else if (array1[i] < array2[j]) {
				flagNum = array1[i];
				s += array1[i] + "/";
				i++;
			} else if (flagNum != array2[j]) {
				flagNum = array2[j];
				s += array2[j] + "/";
				j++;
			} else {
				s += array1[i] + "/";
				break;
			}
			if (i == index) {
				i--;
			}
			if (j == index) {
				j--;
			}
		}

		for (int k = index; k < length; k++) {
			s += arr[k] + "/";
		}*/
		
		//
		String s = new String();
		for (int i = 0; i < array1.length; i++) {
			s += array1[i] + "/";
		}
		for (int i = 0; i < array2.length; i++) {
			if (Arrays.binarySearch(array1, array2[i]) < 0) {
				s += array2[i] + "/";
			}
		}
		
		String[] array = s.split("/");
		int[] newArray = new int[array.length];
		for (int k = 0; k < newArray.length; k++) {
			newArray[k] = Integer.parseInt(array[k]);
		}
		Arrays.sort(newArray);
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
		if (max == 1) {
			return new int[] {};
		}
		int num1 = 1;
		int num2 = 1;
		int sum = 0;
		String s = num1 + " " + num2;
		while (num1 + num2 < max) {
			sum = num1 + num2;
			num1 = num2;
			num2 = sum;
			s += " " + sum;
		}
		String[] array = s.split(" ");
		int[] fibArray = new int[array.length];
		int i = 0;
		for (String ss : array) {
			fibArray[i] = Integer.parseInt(ss);
			i++;
		}
		return fibArray;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max <= 2) {
			return new int[] {};
		}
		int i = 3;
		String s = "2";
		while (i < max) {
			boolean flag = true;
			for (int j = 2; j <= i / 2; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				s += "/" + i;
			}
			i++;
		}
		String[] numStr = s.split("/");
		int[] primesArray = new int[numStr.length];
		for (int j = 0; j < primesArray.length; j++) {
			primesArray[j] = Integer.parseInt(numStr[j]);
		}
		return primesArray;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		String s = new String();
		for (int i = 1; i <= max; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				s += i + "/";
			}
		}
		String[] numStr = s.split("/");
		int[] perfectArray = new int[numStr.length];
		for (int j = 0; j < perfectArray.length; j++) {
			perfectArray[j] = Integer.parseInt(numStr[j]);
		}
		return perfectArray;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String s = "";
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				s += array[i];
				break;
			} 
				s += array[i] + seperator;
		}
		return s;
	}
	
}