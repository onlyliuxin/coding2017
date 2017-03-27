
package com.coderising.array;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.coding.basic.ArrayList;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int size = origin.length;
		if (size == 0) {
			return;
		}
		int semi = size / 2;
		int temp;
		for (int i = 0; i < semi; i++) {
			temp = origin[i];
			origin[i] = origin[size - 1 - i];
			origin[size - 1 - i] = temp;
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
		ArrayList arrayList = new ArrayList();
		int size = oldArray.length;
		for (int i = 0; i < size; i++) {
			if (oldArray[i] != 0)
				arrayList.add(oldArray[i]);
		}

		return arrayListToArray(arrayList);
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
		ArrayList arraylist = new ArrayList();
		int size1 = array1.length;
		int size2 = array2.length;
		int j = 0;
		for (int i = 0; i < size1; i++) {
			if (j >= size2)
				arraylist.add(array1[i]);
			else {
				for (; j < size2; j++) {
					if (array1[i] < array2[j]) {
						arraylist.add(array1[i]);
						break;
					} else if (array1[i] == array2[j]) {
						arraylist.add(array2[j]);
						j++;
						break;
					} else {
						arraylist.add(array2[j]);
					}
				}
			}
		}
		return arrayListToArray(arraylist);
	}

	private int[] arrayListToArray(ArrayList arraylist) {
		int newSize = arraylist.size();
		int[] newArray = new int[newSize];
		for (int i = 0; i < newSize; i++)
			newArray[i] = Integer.parseInt(arraylist.get(i).toString());
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
		int newsize = oldArray.length + size;
		int[] newArray = new int[newsize];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
		int array[] = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(1);
		arraylist.add(1);
		if (max == 1)
			return null;
		int temp = 1;
		for (int i = 1; (temp = Integer.parseInt(arraylist.get(i).toString())
				+ Integer.parseInt(arraylist.get(i - 1).toString())) <= max; i++) {

			arraylist.add(temp);
		}

		return arrayListToArray(arraylist);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		ArrayList al = new ArrayList();
		if (max == 1) {
			return null;
		} else if (max == 2) {
			al.add(2);
		} else {
			for (int i = 2; i < max; i++) {
				for (int j = 2; j <= Math.sqrt(max); j++) {
					if (i % j == 0)
						break;
				}
				al.add(i);
			}
		}
		return arrayListToArray(al);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		ArrayList al = new ArrayList();
		int num = 0;
		for (int i = 1; i < max; i++) {
			num = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0)
					num += j;
			}
			if (num == i)
				al.add(i);
		}
		return arrayListToArray(al);
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
		int lenth = array.length;
		for (int i = 0; i < lenth; i++) {
			if (i == 0)
				s += i;
			else {
				s += seperator + i;
			}
		}
		return s;
	}

}
