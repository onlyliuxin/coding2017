package com.coderising.array;


import java.util.Arrays;
import java.util.TreeSet;
import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int len = origin.length;
		for (int i = 0; i < len / 2; i++) {
			int temp = origin[i];
			origin[i] = origin[len - 1 - i];
			origin[len - 1 - i] = temp;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int count = 0;

		// 创建一个临时数组装没有零的旧数组
		int[] temp = new int[oldArray.length - count];
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				// 如果值为0，统计、跳过不加入新数组
				count++;
				continue;
			} else {
				temp[i - count] = oldArray[i];
			}
		}
		// 定义返回数组的长度
		int len = oldArray.length - count;
		int[] resultArray = new int[len];
		System.arraycopy(temp, 0, resultArray, 0, len);
		return resultArray;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
/*		int[] result = array1;
		// 去除重复元素
		for (int i = 0; i < array2.length; i++) {	
			boolean sameVal = false;
			for (int j = 0; j < array1.length; j++) {
				if (array1[j] == array2[i]) {
					sameVal = true;
					break;
				}
			}
			if(sameVal == false){
				result = grow(result, 1);
				result[result.length-1] = array2[i];
			}			
		}
		//冒泡排序
		for (int i = 0; i < result.length-1; i++) {
			for (int j = i+1; j < result.length; j++) {
				if(result[i]>result[j]){
					int temp = result[i];
					result[i] = result[j];
					result[j] =temp;
				}
			}
		}		
		return result;
*/	
		int len1=0,len2=0;//arr1长度len1
		ArrayList list = new ArrayList();		
		for (int k=0;k < array1.length+array2.length; k++) {
			//如果两个数组都还有元素
			if(len1<array1.length && len2<array2.length){
				if(array1[len1]<array2[len2]){
					list.add(array1[len1]); 
					len1++;
				}else if(array1[len1]>array2[len2]){
					list.add(array2[len2]);
					len2++;
				}else{
					list.add(array1[len1]); 
					len1++;
					len2++;
				}
			}else if(len1==array1.length && len2 < array2.length){
				//如果数组1没有元素,并且2有元素
				list.add(array2[len2]);
				len2++;
			}else if(len2==array2.length && len1 < array1.length){
				//数组2没有元素，并且1有元素
				list.add(array1[len1]); 
				len1++;
			}else{
				break;
			}
		}
		//list转数组
		int[] result = new int[list.size()];
		Iterator it = list.iterator();
		int index = 0;
		while(it.hasNext()){
			result[index++] = ((Integer)it.next()).intValue();
		}
		return result;
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int[] oldArray, int size) {
		int[] resultArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, resultArray, 0, oldArray.length);
		return resultArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if (max <= 1) {
			return new int[0];
		} else {
			int[] temp = new int[max];
			temp[0] = 1;
			temp[1] = 1;
			// 定义返回数组的长度变量
			int len = 2;
			for (int i = 2; i < max; i++) {
				int last = temp[i - 1] + temp[i - 2];
				if (last >= max) {
					break;
				} else {
					temp[i] = last;
					len = i  + 1;
				}
			}
			return Arrays.copyOf(temp, len);
		}
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {

		if (max <= 1) {
			return new int[0];
		} else {
			// 创建临时数组
			int[] temp = new int[max];
			int count = 0;
			// 从零开始遍历到max，如果有是素数就加入临时数组。
			for (int i = 0; i < max; i++) {
				if (isPrimes(i)) {
					temp[i - count] = i;
				} else {
					count++;
				}
			}
			// max -1 -count是最后一个元素索引
			int len = max - count;
			int[] resultArray = Arrays.copyOf(temp, len);
			return resultArray;
		}
	}

	boolean isPrimes(int x) {
		if (x <= 1) {
			return false;
		} else {
			for (int i = 2; i < x; i++) {
				if (x % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		if (max < 1) {
			return new int[0];
		} else {
			ArrayList array = new ArrayList();
			for (int i = 1; i <= max; i++) {
				if (isPerfectNumber(i)) {
					array.add(i);
				}
			}
			int[] result = new int[array.size()];
			Iterator it = array.iterator();
			int index = 0;
			while (it.hasNext()) {
				result[index] = ((Integer) it.next()).intValue();
				index++;
			}
			return result;
		}
	}

	boolean isPerfectNumber(int x) {
		if (x < 1) {
			return false;
		} else {
			int count = 0;
			for (int i = 1; i < x; i++) {
				if (x % i == 0) {
					count += i;
				}
			}
			if (x == count) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if(i == array.length-1){
				sb.append(String.valueOf(array[i]));
				break;
			}
			sb.append(String.valueOf(array[i])+seperator);
		}
		String result = sb.toString();	
		return result;
	}

}
