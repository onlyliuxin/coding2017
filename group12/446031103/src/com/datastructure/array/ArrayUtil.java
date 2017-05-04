package com.datastructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(null ==origin ||0==origin.length){
			return;
		}
		for (int i = 0,j=origin.length-1; i < j; i++,j--) {
			int temp=origin[i] ;
			origin[i]= origin[j];
			origin[i]=temp;
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
		if(null==oldArray||oldArray.length ==0){
			return null;
		}
		int notZeroCnt = 0;
		int [] temp = new int[oldArray.length];
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i]!=0){
				temp[notZeroCnt++] = oldArray[i];
			}
		}
		System.arraycopy(temp, 0, temp, 0, notZeroCnt);
		return temp;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){		
		if(null==array1&&null==array2){
			return null;
		}		
		int [] temp = new int[array1.length+array2.length];
		int i = 0;
		int j = 0;
		int count = 0;
		while (i<array1.length&&j<array2.length) {
			if(array1[i]<array2[j]){
				temp[count++] = array1[i++];
			}
			if(array1[i]>array2[j]){
				temp[count++] = array2[j++];
			}
			if(array1[i]==array2[j]){
				temp[count++] = array2[j];
				i++;
				j++;
			}
		}
		while(i==array1.length&&j<array2.length){
			temp[count++] = array2[j++];
		}
		while(j==array2.length&&i<array1.length){
			temp[count++] = array1[i++];
		}
		System.arraycopy(temp, 0, temp, 0, count);
		return  temp;
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
		return Arrays.copyOf(oldArray, oldArray.length+size);
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(1==max){
			return new int[0];
		}
		if(2==max){
			return new int[]{1,1};
		}
		int [] temp = new int [max]  ;
		temp[0] = 1;
		temp[1] = 1;
		int cnt = 2;
		for (int i = 2 ; i < max; i++) {
			temp[i] = temp[i-1] + temp[i-2];
			if(temp[i]>=max){
				break;
			}else{
				cnt++;
			}
		}
		return Arrays.copyOf(temp, cnt);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<2){
			return new int[0];
		}
		int [] temp = new int[max];
		int cnt = 0;
		for (int i = 2; i < max; i++) {
			if(isPrime(i)){
				temp[cnt++] = i;
			}
		}
		return Arrays.copyOf(temp, cnt);
	}
	
	private boolean isPrime(int n){
		int i = 2;
		while(i<n){
			if(n%i==0){
				break;
			}
			if(n%i!=0){
				i++;
			}
		}
		return i==n;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max<0){
			return null;
		}
		int cnt = 0;
		int [] temp = new int[max];
		for (int i = 2; i < max; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if(i%j==0){
					sum+=j;
				}
			}
			if(sum==i){
				temp[cnt++] = i;
			}
		}
		return Arrays.copyOf(temp, cnt);
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
		if(null==array||array.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if(i<array.length-1){
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
	
	

}
