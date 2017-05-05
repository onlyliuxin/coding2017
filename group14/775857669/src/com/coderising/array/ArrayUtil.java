package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class ArrayUtil {
	
	private static final String List = null;

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		Stack<Integer> stack = new Stack<>();
		for (int item : origin) {
			stack.push(item);
		}
		for (int index = 0; index < origin.length; index++) {
			origin[index] = stack.pop();
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
		Queue<Integer> queue = new LinkedList<>();
		for (int item : oldArray) {
			if (item != 0) {
				queue.add(item);
			}
		}
		int size = queue.size();
		int[] newArray = new int[size];
		for(int i=0 ; i<size ; i++) {
			newArray[i] = queue.poll();
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
		boolean asc = false;
		if (array1[0] < array1[1]) {
			asc = true;
		}
		HashSet<Integer> hashSet = new HashSet<>();
		for (Integer integer : array1) {
			hashSet.add(integer);
		}
		for (Integer integer : array2) {
			hashSet.add(integer);
		}
		Integer[] array =  hashSet.toArray(new Integer[hashSet.size()]);
		if(asc) {
			Arrays.sort(array);
		} else {
			Arrays.sort(array);
		}
		int[] ret = new int[array.length];
		for(int i=0 ; i<array.length; i++) {
			ret[i] = array[i];
		}
		return ret;
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
		java.util.List<Integer> list = new ArrayList<>();
		int fib = 1;
		if(max <= 1){
			return new int[0];
		}
		int count = 1;
		list.add(1);
		while (fib < max) {
			list.add(fib);
			fib = fib(++count);
		}
		int[] ret = new int[list.size()];
		for (int i = 0 ; i<list.size() ; i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	private int fib(int count) {
		if (count == 1)
			return 1;
		if (count == 2)
			return 1;
		return fib(count-1) + fib(count-2);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		List<Integer> list = new ArrayList<>();
		for(int i=2 ; i<max ; i++) {
			if(isPrime(i)){
				list.add(i);
			}
		}
		return list.stream().mapToInt(i->i).toArray();
	}
	/**
	 * 
	 * @Author: yuhe
	 * @Description: TODO
	 * @param num
	 * @return
	 */
	private boolean isPrime(int num) {
		boolean isPrime = true;
		if(num == 2) {
			isPrime = true;
		}else if(num == 3) {
			isPrime = true;
		}else if (num%2 == 0) {
			isPrime = false;
		} else {
			for(int i=3 ; i<num ; i=i+2) {
				if(num % i == 0) {
					isPrime = false;
					break;
				}
			}
		}
		return isPrime;
	}
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		List<Integer> list = new ArrayList<>();
		for(int i=3 ; i<max; i++) {
			if(isPerfect(i)){
				list.add(i);
			}
		}
		return list.stream().mapToInt(i -> i).toArray();
	}
	
	private boolean isPerfect(int num) {
		boolean isPerfect = false;
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= num/2; i++) {
			if(num%i == 0) {
				list.add(i);
			}
		}
		int sum = 0;
		for (Integer item : list) {
			sum += item;
		}
		if (sum == num){
			isPerfect = true;
		}
		return isPerfect;
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
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<array.length-1 ; i++) {
			sb.append(array[i]).append("-");
		}
		sb.append(array[array.length-1]);
		return sb.toString();
	}
}
