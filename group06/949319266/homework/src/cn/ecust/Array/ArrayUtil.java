package cn.ecust.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){		
		int tem = 0;
		for(int i = 0; i < origin.length/2; i++) {
			tem = origin[i];
			origin[i] = origin[origin.length-1-i];
			origin[origin.length-1-i] = tem;
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
	    ArrayList<Integer> arr = new ArrayList<Integer>();
	    for(int i = 0; i < oldArray.length; i++) {
	    	if(oldArray[i] != 0) {
	    		arr.add(oldArray[i]);
	    	}
	    }	   
	    oldArray = null;
	    for(int i = 0; i<arr.size();i++) {
	    	oldArray[i] = arr.get(i);
	    }
		return oldArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if(array1.length == 0 && array2.length == 0)
			return null;
		if(array1.length == 0)
			return array2;
		if(array2.length == 0)
			return array1;	
		//a,b,c分别代表三个数组的长度
		int a = 0,b = 0,c = 0;
		//定义数组3等于数组1,2之合
		int []array3 = new int[array1.length + array2.length];

		while(a < array1.length || b < array2.length){
			if(a >= array1.length){ //数组1已经遍历结束
				array3[c++] = array2[b++];
				continue;
			}

			if(b >= array2.length){ //数组2已经遍历结束
				array3[c++] = array1[a++];
				continue;
			}

			if(array1[a] > array2[b]){
				array3[c++] = array2[b++];
			}else if(array1[a] < array2[b]){
				array3[c++] = array1[a++];
			}else{
				array3[c++] = array1[a++];
				b++;
			}
		}
		array3 = Arrays.copyOf(array3, c);
		return array3;
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
		int[] newArray = new int[oldArray.length+size];
		for(int i = 0 ; i < oldArray.length; i++) {
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
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int i = 1;
		int j = 1;
		arr.add(i);arr.add(j);
		int m=i+j;
		while(m<max){
			arr.add(m);
			i=j;j=m;
			m=i+j;
		}
		int[] result = new int[arr.size()];
		for(int x=0;x<arr.size();x++) {
			result[x] = arr.get(x);
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
		int[] a = {1,5,6,8,6,4,2,3,45,68,111,72,1,2,23};
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i=0;i<a.length;i++) {
			if(a[i]<max) {
				arr.add(a[i]);
				continue;
			}
		}
		int[] b = new int[arr.size()];
		for(int j= 0;j<arr.size();j++) {
			b[j] = arr.get(j);
		}
		return b;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	
	public int[] getPerfectNumbers(int max){
		int sum = 1;
		int [] result = new int[10]; 
		int len = 0;
		for(int i = 2;i < max; i++){
			sum = 1;
			if(len >= result.length)
				result = grow(result, result.length);
			for(int j = 2; j < i; j++){
				if(i % j == 0)
					sum += j;
			}
			if(sum == i)
				result[len++] = sum;	
		}
		result = Arrays.copyOf(result, len);
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
		String result = new String();
		int len = 0;
		result = new Integer(array[0]).toString();
		for(int i = 1; i < array.length; i++){
			result += seperator;
			result += array[i];
		}
		return result;
	}

}
