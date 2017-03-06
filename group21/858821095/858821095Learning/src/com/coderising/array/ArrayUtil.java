package com.coderising.array;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 * origin = {7, 9, 30, 3, 4, 8};
	 */
	public int[] reverseArray(int[] origin){
		int len = origin.length;
		for(int i=0; i<Math.floor(len/2);i++){
			int temp = origin[i];
			origin[i] = origin[len-i-1];
			origin[len-i-1] = temp;
		}
		return origin;
		
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int newlen = 0;
		for(int oldindx = 0;oldindx < oldArray.length;oldindx++){
			if(oldArray[oldindx] != 0)
				newlen++;
		}
		int[] newArray = new int[newlen];
		int newindx = 0;
		for(int oldindx = 0;oldindx < oldArray.length;oldindx++){
			if(oldArray[oldindx] != 0)
				newArray[newindx++] = oldArray[oldindx];
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
		int arr1len = array1.length;
		int arr2len = array2.length;

		
		int newlen = arr1len + arr2len;
		int[] newArray = new int[newlen];
		
		int temp = 0, i = 0, j = 0;
		for(;i < arr1len && j < arr2len;){
				if(array1[i] < array2[j])
					newArray[temp++] = array1[i++];
				else if(array1[i] > array2[j])
					newArray[temp++] = array2[j++];
				else{
					newArray[temp++] = array2[j++];
					i++;
				}
		}
		
		while(i < arr1len)
			newArray[temp++] = array1[i++];
			
		while(j < arr2len)
			newArray[temp++] = array2[j++];

		int count = 0;
		for(int t = newlen - 1; t >= 0; t--){
			if(newArray[t] == 0)
				count++;
		}
	
		int[] newarr = new int[newlen - count];
		for(int t = 0; t<newlen-count;t++){
			newarr[t] = newArray[t];
		}
		return newarr;
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
		int oldlen = oldArray.length;
		int newlen = oldlen + size;
		int[] newArray = new int[newlen];
		for(int i = 0; i< newlen; i++){
			if(i < oldlen)
				newArray[i] = oldArray[i];
			else
				newArray[i] = 0;
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
		if(max <= 1)
			return null;
		int n=0;
		while(fib(n) < max){
			n++;
		}
		
		int[] array = new int[n];
		for(int i = 0; i < n; i++){
			array[i] = fib(i);
		}
		return array;
		
	}
	
	public int fib(int n){
		if(n < 0)
			return -1;
		if(n == 0 || n == 1)
			return 1;
		return  fib(n-1) + fib(n-2);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int len = 0;
		for(int i = max - 1; i > 1; i--){
			if(Primes(i))
				len++;
		}
		
		int[] array = new int[len];
		int j = 0;
		for(int i = max - 1; i > 1; i--){
			if(Primes(i))
				array[j++] = i;
		}
		return array;
	}
	
	public boolean Primes(int num){
		if(num <= 1)
			return false;
		int n = (int) Math.ceil(num / 2);
		for(int i = 0; i <= n; i++){
			if((num % i) == 0)
				return false;
		}
		return true;
	}
	
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		
		int len = 0;
		for(int i = max -1; i > 0; i++){
			if(PerfectNumber(i))
				len++;
		}
		
		int[] array = new int[len];
		int j = 0;
		for(int i = max -1; i > 0; i++){
			if(PerfectNumber(i))
				array[j] = i;
		}
		return array;
	}
	public boolean PerfectNumber(int num){
		
		if(num < 1)
			return false;
		int sum = 0;
		int n = (int) Math.ceil(num / 2);
		for(int i = 1; i <= n; i++){
			if((num % i) == 0)
				sum += i;
		}
		
		if(sum != num)
			return false;
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
	public String join(int[] array, String seperator){
		String str = "";
		for(int i = 0; i< array.length; i++){
			if(i != array.length - 1)
				str = str + array[i] + seperator;
			else
				str = str + array[i];
		}
		return str;
	}
	

}

