package com.pxshuo.se02.array;

import java.util.ArrayList;
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
		int[] result = new int[origin.length];
		for(int i = 0;i<origin.length;i++){
			result[i] = origin[origin.length - i -1];
		}
		System.arraycopy(result, 0, origin, 0, origin.length);//使用复制，是因为origin属于指针，指向原地址，所以可以修改原地址中的内容。而修改指针不会发生任何事情
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int[] tailZero = new int[oldArray.length];
		int tailNum = 0;//记录去除0之后数组的长度
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				tailZero[tailNum++] = oldArray[i];
			}
		}
		int[] removeResult = new int[tailNum];
		System.arraycopy(tailZero, 0, removeResult, 0, tailNum);
		return removeResult;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		List<Integer> resultList = new ArrayList<>();
		for(int i = 0,j = 0;i<array1.length || j < array2.length;){
			if (i == array1.length) {
				for(;j < array2.length;j++){
					resultList.add(array2[j]);
				}
				break;
			}
			if (j == array2.length) {
				for(;i < array1.length;i++){
					resultList.add(array1[i]);
				}
				break;
			}
			if(array1[i] > array2[j]){
				resultList.add(array2[j++]);
			}
			else if (array1[i] < array2[j]) {
				resultList.add(array1[i++]);
			} else {
				j++;
			}
		}
		
		int[] result = new int[resultList.size()];
		for(int i = 0;i < resultList.size();i++){
			result[i] = resultList.get(i);
		}
		return  result;
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
		int[] result = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, result, 0, oldArray.length);
		return result;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		List<Integer> resultList = new ArrayList<>();
		resultList.add(1);
		resultList.add(1);
		int fibon = 1,fibonPrev1 = 1,fibonPrev2 = 1; 
		while(fibon < max){
			fibonPrev2 = fibon;
			fibon = fibonPrev1 + fibonPrev2;
			fibonPrev1 = fibonPrev2;
			resultList.add(fibon);
		}
		resultList.remove(resultList.size()-1);
		
		int[] result = new int[resultList.size()];
		for(int i = 0;i < resultList.size();i++){
			result[i] = resultList.get(i);
		}
		return  result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if (max < 2) {
			return null;
		}else if (max < 4) {
			return new int[]{2};
		}
		if (max < 6) {
			return new int[]{2,3};
		}
		List<Integer> primeList = new ArrayList<>();
		primeList.add(2);
		primeList.add(3);
		boolean isPrime = true;
		/**
		 * 非2偶数肯定不是素数
		 */
		for(int i = 5;i < max; i = i + 2){
			isPrime =true;
			for(int j = 1;j<primeList.size();j++){
				if (i%primeList.get(j) == 0) {
					isPrime =false;
					break;
				}
			}
			if (isPrime) {
				primeList.add(i);
			}
		}
		if (primeList.get(primeList.size() - 1) >= max) {
			primeList.remove(primeList.size() - 1);
		}
		
		int[] result = new int[primeList.size()];
		for(int i = 0;i < primeList.size();i++){
			result[i] = primeList.get(i);
		}
		return  result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if (max < 7) {
			return null;
		}
		List<Integer> perfectList = new ArrayList<>();
		int sum = 1;
		
		for (int i = 2; i < max; i++) {
			sum = 1;
			for(int j = 2;j < i; j++){
				if (i%j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				perfectList.add(sum);
			}
		}
		
		if (perfectList.get(perfectList.size() - 1) >= max) {
			perfectList.remove(perfectList.size() - 1);
		}
		
		int[] result = new int[perfectList.size()];
		for(int i = 0;i < perfectList.size();i++){
			result[i] = perfectList.get(i);
		}
		return  result;
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
		String result = "";
		for (int i = 0; i < array.length; i++) {
			result = result + array[i] + seperator;
		}
		if (result.endsWith(seperator)) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	

}
