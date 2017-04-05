package com.coding.basic.array;

import java.util.Arrays;

/**
 * 数组工具类-第二次作业
 * @author stackwei
 * @date 2017/3/20
 * @status ok
 */
public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int length;
		int[] temp;

		length = origin.length;
		temp = new int[length];
		for (int i = 0; i < length; i++) {
			temp[length - i - 1] = origin[i];
		}
		for (int i = 0; i < length; i++) {
			origin[i] = temp[i];
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
		int flag = 0;
		int j = 0;
		int length;
		length = oldArray.length;
		int[] newArray;

		for (int i = 0; i < length; i++) {
			if (oldArray[i] != 0) {
				flag++;
			}
		}
		newArray = new int[flag];
		for (int i = 0; i < length; i++) {
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
		int[] temp;
		int[] array3;
		int flag = 0;
		int repeat = 0;
		boolean boolea = true;
		int length1 = array1.length;
		int length2 = array2.length;
		temp = new int[length1 + length2];

		// 先把a1添加到temp
		for (int i = 0; i < length1; i++) {
			temp[i] = array1[i];
		}
		// 把a2中不重复的添加到temp
		for (int i = 0; i < length2; i++) {
			for (int j = 0; j < length1; j++) {
				if (temp[j] == array2[i]) {
					boolea = false;
					repeat++;
				}
			}
			if (boolea) {
				temp[length1 + flag] = array2[i];
				flag++;
			}
			boolea = true;
		}
		// 有重复就new一个数组长度减去重复的长度的a3，排序并返回
		if (repeat != 0) {
			array3 = new int[length1 + length2 - repeat];
			for (int i = 0; i < (temp.length - repeat); i++) {
				array3[i] = temp[i];
			}
			Arrays.sort(array3);
			return array3;
		}
		// 无重复就排序并返回
		Arrays.sort(temp);
		return temp;
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
		int[] temp;
		int length;

		length = oldArray.length;
		temp = new int[length + size];
		for (int i = 0; i < length; i++) {
			temp[i] = oldArray[i];
		}
		oldArray = null;
		oldArray = temp;

		return oldArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		int[] temp = new int[2];
		int[] array;
		int f1 = 1;
		int f2 = 1;
		int length = 2;

		if (max <= 1) {
			return null;
		}

		temp[0] = f1;
		temp[1] = f2;
		if (max == 2) {
			return temp;
		}

		for (int i = 2; temp[i - 1] < max; i++) {
			if ((f1 + f2) >= max)
				break;
			if (i + 1 > temp.length) {
				temp = new ArrayUtil().grow(temp, 1);
			}
			temp[i] = f1 + f2;
			f1 = temp[i - 1];
			f2 = temp[i];
			length++;
		}
		array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = temp[i];
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
		int[] temp = new int[1];
		boolean flag = true;
		int i = 0;

		if (max < 3)
			return null;

		for (int j = 2; j < max; j++) {
			for (int k = 2; k <= Math.sqrt(j); k++) {
				if (j % k == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				if (i + 1 > temp.length)
					temp = new ArrayUtil().grow(temp, 1);
				temp[i] = j;
				i++;
			}
			flag = true;
		}
		return temp;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int[] temp = new int[1];
		int i = 0;

		if (max < 6)
			return null;

		for (int j = 1; j < max; j++) {
			int total = 0;
			for (int k = 1; k < j / 2 + 1; k++) {
				if (j % k == 0)
					total += k;
			}
			if (total == j) {
				if (i + 1 > temp.length)
					temp = new ArrayUtil().grow(temp, 1);
				temp[i] = j;
				i++;
			}
		}
		return temp;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		if(array == null || array.length ==0 || seperator == null)
			return null;
		
		StringBuilder sb = new StringBuilder();
		int length = array.length;
		for(int i=0;i<length;i++) {
			sb.append(array[i]);
			if(i != length-1)
				sb.append(seperator);
		}
		return sb.toString();
	}

}