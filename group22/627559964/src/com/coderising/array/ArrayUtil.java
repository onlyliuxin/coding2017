package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int size = origin.length;
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			int temp = origin[size-1-i];
			result[i] = temp;
		}
		return result;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int size = oldArray.length;
		int countZero = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				countZero ++;
			}
		}
		int[] result = new int[size - countZero];
		for (int i = 0, j = 0; i < size; i++) {
			int temp = oldArray[i];
			if (temp != 0) {
				result[j] = temp;
				j ++;
			}
		}
		return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int[] result = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, result, 0, array1.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);
		Arrays.sort(result);
		return  result;
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
	public static int[] grow(int[] oldArray,  int size){
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
		List<Integer> resultList = new ArrayList<Integer>();
		int temp = 0;
		for (int i = 1; i <= max; i++) {
			temp = getFibo(i);
			if (temp > max) {
				break;
			}
			resultList.add(temp);
		}
		return getArrayFromList(resultList);
	}
	/**
	 * 计算斐波那契数列
	 * @param i
	 * @return 
	 */
	private static int getFibo(int i) {
		 if (i == 1 || i == 2)  {
			  return 1;
		  } else {
			  return getFibo(i - 1) + getFibo(i - 2);
		  }
	}
	
	/**
	 * 通过list过得int[]数组
	 * @param list
	 * @return
	 */
	private static int[] getArrayFromList(List<Integer> list) {
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		List<Integer> result = new ArrayList<Integer>();
		boolean isPrime = true;
		for (int i = 2; i < max; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				result.add(i);
			}
			isPrime = true;
		}
		
		return getArrayFromList(result);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= max; i++) {
			int temp = 0;
			for (int j = 1; j < i/2 + 1; j++) {
				if(i%j == 0) {
					temp += j;
				}
			}
			if (temp == i) {
				result.add(i);
			}
		}
		return getArrayFromList(result);
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		List<int[]> result = Arrays.asList(array);
		int size = array.length;
		StringBuffer rs = new StringBuffer();
		for (int i = 0; i < size; i++) {
			rs.append(result.get(0)[i]);
			if (i != size-1) {
				rs.append(seperator);
			}
		}
		return rs.toString();
	}
	

}