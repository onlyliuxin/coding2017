package com.sprint.array;

public class ArrayUtil {
	
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

		int length = origin.length; 
		int[] temp = new int[length];
		for (int i = 0; i < length; i++)
			temp[i] = origin[i];
		for (int i = length - 1, j = 0; i >= 0 && j < length; i--, j++)
			origin[j] = temp[i];
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
			return new int[0];
		}

		int zeroCount = 0;	
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0)
				zeroCount++;
		}
		int[] newArray = new int[oldArray.length-zeroCount];
		for (int i = 0, j = 0; i < oldArray.length && j < newArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[j] = oldArray[i];
				j++;
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
		if (array1 == null && array2 == null)
			return new int[0];
		int index1 = 0, index2 = 0;
		int[] array3 = new int[array1.length + array2.length];
		int index = 0;
		while (index1 != array1.length && index2 != array2.length) {
			if (array1[index1] < array2[index2]) {
				array3[index++] = array1[index1++];
			} else if (array1[index1] > array2[index2]) {
				array3[index++] = array2[index2++];
			} else if (array1[index1] == array2[index2]){
				array3[index++] = array1[index1++];
				index2++;
			}
		}

		if (index1 == array1.length && index2 != array2.length) {
			for (int i = index2; i < array2.length; i++)
				array3[index++] = array2[i];
		} else if (index2 == array2.length && index1 != array1.length) {
			for (int i = index1; i < array1.length; i++) {
				array3[index++] = array1[i];
			}
		}
		
		int[] newArray = new int[index];
		for (int i = 0; i < newArray.length; i++)
			newArray[i] = array3[i];
		return  newArray;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 *
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int [] oldArray,  int size){
		if (size <= 0)
			return new int[0];
		int[] newArray = new int[oldArray.length + size]; 	
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}			
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
		if (max < 1)
			return new int[0];
		if (max == 1)
			return new int[0];
		int[] array = new int[max];
		int i = 0;
		int value = fibonaccis(i+1);
		while ( value < max) {
			array[i++] = value;
			value = fibonaccis(i+1);
		}
		int[] newArray = new int[i];
		for (int j = 0; j < newArray.length; j++) {
			newArray[j] = array[j];
		}
		return newArray;
	}

	private static int fibonaccis(int n) {
		if (n <=0)
			return 0;
		if (n == 1 || n ==2 ) 
			return 1;
		return fibonaccis(n-1)+fibonaccis(n-2);
	} 
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		if (max <= 1) {
			return new int[0];
		}
		int[] array = new int[max];
		int index = 0;
		for (int i = 2; i < max; i++) {
			if (i == 2 || i == 3 || i == 5 || i == 7)
				array[index++] = i;
			if (i%2 !=0 && i%3 != 0 && i%5 != 0 && i%7 != 0)
				array[index++] = i;
		}
		int[] newArray = new int[index];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i];
		}

		return newArray;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		if (max <= 0) 
			return new int[0];
		int[] array = new int[max];
		int index = 0;
		for (int i = 1; i < max; i++) {
			if (isPerfectNumber(i))
				array[index++] = i;
		}

		int[] newArray = new int[index];
		for (int i = 0; i < newArray.length; i++)
			newArray[i] = array[i];

		return newArray;
	}
	
	private static boolean isPerfectNumber(int n) {
		int sum = 0;
		int i = 1;
		while (i < n) {
			if (n%i == 0)
				sum += i;
			i++;
		}
		if (sum == n)
			return true;
		return false;
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
		if (array == null)
			return null;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i == array.length-1)
				str.append(array[i]);
			else 
				str.append(array[i] + seperator);
		}
		return str.toString();
	}
	

}
