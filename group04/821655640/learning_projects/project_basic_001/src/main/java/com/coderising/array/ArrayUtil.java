package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 * 11 01    11|
	 */
	public void reverseArray(int[] origin){
		int len = origin.length-1;
		
		for (int i = 0; i <((0 == len%2) ? len/2 :(len+1)/2) ; i++) {
			origin[i] = origin[i]^origin[len-i];
			origin[len-i] = origin[len-i]^origin[i];
			origin[i] = origin[i]^origin[len-i];
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
		//get count
		int count = 0;
		for (int i : oldArray) {
			if(0 != i) {
				count++;
			}
		}
		
		//remove zero
		int newArray[] = new int[count];
		int j=0;
		for (int i : oldArray) {
			if(0 != i) {
				newArray[j++] = i;
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
	
	public Integer[] merge(int[] array1, int[] array2){
		
		if (null==array1 || null == array2 || 0 == array1.length || 0 == array2.length) {
			new IllegalArgumentException("参数不允许为空数组!");
		}
		
		int i=0,j=0;
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		while (true) {
			if (j == array2.length-1 || i == array1.length-1) {
				break;
			}
			if (array1[i] < array2[j]) {
				mergedList.add(array1[i]);
				i++;
			}else if (array1[i] > array2[j]) {
				mergedList.add(array2[j]);
				j++;
			}else {
				mergedList.add(array2[j]);
				i++;
				j++;
			}
			
		}
		
		//put the least part of one array into list
		if (i == array1.length) {
			for (int k = j; k < array2.length; k++) {
				mergedList.add(array2[k]);
			}
		}else {
			for (int k = i; k < array1.length; k++) {
				mergedList.add(array1[k]);
			}
		}
		
		return  arrayListToInteger(mergedList);
	}
	
	private Integer[] arrayListToInteger(ArrayList<Integer> arrayList) {
		Integer tempArray[] = new Integer[arrayList.size()];
		int i=0;
		for (Integer integer : arrayList) {
			tempArray[i++] = integer;
		}
		
		return tempArray;
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
		
		if (null == oldArray || size<= 0) {
			new IllegalArgumentException("数组为空或者长度非法!!");
		}
		
		int newArray[] = new int[oldArray.length + size];
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
	public Integer[] fibonacci(int max){
		if ( 1 == max ) {
			return new Integer[0];
		}
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(1);
		
		int i=0,j=1;
		while (arrayList.get(i)+arrayList.get(j) < max) {
			arrayList.add(arrayList.get(i)+arrayList.get(j));
			i++;
			j++;
		}
		
		return arrayListToInteger(arrayList);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public Integer[] getPrimes(int max){
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		boolean isPrimes = true;
		for (int i = 2; i < max; i++) {
			isPrimes = true;
			for (int j = 2; j <= Math.sqrt(i) ; j++) {
				if (0 == i % j) {
					isPrimes = false;
					break;
				}
			}
			
			if (isPrimes) {
				arrayList.add(i);
			}
			
		}
		return arrayListToInteger(arrayList);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public Integer[] getPerfectNumbers(int max){
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 2; i < max; i++) {
			sum = 0;
			//the factor is less than half of a number
			for (int j = 1; j <= (i+1)/2; j++) {
				if (0 == i%j) {
					sum += j;
				}
			}
			
			if (sum == i) {
				arrayList.add(i);
			}
		}
		return arrayListToInteger(arrayList);
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
		StringBuilder sb = new StringBuilder();
		for (int i : array) {
			sb.append(i).append(seperator);
		}
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	

}
