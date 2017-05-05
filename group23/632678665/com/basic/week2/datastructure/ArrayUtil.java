package com.basic.week2.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		int index=(int) (origin.length/2);
		for(int i=0;i<index;i++){
			int temp=origin[i];
			origin[i]=origin[origin.length-1-i];
			origin[origin.length-1-i]=temp;
		}
		System.out.println(Arrays.toString(origin));
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int [] removeZero(int[] oldArray){
		ArrayList <Integer >array=new ArrayList <Integer>();
		int from;
		int to;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]==0){
				to=i;
				continue;
			}
			array.add(oldArray[i]);
		}
		int [] array2=new int[array.size()];
		for(int i=0;i<array2.length;i++){
			array2[i]=array.get(i);
		}
		return array2;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public int[] merge(int[] array1, int[] array2){
		List <Integer> list=new ArrayList<Integer>();
		for(int i=0;i<array1.length;i++){
			list.add(array1[i]);
		}
		for(int i=0;i<array2.length;i++){
			list.add(array2[i]);
		}
		HashSet <Integer> set=new HashSet<Integer>();
		set.addAll(list);
		Object[] array=set.toArray();
		int [] arrayInt=new int [array.length];
		for(int i=0;i<array.length;i++){
			arrayInt[i]=(int)array[i];
		}
		Arrays.sort(arrayInt);
		return arrayInt;
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
		int length=oldArray.length+size;
		int [] newArray=new int [length];
		System.arraycopy(oldArray,0,newArray,0,oldArray.length);
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
		if(max==1){
			return new int []{};
		}
		int front=1;
		int behind=1;
		int result=0;
		List <Integer>list=new ArrayList<Integer>();
		list.add(front);
		list.add(behind);
		while(true){
			result=front+behind;
			if(max<result){
				break;
			}
			list.add(result);
			front=behind;
			behind=result;
		}
		int [] array=new int [list.size()];
		for(int i=0;i<array.length;i++){
			array[i]=list.get(i);
		}
		return array;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		List<Integer> list=new ArrayList<Integer>();
		int num=3;
		list.add(2);
		boolean flag=true;
		while(true){
			flag=true;
			for(int i=2;i<num;i++){
				if(num%i==0){
					flag=false;
					break;
				}
			}
			if(max<=num){
				break;
			}
			if(flag==true){
				list.add(num);
			}
			num++;
		}
		int [] array=new int [list.size()];
		for(int i=0;i<array.length;i++){
			array[i]=list.get(i);
		}
		return array;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		List <Integer> list=new ArrayList<Integer>();
		Set <Integer> temp=new HashSet<Integer>();
		int num=1;
		int result=0;
		while(true){
			for(int i=1;i<num;i++){
				if(num%i==0){
					temp.add(i);
				}
			}
			for(Integer i:temp){
				result+=i;
			}
			if(num>max){
				break;
			}
			if(num==result){
				list.add(num);
			}
			temp.clear();
			result=0;
			num++;
		}
		int [] array=new int [list.size()];
		for(int i=0;i<array.length;i++){
			array[i]=list.get(i);
		}
		return array;
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
		List <String> list=new ArrayList<String>();
		for(int i=0;i<array.length;i++){
			list.add(new Integer(array[i]).toString());
			if(i==array.length-1){
				break;
			}
			list.add(seperator);
		}
		StringBuilder str=new StringBuilder();
		for(String s:list){
			str.append(s);
		}
		return str.toString();
	}
	
}
