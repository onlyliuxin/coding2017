package com.github.miniyk2012.coding2017.coderising.array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int size = origin.length;
		if (size == 0) return;
		int start = 0, end = size-1;
		while (start < end) {
			int temp = origin[start];
			origin[start++] = origin[end];
			origin[end--] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	public int[] removeZero(int[] oldArray){
		if (oldArray==null) return null;
		List<Integer> list=new ArrayList<>();  
		for (int e : oldArray) {
			if (e != 0) {
				list.add(e);
			}
		}
		return list2Array(list);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if (array1==null || array2==null) return null;
		if (array1.length == 0) return Arrays.copyOf(array2, array2.length);
		List<Integer> list = array2List(array1);
		int currentIndex = 0;
		for (int e : array2) {
			for (int index = currentIndex; index < list.size(); index++ ) {
				currentIndex = index + 1;
				if (list.get(index) == e) break;
				if (list.get(index) > e) {
					list.add(index, e);
					break;
				}
			}
			if (e > list.get(list.size()-1)) list.add(e);
		}
		return list2Array(list);
	}
	
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 * @throws Exception 
	 */
	public int[] grow(int [] oldArray,  int size) throws Exception{
		if (oldArray==null) return null;
		if (size < 0) throw new Exception();
		int oldSize = oldArray.length;
		int[] newArray = new int[size+oldSize];
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
	public int[] fibonacci(int max){
		if (max <= 1) return new int[0]; 
		int i = 1, j = 1;
		List<Integer> list = new ArrayList<>();
		list.add(i);
		list.add(j);
		int next = i+j;
		while (max > next) {
			list.add(next);
			i = j;
			j = next;
			next = i+j;
		}
		return list2Array(list);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		// TODO 使用筛法,写的不好，有待改善
		if (max <= 2) return new int[0];
		List<Integer> list = new ArrayList<>();
		int i;
		for (i=2; i<max; i++)
			list.add(i);
		
		i = 0;
		int currentNum = list.get(i);  // 当前的筛数
		while(currentNum * currentNum < max) {
			int k = 2 * currentNum;
			while (k < max) {
				int index = list.indexOf(k);
				if (index >= 0)
					list.remove(index);
				k += currentNum;
			}
			currentNum = list.get(++i); 
		}
		return list2Array(list);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		List<Integer> list = new ArrayList<>();
		int[] factors;
		for (int i=1; i<max; i++) {
			factors = getFactors(i);
			int sum = IntStream.of(factors).sum();
			if (sum == i) list.add(i);
		}
		return list2Array(list);
	}
	
	private int[] getFactors(int num) {
		List<Integer> list = new ArrayList<>();
		for (int i=1; i < num; i++) {
			if(num % i == 0) list.add(i);
		}
		return list2Array(list);
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
		if (array.length == 0) return "";
		if (array.length == 1) return "" + array[0];
		StringBuilder s = new StringBuilder();
		for (int i=0; i<array.length-1; i++) {
			s = s.append(String.valueOf(array[i])).append(seperator);
		}
		s.append(String.valueOf(array[array.length-1]));
		return s.toString();
	}
	
	/**
	 * 将List<Integer>转换为相同顺序和长度的int[]
	 * @param list
	 * @return
	 */
	private int[] list2Array(List<Integer> list) {
		int size = list.size();
		int[] newArray = new int[size];
		for (int i=0; i<size; i++) {
			newArray[i] = list.get(i);
		}
		return newArray;
	}
	
	/**
	 * 将int[]转换为相同顺序和长度的List<Integer>
	 * @param array
	 * @return
	 */
	private List<Integer> array2List(int[] array) {
		List<Integer> list = new ArrayList<>();
		for (int e : array) {
			list.add(e);
		}
		return list;
	}
	
	public static void main(String []args) throws Exception {
		ArrayUtil arrayUtil = new ArrayUtil();
		
		// merge
		int[] a1 = {1,2,3}, a2 = {-4,-2,2,3,4};
//		int[] a1 = {}, a2 = {};
//		int[] a1 = {1,2,3}, a2 = {};
//		int[] a1 = {}, a2 = {1,2,3};
//		int[] a1 = {4,5,6}, a2 = {1,2,3};
		int[] a3 = arrayUtil.merge(a1, a2);
		System.out.println(Arrays.toString(a3));
		
		// reverse
//		a1 = new int[] {};
//		a1 = new int[] {4,};
//		a1 = new int[] {4,3,5,6,7,7,8};
		a1 = new int[] {4,3,5,6,7,7,8, 9};
		arrayUtil.reverseArray(a1);
		System.out.println(Arrays.toString(a1));
		
		// remove zero
//		a1 = new int[] {};
//		a1 = new int[] {0,0};
		a1 = new int[] {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		a2 = arrayUtil.removeZero(a1);
		System.out.println(Arrays.toString(a2));
		
		// grow
		a1 = new int[] {1,2,3}; 
		a2 = arrayUtil.grow(a1, 4);
//		a2 = arrayUtil.grow(a1, 2);
//		a2 = arrayUtil.grow(a1, 0);
		System.out.println(Arrays.toString(a2));
		
		// fibonacci
		a1 = arrayUtil.fibonacci(15);
//		a1 = arrayUtil.fibonacci(1);
//		a1 = arrayUtil.fibonacci(2);
//		a1 = arrayUtil.fibonacci(-2);
		System.out.println(Arrays.toString(a1));
		
		// prime
		a1 = arrayUtil.getPrimes(2);
//		a1 = arrayUtil.getPrimes(3);
//		a1 = arrayUtil.getPrimes(8);
//		a1 = arrayUtil.getPrimes(12);
//		a1 = arrayUtil.getPrimes(23);
//		a1 = arrayUtil.getPrimes(24);
//		a1 = arrayUtil.getPrimes(50);
		a1 = arrayUtil.getPrimes(100);
		System.out.println(Arrays.toString(a1));
		
		// perfectNumbers
		a1 = arrayUtil.getPerfectNumbers(1000);
		System.out.println(Arrays.toString(a1));
		
		// join
//		a1 = new int[] {};
//		a1 = new int[] {1};
		a1 = new int[] {1,2,3};
		String str = arrayUtil.join(a1, "-");
		System.out.println(str);
		
	}
}
