package com.nitasty.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayUtil {
	
	
	public static void main(String[] args) {
		ArrayUtil util=new ArrayUtil();
		
		int[] origin={7, 9 ,10, 30, 3};
		util.reverseArray(origin);
		int[] oldArray={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] a1={3, 5,7,9,10};
		int[] a2={4,6,100,111,132}; 
		
		System.out.println(Arrays.toString(origin));
		System.out.println(Arrays.toString(util.removeZero(oldArray)));
		System.out.println(Arrays.toString(util.merge(a1,a2)));
		System.out.println(Arrays.toString(util.grow(a1,3)));
		System.out.println(Arrays.toString(util.fibonacci(100)));
		System.out.println(Arrays.toString(util.getPrimes(100)));
		System.out.println(Arrays.toString(util.getPerfectNumbers(1000)));
		System.out.println(util.join(oldArray,"--"));
	}
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int dataBus;
		int len=origin.length;
		for(int i=0;i<len>>1;i++){
			dataBus=origin[i];
			origin[i]=origin[len-i-1];
			origin[len-i-1]=dataBus;
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
		int[] arrayBus=new int[oldArray.length];
		int count = 0;
		//利用中间数值来剔除0
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i]!=0)
				arrayBus[count++]=oldArray[i];
		}
		//返回新的数值
		int[] newArray=new int[count];
		System.arraycopy(arrayBus, 0, newArray, 0, count);
		
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	//插入排序挺快的
	public int[] merge(int[] array1, int[] array2){
		int len1=array1.length;
		int len2=array2.length;
		List<Integer> longList=new ArrayList<Integer>();
		List<Integer> shortList=new ArrayList<Integer>();
		//数组转list，有没有简单的方法啊我擦
		if(len1>len2){
			for (int i = 0; i < len1; i++) {
				longList.add(array1[i]);
			}
			for (int i = 0; i < len2; i++) {
				shortList.add(array2[i]);
			}
		}else{
			for (int i = 0; i < len1; i++) {
				shortList.add(array1[i]);
			}
			for (int i = 0; i < len2; i++) {
				longList.add(array2[i]);
			}
		}
		
		//将短list中的值插入长list中
		int j=0;
		for (int i = 0; i < longList.size(); i++) {
			if(j==shortList.size())
				continue;
			if((Integer)shortList.get(j)<(Integer)longList.get(i)){
				longList.add(i, shortList.get(j));
				j++;
			}else if(((Integer)shortList.get(j)).equals((Integer)longList.get(i))){
				continue;
			}else{
				if(i==(longList.size()-1)){
					longList.add(shortList.get(j));
					j++;
				}
			}
		}
		
		//list再转数组···阿西吧
		int[] intArray=new int[longList.size()];
		
		for (int i = 0; i < longList.size(); i++) {
			intArray[i]=longList.get(i);
		}
		
		return intArray;
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
		int len=oldArray.length;
		int[] newArray=new int[len+size];
		
		System.arraycopy(oldArray, 0, newArray, 0, len);
		
		for(int i=len;i<len+size;i++){
			newArray[i]=0;
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
		if (max==1)
				return null;
		
		int a1=1;
		int a2=1;
		int count=1;
		int dataBus=0;
		while(a2<max){
			dataBus=a2;
			a2+=a1;
			a1=dataBus;
			count++;
		}
		int[] newArray=new int[count];
		newArray[0]=newArray[1]=1;
		for (int i = 2; i < count; i++) {
			newArray[i]=newArray[i-1]+newArray[i-2];
		}
		return newArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		List<Integer> list=new ArrayList<Integer>();
		for (int i = 1; i < max; i++) {
			if(isPrimeNumber(i))
				list.add(i);
		}
		
		int[] intArr=new int[list.size()];
		for (int i = 0; i < intArr.length; i++) {
			intArr[i]=list.get(i);
		}
		return intArr;
	}
	
	private boolean isPrimeNumber(int n)
	{
	    if (n==2)
	    {
	        return true;
	    }
	 
	    if (n%2==0)
	    {
	        return false;
	    }
	 
	    int sqrtn=(int)Math.sqrt((double)n);
	    boolean flag=true;
	 
	    for (int i=3;i<=sqrtn;i+=2)
	    {
	        if (n%i==0)
	        {
	            flag=false;
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
	public int[] getPerfectNumbers(int max){
		
		List<Integer> list=new ArrayList<Integer>();
		
		for (int i = 1; i < max; i++) {
			int sum=0;
			for (int j = 1; j < i; j++) {
				if(i%j==0)
					sum+=j;
			}
			if(sum==i)
				list.add(i);
		}
		
		int[] intArr=new int[list.size()];
		for (int i = 0; i < intArr.length; i++) {
			intArr[i]=list.get(i);
		}
		return intArr;
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
		StringBuffer buff=new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buff.append(array[i]);
			if(i<array.length-1)
				buff.append(seperator);
		}
		return buff.toString();
	}
	

}
