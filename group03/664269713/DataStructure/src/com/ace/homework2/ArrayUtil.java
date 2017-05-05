package com.ace.homework2;

import java.util.Arrays;

import com.ace.coding.ArrayList;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
        for(int i = 0; i < origin.length/2; i++){
            int temp = origin[i];
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
	
	public int[] removeZero(int[] oldArray){
		int newLength = 0;
		for(int i:oldArray){
			if(i != 0){
				newLength++;
			}
		}
		int[] newArray = new int[newLength];
		int newArrayIndex = 0;
		for(int j = 0; j < oldArray.length; j++){
			if(oldArray[j] != 0){
				newArray[newArrayIndex] = oldArray[j];
				newArrayIndex++;
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
		ArrayList arrayList = new ArrayList();
		for (int i = 0; i < array1.length; i++) {
			arrayList.add(array1[i]);
		}
		for (int j = 0; j < array2.length; j++) {
			if(!arrayList.contains(array2[j])){
				arrayList.add(array2[j]);
			}
		}
		int[] newArray = new int[arrayList.size()];
		for (int k = 0; k < newArray.length; k++) {
			newArray[k] = (int)arrayList.get(k);
		}
		Arrays.sort(newArray);
		return newArray;
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
		int newLength = oldArray.length + size;
		int[] newArray = new int[newLength];
		for(int i = 0; i < newLength; i++){
			if(i < oldArray.length){
				newArray[i] = oldArray[i];
			} else {
				newArray[i] = 0;
			}
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
		int num = 1;
		ArrayList arrayList = new ArrayList();
		while(getFibonacci(num) < max){
			arrayList.add(getFibonacci(num));
			num++;
		}
		int[] newArray = toArray(arrayList);
		return newArray;
	}
	
	private int getFibonacci(int num){
		if(num == 1){
			return 1;
		} else if(num == 2){
			return 1;
		} else {
			return getFibonacci(num-1) + getFibonacci(num-2);
		}
	}
	
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList arrayList = new ArrayList();
		arrayList.add(2);
		for(int i = 3; i <= max; i++){
			int temp = (int)Math.sqrt(i)+1;
			for(int j = 2; j <= temp; j++){
				if(i % j ==0){
					break;
				}
				if(j == temp){
					arrayList.add(i);
				}
			}
		}
		int[] newArray = toArray(arrayList);
		return newArray;
	}
	
	
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList arrayList = new ArrayList();
		for (int i = 2; i <= max; i++) {
			if(getPerfectNumber(i)){
				arrayList.add(i);
			}
		}
		int[] newArray = toArray(arrayList);
		return newArray;
	}
	
	public boolean getPerfectNumber(int num){
		ArrayList arrayList = new ArrayList();
		for(int i = 1; i <= num/2 ; i++){
			if(num % i == 0){
				arrayList.add(i);
			}
		}
		int sum = 0;
		for(int j = 0; j < arrayList.size(); j++){
			sum = sum + (int)arrayList.get(j);
		}
		return sum == num;
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
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < array.length; i++){
			if(i<array.length - 1){
				stringBuilder.append(array[i]+seperator);
			} else {
				stringBuilder.append(array[i]);
			}
		}
		return stringBuilder.toString();
	}
	
	public void printArray(int[] origin){
		for (int i = 0; i < origin.length; i++) {
            if(i < origin.length - 1){
                System.out.print(origin[i] + ", ");
            } else {
                System.out.println(origin[i]);
            }
        }
	}
	
	private int[] toArray(ArrayList arrayList){
		int[] newArray = new int[arrayList.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = (int)arrayList.get(i);
		}
		return newArray;
		
	}

}