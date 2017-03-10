package com.coderising.array;

import com.coding.basic.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int mid=origin.length%2==1?origin.length/2:origin.length/2-1;
		for (int i = 0; i <= mid; i++) {
			int vlaue=origin[i];
			origin[i]=origin[origin.length-1-i];
			origin[origin.length-1-i]=vlaue;
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
		ArrayList list=new ArrayList();
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i]!=0) {
				list.add(oldArray[i]);
			}
		}
		int[] array=new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i]=(int) list.get(i);
		}
		
		return array;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int[] array=new int[array1.length+array2.length];
		int ptr1=0;int ptr2=0;int index=0;
		while (ptr1!=array1.length&&ptr2!=array2.length) {
			array[index++]=array1[ptr1]>array2[ptr2]?array2[ptr2++]:array1[ptr1++];
			}
		if (ptr1==array1.length) {
			for (int i = ptr2; i < array2.length; i++)array[index++]=array2[i];		
		}
		else {
			for (int i = ptr1; i < array1.length; i++)array[index++]=array1[i];
		}
		return array;
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
		int [] array=new int[oldArray.length+size];
		for (int i = 0; i < oldArray.length; i++) {
			array[i]=oldArray[i];
		}
		return array;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		
		if (max==1) {
			return null;
		}
		else if (max==2) {
			return new int[]{1,1};
		}
		else {
			ArrayList list=new ArrayList();
			list.add(1);
			list.add(1);
			int next=2;
			while (next<max) {
				list.add(next);
				next=(int)list.get(list.size()-1)+(int)list.get(list.size()-2);
			}
			int[] array=new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				array[i]=(int)list.get(i);
			}
		return array;	
		}
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if (max<=2) {
			return null;
		}
		ArrayList list=new ArrayList();
		list.add(2);
		for (int i = 3; i < max; i++) {
			int j = 2;
			for (; j < Math.sqrt(i); j++) {
				if (i%j==0) {
					break;
				}
			}
			if (i%j!=0) {
				list.add(i);
			}
		}
		int[] array=new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i]=(int) list.get(i);
		}
		return array;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList list=new ArrayList();
		for (int j = 2; j < max; j++) {
			int value=0;
			for (int i = 1; i < j; i++) {
			if (j%i==0) {
				value=value+i;
				}
			}
			if (value==j) {
				list.add(j);
				}
		}
		if (list.size()!=0) {
			int[] array=new int[list.size()];
			for (int i = 0; i < array.length; i++) {
				array[i]=(int)list.get(i);
			}
			return array;
		}	
		return null;
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
		String str=""+array[0];
		for (int i = 1; i < array.length; i++) {
			str=str+seperator+array[i];
		}
		return str;
	}
	

}
