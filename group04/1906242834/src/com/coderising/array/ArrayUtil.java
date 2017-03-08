package com.coderising.array;
import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int temp;
		for (int i=0;i<origin.length/2;i++) {
			temp = origin[i];
			origin[i] = origin[origin.length-i-1];
			origin[origin.length-i-1] = temp;
		}
		for(int i=0; i<origin.length;i++){
			System.out.print(origin[i]+",");
		}
		System.out.print("\n");
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int count = 0;
		for(int i=0;i<oldArray.length; i++){
			if(oldArray[i]!=0){
				count += 1;
			}
		}
		int [] newArray = new int [count];
		int i = 0;
		for(int j=0;j<oldArray.length;j++){
				if(oldArray[j]!=0){
					newArray[i] = oldArray[j];
					i += 1;
			}
			
		}
		for (int a : newArray) {
			System.out.print(a+",");
			
		}	
		System.out.print("\n");
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
		//构造一个合并后无排序的包含重复元素的数组
		int length = array1.length + array2.length;
		int[] array_merge = new int[length];
		for (int i = 0; i < array1.length; i++) {
			array_merge[i] = array1[i];
		}
		for (int i = array1.length; i < array_merge.length; i++) {
			array_merge[i] = array2[i-array1.length];
		}
		//打印合并后无序数包含重复元素的数组
		System.out.print(array_merge[0]);
		for (int j = 0; j < array_merge.length; j++) {
			System.out.print(","+array_merge[j]);
		} 
		System.out.print("\n");
		//将合并的元素去重复
		Set<Integer> x = new HashSet<>();
		for (int i = 0; i < array_merge.length; i++) {
			x.add(array_merge[i]);
		}
		
		Integer[] arr = x.toArray(new Integer[x.size()]);
		//进行排序，采用插入排序
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i]>arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		int[] array_merger = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			array_merger[i] = arr[i].intValue();
		}
		for (int i : array_merger) {
			System.out.println(i);
		}
		return array_merger;
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
		//确定数组的长度
		int length = oldArray.length + size;
		int[] newArray = new int [length];
		//对数组的原有元素进行赋值
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
		//对扩容的元素进行赋值为0的操作
		for (int i = oldArray.length; i < newArray.length; i++) {
			newArray[i] = 0;
		}
		//打印数组
		System.out.print(newArray[0]);
		for (int j = 1; j < newArray.length; j++) {
			System.out.print(","+newArray[j]);
		}
		System.out.print("\n");
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
		int a = 1, b = 1, count = 0;
		int c = 2;
		while(c<max){
			c = a + b;
			a = b;
			b = c;
			count += 1;
		}
		System.out.println(count);
		int [] array = new int [count+1];
		if (max == 1) {
			return null;
		} else {
			array[0] = 1;
			array[1] = 1;
			for (int i = 2; i < array.length; i++) {
				array[i] = array[i-1] + array[i-2];
			}
			for (int i : array) {
				System.out.println(i);
			}
			return array;
		}
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int number = 2, count = 0, b = 0;
		//获取列表的长度
		while(number<max){
			boolean a = true;
			for (int i = 2; i < number; i++) {
				if(number%i==0){
					a = false;
				}
			}
			if(a == true){
				count += 1;
			}
			number += 1;
		}
		System.out.println(count);
		//给列表中的元素进行赋值
		int [] array = new int [count];
		int counter = 0, num=2;
		while (counter<count) {
			boolean a = true;
			for (int i = 2; i < num; i++) {
				if(num%i==0){
					a = false;
				}
			}
			if(a == true){
				array[counter] = num;
				counter += 1;
			}
			num ++;
		}
		for (int i : array) {
			System.out.println(i);
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
		//先计算出小于max的所有完数的个数
		int count=0, number=1, sum=0;
		while(number<max){
			for (int i = 1; i < number; i++) {
				if(number%i==0){
					sum += i;
				}
			}
			if(sum==number){
				count += 1;
			}
			sum = 0;
			number += 1;	
		}
		int[] perfect_array = new int[count];
		int index = 0, num=1, sum1;
		while(num<max){
			
			sum1 = 0;
			for (int i = 1; i < num; i++) {
				if(num%i==0){
					sum1 += i;
				}
			}
			
			if(sum1==num){
				perfect_array[index] = num;
				index+=1;
			}
			num += 1;
		}
		for (int i : perfect_array) {
			System.out.println(i);
		}
		return perfect_array;
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
		String[] str = new String[array.length];
		for (int i = 0; i < str.length; i++) {
			str[i] = String.valueOf(array[i]);
		}
		String a=str[0];
		for (int i = 1; i < str.length; i++) {
			a = a + seperator+str[i];
		}
		System.out.print(a+"\n");
		return a;
	}
}

class ArrayTest{
	public static void main(String[] args){
		ArrayUtil array = new ArrayUtil();
		int []  list = {7, 9, 30, 3, 4};
		array.reverseArray(list);
		System.out.println("----reverseArray---end----");
		
		ArrayUtil oldArray = new ArrayUtil();
		int []  arrays = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		oldArray.removeZero(arrays);
		System.out.println("----removeZero-----end----");
		
		ArrayUtil array_merge = new ArrayUtil();
		int []  array1 = {3, 5, 7, 8};
		int []  array2 = {1, 3, 10, 100};
		array_merge.merge(array1, array2);
		System.out.println("----merge----------end----");
		
		
		ArrayUtil growArray = new ArrayUtil();
		int []  garray = {1,3,4};
		growArray.grow(garray, 10);
		System.out.println("----grow-----------end---");
		
		
		ArrayUtil fib = new ArrayUtil();
		fib.fibonacci(100);
		System.out.println("----fib------------end---");
		
		ArrayUtil sushu = new ArrayUtil();
		sushu.getPrimes(100);
		System.out.println("----getPrimes------end----");
		
		ArrayUtil perfectnumber = new ArrayUtil();
		perfectnumber.getPerfectNumbers(30);
		System.out.println("----getPerfectNumbers---end-----------");
		
		ArrayUtil string_format = new ArrayUtil();
		int[] a = {1,2,3,4,5,6,7,45,45,768};
		string_format.join(a, "$");
		System.out.println("----string_format------end---");
		
	}
}