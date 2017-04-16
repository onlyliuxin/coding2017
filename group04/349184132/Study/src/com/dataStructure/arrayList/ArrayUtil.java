package com.dataStructure.arrayList;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin) {
		for (int i = 0, j = origin.length - 1; i < origin.length / 2; i++, j--) {
			int temp = origin[i];
			origin[i] = origin[j];
			origin[j] = temp;
		}
		return origin;
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		int count = 0;
		int[] newArray = new int[oldArray.length];
		for (int i = 0, j = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				count++;
				continue;
			} else
				newArray[j++] = oldArray[i];
		}
		int[] temp = new int[newArray.length-count];
		System.arraycopy(newArray, 0, temp, 0, temp.length);
		newArray = temp;
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
	// bug
	public static int[] merge(int[] array1, int[] array2) {
		int len1 = array1.length;
		int len2 = array2.length;
		int[] array3 = new int[len1 + len2];
		int len3 = len1 + len2;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		while (len3 > 0) {
			if (a1 == len1 || a2 == len2) {
				break;
			}
			if (array1[a1] < array2[a2]) {
				array3[a3] = array1[a1];
				a1++;
				a3++;
			} else {
				if (array1[a1] > array2[a2]) {
					array3[a3] = array2[a2];
					a2++;
					a3++;
				} else {
					array3[a3] = array1[a1];
					a1++;
					a2++;
					a3++;
				}
			}
			len3--;
		}
		if (a1 == len1 && a2 == len2) {
			return array3;
		}
		if (a1 == len1) {
			for (int i = a2; i < len2; i++) {
				array3[a3] = array2[a2];
				a3++;
			}
		} else {
			for (int i = a1; i < len1; i++) {
				array3[a3] = array1[a1];
				a3++;
			}
		}
		int[] temp = new int[a3];
		System.arraycopy(array3, 0, temp, 0, temp.length);
		array3 = temp;
		return array3;
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
	public static int[] fibonacci(int max) {
		if (max == 1)
			return new int[0];
		else {
			int[] arr = new int[max];
			arr[0] = 1;
			arr[1] = 1;

			int val = 0;
			int f1 = 1;
			int f2 = 1;

			int index = 2;
			
			while (val < max) {
				val = f1 + f2;
				if(val>max){
					break;
				}
				f1 = f2;
				f2 = val;
				arr[index++] = val;
			}
			int[] temp = new int[index];
			System.arraycopy(arr, 0, temp, 0, temp.length);
			arr = temp;
			return arr;
		}
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		int val = 2; // 数值增加
		int index = 0; // 数组索引
		int[] primes = new int[max]; // 存放素数数组
		boolean flag = true;

		while (val < max) {
			
			for (int i = 2; i <val; i++) { // 判定素数
				if (val % i == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				primes[index++] = val;
			}
			val++;
			flag = true;
		}
		int[] temp = new int[index];
		System.arraycopy(primes, 0, temp, 0, temp.length);
		primes = temp;
		return primes;
	}

	private static void rangeCheck(int max) {
		if (max <= 0)
			throw new IndexOutOfBoundsException(" max ");
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		rangeCheck(max);
		int[] newArray = new int[max];
		int count = 0;
		for (int i = 1; i < max; i++) {
			if (max % i == 0) {
				newArray[count] = i;
				count++;
			}
		}
		int sum = 0;
		for (int i = 0; i < max; i++) {
			sum = sum + newArray[i];
		}
		if (sum == max) {
			int [] temp = new int[count];
			System.arraycopy(newArray, 0, temp, 0, temp.length);
			newArray = temp;
			System.out.println(sum);
			return newArray;

		} else {
			System.out.println("max is not perfectNumber");
			return null;
		}

	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			str.append(array[i]);
			if (i < array.length - 1)
				str.append(seperator);
		}
		return str.toString();
	}
	public static void main(String[] args) {
		int i = 1;
		while(true){
			int[] temp = getPerfectNumbers(i++);
			if(temp!=null){
			System.out.println(Arrays.toString(temp));
			}
		}
	}

}
