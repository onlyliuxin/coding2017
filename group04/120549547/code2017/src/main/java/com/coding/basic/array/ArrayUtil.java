package com.coding.basic.array;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){

		if (origin == null || origin.length == 0){
			return;
		}

		int temp ;
		for (int i = 0; i < (origin.length / 2); i++) {
			temp = origin[i];
			origin[i] = origin[(origin.length - 1) - i];
			origin[(origin.length - 1)- i] = temp;
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
		if (oldArray == null ){
			return null;
		}

		if (oldArray.length == 0){
			return oldArray;
		}

		int count = 0;
		int[] temp = new int[oldArray.length];

		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0){
				temp[count++] = oldArray[i];
			}
		}

		int[] result = new int[count];

		System.arraycopy(temp, 0, result, 0, count);
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
		if (array1 == null && array2 == null){
			return null;
		}

		if (array1 == null){
			return array2;
		}

		if (array2 == null){
			return array1;
		}

		int length1 = array1.length;
		int length2 = array2.length;
		int[] temp = new int[length1 + length2];
		int i,j,k;
		for ( i = 0, j = 0, k = 0; i < length1 && j < length2 ;) {

			if (array1[i] < array2[j]){
				temp[k++] = array1[i++];
			}else if (array1[i] > array2[j]){
				temp[k++] = array2[j++];
			}else {
				temp[k++] = array1[i];
				i++;
				j++;
			}
		}

		while (i < length1){
			temp[k++] = array1[i++];
		}

		while (j < length2){
			temp[k++] = array2[j++];
		}
		int[] result = new int[k];
		System.arraycopy(temp, 0, result, 0, k);
		return result;

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

		int oldSize = oldArray.length;
		int[] newArray = new int[oldSize + size];

		System.arraycopy(oldArray, 0, newArray, 0, oldSize);

		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci1(int max){
		if (max == 1){
			return new int[0];
		}

		int[] temp = new int[max];
		int count = 0;
//		for (int i = 1; i < max; i++) {
//			int num = fibonacci(i);
//			if (num < max){
//				temp[count++] = num;
//			}else {
//				break;
//			}
//		}
		temp[0] = 1;
		temp[1] = 2;
		for (int i = 2; i < max; i++) {
			temp[i] = temp[i-1] + temp[i-2];  //直接在数组中实现斐波那契数列
			if (temp[i] >= max){
				break;
			}else {
				count++;
			}
		}



		return Arrays.copyOf(temp, count);
	}

	private static int fibonacci(int n){
		if (n <= 0){
			throw new IllegalArgumentException();
		}

		if (n == 1 || n == 2) {
			return 1;
		}

		return fibonacci(n - 1) + fibonacci(n - 2);

	}
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){

		if (max <= 2){
			return new int[0];
		}

		int[] temp = new int[max];

		int count = 0;
		for (int i = 2; i < max; i++) {
			int j;
			for ( j = 2; j * j < i; j++) {
				if (i % j == 0){
					break;
				}
			}

			if (j * j >= i){
				temp[count++] = i;
			}
		}

		return Arrays.copyOf(temp, count);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		if ( max <= 1){
			return new int[0];
		}

		ArrayList<Integer> array = new ArrayList<>();
		for (int i = 2; i < max; i++) {
			int sum = 0; //存储因子和
			for (int j = 1; j < i; j++) {
				if (i % j == 0){
					sum += j;
				}
			}

			if (sum == i){
				array.add(i);
			}
		}
		int[] result = new int[array.size()];

		for (int i = 0; i < array.size(); i++) {
			result[i] = array.get(i);
		}
		return result;


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

		if (array == null || array.length == 0){
			return null;
		}

		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < array.length-1; i++) {
			 sb.append(array[i]).append(seperator);
		}

		sb.append(array[array.length-1]);

		return sb.toString();
	}
	

}
