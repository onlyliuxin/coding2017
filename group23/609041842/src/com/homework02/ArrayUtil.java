package com.homework02;

import java.util.ArrayList;
import java.util.Arrays;

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
		int[] reverse = new int[origin.length];
		for (int i = origin.length - 1; i >= 0; i--) {
			reverse[reverse.length - i - 1] = origin[i];
		}
		System.out.println("reverseArray-" + Arrays.toString(reverse));

	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
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
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				for (int j = 0; j < newArray.length; j++) {
					if (newArray[j] == 0) {
						newArray[j] = oldArray[i];
						break;
					}
				}
			}
		}
		System.out.println("removeZero-" + Arrays.toString(newArray));
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
		ArrayUtil atl = new ArrayUtil();
		int[] newArray = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, newArray, 0, array1.length);
		System.arraycopy(array2, 0, newArray, array1.length, array2.length);
		newArray = atl.sort(newArray);
		System.out.println(Arrays.toString(newArray));
		for (int i = 0; i < newArray.length; i++) {
			for (int j = 0; j < newArray.length; j++) {
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
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		System.out.println("grow:" + Arrays.toString(newArray));
		return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public ArrayList fibonacci(int max) {
		int[] array = new int[max];
		int count = 1;
		int a = 1;
		int b = 1;
		int c = 1;
		ArrayList arrayList = new ArrayList();
		if (max <= 1) {
			System.out.println("-" + arrayList);
			return arrayList;
		} else {
			arrayList.add(a);
			arrayList.add(b);
			while (c < max) {
				c = a + b;
				a = b;
				if (c + b > max) {
					break;
				}
				arrayList.add(c);
				b = c;
			}
			System.out.println("-" + arrayList);
		}
		return arrayList;
	}

	@Test
	public void tests() {
		ArrayUtil s = new ArrayUtil();
		s.fibonacci(1);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		return null;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		return null;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String newString = "";
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1)
				seperator = "";
			newString = newString + array[i] + seperator;
		}
		System.out.println("join:" + newString);
		return null;
	}

	public int[] sort(int[] newArray) {
		int count = 0;
		for (int i = 0; i < newArray.length; i++) {
			for (int j = i; j < newArray.length; j++) {
				if (newArray[i] > newArray[j]) {
					int mid = newArray[i];
					newArray[i] = newArray[j];
					newArray[j] = mid;
				}
			}
		}
		System.out.println(count);
		return newArray;
	}

	@Test
	public void testArray() {
		// test reverseArray
		ArrayUtil atl = new ArrayUtil();
		int[] origin = { 4, 0, 7, 0, 2, 9, 8 };
		System.out.println("测试数据:" + Arrays.toString(origin));
		atl.reverseArray(origin);
		// remove zero
		atl.removeZero(origin);
		// join
		atl.join(origin, "-");
		// grow
		atl.grow(origin, 4);
		// merge
		int[] array1 = { 3, 5, 7, 8 };
		int[] array2 = { 1, 7, 4, 8, 2 };
		atl.merge(array1, array2);
	}

}
