/**
 * 
 */
package org.le.b;

import java.util.Arrays;

/**
 * @author yue
 * @time 2017年2月28日
 */
public class ArrayUtil {

	/**  
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] origin = {7, 9 , 30, 3};
		//int[] n = reverseArray(origin);
//		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
//		int[] n = removeZero(oldArr);
//		int[] a1 = {3, 5, 7,8,9,12};
//		int[] a2 = {4, 5, 6,7,8,12,14,5};
//		int[] n =merge(a1, a2);
//		int[] n = grow(a1,5);
		int[] n = fibonacci(100);
		System.out.println(Arrays.toString(n));
	}
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int[] newArry = new int[origin.length];
		int i= 0,j = origin.length;
		while(j > 0){
			newArry[i++] = origin[--j];
		}
		return newArry;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int[]  tempArray = new int[oldArray.length];
		int j = 0;
		for(int i = 0; i < oldArray.length;i++){
			if(oldArray[i] > 0){
				tempArray[j++] = oldArray[i];
			}
		}
		int[]  newArray = new int[j];
		System.arraycopy(tempArray, 0, newArray, 0, j);
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
		int len = array1.length;
		int[] newArray = new int[array1.length+array2.length];
		System.arraycopy(array1, 0, newArray, 0, array1.length);
		for(int i = 0; i < array2.length; i++){
			boolean flag = true;
			for(int j = 0; j <  array1.length; j++){
				if(array2[i] == array1[j]){
					flag = false;
				}
			}
			if(flag){
				newArray[len++] = array2[i];
			}
		}
		int[] aa = new int[len];
		System.arraycopy(newArray, 0, aa, 0, len);
		for(int i = 0; i < aa.length; i++){
			for(int j = i+1; j <  aa.length; j++){
				if(aa[i] > aa[j]){
					int temp =  aa[i];
					aa[i] = aa[j];
					aa[j] = temp;
				}
			}
		}
		return  aa;
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
		int[] aa = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, aa, 0, oldArray.length);
		return aa;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * Fn = F(n-1)+F(n-2)(n >= 2)
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max <2){
			return new int[0];
		}
		int[] temp = new int[8];
		int count = 0;
		for(int i = 1;i < max;i ++){
			int len = createFibo(i);
			if(len > max)
				break;
			temp = growInt(temp,count);
			temp[count++] = len;
		}
		int[] res = new int[count];
		System.arraycopy(temp, 0, res, 0, count);
		return res;
	}
	
	private static int[] growInt(int[] temp,int count){
		int[] n = temp;
		if(count >= temp.length){
			n = new int[temp.length + (temp.length >> 1)];
			System.arraycopy(temp, 0, n, 0, temp.length);
		}
		return n;
	}
	
	private static int createFibo(int n){
		if(n <= 2){
			return 1;
		}
		return createFibo(n-1)+createFibo(n-2);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
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
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			str.append(seperator).append(array[i]);
		}
		return str.substring(1).toString();
	}

}
