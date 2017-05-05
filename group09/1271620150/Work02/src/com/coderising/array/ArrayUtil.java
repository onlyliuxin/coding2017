package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public  void  reverseArray(int[] origin){
		int n = origin.length;
		int temp  =0;
		int halfLength = origin.length/2;
		for (int i = 0; i < halfLength; i++) {
			temp= origin[i];
			origin[i] = origin[n-i-1];
			origin[n-i-1] = temp;
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
		ArrayList<Integer> list  = new ArrayList<>();
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i]!=0){
				list.add(oldArray[i]);
			}
		}
		int[] new_array = new int[list.size()];
		for(int i=0;i<list.size();i++){
			new_array[i]=list.get(i);
		}
		
		return new_array;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < array1.length; i++) {
			set.add(array1[i]);
		}
		for (int i = 0; i < array2.length; i++) {
			set.add(array2[i]);
		}
		Iterator<Integer> i = set.iterator();
		int[] arrays = new int[set.size()];
		int num = 0;
		while (i.hasNext()) {
			int a = (Integer) i.next();
			arrays[num++] = a;
		}
		Arrays.sort(arrays);
		return arrays;
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
		int[] new_array = new int[oldArray.length+size];
		for (int i = 0; i < oldArray.length; i++) {
			new_array[i] = oldArray[i];
		}
		return new_array;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		ArrayList<Integer> list  = new ArrayList<>();
		int a=1,b=1;
		list.add(a);
		list.add(b);
		for(int c=0;c<max;){
			list.add(c);
			c=a+b;
			a=b;
			b=c;
			
		}
		int[] array = new int[list.size()];
		for(int i =0;i<list.size();i++){
			array[i] = list.get(i);
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
		ArrayList<Integer> prime = new ArrayList<Integer>();
        for(int i = 2 ; i < max ;i++){
            boolean sign = true;
            for(int j = 2 ; j < i ;j++){
                if(i%j == 0){
                    sign = false;
                    continue;
                }
            }
            if(sign){
                prime.add(i);
            }
        }
        int[] array = new int[prime.size()];
		for(int i =0;i<prime.size();i++){
			array[i] = prime.get(i);
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
		ArrayList<Integer> perfect = new ArrayList<Integer>();
        for(int i = 1 ; i < max ;i++){
            boolean sign = false;
            for(int j = 1, k=0; j<i; j++){
                if(i%j==0){
                	k=k+j;
                }
                if(i==k){
                	sign = true;
                }
            }
            if(sign){
            	perfect.add(i);
            }
            
        }
        int[] array = new int[perfect.size()];
		for(int i =0;i<perfect.size();i++){
			array[i] = perfect.get(i);
		}
		return array;
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
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(array[i]);
			if(i<array.length-1){
				builder.append(seperator);
			}
		}
		return builder.toString();
	}
	
	

}