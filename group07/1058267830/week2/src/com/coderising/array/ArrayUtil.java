package com.coderising.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		if(origin == null)
			return null;
		int length = origin.length;
		int[] target = new int[length];
		for(int i=0; i<length ;i++){
			target[length-1-i] = origin[i];
		}
		return target;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		if(oldArray == null)
			return null;
		int zeroNum = 0;
		int length = oldArray.length;
		for(int i=0; i<length; i++){
			if(oldArray[i] == 0)
				zeroNum++;
		}
		int[] newArray = new int[length - zeroNum];
		int j = 0;
		for(int i=0; i<length; i++){
			if(oldArray[i] != 0)
				newArray[j++] = oldArray[i];
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
		if(array1 == null || array2 == null)
			return array1 == null ? array2 : array1;
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0; i<array1.length; i++){
			set.add(array1[i]);
		}
		for(int i=0; i<array2.length; i++){
			set.add(array2[i]);
		}
		int[] target = new int[set.size()];
		int i = 0;
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			target[i++] = it.next();
		}
		return target;
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
		if(oldArray == null)
			return null;
		if(size == 0)
			return oldArray;
		if(size < 0)
			throw new RuntimeException("参数错误");
		int length = oldArray.length + size;
		int[] target = new int[length];
		System.arraycopy(oldArray, 0, target, 0, oldArray.length);
		for(int i=oldArray.length; i< length; i++){
			target[i] = 0;
		}
		return target;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max < 1)
			throw new RuntimeException("参数错误");
		if(max == 1){
			int[] result = {};
			return result;
		}
		if(max == 2){
			int[] result = {1, 1};
			return result;
		}
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		for(int i=2; ; i++){
			int tmp = list.get(i-2) + list.get(i-1);
			if(tmp < max){
				list.add(tmp);
			}else{
				break;
			}
		}
		int size = list.size();
		int[] result = new int[size];
		for(int i=0; i<size; i++)
			result[i] = list.get(i);
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max <= 0)
			throw new RuntimeException("参数错误");
		if(max <=2 ){
			int[] result = {};
			return result;
		}
		
		if(max <= 13){
			int[] tmp = {2,3,5,7,11,13};
			int index = 0;
			for(int i=0; i<6; i++){
				index = i;
				if(tmp[i] >= max)
					break;
			}
			int[] result = new int[index];
			System.arraycopy(tmp, 0, result, 0, index);
			return result;
		}
			
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(11);
		for(int i=13; i<max; i+=2){
			boolean isNum = true;
			for(int j=3; j<=Math.sqrt(i); j++){
				if(i%j == 0){
					isNum = false;
					break;
				}
			}
			if(isNum == true)
				list.add(i);
		}
		int[] result = new int[list.size()];
		for(int i=0; i<list.size(); i++)
			result[i] = list.get(i);
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return	
	 */
	public int[] getPerfectNumbers(int max){  
		if(max <= 1)
			throw new RuntimeException("参数错误");
		if(max <= 5){
			int[] result = {};
			return result;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int input = 2; input<max; input++){
			int sum = 1;
			for(int i=2; i<=input/2.0; i++){
				if(input % i == 0){
					sum += i;
				}
			}
			if(sum == input)
				list.add(input);
		}
		int[] result = new int[list.size()];
		for(int i=0; i<list.size(); i++)
			result[i] = list.get(i);
		return result;
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
		String s = "";
		for(int i=0; i<array.length-1; i++){
			s = s + (array[i]) + "-"; 
		}
		s = s + array[array.length-1];
		return s;
	}
	

}
