package com.github.wdn.coding2017.coderising.array;

import java.util.Arrays;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int length = origin.length;
		for (int i = 0; i < length/2; i++) {
			origin[i]=origin[i]+origin[length-1-i];
			origin[length-1-i] = origin[i]-origin[length-1-i];
			origin[i] = origin[i]-origin[length-1-i];
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
		int[] result = new int[oldArray.length];
		int resultSize=0;
		for (int i = 0; i < oldArray.length; i++) {
			int item = oldArray[i];
			if (item!=0){
				result[resultSize]=item;
				resultSize++;
			}
		}
		if(resultSize<oldArray.length) {
			result = Arrays.copyOfRange(result, 0, resultSize);
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
	
	public int[] merge(int[] array1, int[] array2){
		// 方法一：省事但遍历次数多
		int[] mergeArr0 = Arrays.copyOf(array1, array1.length+array2.length);
		System.arraycopy(array2,0,mergeArr0,array1.length,array2.length);
		Arrays.sort(mergeArr0);
		if(mergeArr0.length<2){
			return mergeArr0;
		}
		int[] merge = new int[mergeArr0.length];
		merge[0]=mergeArr0[0];
		int mergeIndex=0;
		// 排重
		for (int i = 1; i < mergeArr0.length; i++) {
			if(mergeArr0[i]!=merge[mergeIndex]){
				mergeIndex++;
				merge[mergeIndex]=mergeArr0[i];
			}
		}
		return Arrays.copyOfRange(merge,0,mergeIndex+1);
	    /*
	    // 方法二：复杂但遍历次数少
		int[] mergeArr = new int[array1.length+array2.length];
		int array1Index = 0;
		int array2Index = 0;
		int mergeArrIndex=0;
		for(int i=0;i<mergeArr.length;i++){
			if(array1Index>=array1.length && array2Index>=array2.length){
				break;
			}
			int array1Value = array1Index < array1.length ? array1[array1Index] : array2[array2Index];
			int array2Value = array2Index < array2.length ? array2[array2Index] : array1[array1Index];
			if (array1Value > array2Value) {
				mergeArr[i] = array2Value;
				array2Index++;
			} else if (array1Value < array2Value) {
				mergeArr[i] = array1Value;
				array1Index++;
			}else{
				mergeArr[i] = array1Value;
				array1Index++;
				array2Index++;
			}
			mergeArrIndex++;
		}
		return  Arrays.copyOfRange(mergeArr,0,mergeArrIndex);
		*/
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
		if (size<0 || oldArray.length+size>Integer.MAX_VALUE){
			throw new IndexOutOfBoundsException();
		}

		// 方法一：使用jdk自带方法
		//return Arrays.copyOf(oldArray,oldArray.length+size);

		// 方法二：遍历
		int[] growArr = new int[oldArray.length+size];
		for (int i = 0; i < oldArray.length; i++) {
			growArr[i] = oldArray[i];
		}
		return growArr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max<1 || max>Integer.MAX_VALUE){
			throw new IllegalArgumentException();
		}
		int[] initArr = new int[]{1, 1};
		if(max<=2){
			return initArr;
		}
		int aIndex = 0;
		int bIndex = 1;
		int[] fibonacciArr = Arrays.copyOf(initArr,max);
		int index = 2;
		while(fibonacciArr[aIndex]+fibonacciArr[bIndex]<max){
			fibonacciArr[index]=fibonacciArr[aIndex]+fibonacciArr[bIndex];
			index++;
			aIndex++;
			bIndex++;
		}
		return Arrays.copyOf(fibonacciArr,index);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] result = new int[max];
		int index = 0;
		for (int i = 0; i <= max; i++) {
			if (isPrimes(i)){
				result[index] = i;
				index++;
			}
		}
		return Arrays.copyOf(result,index);
	}
	private boolean isPrimes(int value){
		boolean result = true;
		if(value<2) return false;
		if(value==2) return true;
		if(value%2==0) return false;
		for (int i = 3; i < value/2; i++) {
			if(value%i==0){
				result=false;
				break;
			}
		}
		return result;
	}
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if (max<1){
			throw new IllegalArgumentException();
		}
		 // 十万里边就四个，此算法已经算的很慢了
		int[] result = new int[4];
		int index = 0;
		for (int i = 1; i <= max; i++) {
			int sum=0;
			for (int j = 1; j < i; j++) {
				if(i%j==0){
					sum+=j;
				}
			}
			if(sum == i){
				result[index]=i;
				index++;
			}
		}
		return result;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param
	 * @return
	 */
	public String join(int[] array, String seperator){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if(i<array.length-1){
				sb.append(array[i]).append(seperator);
			}else{
				sb.append(array[i]);
			}
		}
		return sb.toString();
	}
}
