package com.github.HarryHook.coding2017.array;

public class ArrayUtil 
{
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin)
	{
		 for(int i=0, j = origin.length-1; i<j; i++, j--)
		 {
			 int t = origin[i];
			 origin[i] = origin[j];
			 origin[j] = t;
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
	{	//传进空数组是返回空数组
		if(oldArray == null)
			return null;
		int count = 0; //统计非零元素个数
		int b[] = new int[oldArray.length];
		//先统计非零元素个数，并将非零元素存入一个和原数组同样大小的新数组
		for(int i=0; i < oldArray.length; i++)
		{
			if(oldArray[i] != 0)
			{
				b[count++] = oldArray[i];
			}	
		}
		//初始化一个元素个数为非零元素个数的新数组
		int newArray[] = new int[count]; 
		//将非零元素copy到新数组
		System.arraycopy(b, 0, newArray, 0, count);
		
		/*
		 * int k=0;
		for(int i=0; i<oldArray.length; i++)
		{
			if(oldArray[i] != 0)
			{
				newArray[k++] = oldArray[i];   
			}
		}*/
		
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2)
	{   //当array1和array2都为空时，返回空
		if(array1 == null && array2 == null)
			return null;
		int[] newArray = new int[array1.length + array2.length];
		//应该让a1，a2两个数组先进行比较 比较后插入元素
		int i = 0;  //array1下标
		int j = 0;	//array2下标
		int count = 0;	//array3下标
		while(i < array1.length && j < array2.length)
		{
			if(array1[i] < array2[j])
			{
				newArray[count++] = array1[i++];
			}
			else if(array1[i] > array2[j])
			{
				newArray[count++] = array2[j++];
			}
			else if(array1[i] == array2[j])
			{
				newArray[count++] = array2[j++];
				i++;
			}
		}
		while(i==array1.length && j<array2.length)
		{
			newArray[count++] = array2[j++];
		}
		while(j==array2.length && i<array1.length)
		{
			newArray[count++] = array1[i++];
		}
	
		int[] newArray1 = new int[count];
		System.arraycopy(newArray, 0, newArray1, 0, count);
		
		return  newArray1;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 * @throws Exception 
	 */
	public int[] grow(int [] oldArray,  int size)
	{
		if(oldArray == null)
			return null;
		if(size < 0)
			throw new IndexOutOfBoundsException("size小于0");
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
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
		if(max == 1)
			return new int[0];
		//先将max设置为数组长度,但会浪费空间
		int[] a = new int[max];
		a[0] = 1;
		a[1] = 1;
		for(int i=2; i<max; i++)
			a[i] = a[i-1] + a[i-2];
		//再将max与数组中元素进行比较，获得元素节点位置
		int j = 0;
		for(j = 0; j<a.length; j++)
		{
			if(max < a[j])
				break;
		}
		int[] newArray = new int[j];
		System.arraycopy(a, 0, newArray, 0, j);
			
		return newArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max)
	{
		/*
		 * 思路：先生成素数数组，数组的最大值小于max  
		 */
		//max小于3时，返回空数组
		if(max < 3)
			return new int[0];
		int[] Array = new int[max];
		int count = 0;  //
		int n = 0;
		//判断小于max的数有哪些是素数
		for(n = 2; n < max; n++)
		{
			if( count < max)
		    {	
				//判断当前n是不是素数
				int i = 2;
				while(i < n)  
				{
					if(n % i == 0)
						break;
					if(n % i != 0)
						i++;
				}
				if(i == n)
				{	//将素数统计出来
					Array[count++] = n;
				}
			}
		}
		int[] newArray = new int[count];
		System.arraycopy(Array, 0, newArray, 0, count);
		return newArray;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max)
	{
		int[] Array = new int[max];
		
		int n = 0;
		int count = 0;
		int i = 0;
		for(n = 2; n < max; n++)
		{
			int sum = 0;
			for(i=1; i<n; i++)
			{
				if(n%i == 0)
					sum += i;
			}
			if(sum == n)
				Array[count++] = n;
		}
		
		int[] newArray = new int[count];
		System.arraycopy(Array, 0, newArray, 0, count);
		return newArray;
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
		String s = null;
		if(array.length == 0)
			return "";
		s = Integer.toString(array[0]);
		if(array.length > 1)
		{
			s = s + seperator;
			for(int i=1; i<array.length-1; i++)
				s = s + array[i] + seperator;
			s = s + array[array.length -1];
		}	
		return s;
	}
	
 
	public static void main(String[] args)
	{
		/*  reverseArray()
		 * 
		 *
		int[] a = {1, 2,1, 3, 5, 6};
		reverseArray(a);
		for(int i=0; i<a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		*/
		
		
		/* removeZero()
		 * 
		 * 
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5, 7, 8}; 
		int[] a = removeZero(oldArr);
		for(int i=0; i<a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		*/
		
		
		/* merge()
		 * 
		 * 
		 int[] a1 = {3, 5, 7, 8, 9};
		 int[] a2 = {1, 2, 3, 4, 5, 6, 7};
		 int[] a3 = merge(a1, a2);
		 for(int i=0; i<a3.length; i++)
		 {
			 System.out.print(a3[i] + " ");
		 }
		 */
		
		/* grow()
		 * 
		 *
		int[] a = new int[0];//{3, 5, 7, 8, 9};
		int[] newArray = grow(a, -3);
		for(int i=0; i<newArray.length; i++)
		{
			System.out.print(newArray[i] + " ");
		}
		*/
		
		/* fibonacci()
		 *
		int[] array =  fibonacci(35);
		for(int i=0; i<array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		*/
		
		/* getPrimes()
		 *
		int[] array =  getPrimes(2);
		for(int i=0; i<array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		*/
		
		/*  getPerfectNumbers()
		 *
		int[] array =  getPerfectNumbers(10000);
		for(int i=0; i<array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		*/
		
		/*  join()
		 *
		int[] Array = {3, 5, 7, 8, 9};
		int[] array = {};
		String s = join(array, "-");
		System.out.println(s);
		*/
		 
	}


}
