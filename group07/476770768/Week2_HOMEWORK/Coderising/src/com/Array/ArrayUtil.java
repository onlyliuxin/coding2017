package com.Array;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		if (origin == null)
			return;
		int head = 0;
		int tail = origin.length - 1;
		int tmp;
		while (head < tail) {
			tmp = origin[tail];
			origin[tail] = origin[head];
			origin[head] = tmp;
			head++;
			tail--;
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
		int nonZeroNum = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				nonZeroNum++;
			}
		}
		int cnt = 0;
		int[] newArray = new int[nonZeroNum];
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[cnt++] = oldArray[i];
			}
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
		int[] resultArray = new int[array1.length + array2.length];
		int cnt1 = 0;
		int cnt2 = 0;
		int cntResult = 0;
		resultArray[cntResult++] = array1[0]<array2[0]?array1[0]:array2[0];
		//将array1[0]和array2[0]中最小值放入resultArray，不用考虑时候已经存在
		//避免出现第一个值为0而与resultArray[0]的初始值相同的情况
		while(cnt1 < array1.length && cnt2 < array2.length){
			if(array1[cnt1] > array2[cnt2]){
				if(resultArray[cntResult-1] != array2[cnt2]){
					//array2[cnt2]中的数小，且没有重复
					//加入resultArray中
					resultArray[cntResult++] = array2[cnt2++];
				}else{
					//array2[cnt2]中的数小，但是重复
					cnt2++;
				}
			}else{
				if(resultArray[cntResult-1] != array1[cnt1]){
					//array1[cnt1]中的数小，且没有重复
					//加入resultArray中
					resultArray[cntResult++] = array1[cnt1++];
				}else{
					//array1[cnt1]中的数小，但是重复
					cnt1++;
				}
			}
		}
		
		//将为遍历完的数组的剩余部分放入resultArray
		if(cnt1 == array1.length){
			//array2有剩余
			while(cnt2 < array2.length){
				//将array2中剩余的数，无重复地加入resultArray中
				if(resultArray[cntResult-1] != array2[cnt2]){
					resultArray[cntResult++] = array2[cnt2++];
				}else{
					cnt2++;
				}
			}		
		}else{
			while(cnt1 < array1.length){
				//将array1中剩余的数，无重复地加入resultArray中
				if(resultArray[cntResult-1] != array1[cnt1]){
					resultArray[cntResult++] = array1[cnt1++];
				}else{
					cnt1++;
				}
			}
		}
		return Arrays.copyOf(resultArray, cntResult);
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
		int[] newArray = new int[oldArray.length + size];
		for(int i=0; i<oldArray.length; i++){
			newArray[i] = oldArray[i];
		}
		return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if(max <= 1) return null;
		int[] result = new int[1000];
		result[0] = 1;
		result[1] = 1;
		int index = 2;//index of result[]
		int tmpResult = 2;//current result
		while(tmpResult < max){
			result[index++] = tmpResult;
			tmpResult = result[index-2] + result[index-1];
		}
		return Arrays.copyOf(result, index);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if(max <= 1) return null;
		int[] result = new int[max];
		int index = 0;
		for(int currentNum=2; currentNum<max; currentNum++){
			boolean isPrime = true;
			for(int j = 2; j<currentNum; j++){
				if(currentNum%j == 0){
					//i is not prime
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				result[index++] = currentNum;
			}
		}
		return Arrays.copyOf(result, index);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		if(max < 1) return null;
		int[] result = new int[max];
		int index = 0;
		for(int currentNum=1; currentNum<max; currentNum++){
			int sum = 0;
			for(int i=1; i<currentNum; i++){
				//get sum of factor for each number
				if(isFactor(i, currentNum)){
					sum +=i;
				}
			}
			if(sum == currentNum){
				result[index++] = currentNum;
			}
		}
		return Arrays.copyOf(result, index);
	}
	
	public boolean isFactor(int a, int num){
		return num%a == 0;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		if(array == null || seperator == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(array[0]));
		for(int i=1; i<array.length; i++){
			sb.append(seperator);
			sb.append(Integer.toString(array[i]));
		}
		return sb.toString();
	}

}
