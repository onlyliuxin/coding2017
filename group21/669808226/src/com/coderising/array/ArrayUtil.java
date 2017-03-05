package com.coderising.array;

import java.util.ArrayList;

import java.security.InvalidParameterException;

public final class ArrayUtil {
	
	private ArrayUtil(){}
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) throws NullPointerException
	{
		check(origin);
		int len = origin.length;
		if(len == 0)
			return;
		int[] temp = new int[len];
		for(int i = 0; i < len; ++i)
		{
			temp[i] = origin[len-i-1];
		}
		System.arraycopy(temp, 0, origin, 0, len);
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray) throws NullPointerException
	{
		check(oldArray);
		int len = oldArray.length;
		int[] temp= new int[len];
		int size = 0;
		if(len == 0)
			return temp;
		for(int i=0; i<len; ++i)
		{
			if(oldArray[i] != 0)
			{
				temp[size++] = oldArray[i];
			}
		}
		int[] result = new int[size];
		System.arraycopy(temp, 0, result, 0, size);
		return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2) throws NullPointerException
	{
		check(array1);
		check(array2);
		int len1 = array1.length;
		int len2 = array2.length;
		if(len1 == 0)
			return array2;
		if(len2 == 0)
			return array1;
		int index1 = 0, index2 = 0, size = len1 + len2;
		int[] temp = new int[size];
		for(int i=0; i< size; ++i)
		{
			if(index1  == len1)
			{
				System.arraycopy(array2, index2, temp, i, len2 - index2);
				break;
			}
			else if(index2 == len2)
			{
				System.arraycopy(array1, index1, temp, i, len1-index1);
				break;
			}
			int result= Math.min(array1[index1], array2[index2]);
			temp[i] = result;
			int difference= array1[index1] - array2[index2];
			index1 = difference <= 0 ? index1 + 1 : index1;
			index2 = difference >= 0 ? index2 + 1 : index2;
			size = difference == 0 ? size - 1 : size;
		}
		int[] result = new int[size];
		System.arraycopy(temp, 0, result, 0, size);
		return  result;
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
	public static int[] grow(int [] oldArray,  int size) throws NullPointerException
	{
		check(oldArray);
		int len = oldArray.length;
		int[] result = new int[len + size];
		System.arraycopy(oldArray, 0, result, 0, len);
		return result;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max) throws InvalidParameterException
	{
		if(max < 1)
			throw new InvalidParameterException("参数不能小于1");
		if(max == 1)
			return new int[]{};
		int size = 0;
		int[] temp = new int[max];
		for(int i=0; i<max; ++i)
		{
			int n = fibonacci1(i+1, 3, 1, 1);
			if(n < max)
			{
				temp[size++] = n;
			}
			else
				break;
		}
		int[] result = new int[size];
		System.arraycopy(temp, 0, result, 0, size);
		return result;
	}
	
	/*
	 * 求解第n个斐波那契数
	 */
	private static int fibonacci1(int n, int counter, int oneBeforeResult, int twoBeforeResult) 
	{
		if(n<=0)
			return 0;
		else if(n<=2)
			return 1;
		else
		{
			if(counter == n)
				return oneBeforeResult + twoBeforeResult;
			int tmp = oneBeforeResult + twoBeforeResult;
			twoBeforeResult = oneBeforeResult;
			oneBeforeResult = tmp;
			return fibonacci1(n, ++counter, oneBeforeResult, twoBeforeResult);
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max)
	{
		if(max < 2)
			return new int[]{};
		int[] temp = new int[max];
		int size = 0;
		for(int i=2; i<max; ++i)
		{
			//如果是素数
			if(getFactorsSum(i) == 1)
			{
				temp[size++] = i;
			}
		}
		int[] result = new int[size];
		System.arraycopy(temp, 0, result, 0, size);
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		if(max < 6)
			return new int[]{};
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=6; i<max; ++i)
		{
			if(getFactorsSum(i) == i)
			{
				list.add(new Integer(i));
			}
		}
		int[] result = new int[list.size()];
		for(int j=0; j<list.size(); ++j)
		{
			result[j] = list.get(j).intValue();
		}
		return result;
	}
	
	private static int getFactorsSum(int num)
	{
		int result = 1;
		int loop= num / 2;
		for(int i=2; i<=loop; ++i)
		{
			if(num % i == 0)
				result += i;
		}
		return result;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) throws NullPointerException
	{
		check(array);
		int len= array.length;
		if(len == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(array[0]);
		for(int i=1; i<len; ++i)
		{
			sb.append(seperator);
			sb.append(array[i]);
		}
		return sb.toString();
	}
	
	private static void check(Object param) throws NullPointerException
	{
		if(param == null)
		{
			throw new NullPointerException("参数不能为null");
		}
	}
	

}
