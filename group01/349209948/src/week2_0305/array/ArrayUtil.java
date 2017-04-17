package week2_0305.array;

import java.util.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if (origin == null) {
			throw new IllegalArgumentException();
		}
		int temp = 0;
		for (int i = 0; i< origin.length/2; i++) {
			temp = origin[i];
			origin[i] = origin[origin.length - i];
			origin[origin.length - i] = temp; 
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
		if (oldArray == null) {
			throw new IllegalArgumentException();
		}
		int[] newArray = new int[oldArray.length];
		int index = 0;
		for (int i =0; i < oldArray.length; i ++){
			if (oldArray[i] == 0) {
				continue;
			} else {
				newArray[index++] = oldArray[i];
			}
		}
		return copyOf(newArray, index);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if (array1 == null || array2 == null) {
			throw new IllegalArgumentException();
		}
		int[] array3 = new int[array1.length + array2.length];
		int index1 = 0;
		int index2 = 0;
		int actualSize = 0;
		for (int i = 0; i < array3.length; i ++) {
			//把array2剩余的部分拷入数据
			if (index1 >= array1.length) {
				arrayCopy(array2, index2, array3, i, array2.length - index2);
				actualSize = i + array2.length - index2;
				return copyOf(array3, actualSize);
			}
			if (index2 >= array2.length) {
				arrayCopy(array1, index1, array3, i, array1.length - index1);
				actualSize = i + array1.length - index1;
				return copyOf(array3, actualSize);
			}
			if (array1[index1] < array2[index2]) {
				array3[i] = array1[index1];
				index1 ++;
			} else if (array1[index1] == array2[index2]) {
				array3[i] = array1[index1];
				index1 ++;
				index2 ++;
			} else {
				array3[i] = array2[index2];
				index2 ++;
			}	
		}
		// array1 he array2 均为 空数组的情况
		return new int[0];
	}
	
	private void arrayCopy(int[] src, int srcPos, int[] dest, int destPos, int length) {
		for (int i = 0; i< length; i++) {
			dest[destPos++] = src[srcPos++];
		}
	}
	
	private int[] copyOf(int[] arr, int size) {
		int[] dest = new int[size];
		for (int i = 0; i< size; i++) {
			dest[i] = arr[i];
		}
		return dest;
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
		if (oldArray == null || size <0) {
			throw new IllegalArgumentException();
		}
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
		if (max < 0 ) {
			throw new IllegalArgumentException();
		} else if (max == 1) {
			return new int[0];
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		int i = 0;
		while(true) {
			int num = (int)list.get(i) + (int)list.get(i+1);
			if (num < max) {
				list.add(num);
			} else {
				break;
			}
		}
		return listToArray(list);
	}
	
	private int[] listToArray(ArrayList<Integer> list){
		int[] arr = new int[list.size()];
		for (int i = 0;i < list.size(); i++) {
			arr[i] = (int)list.get(i);
		}
		return arr;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if (max <2) {
			throw new IllegalArgumentException();
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i < max; i++) {
			if (isPrime(i)) {
				list.add(i);
			}
		}
		return listToArray(list);
	}
	
	public boolean isPrime(int m) {
		for (int i = 1; i < m/2; i++) {
			if (m % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if (max < 0) {
			throw new IllegalArgumentException(); 
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i< max; i++) {
			if (isPerfectNumber(i)) {
				list.add(i);
			}
		}
		return listToArray(list);
	}
	
	public boolean isPerfectNumber(int num) {
		int sum = 0;
		for (int i = 0; i < num; i++) {
			if (num % i == 0) {
				sum += i;
			}
		}
		if (sum == num) {
			return true;
		} else {
			return false;
		}
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
		if (array == null) {
			throw new IllegalArgumentException();
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(array[i]).append(seperator);
		}
		return builder.substring(0, builder.length() - seperator.length());
	}
	

}
