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
		int len =origin.length;
		int [] newArray =new int[len];
		
		int j=0;
		for(int i=len-1;i>=0;i--){
			if(j<newArray.length){
				newArray[j]=origin[i];
				j++;
			}
		}
		System.out.println(Arrays.toString(newArray));
		for (int i : newArray) {
			System.out.print(i+",");
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
		int len = oldArray.length;
		int [] midA=new int[len];
		
		int j=0;
		int num=0;
		for(int i=0;i<len;i++){
			if(oldArray[i] != 0){
				midA[j]=oldArray[i];
				j++;
				++num;
			}
		}
		
		int[] newA = Arrays.copyOf(midA, num);
		
		System.out.println(Arrays.toString(newA));
		return newA;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		if(array1[0] != array2[0]){
			list.add(array1[0]);
			list.add(array2[0]);
		}else if(array1[0] == array2[0]){
			list.add(array1[0]);
		}
		
		int i =1;
		for(int j=1;j<array1.length;j++){
			if(array1[i-1] == array2[j]){
				list.add(array1[i]);
			}else if(array1[i] == array2[j-1]){
				list.add(array2[j]);
			}else if(array1[i] > array2[j]){
				list.add(array2[j]);
				list.add(array1[i]);
			}else if(array1[i] < array2[j]){
				list.add(array1[i]);
				list.add(array2[j]);
			}else if(array1[i] == array2[j]){
				list.add(array1[i]);
			}
			i++;
		}	

			int[] newA =new int[list.size()];
			
			for(int k=0;k<newA.length;k++){
				newA[k]=list.get(k);
			}
			
			System.out.println(Arrays.toString(newA));
			return newA;
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
		int len =oldArray.length+size;
		int[] newA =new int[len];
		
		newA =Arrays.copyOf(oldArray, len);
		System.out.println(Arrays.toString(newA));
		return newA;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int f(int n){
		return n>2?f(n-1)+f(n-2):1;
	}
	public int[] fibonacci(int max){
		ArrayList<Integer> list =new ArrayList<Integer>();
		for(int i=1; i<max; i++){
			list.add(f(i));
		}
		
		int num=0;
		int [] newA =null;
		for(int i=0; i<list.size(); i++){
			if(max <= list.get(i+1)){
				break;
			}
			num=i+1;
		}
		
		newA =new int[num+1];
		for(int i=0; i<=num; i++){
			newA[i]=list.get(i);
		}
		for (int i : list) {
			System.out.println(i);
		}
		for (int i : newA) {
			System.out.println(i);
		}
		return newA;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int j;
		for(int i=2; i<=max; i++){
			j=2;
			while(i%j != 0){
				j++;
			}
			if(j == i){
				list.add(i);
			}
		}
		
		for (Integer in : list) {
			System.out.println(in);
		}
		
		int num=0;
		int [] newA =null;
		for(int i=0; i<list.size(); i++){
			if(max <= list.get(i+1)){
				break;
			}
			num=i+1;
		}
		
		int [] newA1 =new int[num+1];
		for(int i=0; i<=num; i++){
			newA1[i]=list.get(i);
		}
			
		System.out.println(Arrays.toString(newA1));
		
		return newA1;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int s;
	     for(int i=1;i<=10000;i++)
	     {
	        s=0;
	        for(int j=1;j<i;j++)
	            if(i % j==0)
	               s=s+j;
	           if(s==i)
	             list.add(i);
	     }
	   
	     
	     int num=0;
	     int [] newA =null;
	     for(int i=0; i<list.size(); i++){
			if(max <= list.get(i+1)){
				break;
			}
			num=i+1;
		}
	     
	     	int [] newA1 =new int[num+1];
			for(int i=0; i<=num; i++){
				newA1[i]=list.get(i);
			}
				
			System.out.println(Arrays.toString(newA1));
			
			return newA1;
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
		StringBuffer sb =new StringBuffer();
		for(int i=0; i<array.length; i++){
			if(i != array.length-1){
				sb.append(array[i]);
				sb.append(seperator);
			}else if(i == array.length-1){
				sb.append(array[i]);
			}
		}
		return sb.toString();
	}
	

}
