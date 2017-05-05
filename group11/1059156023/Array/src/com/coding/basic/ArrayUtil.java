package com.coding.basic;

import java.util.Arrays;
import java.util.HashSet;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
        for (int i = 0; i < origin.length/2; i++) {//前后交换元素
            int temp = origin[i];
            origin[i] = origin[origin.length-i-1];
            origin[origin.length-i-1]= temp;
        }
        System.out.println(Arrays.toString(origin));//输出数组
		
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray){
		int[] arr = new int[oldArray.length];
		int count = 0;
		for(int i=0;i<oldArray.length;i++){
			if (oldArray[i]!=0) {
				arr[count] = oldArray[i];
				count++;
			}
		}
		
		int[] newArr = new int[count];
		System.arraycopy(arr, 0, newArr, 0, count);
		
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		HashSet set1 = new HashSet(Arrays.asList(array1));
		HashSet set2 = new HashSet(Arrays.asList(array2));
		set1.addAll(set2);
		/*
		HashSet<Integer> set1 = new HashSet(Arrays.asList(array1));//以array1建立集合
		HashSet<Integer> set2 = new HashSet(Arrays.asList(array2));
		set1.addAll(set2);//求并集
		Integer[] arr = set1.toArray(new Integer[set1.size()]);//获取并集后的数组
		Arrays.sort(arr);//数组排序
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		*/

		
		
		return  null;
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
		int[] newArr = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, newArr, 0, oldArray.length);
		
		return newArr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		
		return null;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		return null;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
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
		String aString = "";
		String bString = "";
		for(int a:array){
			aString = a+seperator;
			bString = bString+aString;
		}
		return bString;
	}
	
	public static void main(String [] args){
		ArrayUtil arrayUtil = new ArrayUtil();
		int[] a = {7,9,30,3,0,6,0};
		arrayUtil.reverseArray(a);
		
		System.out.println(arrayUtil.join(a, "-"));
		
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		System.out.println(Arrays.toString(arrayUtil.removeZero(oldArr)));
		
		System.out.println(Arrays.toString(arrayUtil.grow(a, 4)));
		

	}
	

}
