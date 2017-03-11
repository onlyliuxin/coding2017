package com.leijing.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * @author: leijing
 * @date: 2017年3月2日 下午5:30:38
 */
public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 * @throws Exception 
	 */
	public void reverseArray(int[] origin) throws Exception{
		if(origin.length < 1){
			throw new Exception("array origin is empty");
		}
		int size = origin.length;
		int[] copy = Arrays.copyOf(origin, size);
		for (int i = 0; i < size; i++) {
			origin[size - i - 1] = copy[i];
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
		int newSize = countNotZero(oldArray);//先计算非零元素个数
		int[] newArray = new int[newSize];//创建新数组
		int newIndex = 0;//新数组索引
		int oldIndex = 0;//旧数组索引
		while(newIndex < newSize){
			if(oldArray[oldIndex] != 0){
				newArray[newIndex++] = oldArray[oldIndex];
			}

			oldIndex++;
		}

		return newArray;
	}

	private int countNotZero(int[] oldArray) {
		int count = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] != 0){
				count++;
			}
		}
		return count;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2){
		int size = getSize(array1 , array2);
		int[] newArray = new int[size];

		int minIndex1 = 0;
		int minIndex2 = 0;

		for (int i = 0; i < newArray.length; i++) {

			if(minIndex1 < array1.length && minIndex2 == array2.length ){//如果array2的元素取完了，就取array1的元素
				newArray[i] = array1[minIndex1++];
			}else if(minIndex1 == array1.length && minIndex2 < array2.length){//如果array1的元素取完了，就取array2的元素
				newArray[i] = array2[minIndex2++];
			}else{//两个数组都没有取完
				if(array1[minIndex1] < array2[minIndex2]){//第一个数组的元素小
					newArray[i] = array1[minIndex1++];
				}else if(array1[minIndex1] == array2[minIndex2]){//元素相等，保留第一个数组的，第二个数组索引加一
					newArray[i] = array1[minIndex1++];
					minIndex2++;
				}
				else{//第二个数组元素小
					newArray[i] = array2[minIndex2++];
				}
			}
		}

		return  newArray;
	}

	private int getSize(int[] array1 , int[] array2) {
		Set<Integer> set = new HashSet<Integer>();
		int sameNum = 0;

		for (int i = 0; i < array1.length; i++) {
			set.add(array1[i]);
		}

		for (int i = 0; i < array2.length; i++) {
			if(!set.add(array2[i])){
				sameNum++;
			}
		}
		return array1.length + array2.length - sameNum;
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
		if(size < 1){
			return oldArray;
		}
		int[] newArray = new int[oldArray.length + size];

		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
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
		if(max == 1){
			return new int[0];
		}
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		
		int next = 0;
		int i = 2;
		while(next < max) {
			next = list.get(i - 1) + list.get(i - 2 );
			list.add(next);
			i++;
		}
		int[] array = new int[list.size()];
		list2Array(list , array);
		return array;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i < max; i++) {
			if(isPrimes(i)){
				list.add(i);
			}
		}
		int[] array = new int[list.size()];
		list2Array(list , array);
		return array;
	}

	private boolean isPrimes(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if(num % i == 0){
				return false;
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
	public int[] getPerfectNumbers(int max){

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < max; i++) {
			if(isPerfectNum(i)){
				list.add(i);
			}
		}
		int[] array = new int[list.size()];
		list2Array(list , array);
		return array;
	}

	private void list2Array(List<Integer> list, int[] array) {
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
	}

	private boolean isPerfectNum(int num) {
		Set<Integer> list = new HashSet<Integer>();
		if(num == 1){
			return false;
		}else{
			list.add(1);
		}
		for (int i = 2; i <= num / 2; i++) {
			if(num % i == 0){
				list.add(i);
				list.add(num / i);
			}
		}
		Iterator<Integer> iter = list.iterator();
		int sum = 0;
		while (iter.hasNext()) {
			Integer val = iter.next();
			sum += val;
		}

		if(sum == num){
			System.out.println("num:"+num+","+list.toString());
			return true;
		}
		return false;
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
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if(i < array.length - 1){
				sb.append(seperator);
			}
		}
		return sb.toString();
	}

	public void printArray(int[] array , String msg){
		System.out.print(msg+"[");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if(i < array.length - 1){
				System.out.print(",");
			}
		}
		System.out.println("]");
	}


}
