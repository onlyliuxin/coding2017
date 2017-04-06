package com.donaldy.basic;

import java.util.ArrayList;

public class ArrayUtil {

	private java.util.ArrayList arrayList;
	private int[] newArr;
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		makeSure(origin);
		/*int length = origin.length - 1;
		int [] newArr = new int[length + 1];

		for (int i = 0; i <= length; ++i)
			newArr[i] = origin[length - i];

		for (int i = 0 ; i <= length; ++i)
			origin[i] = newArr[i];*/

		for (int i = 0, j = origin.length - 1; i < j; ++i, --j) {
			int t = origin[i];
			origin[i] = origin[j];
			origin[j] = t;
		}

	}

	private void makeSure(int [] arr) {
		if (arr.length == 0 || arr == null)
			throw new RuntimeException();
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		makeSure(oldArray);
		int length = oldArray.length;
		arrayList = new ArrayList(length);
		for (int i = 0; i < length ; ++i)
			if (oldArray[i] != 0)
				arrayList.add(oldArray[i]);

		return toArray(arrayList.size());
	}

	private int[] toArray(int length) {
		newArr = new int[length];
		for (int i = 0; i < length ; ++i)
			newArr[i] = (int)arrayList.get(i);
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素，
	 * 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    ,
	 * 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2){
		makeSure(array1);
		makeSure(array2);
		int length1 = array1.length;
		int length2 = array2.length;
		arrayList = new ArrayList(length1 + length2);
		int i, j, cnt = 0, temp;
		boolean flag = false;
		for (i = 0, j = 0; i < length1 && j < length2; ) {
			if (array1[i] < array2[j]) {
				temp = array1[i];
				i ++;
			} else {
				temp = array2[j];
				j ++;
			}
			if (flag && temp != (int)arrayList.get(cnt)) {
				arrayList.add(temp);
				cnt ++;
			} else if (!flag){
				arrayList.add(temp);
				flag = true;
			}

		}
		for (i += 1; i < length1; ++i)
			arrayList.add(array1[i]);
		for (j += 1; j < length2; ++j)
			arrayList.add(array2[j]);

		return  toArray(arrayList.size());
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
	public int[] grow(int [] oldArray,  int size) {
		int length = oldArray.length + size;
		newArr = new int[length];
		int i = 0;
		for (; i < oldArray.length ; ++i)
			newArr[i] = oldArray[i];
		for (; i < length; ++i)
			newArr[i] = 0;
		return newArr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		arrayList = new ArrayList();
		arrayList.add(1);
		arrayList.add(1);
		int maxn = 2;
		int i = 1;
		while (maxn < max) {
			arrayList.add(maxn);
			i ++;
			maxn = (int)arrayList.get(i) + (int)arrayList.get(i - 1);
		}
		;
		return toArray(i + 1);
	}

	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		arrayList = new ArrayList();
		boolean prime[] = new boolean[max + 1];
		int p = 0;
		for (int i = 0; i < max; ++i)
			prime[i] = true;
		prime[0] = prime[1] = false;
		for (int i = 2; i < max; ++i) {
			if (prime[i]) {
				arrayList.add(p ++, i);
				for (int j = 2 * i; j < max; j += i)
					prime[j] = false;
			}
		}
		return toArray(arrayList.size());
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		arrayList = new ArrayList();
		int sum;
		for (int i = 1; i < max; ++i) {
			sum = 0;
			for (int j = 1; j < i; ++j) {
				if (i % j == 0)
					sum += j;
			}
			if (sum == i)
				arrayList.add(sum);
		}
		return toArray(arrayList.size());
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public String join(int[] array, String seperator){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(array[0]);
		for (int i = 1; i < array.length; ++i) {
			stringBuilder.append(seperator + array[i]);
		}
		return stringBuilder.toString();
	}
	

}
