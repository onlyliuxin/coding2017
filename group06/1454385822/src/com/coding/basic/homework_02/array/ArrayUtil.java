package com.coding.basic.homework_02.array;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a，对该数组的值进行置换
	 * 	例如：a = [7, 9, 30, 3] , 置换后为[3, 30, 9, 7]
	 *  如果 a = [7, 9, 30, 3, 4] , 置换后为[4, 3, 30, 9, 7]  
	 * @param origin
	 */
	public static void reverseArray(int [] origin){
		if(origin.length > 1){ // 数组的长度大于1置换才有意义
			int tem;
			for(int i = 0; i < origin.length / 2; i++){
				tem = origin[i];
				origin[i] = origin[origin.length - 1 - i];
				origin[origin.length - 1 - i] = tem;
			}
		}
	}
	
	/**
	 * 现在有如下的一个数组：int oldArr[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
	 * {1,3,4,5,6,6,5,4,7,6,7,5}
	 * @param oldArray
	 * @return
	 */
	public static int[] removeZero(int[] oldArray){
		int len = 0;
		for(int i : oldArray){
			if(i == 0)
				continue;
			len++;
		}
		int[] result = new int[len];
		len = 0;
		for(int i = 0; i < oldArray.length; i++){
			if(oldArray[i] == 0)
				continue;
			result[len++] = oldArray[i];
		}
		return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组 a1,a2,创建一个新的数组a3,使得a3包含a1和a2的所有元素并集
	 * 例如a1 = [3,5,7,8] , a2 = [4,5,6,7] 则a3为[3,4,5,6,7,8]
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static int [] merge(int[] array1, int [] array2){
		if(array1.length == 0)
			return array2;
		if(array2.length == 0)
			return array1;
		if(array1.length == 0 && array2.length == 0)
			return null;
		int arrLen1 = 0;
		int arrLen2 = 0;
		int arrLen3 = 0;
		int []result = new int[array1.length + array2.length];
		while(arrLen1 < array1.length || arrLen2 < array2.length){
			if(arrLen1 >= array1.length){ //数组1已经没了
				result[arrLen3++] = array2[arrLen2++];
				continue;
			}
			if(arrLen2 >= array2.length){ //数组2已经没了
				result[arrLen3++] = array1[arrLen1++];
				continue;
			}
			if(array1[arrLen1] > array2[arrLen2]){
				result[arrLen3++] = array2[arrLen2++];
			}else if(array1[arrLen1] < array2[arrLen2]){
				result[arrLen3++] = array1[arrLen1++];
			}else{
				result[arrLen3++] = array1[arrLen1++];
				arrLen2++;
			}
		}
		result = Arrays.copyOf(result, arrLen3);
		return result;
	}
	
	/**
	 * 把一个已经存满数据的数组oldArray的容量进行扩容，扩容后的新数据大小为oldArray.length * 2
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如oldArray = {2,3,6}, size = 3,则返回的新数组为{2,3,6,0,0,0}
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int[] oldArray, int size){
		int[] result = new int [size * 2];
		for(int i = 0; i < oldArray.length; i++){
			result[i] = oldArray[i];
		}
		return result;
	}
	
	/**
	 * 斐波那契数列为：1， 1， 2， 3， 5， 8， 13， 21...... ,给定一个最大值，返回小于该值的数列
	 * 例如：max = 15, 则返回的数组应该为{1， 1， 2， 3， 5， 8， 13}
	 * max = 1, 则返回空数组{}
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max == 1)
			return new int[]{};
		int[] result = new int[10];
		int reLen = 2;
		result[0] = result[1] = 1;
		while(result[reLen - 1] + result[reLen -2] < max){
			if(reLen >= result.length)
				result = grow(result, result.length);
			result[reLen] = result[reLen - 1] + result[reLen -2];
			reLen++;
		}
		result = Arrays.copyOf(result, reLen);
		
		return result;
	}
	
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23，返回的数组为{2,3,5,7,11,13,17,19}
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		if(max <= 2){
			return null;
		}
		int[] result = new int[10];
		int len = 0;
		boolean flag = true;
		result[0] = 2;
		if(max == 3)
			return result;
		len++;
		for(int i = 3; i < max; i++){
			flag = true;
			if(len >= result.length)
				result = grow(result, result.length);
			for(int j = 2; j < i; j++){
				if(i % j == 0){
					flag = false;
				}
			}
			if(flag == true){
				result[len++] = i;
			}
				
		}
		result = Arrays.copyOf(result, len);
		return result;
	}
	
	/**
	 * 所谓"完数"，是指这个数恰好等于他的因子之和，例如6 = 1+2+3
	 * 给定一个最大值max，返回一个数组，数组中是小于max的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int sum = 1;
		int [] result = new int[10]; 
		int len = 0;
		for(int i = 2;i < max; i++){
			sum = 1;
			if(len >= result.length)
				result = grow(result, result.length);
			for(int j = 2; j < i; j++){
				if(i % j == 0)
					sum += j;
			}
			if(sum == i)
				result[len++] = sum;	
		}
		result = Arrays.copyOf(result, len);
		return result;
	}
	
	
	/**
	 * 用seperator 把数组array给连接起来
	 * 例如array = [3,8,9] ,seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public static String join(int[]array, String seperator){
		String result = new String();
		int len = 0;
		result = new Integer(array[0]).toString();
		for(int i = 1; i < array.length; i++){
			result += seperator;
			result += array[i];
		}
		return result;
	}

}









