package com.sanmubird.array;

import java.util.Arrays;

import com.sanmubird.basicDataStructure.ArrayList;
import com.sanmubird.basicDataStructure.Iterator;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public  void reverseArray(int[] origin){
		int[] result = new int[origin.length];
		for(int i = 0 ; i < origin.length ; i++){
			result[i] = origin[origin.length -1 -i];
			System.out.println(result[i]);
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public  int[] removeZero(int[] oldArray){
		int countZero = 0 ;
		for(int i = 0 ; i < oldArray.length ; i++){
			if(oldArray[i] == 0 ) {
				countZero++ ;
			}
		}
		int[] newArray = new int[ oldArray.length - countZero];
		int index = 0 ;
		for(int i = 0 ; i < oldArray.length ; i++){
			if(oldArray[i] != 0 ){
				newArray[index] = oldArray[i] ;
				System.out.println(newArray[index]);
				index++;
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
	/**
	 * 	第一种思路：不考虑原有的数组是否有序，先将这些数组，混合到一个数组里面，然后再去重，再排序；
	 * 	第二种思路: 能不能使用二叉树的方法快速排序？
	 * 
	 * */
	
	
	public  int[] merge(int[] array1, int[] array2){
		//先将两个数组合并成为一个数组；
		int newLength = array1.length + array2.length;
		int[] array3 = Arrays.copyOf(array1, newLength);
		for(int i = array1.length ; i < newLength ; i++ ){
			array3[i] = array2[newLength - i -1];
		}
		// 对这个数组进行冒泡排序；
		int temp ;
		for(int i = 0 ; i < array3.length ; i++){
			for(int j = 0 ; j < array3.length - i -1 ; j++){
				if(array3[j+1] < array3[j]){
					 temp = array3[j+1];
					array3[j+1] = array3[j];
					array3[j] = temp ;
				}
			}
		}
		//计算出排序后的数组中重复值的个数；
		int count = 0 ;
		for(int i = 0 ; i  < array3.length -1 ; i++){
			if(array3[i] == array3[i+1]){
				count++;
			}
		}
		//创建一个新的数组；
		int[] array = new int[array3.length - count];
		//把合并后的数组复制到新的数组中去，在复制的过程中，去除重复的元素；
		int size = 0 ;
		for(int i = 0 ; i < array3.length  ; i++){
			if(i < array3.length - 1){
				if(array3[i] == array3[i+1]){
					array[size] = array3[i+1] ;
				}else{
					array[size] = array3[i];
					size++;
				}
			}else{
				array[size] = array3[i];
			}
		}
		return  array;
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
		for(int i = 0 ; i < oldArray.length ; i++){
			newArray[i] = oldArray[i]; 
		}
		for(int i = 0 ; i < newArray.length ; i++){
			System.out.print(newArray[i]+",");
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
		int count1 = 0 ;
		int result1 ;
		for(int i = 1 ; i <= max ;i++ ){
			result1 = getFiBo(i);
			if(max >= result1){
				count1++;
			}else{
				break;
			}
		}
		System.out.println(count1);
		int[] array = new int[count1];
		int count = 0 ;
		int result ;
		for(int i = 1 ; i <= max ;i++ ){
			result = getFiBo(i);
			if(max >= result){
				array[count] = result ;
				count++;
			}else{
				break;
			}
		}
			
		for(int i = 0 ; i < array.length ; i++){
			System.out.println("array["+i+"]:"+array[i]+",");
		}
		return array;
	}
	
	public static int getFiBo(int i){
		if(i == 1 || i == 2){
			return 1 ;
		}else{
			return getFiBo(i-1) + getFiBo(i -2) ;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int count1 = 0 ;
		for(int i = 1 ; i < max ; i++ ){
			if( isPrime(i )){
				count1++;
			}
		}
		int[] array = new int[count1];
		int count = 0 ;
		for(int i = 1 ; i < max ; i++ ){
			if( isPrime(i )){
				array[count] = i ;
				count++;
			}
		}
		for(int i = 0 ; i < array.length ; i++){
			System.out.println("array["+i+"]:"+array[i]+",");
		}
		
		return array;
	}
	
	public static boolean isPrime(int a ){
		int count = 0 ;
		for(int i = 1 ; i <= a ; i++){
			if(a % i == 0 ){
				count++;
			}
		}
		if(count == 2 ){
			return true ;
		}
		return false ;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		ArrayList al = new ArrayList();
		for(int i = 1 ; i <= max ; i++){
			if(isPerfectNumber(i)){
				al.add(i);
			}
		}
		int[] array = arrayListToArray(al);
		for(int i = 0 ; i < array.length ; i++){
			System.out.println("array["+i+"]:"+array[i]+",");
		}
		return array ;
	}
	
	public static int[] arrayListToArray(ArrayList al){
		int size = al.size();
		int[] array = new int[size];
		for(int i = 0 ; i < size ; i++){
			array[i] = (int) al.get(i);
		}
		return array ;
	}
	
	public static boolean isPerfectNumber(int a){
		ArrayList al = new ArrayList();
		for(int i = 1 ; i <= a/2  ; i++ ){
			if(a%i == 0){
				al.add(i);
			}
		}
		int sum = 0 ;
		Iterator it=al.iterator();
		while(it.hasNext()){
			sum += (int) it.next();
		}
		if(a == sum ){
			return true ;
		}
		return false ;
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
		String s = "";
		String ss = "";
		String sss ="";
		for(int i = 0 ; i < array.length ; i++){
			if(i == array.length-1){
				sss = array[i]+"";
				s += sss;
			}else{
				ss = array[i]+seperator;
				s += ss;
			}
		}
		return s;
	}
}
