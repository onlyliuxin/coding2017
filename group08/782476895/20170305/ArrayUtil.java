package com.sl.test20170304.arrayutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int low = 0;
		int high = origin.length - 1;
		while(low < high){
			int temp = 0;
			temp = origin[low];
			origin[low] = origin[high];
			origin[high] = temp;
			low++;
			high--;
		}
	}
	
	@Test
	public void test1(){
		int[] arr = {7, 9 , 30, 3};
		reverseArray(arr);
		System.out.println(Arrays.toString(arr));
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
		int num = 0;
		for(int i = 0;i < oldArray.length;i++){
			if(oldArray[i] != 0){
				arr[num] = oldArray[i];
				num++;
			}
		}
		int[] newArray = Arrays.copyOf(arr, num);
		return newArray;
	}
	
	@Test
	public void test2(){
		int oldArr[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray = removeZero(oldArr);
		System.out.println(Arrays.toString(newArray));
	}
	
	
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int len1 = array1.length;
		int len2 = array2.length;
		
		int[] arr = new int[len1 + len2]; 
		int len = 0;
		int i = 0;
		int j = 0;
		int min = 0;
		while(i < len1 && j< len2){
			if(array1[i] == array2[j]){
				min = array1[i];
				arr[len] = min;
				i++;
				j++;
				len++;
				continue;
			}
			
			if(array1[i] > array2[j]){
				min = array2[j];
				arr[len] = min;
				j++;
				len++;
				continue;
			}
			
			if(array1[i] < array2[j]){
				min = array1[i];
				arr[len] = min;
				i++;
				len++;
				continue;
			}
		}
		
		if(i > j){
			for(int k = j;k < len2;k++){
				arr[len] = array2[k];
				len++;
			}
		}
		
		if(i < j){
			for(int k = i;k < len1;k++){
				arr[len] = array1[k];
				len++;
			}
		}
		
		int[] newArray = Arrays.copyOf(arr, len);
		return  newArray;
	}
	
	
	@Test
	public void test3(){
		int[] a1 = {3, 7, 9,10,34,78,123};
		int[] a2 = {4, 5, 6,7,56,34,345,2432,545345345};
		int[] newArray = merge(a1, a2);
		System.out.println(Arrays.toString(newArray));
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
		int[] newArray = Arrays.copyOf(oldArray, oldArray.length + size);
		return newArray;
	}
	
	
	@Test
	public void test4(){
		int[] oldArray = {2,3,6};
		int[] newArray = grow(oldArray,3);
		System.out.println(Arrays.toString(newArray));
 	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		
		int[] arr = new int[100];
		int len = 2;
		int f1 = 1;
		int f2 = 1;
		int f3 = f1 + f2;
		
		if(max == 1){
			return new int[0];
		}

		arr[0] = f1;
		arr[1] = f2;
		
		while(f3 <= max){
			arr[len] = f3;
			len++;
			f1 = f2;
			f2 = f3;
			f3 = f1 + f2;
		}
		int[] newArray = Arrays.copyOf(arr, len);
		return newArray;
	}
	
	@Test
	public void test5(){
		int[] newArray = fibonacci(100);
		System.out.println(Arrays.toString(newArray));
	}
	
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] arr = new int[max];
		int len = 0; 
		for(int i = 0;i <= max;i++){
			if(i == 0 || i == 1){
				continue;
			}
			if(isPrime(i)){
				arr[len] = i;
				len++;
			}
		}
		int[] newArray = Arrays.copyOf(arr, len);
		return newArray;
	}
	
	private boolean isPrime(int n){
	    for(int i=2;i<=n/2;i++){
	         if(n%i == 0)
	              return false; 
	    }
	    return true; 
	}
	
	@Test
	public void test6(){
		int max = 100;
		int[] newArray = getPrimes(max);
		System.out.println(Arrays.toString(newArray));
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] arr = new int[max];
		int len = 0; 
		for(int i = 0;i <= max;i++){
			if(i == 0 || i == 1){
				continue;
			}
			if(isPerfectNumber(i)){
				arr[len] = i;
				len++;
			}
		}
		int[] newArray = Arrays.copyOf(arr, len);
		return newArray;
	}
	
	
	private boolean isPerfectNumber(int num){
		List<Integer> list = new ArrayList<Integer>();
		int sum = 0;
		for(int i = 1;i < num;i++){
			if(num % i == 0){
				list.add(i);
			}
		}
		
		for(int j : list){
			sum += j;
		}
		
		return sum == num ? true : false;
	}
	
	
	@Test
	public void test7(){
		int max = 100000;
		int[] newArray = getPerfectNumbers(max);
		System.out.println(Arrays.toString(newArray));
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
		for(int num : array){
			sb.append(num).append(seperator);
		}
		
		String str = sb.toString();
		str = str.substring(0, str.lastIndexOf(seperator));
		return str;
	}
	
	@Test
	public void test8(){
		int[] arr = {1,2,5,6,78,34,332,4452,4342,3,435,233};
		System.out.println(join(arr,"-"));
	}
	

}
