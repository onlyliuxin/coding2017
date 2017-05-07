package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int arrayLength = origin.length;
		//注意整数截断，奇数长度不用额外处理
		for (int i = 0 ; i < arrayLength / 2 ; i++){
			int temp = origin[i];
			origin[i]=origin[arrayLength - i - 1];
			origin[arrayLength - i - 1] = temp;
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
		int newArrayLength = 0;
		for (int i : oldArray){
			if (i != 0){
				newArrayLength ++;
			}
		}
		
		int[] newArray = new int[newArrayLength];
		int newArrayIndex = 0;
		for (int i : oldArray){
			if (i != 0){
				newArray[newArrayIndex++] = i;
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
		
		//注意此处算出来的newArrayLength是最大值，实际可能小于这个长度
		int newArrayLength = array1.length + array2.length;
		int[] newArray = new int[newArrayLength];
		final int MAX_VALUE = Integer.MAX_VALUE;
		int index1 = 0;
		int index2 = 0;
		int newArrayIndex = 0;
		int element1;
		int element2;
		
		//注意是两个数组都是递增的，可以交替步进比较，当大小翻转的时候交替，要求踢重，则相等的时候步进但不保存
		while(index1 < array1.length || index2 < array2.length){
			
			//此处取巧的点在于已知数组是int型，故最大值是已知的，当步进到头时取最大值，即“钳位”
			if (index1 < array1.length){
				element1 = array1[index1];
			}else{
				element1 = MAX_VALUE;
			}
			
			if (index2 < array2.length){
				element2 = array2[index2];
			}else{
				element2 = MAX_VALUE;
			}		
			
			//谁小谁步进
			if (element1 < element2){
				newArray[newArrayIndex++] = element1;
				index1++;
			}else{
				if (element1 == element2){
					//相等后不再赋值给新数组
					index2++;
				}else{
					newArray[newArrayIndex++] = element2;
					index2++;
				}
			}
		}
		//移除没用到的位置
		return Arrays.copyOf(newArray, newArrayIndex);
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
		int[] newArray = new int[oldArray.length + size];
		//Java对引用类型自动赋值，故0不需要额外的处理
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
		ArrayList list = new ArrayList();
		int index = 0;
		if (max >= 2){
			while( true ){
				int fibonacciNum = getFibonacci(index++);
				if ( fibonacciNum < max ){
					list.add(fibonacciNum);
				}else{
					break;
				}
			}
		}
		return list2Array(list);
	}
	
	private int getFibonacci(int index){
		if (index == 0){ return 1; }
		if (index == 1){ return 1; }
		return getFibonacci(index - 2) + getFibonacci(index - 1);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList list = new ArrayList();
		
		for (int i = 0 ; i < max ; i++){
			if (isPrime(i)){ list.add(i); }
		}
		
		return list2Array(list);
	}
	
	private boolean isPrime(int num){
		if (num == 0 || num == 1){
			return false;
		}
		for (int i = 2 ; i <= num / 2 ; i++){
			if (num % i == 0){
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
		ArrayList list = new ArrayList();
		
		for (int i = 1 ; i < max ; i++){
			if (isPerfectNumbers(i)){ list.add(i); }
		}
		
		return list2Array(list);
	}
	
	private boolean isPerfectNumbers(int num){
		if (num == 1){
			return false;
		}
		int sum = 1;
		//注意因子是对称的，故比较的上限是的平方根
		int sqr = (int) Math.sqrt(num);
		for (int i = 2 ; i <= sqr ; i++){
			if (num % i == 0){
				sum = sum + i + num / i;
			}
		}
		return sum == num;
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
		for (int i = 0 ; i < array.length ; i++){
			sb.append(array[i]);
			if (i != array.length - 1){
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
	
	private int[] list2Array(ArrayList list){
		int[] array = new int[list.size()];
		for (int i = 0 ; i < list.size() ; i++){
			array[i] = (int) list.get(i);
		}
		return array;
 	}
}