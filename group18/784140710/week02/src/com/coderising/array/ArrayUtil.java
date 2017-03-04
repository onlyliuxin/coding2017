package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayUtil {
	
	@Test
	public void test01(){
//		int[] a = {7,89,30,3};
//		int[] b = {7,89,30,3,4};
//		reverseArray(b);
		
//		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
//		oldArr = removeZero(oldArr);
//		System.out.println(Arrays.toString(oldArr));
		
//		 int[] a1 = {3, 5, 7,8};
//		 int[] a2 = {4, 5, 6,7};
//		 System.out.println(Arrays.toString(merge(a1,a2)));
		
//		int[] oldArray = {2,3,6};
//		System.out.println(Arrays.toString(grow(oldArray,3)));
		
		fibonacci(3);
		
		
		
//		int max = 23;
//		getPrimes(23);
		
		
//		int[]array= {3,8,9};
//		String seperator = "-";
//		System.out.println(join(array,seperator));
		
	}
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin==null){
			return;
		}
		int[] tmp = new int[origin.length];
		for(int i=0;i<origin.length;i++){
			tmp[origin.length-1-i]=origin[i];
		}
		System.out.println(Arrays.toString(tmp));
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int index = 0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				index++;
			}
		}
		int[] newArray = new int[index];
		index = 0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				newArray[index]=oldArray[i];
				index++;
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
		
		int[] array3 = new int[array1.length+array2.length];
		int a=0;
		int b=0;
		int i=0;
		while(i<array3.length){
			
			if(a==array1.length){
				System.arraycopy(array2, b, array3, i, array2.length-b+1);
				array3=removeZero(array3);
				return array3;
			}
			if(b==array2.length){
				System.arraycopy(array1, a, array3, i, array1.length-b+1);
				array3=removeZero(array3);
				return array3;
			}
			if(array1[a]<array2[b]){
				array3[i++]=array1[a];
				a++;
			}else if(array1[a]>array2[b]){
				array3[i++]=array2[b];
				b++;
			}else{
				array3[i++]=array1[a];
				a++;
				b++;
			}
		}
		array3=removeZero(array3);
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
	public int[] grow(int [] oldArray,  int size){
		 int[] newArray = Arrays.copyOf(oldArray, oldArray.length+size);
		
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
			return new int[0];
		}
		List<Integer> list = new ArrayList<Integer>();
		int i=1;
		int j=i;
		while(j<max){
			list.add(i);
			list.add(j);
			i=i+j;
			j=i+j;
			
		}
		list.add(i);
		return null;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		boolean flag = true;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=2;i<max;i++){
			int j;
			for(j=2;j<i;j++){
				if(i%j==0){
					flag =false;
					break;
				}
			}
			if(flag){
				list.add(i);
			}
			flag=true;
		}
		
		return null;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		
		
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
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++){
			if(i==array.length-1){
				sb.append(array[i]);
			}else{
				sb.append(array[i]+seperator);
			}
		}
		
		return sb.toString();
	}
	

}
