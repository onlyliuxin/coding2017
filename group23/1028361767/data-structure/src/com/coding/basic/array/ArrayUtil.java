package com.coding.basic.array;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		int len = origin.length;
		int[] ret = new int[len];
		for(int i=0;i<len;i++){
			ret[i] = origin[len-i-1];
		}
		origin = null;
		return ret;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int[] ret = new int[oldArray.length];
		int zeroNum = 0;
		int tmp;
		int retIndex = 0;
		for(int i=0;i<oldArray.length;i++){
			tmp = oldArray[i];
			if(tmp == 0){
				zeroNum++;
			}else{
				ret[retIndex++] = tmp;
			}
		}
		if(zeroNum == 0){
			return oldArray;
		}else{
			return Arrays.copyOf(ret, retIndex);
		}
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2){
		int[] ret = new int[array1.length + array2.length];
		int i1 = 0, i2 = 0;
		int val1, val2;
		int sameNum = 0;
		int len1 = array1.length-1, len2 = array2.length -1;
		for(int i=0;i<ret.length;){
			if(i1 <= len1 && i2 <= len2){
				val1 = array1[i1];
				val2 = array2[i2];
				if(val1 < val2){
					ret[i++] = val1;
					i1++;
				}else if(val1 == val2){
					sameNum++;
					i2++;
				}else{
					ret[i++] = val2;
					i2++;
				}
			}else{
				if(i1 > len1 && i2 > len2){
					break;
				}else if(i1 > len1){
					ret[i++] = array2[i2++];
				}else{
					ret[i++] = array1[i1++];
				}
			}
				
		}
		if(sameNum > 0){
			ret = Arrays.copyOf(ret, ret.length - sameNum);
		}
		return ret;
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
		return Arrays.copyOf(oldArray, oldArray.length + size);
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
		}else{
			int[] tmp = new int[max + 1];
			int x1 = 1, x2 = 1;
			int i = 1, j = 0;
			tmp[j++] = x1;
			tmp[j++] = x2;
			while(true){
				i = x1 + x2;
				if(i > max){
					break;
				}
				x1 = x2;
				x2 = i;
				tmp[j++] = i;
			}
			return Arrays.copyOf(tmp, j);
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] tmp = new int[max/2 + 1];
		boolean isPrime;
		int k = 0;
		for(int i=2;i<max;i++){
			isPrime = true;
			for(int j=2;j<i;j++){
				if(i % j == 0 && i != j){
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				tmp[k++] = i;
			}
		}
		return Arrays.copyOf(tmp, k);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max < 3){
			return new int[0];
		}
		int[] tmp = new int[max/6];
		int[] factors;
		int i = 0, l, sum;
		for(int j=2;j<max;j++){
			l = 0;sum = 1;
			factors = new int[j];
			for(int k=2;k<j;k++){
				if(j % k == 0 && j != k){
					factors[l++] = k;
				}
			}
			for(int m=0;m<l;m++){
				sum += factors[m];
			}
			if(sum == j){
				tmp[i++] = j;
			}
		}
		return Arrays.copyOf(tmp, i);
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
			sb.append(array[i]).append(seperator);
		}
		return sb.length() == 0? "" : sb.substring(0, sb.length() - 1);
	}
	
}
