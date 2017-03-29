package com.coderising.array;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin)
	{
		int[] a = origin;
		for (int i = 0; i < a.length; i++)
		{
			origin[i] = a[a.length-i];
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray)
	{
		ArrayList<Integer> a= new ArrayList<Integer>();
		
		for (int i : oldArray)
		{
			if (i != 0)
			{
				a.add(i);
			}
		}
		
		int[] newArray = new int[a.size()];
		for (int i = 0; i < a.size(); i++)
		{
			newArray[i] = a.get(i);
		}
		return null;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2)
	{
		for (int i = 0; i < array1.length; i++)
		{
			for (int j = 0; j < array2.length; j++)
			{
				if(array1[i] == array2[j])
				{
					array2[j] = 0;
				}
			}
		}
		
		removeZero(array2);
		
		int[] array3 = new int[array1.length + array2.length];
		
		for (int i = 0; i < array1.length; i++)
		{
			array3[i] = array1[i];
		}
		
		for (int i = 0; i < array2.length; i++)
		{
			array3[array1.length + i] = array2[i];
		}
		
		Arrays.sort(array3);
		return  array3;
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
	public int[] grow(int [] oldArray,  int size)
	{
		return new int[oldArray.length + size];
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max)
	{
		int top = 1;
		int sec = 1;
		ArrayList<Integer> tem = new ArrayList<Integer>();
		while (top < max)
		{
			tem.add(top);
			int a = top;
			top += sec;
			sec = a;
		}
		
		
		return toArray(tem);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max)
	{
		ArrayList<Integer> tem = new ArrayList<Integer>();
		for (int i = 0; i < max; i++)
		{
			if (isPrimes(i))
			{
				tem.add(i);
			}
		}
		
		return toArray(tem);
	}
	
	private boolean isPrimes(int i)
	{
		return true;
	}
	
	private int[] toArray(ArrayList<Integer> tem)
	{
		int[] newArr = new int[tem.size()];
		for (int i = 0; i < newArr.length; i++)
		{
			newArr[i] = tem.get(i);	
		}
		return newArr;
	}
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max)
	{
		ArrayList<Integer> tem = new ArrayList<Integer>();
		for (int i = 0; i < max; i++)
		{
			if (isPerfectNumbers(i))
			{
				tem.add(i);
			}
		}
		
		return toArray(tem);
	}
	
	
	private boolean isPerfectNumbers(int i)
	{
		return true;
	}
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator)
	{
		String newStr = "";
		for (int i = 0; i < array.length; i++)
		{
			if(i == array.length-1)
			{
				seperator = "";
			}
			newStr += array[i] + seperator;
		}
		return newStr;
	}
	
}
