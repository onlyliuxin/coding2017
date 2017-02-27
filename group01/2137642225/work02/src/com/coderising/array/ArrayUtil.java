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
		if (origin != null && origin.length > 1) {
			int len = origin.length;
			int temp;
			for(int left = 0,right = len - 1; left < right; left++,right = len - left - 1){
				temp = origin[left];
				origin[left] = origin[right];
				origin[right] = temp;
			}
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
		int[] newArray = null;
		if (oldArray != null && oldArray.length > 0) {
			int[] indexArray = new int[oldArray.length];
			int j = 0;
			for (int i = 0; i < oldArray.length; i++) {
				if(oldArray[i] != 0){
					indexArray[j++] = i;
				}
			}
			newArray = new int[j];
			for (int i = 0; i < j; i++) {
				newArray[i] = oldArray[indexArray[i]];
			}
			indexArray = null;
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
		if(array1 == null || array1.length <= 0){
			return array2;
		}
		if(array2 == null || array2.length <= 0){
			return array1;
		}
		int[] tempArray = new int[array1.length + array2.length];
		int i = 0,j = 0,k = 0;
		for (; i < array1.length && j < array2.length; ) {
			if (array1[i] > array2[j]) {
				tempArray[k++] = array2[j++];
			}
			else if(array1[i] < array2[j]){
				tempArray[k++] = array1[i++];
			}
			else {
				tempArray[k++] = array1[i++];
				j++;
			}
		}
		// 以array1为结束点
		if(array1[array1.length - 1] > array2[array2.length - 1]){
			for (; i < array1.length;) {
				tempArray[k++] = array1[i++];
			}
		} else { // 以array2为结束点
			for (; j < array2.length;) {
				tempArray[k++] = array1[j++];
			}
		}
		int[] mergeArray = new int[k];
		for (int l = 0; l < mergeArray.length; l++) {
			mergeArray[l] = tempArray[l];
		}
		tempArray = null;
		return mergeArray;
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
		if(size <= 0){
			throw new RuntimeException("size大于0");
		}
		int[] newArray = null;
		if(oldArray != null && oldArray.length > 0){
			newArray = new int[oldArray.length + size];
			for (int i = 0; i <  oldArray.length; i++) {
				newArray[i] = oldArray[i];
			}
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
		if(max <= 1){
			return new int[0];
		}
		int[] tempArray = new int[max];
		int i = 0;
		tempArray[i++] = 1;
		tempArray[i] = 1;
		while(tempArray[i] < max){
			i++;
			tempArray[i] = tempArray[i - 1] + tempArray[i - 2];
		}
		int[] array = new int[i];
		for (int j = 0; j < array.length; j++) {
			array[j] = tempArray[j];
		}
		tempArray = null;
		return array;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max <= 2){
			return new int[0];
		}
		int[] tempArray = new int[max];
		int j = 0;
		for (int i = 2; i < max; i++) {
			if(isPrime(i)){
				tempArray[j++] = i;
			}
		}
		int[] array = new int[j];
		for (int i = 0; i < j; i++) {
			array[i] = tempArray[i];
		}
		tempArray = null;
		return array;
	}
	
	private boolean isPrime(int i) {
		for (int j = 2; j < i; j++) {
			if(i % j == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max <= 2){
			return new int[0];
		}
		int[] tempArray = new int[max];
		int j = 0;
		for (int i = 3; i < max; i++) {
			if(isPerfectNumber(i)){
				tempArray[j++] = i;
			}
		}
		int[] array = new int[j];
		for (int i = 0; i < j; i++) {
			array[i] = tempArray[i];
		}
		tempArray = null;
		return array;
	}
	
	private boolean isPerfectNumber(int num) {
		int sum = 1;
		for(int i = 2; i < num; i++){
			if(num % i == 0){
				sum += i;
			}
		}
		if(sum == num){
			return true;
		}
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
	public String join(int[] array, String seperator){
		char[] chars = new char[array.length<<1];
		for (int i = 0,j = 1; i < chars.length; i+=2,j+=2) {
			chars[i] = (char) (array[i>>1] + 48);
			chars[j] = seperator.charAt(0);
		}
		return new String(chars, 0, chars.length - 1);
	}
	

}
