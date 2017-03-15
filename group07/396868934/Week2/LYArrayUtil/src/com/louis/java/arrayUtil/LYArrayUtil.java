package com.louis.java.arrayUtil;

import java.util.ArrayList;
import java.util.List;

public class LYArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		if (origin == null) return null;
		int length = origin.length;
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[length - 1 - i] = origin[i];
		}
		return list;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		if (oldArray == null) return null;
		int unZeroCount = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				unZeroCount++;
			}
		}
		int[] array = new int[unZeroCount];
		int index = 0;
		for (int i = 0; i < oldArray.length; i++) {
			int num = oldArray[i];
			if (num != 0) {
				array[index] = num;
				index++;
			}
		}
		return array;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		ArrayList<Integer> result = new ArrayList<Integer>();
		int index1 = 0, index2 = 0;
		while (index1 < array1.length && index2 < array2.length) {
			int num1 = array1[index1];
			int num2 = array2[index2]; 
			if (num1 < num2) {
				result.add(new Integer(num1));
				index1++;
			} else if (num1 > num2) {
				result.add(new Integer(num2));
				index2++;
			} else if (num1 == num2) {
				result.add(new Integer(num1));
				index1++;
				index2++;
			}
		}
		while (index1 < array1.length) {
			result.add(new Integer(array1[index1++]));
		}
		while (index2 < array2.length) {
			result.add(new Integer(array2[index2++]));
		}
		int[] newResult = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			newResult[i] = result.get(i).intValue();
		}
		return newResult;
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
		int[] newArray = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
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
		ArrayList<Integer> array = new ArrayList<Integer>();
		int lastValue = 0;
		for (int i = 1; i < max;) {
			array.add(new Integer(i));
			int temp = i;
			i = i + lastValue;
			lastValue = temp;
		}
		int[] result = new int[array.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = array.get(i).intValue();
		}
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] prime = new int[max + 1];
        ArrayList<Integer> list = new ArrayList<Integer>();
    
        // 两个嵌套循环 ，赋值不是质数
        for (int j = 2; j * j <= max; j++) {
            for (int k = 2*j; k <= max; k++) {
                if (k % j == 0) {
                    //不是质数 数组对应赋值为1
                    prime[k] = 1;
                }
            }
        }
        for (int i = 2; i < max; i++) {
            //因为JAVA数组默认赋值为整数“0”，所以所有是质数的经过上面的嵌套循环数组所对应的值是没有发生的
            if (prime[i] == 0) {
                list.add(new Integer(i));
            }
        }
    //将list 转换为数组返回
        int[] p = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            p[i] = (Integer) list.get(i);
        }
        return p;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return	
	 */
	public int[] getPerfectNumbers(int max){ 
		if(max <= 1)
			throw new RuntimeException("参数错误");
		if(max <= 5){
			int[] result = {};
			return result;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int input = 2; input<max; input++){
			int sum = 1;
			for(int i=2; i<=input/2.0; i++){
				if(input % i == 0){
					sum += i;
				}
			}
			if(sum == input)
				list.add(input);
		}
		int[] result = new int[list.size()];
		for(int i=0; i<list.size(); i++)
			result[i] = list.get(i);
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
	public String join(int[] array, String seperator){
		String s = "";
		for(int i=0; i<array.length-1; i++){
			s = s + (array[i]) + "-"; 
		}
		s = s + array[array.length-1];
		return s;
	}
}
