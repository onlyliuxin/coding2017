package com.github.PingPi357.coding2017.homework2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin) {
		int len = origin.length;
		int[] temp = new int[len];
		for (int i = 0; i < len; i++) {
			temp[i] = origin[--len];
		}
		return temp;
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int[] newArray = new int[oldArray.length];
		int i = 0;
		for (int element : oldArray) {
			if (element != 0) {
				newArray[i++] = element;
			}
		}
		return newArray;
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
		ArrayList<Integer> list = new ArrayList<Integer>();
		int k1=0;
		int k2 = 0;
		for (int i = 0; i < array1.length; i++) {
				for (int j = k2; j < array2.length; j++) {
					if (array1[i] < array2[j]) {
						list.add(array1[i]);
						k1++;
						break;
					} else if(array1[i] > array2[j]) {
						list.add(array2[j]);
						k2++;
					}else{
						list.add(array1[i]);
						k1++;
						k2++;
						break;
						}
				}
				if(k2==array2.length-1){
					for (; k1 <= array1.length-1 ; k1++) {
						list.add(array1[k1]);
					}
					break;
				}
				if(k1== array1.length-1){
					for (; k2 <= array2.length-1 ; k2++) {
						list.add(array2[k2]);
					}
					break;
				}
		}
			

		int[] mergeArray = arrayListToArray(list);
		return mergeArray;
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
		int[] growArray = Arrays.copyOf(oldArray, oldArray.length + size);
		return growArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		int a = 1;
		int b = 1;
		int fibMaxNum = 1;
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(1);
		if (max <= 1) {
			return new int[0];
		}
		while (max > fibMaxNum) {
			list.add(fibMaxNum);
			fibMaxNum = a + b;
			b = fibMaxNum;
			a = b;
		}
		return arrayListToArray(list);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		ArrayList<Integer> list =new ArrayList<Integer>();
		for(int i=2; i <=max-1;i++){
			boolean flag=true;
			for(int j=2; j < Math.floor(i/2); j++){
				if(i%j==0){
					flag=false;
					break;
				}
			}
			if(!flag){
				list.add(i);
			}
		}
		return arrayListToArray(list);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		ArrayList<Integer> list =new ArrayList<Integer>();
		int sum=0;
		if(max<=1){
			return null;
		}
		for(int i=1;i <=(max-1);i++){
			for(int j=1; j<=i/2;j++){
				if(i%j==0){
					sum+=j;
				}
			}
			if(sum==i){
				list.add(i);
			}
		}
		return arrayListToArray(list);
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
		 sb.append(array[0]);
		for(int i=1;i<array.length;i++){
			sb.append(seperator);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	private int[] arrayListToArray(ArrayList<?> list) {
		int[] Array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Array[i] = (int) list.get(i);
		}
		return Array;
	}
}
