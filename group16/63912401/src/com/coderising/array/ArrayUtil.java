package com.coderising.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * ArrayUtil
 * @author greenhills
 * 2017年2月28日 下午10:49:41
 */
public class ArrayUtil {
	
	public static void main(String[] args) {
//		int[] array1={5,8,9,0,-4};
//		int[] array2={4,5,6,7,8,9};
//		int[] array3=ArrayUtil.merge(array1, array2);
//		for(Integer t:array3){
//			System.out.print(t +"\t");
//		}
		
//		int[] array3={4,5,6,7,8,9};
//		array3 = ArrayUtil.grow(array3,5);
//		for(int t:array3){
//			System.out.print(t +"\t");
//		}
		
//		int[] array4=ArrayUtil.fibonacci(100);
//		for(Integer t:array4){
//			System.out.print(t +"\t");
//		}
		
//		int[] array5=ArrayUtil.getPrimes(2);
//		for(Integer t:array5){
//			System.out.print(t +"\t");
//		}
		
		int[] array6=ArrayUtil.getPerfectNumbers(2000);
		for(Integer t:array6){
			System.out.print(t +"\t");
		}
			
	}
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		for (int i = 0; i < origin.length>>1; i++) {
			origin[i]^=origin[origin.length - 1 - i]^(origin[origin.length - 1 - i]=origin[i]);
		}
		
		//方法2 可以使用Collections.reverse(list)方法，但是int 和 Integer数组之间转化消耗性能
		//Collections.reverse(list);
	}
	
	/**
	 * 现在有如下的一个数组：   Integer oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		List<Integer> list=new ArrayList<Integer>();
		for(Integer data:oldArray){
			if(data != 0){
				list.add(data);
			}
		}
		int[] newArray=new int[list.size()];
		for(Integer i=0;i<list.size();i++){
			newArray[i] = list.get(i);
		}
		
		//newArray = list.toArray(newArray); //Integer类型可用此方法
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
		Set<Integer> set=new HashSet<Integer>();
		for(Integer t:array1){
			set.add(t);
		}
		for(Integer t:array2){
			set.add(t);
		}
		List<Integer> list=new ArrayList<Integer>(set);
		java.util.Collections.sort(list);
		int[] result =new int[list.size()];
		for(int i=0;i<list.size();i++){
			result[i] = list.get(i);
		}
		return  result;
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
		int len = oldArray.length;
		return Arrays.copyOf(oldArray, len + size);
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		int [] result=new int[0];
		if(max > 1){
			List<Integer> list=new ArrayList<Integer>();
			list.add(1);
			list.add(1);
			int i=0;
			int temp=list.get(i)+list.get(i+1);
			while(temp < max){
				list.add(temp);
				i++;
				temp=list.get(i)+list.get(i+1);
			}
			
			result=new int[list.size()];
			
			i=0;
			for(int d:list){
				result[i]=d;
				i++;
			}
		}
		
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		List<Integer> list=new ArrayList<Integer>();
		for(int i=2;i<max;i++){
			if(ArrayUtil.isPrimes(i)){
				list.add(i);
			}
		}
		
		int[] result=new int[list.size()];
		
		int i=0;
		for(int d:list){
			result[i]=d;
			i++;
		}
		return result;
	}
	
	/**
	 * 判断一个数是否为素数
	 * 
	 * @param d
	 * @return  true是素数，false不是素数
	 */
	public static boolean isPrimes(int d){
		if(d<=1) return false;
		if(d==2) return true;
		
		int base=(int) Math.sqrt(d);
		for(int i=2;i<=base;i++){
			if(d%i==0){
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
	public static int[] getPerfectNumbers(int max){
		List<Integer> list=new ArrayList<Integer>();
		for(int i=1;i<max;i++){
			if(ArrayUtil.isPerfectNumber(i)){
				list.add(i);
			}
		}
		
		int[] result=new int[list.size()];
		
		int i=0;
		for(int d:list){
			result[i]=d;
			i++;
		}
		return result;
	}
	
	/**
	 * 判断一个数是否为完数(这个数等于它的所有因子之和)
	 * 
	 * @param d
	 * @return true是完数，false不是完数
	 * @author greenhills
	 * 2017年2月28日 下午10:18:29
	 */
	public static boolean isPerfectNumber(int d){
		int sum=0;
		for(int i=1;i<d;i++){
			if(d%i==0){
				sum+=i;
			}
		}
		if(d==sum) return true;
		
		return false;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(Integer[] array, String seperator){
		StringBuffer sb=new StringBuffer();
		for(Integer i:array){
			sb.append(i+seperator);
		}
		String result=null;
		//去除末尾的seperator
		if(sb.lastIndexOf(seperator) == sb.length()-seperator.length()){
			result = sb.substring(0, sb.length()-seperator.length());
		}else{
			result = sb.toString();
		}
		return result;
	}
}
