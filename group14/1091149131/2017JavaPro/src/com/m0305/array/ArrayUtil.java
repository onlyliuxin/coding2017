package com.m0305.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int len=origin.length;
		
		//方法2，用新数据去取旧数组的值
		int[] src=new int[len];
		long start=System.currentTimeMillis();
		for(int i=0;i<len;i++){
			src[i]=origin[len-1-i];
		}
		long end=System.currentTimeMillis();
		System.out.println("方法2，用新数据去取旧数组的值 耗时："+(end-start));
		for(int i=0;i<len;i++){
			System.out.print(src[i]+",");
		}
		System.out.println();
		
		//方法1，在本数组之间移动
		int temp;
		int j=len/2;
		start=System.currentTimeMillis();
		for(int i=0;i<j;i++){
			temp=origin[i];
			origin[i]=origin[len-1-i];
			origin[len-1-i]=temp;
		}
		end=System.currentTimeMillis();
		System.out.println("方法1，在本数组之间移动 耗时："+(end-start));
		for(int i=0;i<len;i++){
			System.out.print(origin[i]+",");
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
		//如何得到去掉0后的数组长度，装箱会不会影响性能？？
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]==0){
				continue;
			}
			list.add(oldArray[i]);
		}
		//如何更好的将list转为int[]
		int[] srcArr=new int[list.size()];
		for(int i=0;i<list.size();i++){
			srcArr[i]=list.get(i);
		}
		return srcArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	
	public int[] merge(int[] arr1, int[] arr2){
		//合并排序算法
		int len1=arr1.length;
		int len2=arr2.length;
		ArrayList<Integer> list=new ArrayList<>();
		//i指向arr1，j指向arr2,
		int i=0,j=0;
		while(i<len1&&j<len2){
			
			while(i<len1&&j<len2&&arr1[i]<=arr2[j]){
				if(arr1[i]==arr2[j]){
					j++;
				}
				list.add(arr1[i]);
				i++;
			}
			while(i<len1&&j<len2&&arr1[i]>arr2[j]){
				list.add(arr2[j]);
				j++;
			}
		}
		if(i>=len1||j>=len2){
			//如果其中一个数组已经遍历完了，则另外一个数组直接加入到list中
			for(int k1=i;k1<len1;k1++){
				list.add(arr1[k1]);
			}
			for(int k2=j;k2<len2;k2++){
				list.add(arr2[k2]);
			}
		}
		
		int[] srcArr=new int[list.size()];
		for(int k=0;k<list.size();k++){
			srcArr[k]=list.get(k);
		}
		return  srcArr;
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
		int[] newArr=new int[oldArray.length+size];
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
	public int[] fibonacci(int max){
		ArrayList<Integer> list=new ArrayList<>();
		if(max==1){
			return new int[0];//??空数组？？
		}
		
		int one=1;
		int two=2;
		list.add(one);
		list.add(two);
		int temp=one+two;
		while(temp<max){
			list.add(temp);
			one=two;
			two=temp;
			temp=one+two;
		}
		
		return list2arr(list);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<2) return new int[0];
		ArrayList<Integer> list=new ArrayList<>();
		int j=2;
		for(int i=2;i<max;i++){
			j=2;
			for(;j<i;j++){
				if(i%j==0){
					break;
				}
			}
			if(j==i){
				list.add(i);
			}
		}
		return list2arr(list);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		//if(max<2) return new int[0];
		ArrayList<Integer> list=new ArrayList<>();
		//1是完数吗
		int sum=1;
		for(int i=2;i<max;i++){
			sum=1;
			for(int j=2;j<i;j++){
				if(i%j==0){
					sum+=j;
				}
			}
			if(sum==i){
				list.add(i);
			}
		}
		return list2arr(list);
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
		if(array.length<1) return "";
		StringBuffer sBuffer=new StringBuffer();
		
		for(int i:array){
			sBuffer.append(i+""+seperator);
		}
		return sBuffer.substring(0, sBuffer.length()-1);
	}
	
	public static int[] list2arr(ArrayList<Integer> list){
		if(list==null) return null;
		int[] descArr=new int[list.size()];
		for(int i=0;i<list.size();i++){
			descArr[i]=list.get(i);
		}
		return descArr;
	}

}
