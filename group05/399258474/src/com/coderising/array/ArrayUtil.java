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
		int[] arr = new int[origin.length];
		for (int i = 0; i < origin.length; i++) {
			arr[i] = origin[origin.length-i-1];
		}
		for (int i = 0; i < arr.length; i++) {
			origin[i] = arr[i];
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
		int size = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] != 0){
				size ++;
			}
		}
		int[] newArray = new int[size];
		for (int i = 0,j = 0; i < oldArray.length; i++) {
			if(oldArray[i] != 0){
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
	
	public int[] merge(int[] array1, int[] array2){
		int[] totalArr = new int[array1.length + array2.length];
		for (int i = 0; i < array1.length; i++) {
			totalArr[i] = array1[i];
		}
		for (int j = array1.length,i = 0; i < array2.length; i++) {
			totalArr[j] = array2[i];
			j++;
		}
		//排序
		for (int i = 0; i < totalArr.length; i++) {
			for (int j = 0; j < totalArr.length-i-1; j++) {
				if(totalArr[j] > totalArr[j+1]){
					int temp = totalArr[j+1];
					
					totalArr[j+1] = totalArr[j];
					totalArr[j] = temp;
				}
			}
		}
		//去重
		if(totalArr.length < 2){
			return totalArr;
		}
		int size = 1;
		for (int i = 0; i < totalArr.length-1; i++) {
			if(totalArr[i] != totalArr[i+1]){
				size ++ ;
			}
		}		
		int[] newArr = new int[size];
		for (int i = 0,j = 0; i < totalArr.length-1; i++) {
			if(totalArr[i] != totalArr[i+1]){
				newArr[j] = totalArr[i];
				j++;
			}
		}		
		newArr[size-1] = totalArr[totalArr.length-1];
		
		return  newArr;
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
	public int[] fibonacci(int max){
		int size = 0;
		while(f(size) < max){
			size++;
		}
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = f(i);
		}
		return arr;
	}
	
	public int f(int n){
		if(n == 0 || n ==1){
			return 1;
		}else{
			return f(n-1)+f(n-2);
		}
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max <= 2){
			return null;
		}
		int size = 1;
 		for (int i = 3; i < max; i++) {
			int n = 2;
			while(n < i){
				if(i%n == 0){
					break;
				}
				n++;
			}
			if(n == i){
				size ++;
			}
		}
 		int[] arr = new int[size];
 		arr[0] = 2;
 		for (int i = 3,j = 1; i < max; i++) {
			int n = 2;
			while(n < i){
				if(i%n == 0){
					break;
				}
				n++;
			}
			if(n == i){
				arr[j] = i;
				j ++;
			}
		}
		return arr;
	}
	
	
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max <= 5){
			return null;
		}
		int size = 0;
		for (int i = 6; i < max; i++) {
			int sum = 0;
			int n = 1;
			while(n < i){
				if(i % n == 0){
					sum += n;
				}
				n++;
			}
			if(sum == i){
				size ++;
			}
		}
		int[] arr = new int[size];
		for (int i = 6,j = 0; i < max; i++) {
			int sum = 0;
			int n = 1;
			while(n < i){
				if(i % n == 0){
					sum += n;
				}
				n++;
			}
			if(sum == i){
				arr[j] = i;
				j ++;
			}
		}
		return arr;
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
		String str = "";
		for (int i = 0; i < array.length; i++) {
			if(i != array.length-1){
				str += (array[i] + seperator);
			}else{
				str += array[i];
			}
		}
		return str;
	}
	

}
