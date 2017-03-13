package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin == null) return;
		int tmp;
		for(int i = 0; i< origin.length; i++){
			int end = origin.length - 1 -i;
			if(end>i){
				tmp = origin[i];
				origin[i] = origin[end];
				origin[end] = tmp;
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
		LinkedList<Integer> newArray = new LinkedList<>();
		
		for(int i = 0; i< oldArray.length; i++){
			if(oldArray[i] != 0){
				newArray.add(oldArray[i]);
			}
		}
		int[] ret = new int[newArray.size()];
		for(int i = 0; i<newArray.size(); i++){
			ret[i] = newArray.get(i);
		}
		return ret;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if(array1.length == 0) return array2;
		if(array2.length == 0) return array1;
		int i = 0, j=0, index=0;
		int[] merged = new int[array1.length + array2.length];
		//o(n+m)
		while(i < array1.length && j < array2.length){
			if(array1[i] < array2[j]){
				merged[index] = array1[i];
				i++;
			}else if(array1[i] >= array2[j]){
				merged[index] = array2[j];
				j++;}
//			}else{
//				merged[index] = array1[i];
//				i++;
//				j++;
//			}
			
			index++;
		}
		//o(n)
		while(i<array1.length){
			merged[index] = array1[i];
			i++;
			index++;
		}
		//o(m)
		while(j<array2.length){
			merged[index] = array1[j];
			j++;
			index++;
		}
		//o(n+m)
		int low=0, high = 1; ;
		while(high < merged.length){
			if(merged[high] != merged[low]){
				low++;
				merged[low] = merged[high];
			}
			high ++;
		}

		return  Arrays.copyOf(merged, low+1);
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
		if(max <= 1) return new int[0];
		int[] memo = new int[max+2];
		int n = 1;
		while(fib(n, memo)<max){
			n++;
		}
		//System.out.println(Arrays.toString(memo));
		int[] ret = new int[n-1];
		System.arraycopy(memo, 1, ret, 0, n-1);
		//System.out.println(Arrays.toString(ret));
		return ret;
	}
	
	private int fib(int n, int[] memo){
		if(n <= 0) return 0;
		if(n == 1) {
			if(memo[1] == 0){
				memo[1] = 1;
			}
			return 1;
		}
		else{
			if(memo[n] == 0){
				memo[n] = fib(n-1, memo) + fib(n-2, memo);
			}
			return memo[n];
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] ret = new int[max];
		int count =0;
		for(int i = 1; i<= max; i++){
			if(isPrime(i)){
				ret[count++] = i;
			}
		}
		
		return Arrays.copyOf(ret, count);
	}
	private boolean isPrime(int n){
		for(int i = 2; i*i <=n; i++){
			if(n%i == 0) return false;
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
		if(max <2) return new int[0];
		List<Integer> store = new LinkedList<>();
		for(int candidate = 2; candidate< max; candidate++ ){
			int sum = 0;
			for(int i = 1; i<candidate; i++){
				if(candidate%i == 0){
					sum+=i;
				}
			}
			if(sum == candidate){
				store.add(sum);
			}
		}
		int[] ret = new int[store.size()];
		int index = 0;
		for(Integer num : store){
			ret[index++] = num.intValue();
		}
		return ret;
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
		if(array.length<=0) return ""; 
		StringBuilder sb = new StringBuilder();
		for(int lo = 0; lo<array.length; lo++){
			sb.append(array[lo]);
			if(lo < array.length -1)
			sb.append(seperator);
		}
		return sb.toString();
	}
	

}
