package com.coderising.array;

import java.util.LinkedList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin==null)
		{
			return ;
		}
		int from=0;
		int to=origin.length-1;
		while(from<to)
		{
			int temp=origin[from];
			origin[from]=origin[to];
			origin[to]=temp;
			from++;
			to--;
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
		if(oldArray==null)
		{
			return new int[0];
		}
		int zeroCount=0;
		for(int value:oldArray)
		{
			if(value==0)
				zeroCount++;
		}
		int[] newArray=new int[oldArray.length-zeroCount];
		int index=0;
		for(int value:oldArray)
		{
			if(value!=0)
			{
				newArray[index++]=value;
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
		
		if(array1==null)
		{
			return array2;
		}
		if(array2==null)
		{
			return array1;
		}
		int[] allArray=new int[array1.length+array2.length];
		int index1=0,index2=0,index=0;
		while(index1<array1.length&&index2<array2.length)
		{
			if(array1[index1]<array2[index2])
			{
				allArray[index++]=array1[index1++];
			}else{
				allArray[index++]=array2[index2++];
			}
		}
		if(index1<array1.length)
		{
			for(int i=index1;i<array1.length;i++)
			{
				allArray[index++]=array1[i];
			}
		}
		if(index2<array2.length)
		{
			for(int i=index2;i<array2.length;i++)
			{
				allArray[index++]=array2[i];
			}
		}
		return  allArray;
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
		if(oldArray==null)
		{
			return new int[size];
		}
		int[] newArray=new int[oldArray.length+size];
		for(int i=0;i<oldArray.length;i++)
		{
			newArray[i]=oldArray[i];
		}
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
		if(max<2)
		{
			return new int[0];
		}
		int first=1,second=1;
		int count=2;
		while(true)
		{
			int sum=first+second;
			if(sum<max)
			{
				count++;
				first=second;
				second=sum;
			}else{
				break;
			}
		}
		int[] array=new int[count];
		array[0]=1;
		array[1]=1;
		int index=2;
		while(index<count)
		{
			array[index]=array[index-1]+array[index-2];
			index++;
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
		if(max<3)
			return new int[0];
		//素数筛法
		return null;
	}
	
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		//逐个进行质因数分解
		return null;
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
		if(array==null || array.length<1)
		{
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<array.length;i++)
		{
			sb.append(Integer.toString(i));
			if(i<array.length-1)
			{
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
	

}
