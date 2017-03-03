package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		int temp;
		for(int i = 0;i < origin.length;i++){
			if(i >= origin.length-1-i){
				break;
			}
			temp = origin[i];
			origin[i] = origin[origin.length-1-i];
			origin[origin.length-1-i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	public static int[] removeZero(int[] oldArray){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i < oldArray.length;i++){
			if(oldArray[i] != 0){
				list.add(oldArray[i]);
			}
		}
		int[] result = listToArray(list);
		return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */	
	public static int[] merge(int[] array1, int[] array2){//参数数组均有序且无重复值
		ArrayList<Integer> list = new ArrayList<Integer>();
		int i = 0;//array1
		int j = 0;//array2
		while(i < array1.length && j < array2.length){
			if(array1[i] == array2[j]){
				list.add(array1[i]);
				i++;
				j++;
			}else if(array1[i] < array2[j]){
				list.add(array1[i]);
				i++;
			}else{//array1[i] > array2[j]
				list.add(array2[j]);
				j++;
			}
		}//while结束 
		
	    //此时(i == array1.length && j == array2.length)or
		   //(i == array1.length && j < array2.length)or
		   //(i < array1.length && j == array2.length)
		while(i < array1.length){
			list.add(array1[i]);
			i++;
		}
		while(j < array2.length){
			list.add(array2[j]);
			j++;
		}
		
		int[] result = listToArray(list);
		return result;
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
	public static int[] grow(int [] oldArray,  int size){
		return Arrays.copyOf(oldArray, oldArray.length + size);
//		int[] result = new int[oldArray.length + size];
//		System.arraycopy(oldArray, 0, result, 0, oldArray.length);
//		return result;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max < 2){//max=1 特殊
			return new int[0];
		}
		int one = 1;
		int two = 1;
		int temp = 0;
		int i = 2;
		while(one + two < max){
			temp = two;
			two = one + two;
			one = temp;
			i++;
		}
		int[] result = new int[i];
		result[0] = 1;
		result[1] = 1;
		for(int j = 2;j < result.length;j++){
			result[j] = result[j-1] + result[j-2];
		}
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){//max:3 return:[2]
		if(max <= 2){
			return new int[0];
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 2;i < max;i++){
			if(isPrimes(i)){
				list.add(i);
			}
		}
		int[] result = listToArray(list);
		return result;
	}
	
	/*
	 * 判断一个数是不是质数
	 */
	public static Boolean isPrimes(int data){
		if(data < 2){
			return false;
		}
		for(int i = 2;i < data;i++){
			if(data % i == 0){
				return false;
			}
			if(i * i > data){
				break;
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
	public static int[] getPerfectNumbers(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 6;i < max;i++){
			if(isPerfectNumber(i)){
				list.add(i);
			}
		}
		int[] result = listToArray(list);
		return result;
	}
	
	/*
	 * ArrayList<Integer>  --->  int[]
	 */
	public static int[] listToArray(ArrayList<Integer> list){
		int[] result = new int[list.size()];
		for(int j = 0;j < result.length;j++){
			result[j] = (int) list.get(j);
		}
		return result;
	}
	
	/*
	 * 判断一个数是不是完数
	 */
	public static Boolean isPerfectNumber(int data){
		if(data < 6){
			return false;
		}
		int sum = 1;
		for(int i = 2;i < data;i++){
			if(i * i > data){
				break;
			}
			if(data % i == 0){
				if(i == data/i){
					sum = sum + i;
				}else{
					sum = sum + i + data/i;					
				}
//				sum = sum + i;
				if(sum > data){
					return false;
				}
			}
		}//for
		if(sum == data){
			return true;
		}else{//sum < data
			return false;
		}
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		StringBuffer result = new StringBuffer();
		for(int i = 0;i < array.length;i++){
			result.append(array[i]);
			result.append(seperator);
		}
		
		String res = result.toString();
		if(array.length > 0){
			res = res.substring(0, result.length()-seperator.length());//删除最后一个seperator
		}
		return res;
	}
	
	/*
	 * 数组转字符串 格式：[]or[1]or[1 2]
	 */
	public static String arrayToString(int[] array){
		StringBuffer result = new StringBuffer();
		result.append("[");
		for(int i = 0;i < array.length;i++){
			result.append(array[i]);
			result.append(" ");
		}
		String res = result.toString();
		if(array.length > 0){
			res = res.substring(0, result.length()-1);//删除最后一个空格
		}
		res = res + "]";
		return res;
	}
	
	public static void main(String[] args){
		System.out.println(new Date());
		int[] a1 = getPerfectNumbers(2000000);
		System.out.println(arrayToString(a1));
		System.out.println(new Date());
	}
}
