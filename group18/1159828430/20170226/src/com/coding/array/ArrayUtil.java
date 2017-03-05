package com.coding.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Scholar
 * @Time：2017年2月27日 下午8:46:07
 * @version 1.0
 */
public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return 
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		for (int i = 0; i < origin.length/2; i++) {
			int temp = origin[i];
			origin[i] = origin[origin.length-i-1];
			origin[origin.length-i-1] = temp;
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
	
	public static int[] removeZero(int[] oldArray){
		//正常方式
		/*List<Integer> list = new ArrayList<Integer>();
		for (int i : oldArray) 
			if (i != 0) 
				list.add(i);
		
		int[] newArray = new int[list.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = list.get(i);
		}*/
		//jdk1.8
		IntStream intStream = Arrays.stream(oldArray);
		int[] newArray = intStream.filter(i -> i != 0).toArray();
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int lena = array1.length;
		int lenb = array2.length;
		int[] newArray = new int[lena + lenb];
		int i = 0, j = 0, k = 0;// 分别代表数组a ,b , c 的索引
		
		while (i < array1.length && j < array2.length)
			if (array1[i] <= array2[j]) {
				if (k == 0 || newArray[k - 1] != array1[i]) // 去重复
					newArray[k++] = array1[i];
				i++;
			} else {
				if (k == 0 || newArray[k - 1] != array2[j]) // 去重复
					newArray[k++] = array2[j];
				j++;
			}

		while (i < array1.length) {
			if (k == 0 || newArray[k - 1] != array1[i]) // 去重复
				newArray[k++] = array1[i];
			i++;
		}
		while (j < array2.length) {
			if (k == 0 || newArray[k - 1] != array2[j]) // 去重复
				newArray[k++] = array2[j];
			j++;
		}
		newArray = removeZero(newArray);
		return  newArray;
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
	public static int[] grow(int [] oldArray,  int size){
		oldArray = Arrays.copyOf(oldArray, oldArray.length + size);
		return oldArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int[] fibonacci(int max){
		List<Integer> list = new ArrayList<Integer>();
		if (max == 1) {
			int[] newArr = null;
			return newArr;
		} else {
			for (int i = 0; i <= max; i++) {
				int value = fibonacciSequence(i);
				if (value < max) {
					list.add(value);
				} else {
					break;
				}
			}
		}
		int[] newArray = list.stream().mapToInt(i -> i).toArray();
		return newArray;
	}
	
    private static int fibonacciSequence(int n){  
        if(n < 2){  
            return 1;  
        }else{  
            return fibonacciSequence(n-1) + fibonacciSequence(n-2);  
        }  
    } 
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	@SuppressWarnings("unused")
	public static int[] getPrimes(int max){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i < max; i++) {
			list.add(i);
			for (int j = 2; j <= Math.sqrt(i); j++) {  
                if (i % j == 0){
                	Integer temp = i;
                	list.remove(temp);
                    break;
                }  
            }
		}
		
		int[] newArray = list.stream().mapToInt(i -> i).toArray();
		return newArray;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		List<Integer> list = new ArrayList<Integer>();
		int[] newArray = null;
		if (!(max < 6)) {//第一个完数为6 
			for (int i = 6; i < max; i++) {
				int sum = 0;
				for (int j = 1; j <= i/2; j++) {
					if (i % j == 0) {
						sum += j;
					}
				}
				if (sum == i) {
					list.add(i);
				}
				System.out.println(i);
			}
			newArray = list.stream().mapToInt(i -> i).toArray();
		}
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
	public static String join(int[] array, String seperator){
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			str.append(array[i]);
			if(i != array.length - 1){
				str.append(seperator);
			}
		}
		return str.toString();
	}
	
}

