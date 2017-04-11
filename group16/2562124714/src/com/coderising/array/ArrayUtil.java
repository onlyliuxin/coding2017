package com.coderising.array;

import com.*;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if (1 == origin.length || 0 == origin.length)
		{
			return;
		}

		int temp = 0;
		for (int i = 0; i < origin.length / 2; i++)
		{
			temp = origin[i];
			origin[i] = origin[origin.length - 1 - i];
			origin[origin.length - 1 - i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public Integer[] removeZero(int[] oldArray){
		com.coding.basic.ArrayList blist = new com.coding.basic.ArrayList();

		//int j = 0;

		for(int i = 0; i < oldArray.length; i++)
		{
			if (0 != oldArray[i])
			{
				blist.add(oldArray[i]);
			}
		}

		Object[] newArray = blist.ToArray();

		return (Integer[])newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public Integer[] merge(int[] array1, int[] array2){
		com.coding.basic.ArrayList blist = new com.coding.basic.ArrayList();
		int i = 0;

		for (i = 0; i < array1.length; i++)
		{
			blist.add(array1[0]);
		}

		for(i = 0; i < array2.length; i++)
		{
			for (int j = 0; j < blist.size(); j ++)
			{
				if (array2[i] >= (int)blist.get(j + 1))
				{
					if (array2[i] == (int)blist.get(j + 1))
					{
						break;
					}
					//已经到最后了
					if (j == blist.size() - 1)
					{
						if (array2[i] == (int)blist.get(j + 1))
						{
							break;
						}
						else
						{
							blist.add(j + 1, array2[i]);
							break;
						}
					}
					else
					{
						if (array2[i] <= (int)blist.get(j + 2))
						{
							if (array2[i] == (int)blist.get(j + 2))
							{
								break;
							}
							else
							{
								blist.add(j + 1, array2[i]);
								break;
							}
						}
					}

				}
				else
				{
					if (j == 0)
					{
						blist.add(j + 1, array2[i]);
						break;
					}
					else
					{
						continue;
					}
				}
			}
		}

		return  (Integer[]) blist.ToArray();
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
		int[] NewArray = new int[oldArray.length + size];

		for(int i = 0; i < NewArray.length; i++)
		{
			if (i < oldArray.length) {
				NewArray[i] = oldArray[i];
			}
			else
			{
				NewArray[i] = 0;
			}
		}

		return NewArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public Integer[] fibonacci(int max){
		com.coding.basic.ArrayList result = new com.coding.basic.ArrayList();
		int i = 0;
		int TempMax = 0;


		while (true)
		{
			TempMax = CaculateFibonacci(i++);
			if (TempMax <= max)
			{
				result.add(TempMax);
				continue;
			}
			else
			{
				break;
			}
		}

		return (Integer[])result.ToArray();
	}

	public int CaculateFibonacci(int i)
	{
			if (1 == i)
				return 1;
			else if (2 == i)
				return 1;
			else
				return CaculateFibonacci(i - 1) + CaculateFibonacci(i - 2);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public Integer[] getPrimes(int max){
		com.coding.basic.ArrayList result = new com.coding.basic.ArrayList();



		for(int i = 2; i < max; i ++)
		{
				if(CaculatePrimes(i))
				{
					result.add(i);
				}
		}

		return (Integer[])result.ToArray();
	}

	//计算素数函数  算法好像不高明啊！
	public boolean CaculatePrimes(int Num)
	{
		for (int i = 2; i < Math.sqrt(Num); i++)
		{
			if (Num % i == 0)
			{
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
	public Integer[] getPerfectNumbers(int max){
		com.coding.basic.ArrayList result = new com.coding.basic.ArrayList();

		for (int i = 6; i < max; i++)
		{
			if (IsPerfectNumber(i))
			{
				result.add(i);
			}
		}
		return (Integer[])result.ToArray();
	}

	//计算所有的因子之和 算法并不高明啊！
	public boolean IsPerfectNumber(int Num)
	{
		int temp = 0;
		for (int i = 1; i < Num; i++)
		{
			if (Num % i == 0)
			{
				temp += i;
			}
		}
		if (temp == Num)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param
	 * @return
	 */
	public String join(int[] array, String seperator){
		String result = "";

		for (int i = 0; i < array.length - 1; i++)
		{
			result += Integer.toString(array[i])+ seperator;
		}

		result += Integer.toString(array[array.length]);


		return result;
	}
	

}
