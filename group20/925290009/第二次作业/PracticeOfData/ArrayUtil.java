package org.Ralf.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;

public class ArrayUtil {

	public static void reverseArray(int[] origin) {
		/**
		 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果
		 * a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
		 * 
		 * @param origin
		 * @return
		 * 
		 */
		
		for (int i = 0; i < origin.length / 2; i++) {
			int temp = origin[i];
			origin[i] = origin[origin.length - i - 1];
			origin[origin.length - i - 1] = temp;
		}
		
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return int[]
	 */
	public static int[] removeZero(int[] oldArr) {

		if (oldArr == null) {
			return null;
		}
		int[] newArr = new int[oldArr.length];
		int size = 0;

		for (int i = 0; i < oldArr.length; i++) {
			if (oldArr[i] != 0) {
				newArr[size] = oldArr[i];
				size++;
			}
		}
		return Arrays.copyOf(newArr, size);

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

		// method
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < array1.length; i++) {
			if (!arrayList.contains(array1[i])) {
				arrayList.add(array1[i]);
			}
		}
		for (int i = 0; i < array2.length; i++) {
			if (!arrayList.contains(array2[i])) {// 可用list的index找到索引，根据索引判断是否包含该元素
				arrayList.add(array2[i]);
			}
		}
		int[] newArr = new int[arrayList.size()];
		// arrayList.toArray(newArr);
		for (int i = 0; i < arrayList.size(); i++) {
			newArr[i] = arrayList.get(i);
		}
		Arrays.sort(newArr);// 可用冒泡排序，插入排序，快速排序法等实现
		return newArr;
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
		int[] newArray = {};
		if (max == 1) {
			return newArray;
		}
		newArray = new int[2 * max];
		int size = 0;
		int a = 1;
		int b = 1;
		newArray[size++] = a;
		newArray[size++] = b;
		while (b <= max) {
			int temp = b;
			b = a + b;
			newArray[size++] = b;
			a = temp;
		}

		return Arrays.copyOf(newArray, size - 1);
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
		int[] aar = new int[max];
		int size = 0;
		for (int i = 2; i < max; i++) {
			if (isPrime(i)) {
				aar[size++] = i;
			}
		}
		return Arrays.copyOf(aar, size);
	}

	private static boolean isPrime(int aar) {
		boolean flag = true;
		for (int i = 2; i <= Math.sqrt(aar); i++) {
			if (aar % i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		if (max < 1) {
			return null;
		}
		int[] arr = new int[max];
		int size = 0;
		for (int i = 1; i <= max; i++) {
			if (isPerfectNumber(i)) {
				arr[size++] = i;
			}
		}
		return Arrays.copyOf(arr, size);
	}

	private static boolean isPerfectNumber(int num) {
		int sum = 0;
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				sum += i;
			}

		}
		if (sum == num) {
			return true;
		} else
			return false;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		if (array.length < 1) {
			return null;
		}
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < array.length - 1; i++) {
			string.append(array[i]).append(seperator);
		}
		string.append(array[array.length - 1]);
		return string.toString();
	}
}
