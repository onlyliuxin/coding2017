package arrayTests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		
		int len = origin.length;
		int[] arr = new int[len];
		
		for(int i = 0; i < len; i++)
		{
			arr[i] = origin[ len -1 - i];
		}
		
		return arr;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		int len = oldArray.length;
		for(int i = 0; i < len; i++)
		{
			if(oldArray[i] != 0)
			{
				al.add(oldArray[i]);
			}
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		

	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		TreeSet<Integer> tr = new TreeSet<Integer>();
		for(int i = 0; i < array1.length; i++)
		{
			tr.add(array1[i]);
		}
		for(int j = 0; j < array2.length; j++)
		{
			tr.add(array2[j]);
		}
		
		int arrLen = tr.size();
		int[] arr = new int[arrLen];
		int index = 0;
		
		Iterator it = tr.iterator();
		while(it.hasNext())
		{
			arr[index] = (int) it.next();
			index++;
		}
		
		return  arr;
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
		
		int len = oldArray.length;
		int arrLen = len + size;
		int[] arr = new int[arrLen];
		
		for(int i = 0; i < arrLen; i++)
		{
			if (i < len)
				arr[i] = oldArray[i];
			else
				arr[i] = 0;
		}
		
		return arr;
		
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		int first = 1;
		int second = 1;
		int value = 0;
		if(max >= 2)
		{
			al.add(first);
			al.add(second);
		}
		do
		{
			value = first + second;
			if(value < max)
			{
				al.add(value);
				first = second;
				second = value;
			}
		}while(value < max);
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		if(max > 2)
			al.add(2);
		
		int value = 3;
		while(value < max)
		{
			int flag = 1;
			for(int i = 2; i < value; i++)
			{
				if(value % i == 0)
				{
					flag = 0;
					break;
				}
			}
			
			if (flag == 1)
				al.add(value);
			
			value++;
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 1; i < max; i++)
		{
			if (isPerfectNumber(i))
				al.add(i);
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
	}
	
	public boolean isPerfectNumber(int number)
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i = 1; i < number; i++)
		{
			if(number % i == 0)
				al.add(i);
		}
		
		int value = 0;
		for(int j = 0; j < al.size(); j++)
		{
			value = value + al.get(j);
		}
		
		return value == number;
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
		
		String str = "";
		int len = array.length;
		for(int i = 0; i < len-1; i++)
		{
			str = str + array[i] + seperator;
		}
		str = str + array[len-1];
		
		return str;
	}
	
	

}
