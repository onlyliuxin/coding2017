package com.coderising.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int len = origin.length;
		for (int i = 0; i < len/2; i++) {
			int temp = origin[i];
			origin[i] = origin[len-1-i];
			origin[len-1-i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int slow = 0;
		for (int i = 0; i < oldArray.length; i++) {
			
			if (oldArray[i] != 0) {
				oldArray[slow++] = oldArray[i];
			}
		}
		int[] newArray = new int[slow];
		for (int i = 0; i < slow; i++) {
			newArray[i] = oldArray[i];
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
	
	public int[] merge(int[] array1, int[] array2){
		int[] result = new int[array1.length + array2.length];
		int i1 = 0 , i2 = 0, i3 = 0;
		while (i1 < array1.length && i2 < array2.length) {
			if (array1[i1] == array2[i2]) {
				result[i3++] = array1[i1++];
				i2++;
			} else if (array1[i1] < array2[i2]) {
				result[i3++] = array1[i1++];
			} else {
				result[i3++] = array2[i2++];
			}
		}
		
		while (i1 < array1.length) {
			result[i3++] = array1[i1++];
		}
		while (i2 < array2.length) {
			result[i3++] = array2[i2++];
		}
		
		int[] finalResult = new int[i3];
		for (int i = 0; i < i3; i++) {
			finalResult[i] = result[i];
		}
		
		return finalResult;
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
	public int[] grow(int [] oldArray,  int size){
		int[] result = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			result[i] = oldArray[i];
		}
		
		return result;
	
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		List<Integer> result = new ArrayList<>();
		if (max <= 1) return new int[]{};
		if (max < 2) return new int[]{1,1};
		result.add(1);
		//result.add(1);
		int num = 1, index = 1;
		
		while (num < max) {
			result.add(num);
			num = result.get(index) + result.get(index - 1);
			index++;
			
		}
		
		int[] resArray = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			resArray[i] = result.get(i);
		}
		
		return resArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		List<Integer> result = new ArrayList<>();
		boolean[] notPrime = new boolean[max];
		for (int i = 2; i < max; i++) {
			if (!notPrime[i]) result.add(i);
			for (int j = 2; i*j < max; j++) {
				notPrime[i*j] = true;
			}
		}
		
		int[] resArray = new int[result.size()];
		for (int i = 0; i < result.size();i++) {
			resArray[i] = result.get(i);
		}
		
		return resArray;
	
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i < max; i++) {
			int sum = 0;
			for (int j = 1; j < i/2; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) result.add(i);
		}
		
		int[] resArray = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			resArray[i] = result.get(i);
		}
		return resArray;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		if (array == null || array.length == 0) return null;
		int len = array.length;
		char[] result = new char[len + seperator.length()*(len - 1)];
		int indexA = 0, indexR = 0;
		while (indexA < array.length && indexR < result.length) {
			result[indexR++] = (char)array[indexA++];
			if (indexA < array.length - 1){
				for (int i = 0; i < seperator.length(); i++) {
					result[indexR++] = seperator.charAt(i);
				}
			}
		}
		
		return String.valueOf(result);
		
		
		
	
	}
	

}
