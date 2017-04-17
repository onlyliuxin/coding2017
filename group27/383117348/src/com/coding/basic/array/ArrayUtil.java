package com.coding.basic.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int[] copy = new int[origin.length];
		for (int x = 0; x < copy.length; x++) {
			copy[x] = origin[x];
		}
		for (int x = 0; x < origin.length; x++) {
			origin[x] = copy[origin.length - 1 - x];
			System.out.println(copy[origin.length - 1 - x]);
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
		List<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < oldArray.length; x++) {
			if (oldArray[x] != 0)
				list.add(oldArray[x]);
		}

		return parseToInt(list);
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
		int[] newArray = new int[array1.length + array2.length];
		// 如果array1为空,array2不为空,则返回array2排序后的数组
		if (array1 == null || array1.length == 0 && array2 != null && array2.length > 0) {
			return sort(array2);
		}
		// 如果array2为空,array1不为空,则返回array1排序后的数组
		if (array1 == null || array1.length == 0 && array2 != null && array2.length > 0) {
			return sort(array1);
		}
		// 如果都不为空,则将两个数组放入一个数组中,先排序后去重
		if (array1 != null && array2 != null & array1.length > 0 && array2.length > 0) {
			// 将array1的数组正序存放
			for (int x = 0; x < array1.length; x++) {
				newArray[x] = array1[x];
			}
			// 将array2的数组倒序存放
			for (int x = 0; x < array2.length; x++) {
				newArray[newArray.length - 1 - x] = array2[x];
			}
			newArray = sort(newArray);
			newArray = getDistinct(newArray);
		}
		return newArray;
	}

	// 数组去重
	private int[] getDistinct(int[] newArray) {
		List<Integer> list = new java.util.ArrayList<Integer>();
		for (int i = 0; i < newArray.length; i++) {
			if (!list.contains(newArray[i])) {// 如果list数组不包括num[i]中的值的话，就返回true。
				list.add(newArray[i]); // 在list数组中加入num[i]的值。已经过滤过。
			}
		}
		int[] result = parseToInt(list);
		return result;
	}

	// 冒泡排序
	private int[] sort(int[] array1) {
		// TODO Auto-generated method stub
		int[] arr = array1;
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int t = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = t;
				}
			}
		return arr;
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
		for (int x = 0; x < oldArray.length; x++) {
			newArray[x] = oldArray[x];
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

	private boolean primeNumber(int number) {
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
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i < max; i++) {
			int sum = 0;
			// 查找因数
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i)
				list.add(i);

		}

		return parseToInt(list);
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
		if (array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				str += array[i] + seperator;
			}
			str = str.substring(0, str.length() - seperator.length());
		}
		return str;
	}
	
	/**
	 * 将集合转化为int数组
	 * 
	 * @param list
	 * @return
	 */
	private int[] parseToInt(List<Integer> list) {
		int[] arr = new int[list.size()];
		for (int x = 0; x < list.size(); x++) {
			arr[x] = list.get(x);
		}
		return arr;
	}

	/*********************************** 单元测试 ***********************************/
	@Test
	public void testReserve() {
		int[] arr = { 7, 9, 30, 3 };
		reverseArray(arr);
		for (int x = 0; x < 4; x++) {
			System.out.println(arr[x]);
		}
	}

	@Test
	public void testZero() {
		int oldArr[] = { 1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5 };
		int[] arr = removeZero(oldArr);
		for (int x = 0; x < arr.length; x++) {
			System.out.print(arr[x] + " ");
		}
	}

	@Test
	public void testMerge() {
		// a1=[3, 5, 7,8] a2 = [4, 5, 6,7]
		int[] a1 = { 3, 5, 7, 8 };
		int[] a2 = { 4, 5, 6, 7 };
		// a3 = [3,4,5,6,7,8]
		int[] a3 = merge(a1, a2);
		for (int x = 0; x < a3.length; x++) {
			System.out.print(a3[x] + " ");
		}
	}

	@Test
	public void testGrow() {
		int[] oldArray = { 2, 3, 6 };
		int[] newArray = grow(oldArray, 3);
		for (int x = 0; x < newArray.length; x++) {
			System.out.println(newArray[x]);
		}
	}

	@Test
	public void testFibo() {
		// 1，1，2，3，5，8，13
		int[] arr = fibonacci(20);
		for (int x = 0; x < arr.length; x++) {
			System.out.print(arr[x] + " ");
		}
	}

	@Test
	public void testPrime() {
		int arr[] = getPrimes(23);
		for (int x = 0; x < arr.length; x++) {
			System.out.print(arr[x]);
			System.out.print(" ");
		}
	}

	@Test
	public void testPerfectNum() {
		int[] arr = getPerfectNumbers(25);
		for (int x = 0; x < arr.length; x++) {
			System.out.println(arr[x]);
		}

	}

	@Test
	public void testJoin() {
		int[] arr = new int[10];
		for (int x = 0; x < arr.length; x++) {
			arr[x] = x;
		}
		String s = join(arr, "--");
		System.out.println(s);
	}

	@Test
	public void testParseToInt() {
		List<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < 10; x++) {
			list.add(x);
		}
		int[] arr = parseToInt(list);
		for (int x = 0; x < arr.length; x++) {
			System.out.println(arr[x]);
		}
	}

}
