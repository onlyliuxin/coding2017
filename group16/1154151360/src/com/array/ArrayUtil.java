package com.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		
		int [] temp = new int [origin.length];
		
		for (int i = 0; i < temp.length; i++){
			temp [i] = origin [temp.length - 1 - i];
		}
		
		origin = temp;
		
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
		
		int newLength = 0;
		
		for (int i = 0; i < oldArray.length ; i++){
			if (oldArray[i] == 0)
				newLength ++;
		}
		
		int [] newArray = new int [oldArray.length - newLength];
		int index = 0;
		for (int j = 0; j < oldArray.length; j++){
			if (oldArray[j] != 0)
				newArray[index++] = oldArray[j];
		}
		
		newArray = sort(newArray, 0, newArray.length - 1);
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
		
		int newLength = array1.length + array2.length;
		int [] newArray = new int [newLength];
		System.arraycopy(array1, 0, newArray, 0, array1.length);
		System.arraycopy(array2, 0, newArray, array1.length, array2.length);
		Set<Integer> arraySet = new HashSet<Integer>();
		for (int i = 0; i < newArray.length; i++){
			arraySet.add(newArray[i]);
		}
		int [] tempArray = new int[arraySet.size()];
		Iterator<Integer> iterator = arraySet.iterator();
		int index = 0;
		while (iterator.hasNext()){
			tempArray[index++] = iterator.next();
		}
		newArray = sort(tempArray, 0, tempArray.length - 1);
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
	public int[] grow(int [] oldArray,  int size){
		int [] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, size);
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
		if (max > 1){
			List<Integer> array = new ArrayList<Integer>();
			array.add(1);
			array.add(1);
			for(int i = 2;;i++){
				array.add(array.get(i - 1) + array.get(i - 2));
				if (array.get(i) > max){
					array.remove(i);
					break;
				}
			}
			int [] result = returnArray(array);
			return result;
		}else{
			int [] b = new int[]{};
			return b;
		}
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		for (int i = 2; i < max; i++){
			list1.add(i);
		}
		
		for (int m = 0; m < list1.size(); m++){
			boolean flag = true;
			for (int n = 2; n < list1.get(m); n++){
				if (list1.get(m) % n == 0){
					flag = false;
					break;
				}
				
			}
			if (flag)
				list2.add(list1.get(m));
		}
		
		int [] result = returnArray(list2);
		
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList<Integer> array = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> arrayMap = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		for (int i = 2; i < max; i++){
			array.add(i);
		}
		for(int m = 0; m < array.size(); m++){
			ArrayList<Integer> tempArray = new ArrayList<Integer>();
			for (int n = 2; n < array.get(m);n++){
				if (array.get(m) % n == 0)
					tempArray.add(n);
			}
			arrayMap.put(array.get(m), tempArray);
		}
		for(Map.Entry<Integer, ArrayList<Integer>> entry: arrayMap.entrySet()){
			Integer key = entry.getKey();
			ArrayList<Integer> tempArray = entry.getValue();
			Integer tempInt = 0;
			for (Integer i:tempArray){
				tempInt += i;
			}
			if (key == tempInt){
				array2.add(key);
			}
		}
		int [] result = returnArray(array2);
		
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
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < array.length; i++){
			builder.append(array[i]);
			builder.append(seperator);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
//***************************工具类*****************************************
	public int [] returnArray( List list){
		int [] result = new int[list.size()];
		int index = 0;
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()){
			result[index++] = iterator.next();
		}
		return result;
	}

	//快速排序算法
	public int [] sort (int [] array, int low, int height ){
		
		int start = low;
		int end = height;
		int key = array[low];
		
		while (end > start){
			//从后往前遍历
			while (end > start && array[end] >= key)
				end--;
			
			if (array[end] <= key){
				int temp = array[end];
				array[end] = array[start];
				array[start] = temp;
			}
			
			//从前往后遍历
			while(end > start && array[start] <= key)
				start++;
			
			if (array[start] >= key){
				int temp = array[start];
				array[start] = array[end];
				array[end] = temp;
			}
		}
		
		if(start > low)sort(array, low, start - 1);
		if (end < height)sort(array, end + 1, height);
		
		return array;
	}
//******************************************************************8
}
