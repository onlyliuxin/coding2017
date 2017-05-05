package com.coderising.array;

import java.util.ArrayList;
import java.util.List;

public class MyArrayUtils {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		if (origin == null) {
			return;
		}
		int temp = 0;
		for (int i = 0; i < origin.length/2; i++) {
			temp = origin[i];
			origin[i] = origin[origin.length - 1 - i];
			origin[origin.length - 1 - i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		if (oldArray == null) {
			return null;
		}
		int notZeroLength = oldArray.length;
		for (int element : oldArray) {
			if (element == 0) {
				notZeroLength--;
			}
		}
		if (notZeroLength == 0) {
			return new int[0];
		}
		int[] newArray = new int[notZeroLength];
		for (int i = 0,index = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[index++] = oldArray[i];
			}
		}
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		if (array1 == null && array2 == null) {
			return  null;
		}
		int[] newArray;
		if (array1 == null) {
			newArray = new int[array2.length];
			System.arraycopy(array2, 0, newArray, 0, array2.length);
			return newArray;
		}
		if (array2 == null) {
			newArray = new int[array1.length];
			System.arraycopy(array1, 0, newArray, 0, array1.length);
			return newArray;
		}
		newArray = new int[array1.length + array2.length];
		int i = 0;
		int j = 0;
		int k = 0;
		for (; i < array1.length && j < array2.length;) {
			if (array1[i] < array2[j]) {
				newArray[k++] = array1[i++];				
			} else if (array1[i] == array2[j]) {
				newArray[k++] = array1[i++];
				j++;
			} else {
				newArray[k++] = array2[j++];
			}
		}
		if (i == array1.length) {
			System.arraycopy(array2, j, newArray, k, array2.length - j);
			k += array2.length - j;
		}
		if (j == array2.length) {
			System.arraycopy(array1, i, newArray, k, array1.length - i);
			k += array1.length - i;
		}
		int[] results = new int[k];
		System.arraycopy(newArray, 0, results, 0, k);
		return  results;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int [] oldArray,  int size){
		if (size <= 0 || oldArray == null) {
			return oldArray;
		}
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if (max <= 1) {
			return new int[0];
		}
		
		List<Integer> list = new ArrayList<Integer>();
		int temp = 0;
		for (int i = 0; ; i++) {			
			if ((temp = getfibonacci(i + 1)) < max) {
				list.add(temp);
			} else {
				break;
			}
		}
		
		return List2Array(list);
	}
	
	private static int getfibonacci(int i){
		if (i <= 2) {
			return 1;
		}
		return getfibonacci(i-2) + getfibonacci(i-1);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		if (max <= 2) {
			return new int[0];
		}
		List<Integer> primeList = new ArrayList<Integer>();
		primeList.add(2);
		for (int i = 3; i < max; i += 2) {
			int j;
			for (j = 2; j <= Math.ceil(Math.sqrt(i)); j++) {
				if (i % j == 0) {				
					break;
				}
			}
			if (j == Math.ceil(Math.sqrt(i)) + 1) {
				primeList.add(i);
			}
		}
		
		return List2Array(primeList);
	}
	
	private static int[] List2Array(List<Integer> list) {
		int[] results = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			results[i] = list.get(i);
		}
		return results;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		if (max < 2) {
			return new int[0];
		}
		List<Integer> PerfectList = new ArrayList<Integer>();
		for (int i = 1; i < max; i++) {
			int factorSum = 0;
			int j;
			for ( j= 1; j < i; j++) {
				if (i % j == 0) {
					factorSum += j;
				}
				if (factorSum > i) {
					break;
				}
			}
			if (j == i && factorSum == i) {
				PerfectList.add(i);
			}
		}
		
		return List2Array(PerfectList);
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public static String join(int[] array, String seperator){
		if (array == null || array.length < 1) {
			return "";
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(array[0]);
		for (int i = 1; i < array.length; i++) {
			stringBuilder.append(seperator).append(array[i]);
		}
		return stringBuilder.toString();
	}
}
