package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int i, tmp;
		int len = origin.length;
		
		for (i = 0; i < len/2; i++) {
			tmp = origin[i];
			origin[i] = origin[len - i - 1];
			origin[len - i - 1] = tmp;
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
		int newLen = 0;
		int[] tmpArray = new int[oldArray.length];
		
		for (int item:oldArray) {
			if (item != 0) {
				tmpArray[newLen++] = item;
			}
		}
		
		int[] newArray = new int[newLen];
		System.arraycopy(tmpArray, 0, newArray, 0, newLen);
		
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
		int i = 0, j = 0, newLen = 0;
		int len1 = array1.length, len2 = array2.length;
		int[] tmpArray = new int[len1+len2];
		
		while (i < len1 && j < len2) {
			if (array1[i] < array2[j]) {
				tmpArray[newLen++] = array1[i++];
			}
			else if (array1[i] > array2[j]) {
				tmpArray[newLen++] = array2[j++];
			}
			else {
				tmpArray[newLen++] = array1[i];
				i++;
				j++;
			}
		}
		
		if (i < len1) {
			System.arraycopy(array1, i, tmpArray, newLen, len1 - i);
			newLen += (len1 - i);
		}
		else if (j < len2) {
			System.arraycopy(array2, j, tmpArray, newLen, len2 - j);
			newLen += (len2 - j);
		}
		
		int[] newArray = new int[newLen];
		System.arraycopy(tmpArray, 0, newArray, 0, newLen);
		
		return  newArray;
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
	public int[] fibonacci(int max){
		int realSize = 0;
		int[] array = new int[10];
		int[] resultArray;
		int x0 = 1, x1 = 1;
		int tmp;
		
		if (max <= x0) {
			return new int[0];
		}
		
		for (array[realSize++] = x0; max > x1; tmp = x0 + x1, x0 = x1, x1 = tmp) {
			if (realSize + 1 > array.length) {
				array = grow(array, 5);
			}
			
			array[realSize++] = x1;
		}
		
		if (array.length > realSize) {
			resultArray = new int[realSize];
			System.arraycopy(array, 0, resultArray, 0, realSize);
		}
		else {
			resultArray = array;
		}
		
		return resultArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	private boolean isPrime(int num) {
		for (int i = 2; i <= num/2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public int[] getPrimes(int max){
		int realSize = 0;
		int[] array = new int[10];
		int[] resultArray;
		
		for (int num = 2; num < max; num++) {
			if (isPrime(num)) {
				if (realSize + 1 > array.length) {
					array = grow(array, 5);
				}
				array[realSize++] = num;
			}
		}
		
		if (array.length > realSize) {
			resultArray = new int[realSize];
			System.arraycopy(array, 0, resultArray, 0, realSize);
		}
		else {
			resultArray = array;
		}
		return resultArray;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	private int exponentiation(int base, int expo) {
		int result = 1;
		for (int i = 0; i < expo; i++) {
			result *= base;
		}
		return result;
	}
	/*
	 * a perfect number = 2^(n-1) * (2^n - 1), where (2^n - 1) is a prime
	 */
	public int[] getPerfectNumbers(int max){
		int realSize = 0;
		int[] array = new int[10];
		int[] resultArray;
		int num = 0;
		int x1, x2;
		
		for (int i = 2; true; i++) {
			x2 = exponentiation(2, i) - 1;
			if (x2 >= max) {
				break;
			}
			if (!isPrime(x2)) {
				continue;
			}
			x1 = exponentiation(2, i - 1);
			num = x1 * x2;
			if (num >= max) {
				break;
			}
			
			if (realSize + 1 > array.length) {
				array = grow(array, 5);
			}
			array[realSize++] = num;
		}
		
		if (array.length > realSize) {
			resultArray = new int[realSize];
			System.arraycopy(array, 0, resultArray, 0, realSize);
		}
		else {
			resultArray = array;
		}
		return resultArray;
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
		for (int i = 0; i < array.length - 1; i++) {
			sb.append(array[i]).append(seperator);
		}
		if (array.length > 0)
			sb.append(array[array.length-1]);
		return sb.toString();
	}
	

}
