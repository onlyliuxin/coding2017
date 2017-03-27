package com.danny.hw2;

import java.util.Arrays;

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
		int[] temp = new int[size];

		for(int i=0;i < size; i++){
			temp[i] = origin[size-i-1];
		}
		
		for(int i=0;i<size;i++){
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
		int oldArraySize = oldArray.length;
		int resultSize = 0;
		int[] temp = new int[oldArraySize];

		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] != 0){
				temp[resultSize++] = oldArray[i];
			}
		}
		return temp;
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
		int array1Size = array1.length;
		int array2Size = array2.length;
		int[] result = new int[array1Size+array2Size];
		int resultIndex = 0;
		
		for (int index1 = 0,index2 = 0; index1 < array1Size;) {
			if ( index2 < array2Size ) {
				if ( array1[index1] < array2[index2] ) {
					result[resultIndex++] = array1[index1++];
					break;
				}
				if( array1[index1] == array2[index2] ){
					result[resultIndex++] = array1[index1++];
					index2++;
					break;
				}
				if( array1[index1] > array2[index2] ){
					result[resultIndex++] = array2[index2++];
					break;
				}
			} else{
				result[resultIndex++] = array1[index1++];
			}
		}
		System.out.println(resultIndex);
		return Arrays.copyOf(result, resultIndex);
		
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
		int oldSize = oldArray.length;
		int[] newArray= new int[oldSize+size];
		
		for ( int i = 0; i < newArray.length; i++) {
			if ( i < oldSize ) {
				newArray[i] = oldArray[i];
			} else{
				newArray[i] = 0;
			}
		}
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
		if(max == 1){
			return new int[0];
		}
		
		int maxIndex = 1000;
		int realIndex = 0;
		int[] array=new int[maxIndex];
		for (; realIndex <= array.length; realIndex++) {
			int fibonacciNum = getFibonacciArray(realIndex+1);
			if(fibonacciNum > max){
				break;
			}
			array[realIndex] = fibonacciNum;
		}
		
		
		
		return Arrays.copyOf(array, realIndex);
	}
	
	
	private int getFibonacciArray(int index){
		
		if(index == 0 || index == 1){
			return index;
		}
		return getFibonacciArray(index - 1) + getFibonacciArray(index - 2); 
	}
	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int maxIndex = 1000;
		int realSize = 0;
		int[] array = new int[maxIndex];
		for (int i = 0; i < max; i++) {
			if(isPrimers(i)){
				array[realSize++] = i;
			}
		}
		return Arrays.copyOf(array, realSize);
	}
	private static boolean isPrimers(int n){
		if (n <= 3) {
			return n > 1;
		}

		for(int i=2;i<=Math.sqrt(n);i++){
			if(n%i == 0)
				return false;
		}
		return true;
	}


	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int maxIndex = 1000;
		int realIndex = 0;
		int[] array = new int[maxIndex];
		int sum = 0;
		
		for (int i = 1; i < max /2 +1; i++) {
			if(max % i == 0){
				sum += i;
				array[realIndex++] = i;
			}			
		}
		if(sum == max){
			return Arrays.copyOf(array, realIndex);
		}else{
			return new int[0];
		}
		
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String result="";
		for (int i = 0; i < array.length; i++) {
			result+= String.valueOf(array[i]) + seperator;
		}
		//去掉多余的一个seperator
		return result.substring(0, result.length()-1);
	}
	
	


}
