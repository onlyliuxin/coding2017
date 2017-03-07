package com.coderising.array;

import java.util.ArrayList;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = {7, 9 , 30, 3} , 置换后为 {3, 30, 9,7} 如果 a =
	 * {7, 9, 30, 3, 4} , 置换后为 {4,3, 30 , 9,7}
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int[] temp = new int[origin.length];
		int index = 0;
		for (int i = origin.length - 1; i >= 0; i--) {
			temp[index++] = origin[i];
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		int countZero = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] == 0){
				countZero++;
			}
		}
		
		int[] temp = new int[oldArray.length - countZero];
		int index = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				temp[index++] = oldArray[i];
			}
		}
		return temp;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * {3, 5, 7,8} a2 = {4, 5, 6,7} 则 a3 为{3,4,5,6,7,8} , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public Integer[] merge(int[] array1, int[] array2) {
        Integer[] temp = new Integer[array1.length + array2.length];
        int i = 0,j = 0;
        int index = 0;
        
        while(i < array1.length && j < array2.length){
        	if(array1[i] <= array2[j]){
        		temp[index++] = array1[i++];
        	}else{
        		temp[index++] = array2[j++];
        	}
        }
        
        while(i < array1.length){
        	temp[index++] = array1[i++];
        }
        while(j < array2.length){
        	temp[index++] = array2[j++];
        }
		return removeRepetition(temp);
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = {2,3,6} , size = 3,则返回的新数组为
	 * {2,3,6,0,0,0}
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int[] oldArray, int size) {
		int[] temp = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, temp, 0, oldArray.length);
		return temp;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		int[] temp = new int[max];
		if(max == 1){
			return null;
		}
		int index = 0;
		for(int i = 0;i<max;i++){
			if(fibonacciNum(i) < max){
				temp[index++] = fibonacciNum(i);
			}
		}
		return removeZero(temp);
	}

	public static int fibonacciNum(int n) {
		if (n <= 1) {
			return 1;
		}
		return fibonacciNum(n-1) + fibonacciNum(n-2);
	}
	

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为{2,3,5,7,11,13,17,19}
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		boolean isPrime = false;
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i=2; i < max; i++){
		   isPrime = true;
		   for (int j = 2; j < i; j++){
		      if ((i % j) == 0) {
		         isPrime = false;
		         break;
		      }
		   }
		   if(isPrime){
			   temp.add(i);
		   }
		}
		int[] array = new int[temp.size()];
		int index = 0;
		for(Integer te : temp){
			array[index++] = te;
		}
		return array;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static Integer[] getPerfectNumbers(int max) {
		Integer[] temp = new Integer[max];
		int index = 0;
		for (int i = 2; i < max; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
				if (sum == i){
					temp[index++] = i;
				}
			}
		}
		return removeRepetition(temp);
	}
	
	/**
	 * 用seperator 把数组 array给连接起来 例如array= {3,8,9}, seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String temp = "";
		for(int i = 0;i < array.length -1; i++){
			temp += array[i] + seperator;
		}
		temp += array[array.length-1];
		return temp;
	}

	public static Integer[] removeRepetition(Integer[] oldArray){
		ArrayList<Integer> temp = new ArrayList<>();  
		for(int i=0;i<oldArray.length;i++){  
		    for(int j=i+1;j<oldArray.length;j++){  
		        if(oldArray[i] == oldArray[j]){  
		            j = ++i;  
		        }  
		    }  
		    temp.add(oldArray[i]);  
		}  
		Integer[] result = (Integer[]) temp.toArray(new Integer[temp.size()]);  
		return result;
	}
	
}
