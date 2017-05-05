package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(checkArrayIsNull(origin)){
			return;
		}
		int size = origin.length;
		for (int i = 0; i < size/2; i++) {
			int swap = origin[i];
			origin[i] = origin[size - 1 - i];
			origin[size - 1 - i] = swap;
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
		if(checkArrayIsNull(oldArray)){
			return null;
		}
		int[] swap = new int [oldArray.length];
		int size = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] == 0){
				continue;
			}
			swap[size] = oldArray[i];
			++size;
			
		}
		int[] newArray = new int[size];
		System.arraycopy(swap, 0, newArray, 0, size);
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
		if(checkArrayIsNull(array1) && checkArrayIsNull(array2)){
			return null;
		}
		int[] swap = new int[array1.length + array2.length];
		int index1 = 0,index2 = 0,size = 0;
		while(index1 < array1.length && index2 < array2.length){
			if(array1[index1] == array2[index2]){
				swap[size++] = array1[index1];
				++index1;
				++index2;
			}else if(array1[index1] < array2[index2]){
				if(size > 0 && swap[size-1] == array1[index1]){
					++index1;
					continue;
				}
				swap[size++] = array1[index1++];
			}else{
				if(size > 0 && swap[size-1] == array2[index2]){
					++index2;
					continue;
				}
				swap[size++] = array2[index2++];
			}
			
		}
		while(index1 < array1.length){
			swap[size++] = array1[index1];
			++index1;
		}
		while(index2 < array2.length){
			swap[size++] = array2[index2];
			++index2;
		}
		int[] newArray = new int [size];
		System.arraycopy(swap, 0, newArray, 0, size);
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
	public int[] grow(int[] oldArray,  int size){
		if(checkArrayIsNull(oldArray)){
			return null;
		}
		int length = oldArray.length;
		int[] newArray = new int [length + size];
		System.arraycopy(oldArray, 0, newArray, 0, length);
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
		if(max <= 0){
			return null;
		}
		if(max == 1){
			return new int[0];
		}
		int array[] = null;
		if(max < 20 ){
			array = new int [max];
		}else{
			array =new int [max/2];
		}
		array[0] = array[1] = 1;
		int index = 1;
		while(array[index-1] + array[index] < max){
			array[index+1] = array[index-1] + array[index];
			++index;
		}
		int[] newArray = new int [index+1];
		System.arraycopy(array, 0, newArray, 0, index+1);
		return newArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max < 2){
			return null;
		}
		int init = 2;
		int size = 0;
		int array[] = new int[max];
		boolean flag = true;
		while(init < max){
			flag = true;
			for (int i = 2; i < init; i++) {
				if(init % i == 0){
					flag = false;
					break;
				}
			}
			if(flag){
				array[size++] = init;
			}
			++init;
		}
		int[] newArray = new int [size];
		System.arraycopy(array, 0, newArray, 0, size);
		return newArray;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max < 6){
			return null;
		}
		int array[] = new int[max];
		int init = 6;
		int size = 0,sum = 0;
		while(init < max){
			sum = 0;
			for (int i = 1; i <= init/2; i++) {
				if(init % i == 0){
					sum += i;
				}
			}
			if(sum == init){
				array[size++] = init;
			}
			++init;
		}
		int[] newArray = new int [size];
		System.arraycopy(array, 0, newArray, 0, size);
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
	public String join(int[] array, String seperator){
		if(checkArrayIsNull(array) || seperator == null){
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		int size = array.length;
		for (int i = 0; i < size; i++) {
			if(i != size - 1){
				stringBuilder.append(array[i]).append(seperator);
				continue;
			}
			stringBuilder.append(array[i]);
		}
		return stringBuilder.toString();
	}
	
	private boolean checkArrayIsNull(int[] array){
		if(array == null){
			return true;
		}
		return false;
	}

}
