package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	
	public static void main(String[] args) {
		int[] a = {7, 9, 30, 3, 4};
		reverseArray(a);
		System.out.println(Arrays.toString(a));
		
		
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5,0} ;
		System.out.println(Arrays.toString(removeZero(oldArr)));
		
		
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		
		System.out.println(Arrays.toString(merge(a1,a2)));
		
		
		int[] b = { 2,3,6};
		System.out.println(Arrays.toString(grow(b,5)));
		
		System.out.println(genFibonacci(5));
		
		System.out.println(Arrays.toString(fibonacci(30)));
		
		System.out.println(Arrays.toString(getPrimes(10000)));
		
		System.out.println(getFactor(10));
		
		System.out.println(isPerfectNum(1000));
		
//		System.out.println();
		System.out.println(Arrays.toString(getPerfectNumbers(100)));
		
		System.out.println(join(a,"&"));
		
		
	}
	public static void reverseArray(int[] origin){
		
		if(origin.length==0){
			return;
			
		}
		int[] copy = new int[origin.length];
		System.arraycopy(origin, 0, copy, 0, origin.length);
	
		for (int i = 0; i < copy.length; i++) {
			
			origin[i] = copy[copy.length-1-i];
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
		int newSize = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i]!=0){
				newSize++;
			}
		}
		int index = 0;
		int[] newArr = new int[newSize];
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i]!=0){
				newArr[index] = oldArray[i];
				index++;
			}
		}
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		Arrays.sort(array1);
		Arrays.sort(array2);
		
		int[] newArr = new int[array1.length+array2.length];
		
		System.arraycopy(array1, 0, newArr, 0,array1.length );
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		Arrays.sort(newArr);
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<newArr.length;i++){
			
			if(i==0){
				
				list.add(newArr[i]);
			}else{
				if(newArr[i] != newArr[i-1]){
					list.add(newArr[i]);
					
				}
			}
		}
//		Collections.sort(list);
		System.out.println(list);
		int[] target = new int[list.size()];
		for(int i=0;i<list.size();i++){
			target[i] = list.get(i);
		}
		return  target;
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
	public static int[] fibonacci(int max){
		
		if(max == 0){
			System.out.println("max必须大于或者等于1");
			return null;
		}else if(max ==1){
			return new int[0];
		}else{
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0;i<max;i++){
				int fib = genFibonacci(i);
				if(fib<=max){
					list.add(fib);
				}
			}
			
			int[] newArr = new int[list.size()];
			for (int i=0;i<list.size();i++) {
				newArr[i] = list.get(i);
				
			}
			return newArr;
		}
	}
	
	
	public static int genFibonacci(int index){
		
		if(index<0){
			System.out.println("索引不能为负数");
			return -1;
		}
		if(index<=1){
			
			return 1;
		}else{
			return genFibonacci(index-2)+genFibonacci(index-1);
		}
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		if(max<2){
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i=2;i<=max;i++){
			if(isPrime(i)){
				list.add(i);
			}
		}
		
		return listToArray(list);
		
		
	}
	
	public static int[] listToArray(List<Integer> list){
		if(list == null){
			return null;
		}
		
		int[] arr = new int[list.size()];
		
		for(int i=0;i<list.size();i++){
			arr[i] = list.get(i);
			
		}
		return arr;
	}
	
	/**
	 * 某数是否为素数
	 * @param num
	 * @return
	 */
	public static boolean isPrime(int num){
		if(num<=1){
			return false;
		}
		boolean flag = true;
		for(int i=2;i<num;i++){
			if(num % i ==0){
				flag = false;
			}
			
			
		}
		
		return flag;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<=max;i++){
			if(isPerfectNum(i)){
				list.add(i);
			}
		}
		
		return listToArray(list);
	}
	
	
	public static boolean isPerfectNum(int num){
		if(num <=1){
			return false;
		}
		
		List<Integer> factors = getFactor(num);
		int sum = 0;
		for (Integer integer : factors) {
			sum = integer+sum;
		}
		if(sum == num){
			return true;
		}
		return false;
	}
	
	public static List<Integer> getFactor(int num){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		
		
		for(int i=2;i<num;i++){
			if(num%i ==0){
				list.add(i);
			}
		}
//		list.add(num);
		
		return list;
	}
	/*public static List<Integer> getFactor(int num){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		int temp = num;
		
		while(!isPrime(temp)){
			if(temp ==1){
				break;
			}
			for(int i=2;i<=temp;i++){
				if(temp % i ==0){
					list.add(i);
					temp = temp/i;
					break;
				}
			}
			
		}
		list.add(temp);
		
		return list;
	}*/
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		StringBuilder sb = new StringBuilder();
		for (int i : array) {
			sb.append(i);
			sb.append(seperator);
			
		}
		
		return sb.subSequence(0, sb.length()-1).toString();
	}
	

}
