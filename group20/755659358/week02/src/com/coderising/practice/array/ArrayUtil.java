package com.coderising.practice.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int j = origin.length - 1;
		for (int i = 0; i <= j; i++, j--) {
			int temp = origin[i];
			origin[i] = origin[j];
			origin[j] = temp;
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
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < oldArray.length; i++) {
			list.add(oldArray[i]);
		}
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if (integer.equals(0)) {
				iterator.remove();
			}
		}
		
		return integerListToArray(list);
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
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < array1.length; i++) {
			if (list.contains(Integer.valueOf(array1[i]))) {
				continue;
			}
			list.add(Integer.valueOf(array1[i]));
		}
		for (int i = 0; i < array2.length; i++) {
			if (list.contains(Integer.valueOf(array2[i]))) {
				continue;
			}
			list.add(Integer.valueOf(array2[i]));
		}
		Collections.sort(list);
		return integerListToArray(list);
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

		return Arrays.copyOf(oldArray, oldArray.length + size);
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if (max < 2) {
			return new int[0];
		}
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		int nextFibo = list.get(list.size() - 2) + list.get(list.size() - 1);
		while (nextFibo < max) {
			list.add(nextFibo);
			nextFibo = list.get(list.size() - 2) + list.get(list.size() - 1);
		}

		return integerListToArray(list);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max<2) {
			return new int[]{};
		}
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 2; i < max; i++) {
			if (isPrime(i)) {
				list.add(i);
			}
		}
		return integerListToArray(list);
	}
	
	public int[] integerListToArray(List<Integer> list){
		int len = list.size();
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public boolean isPrime(int a) {
		boolean flag = true;
		for (int i = 2; i <= Math.sqrt(a); i++) {
			if (a % i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		ArrayList<Integer> list=new ArrayList<>();
		for (int i = 2; i < max; i++) {
			int [] splits=numSplit(i);
			if (sumArray(splits)==i) {
				list.add(i);
			}
		}

		return integerListToArray(list);
	}
	
	public int sumArray(int[] arr){
		int sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum+=arr[i];
		}
		return sum;
	}
	
	public int[] numSplit(int x){
		if (x<=1) {
			return new int[]{};
		}
		if (x==2) {
			return new int[]{1,2};
		}
		int k=1;
		ArrayList<Integer> list=new ArrayList<>();
		while (k<=x&&(x/k>=2)) {
			if (x%k==0) {
				list.add(k);
			}
			k++;
		}
		
		
		return integerListToArray(list);
	}
	

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i!=array.length-1) {
				sb.append(seperator);
			}
		}
		return sb.toString();
	}

}
