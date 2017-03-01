package com.coderising.array;

import java.util.Arrays;

import com.coding.basic.ArrayList;
import com.coding.basic.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int length = origin.length;
		if(length <= 1)
			return origin;
		
		int[] newArray = new int[length];
		for (int i = 0; i < length; i++) {
			newArray[i] = origin[length-i-1];
		}
		return newArray;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int length = oldArray.length;
		if(length == 0)
			return oldArray;
		
		int[] newArray = new int[length];
		int oi = 0;
		int ni = 0;
		while(oi < length){
			if(oldArray[oi] == 0){
				oi++;
			}else{
				newArray[ni++] = oldArray[oi++];
			}
		}
		if(ni == 0)
			return null;//输入的数组全部都是0时
		if(ni == length)
			return newArray;//输入的数组不含0时，不需要截取
		
		return Arrays.copyOf(newArray, ni);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){//时间复杂度O(n)
		int length1 = array1.length;
		int length2 = array2.length;
		if(length1 == 0 ||(length1 == 0 && length2 ==0)){
			return array2;
		}
		if(length2 == 0){
			return array1;
		}
		
		int i1 = 0;//始终指向array1中剩余的最小元素
		int i2 = 0;//始终指向array2中剩余的最小元素
		ArrayList stack = new ArrayList();//“过去的”最小值放入栈中
		
		while (i1 < length1 || i2 < length2){//判定，只要还有一个数组中有未入栈元素
			if(i1 == length1){//判定，array1元素都已经入栈
				stack.add(array2[i2++]);
				continue;
			}
			if(i2 == length2){//判定，array2元素都已经入栈
				stack.add(array1[i1++]);
				continue;
			}
			
			int comRes = compare(array1[i1], array2[i2]);
			if(comRes > 0){
				stack.add(array2[i2++]);
			}else if(comRes <0){
				stack.add(array1[i1++]);
			}else{
				stack.add(array1[i1]);
				i1++;
				i2++;
			}
		}
		
		int[] result = new int[stack.size()];
		for (int i = 0; i < stack.size(); i++) {
			result[i] = (int) stack.get(i);
		}
		return result;
	}
	/**
	 * when i < j returns -1;
	 * when i = j returns 0;
	 * when i > j returns 1;
	 * @param i
	 * @param j
	 * @return
	 */
	private static int compare(int i, int j){
		if(i < j){
			return -1;
		}else if(i == j){
			return 0;
		}else{
			return 1;
		}
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
		int[] newArray = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max <= 1)
			return new int[0];
		
		ArrayList l = new ArrayList();
		for (int i = 0; max>fibonacciNum(i); i++) {
			l.add(fibonacciNum(i));
		}
		
		int[] result = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			result[i] = (int) l.get(i);
		}
		return result;
	}
	/**
	 * 私有方法
	 * 根据 斐波那契数列 
	 * 通过下标获取数列元素
	 * 
	 * 例如 [1，1，2，3，5，8，13，...]  fibonacciNum(1) = 1,fibonacciNum(4) = 3
	 * @param index 下标
	 * @return 斐波那契数列元素
	 */
	private static int fibonacciNum(int index){
		if(index < 0 )
			throw new IndexOutOfBoundsException("下标越界，index>=0");
		
		if(index == 0||index == 1)
			return 1;
		return fibonacciNum(index-1)+fibonacciNum(index-2);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		if(max<2)
			return new int[0];
		
		ArrayList list = new ArrayList();
		for (int i = 0; i < max; i++) {
			if(isPrimeNum(i))
				list.add(i);
		}
		
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (int) list.get(i);
		}
		return result;
	}
	/**
	 * 判断一个整数是否是质数
	 * @param num
	 * @return
	 */
	private static boolean isPrimeNum(int num){
		if(num < 2)//质数都是正数，并且大于等于2
			return false;
		
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i ==0){
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
		ArrayList list = new ArrayList();
		for (int i = 6; i < max; i++) {
			if(isPerfectNumber(i))
				list.add(i);
		}
		
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (int) list.get(i);
		}
		return result;
	}
	/**
	 * 判断一个整数是否是完数
	 *  
	 * 因为所有的完数都是三角形数，所有我们通过遍历三角形数判断，如果是三角形数，再判断是否所有因子的积为这个整数
	 * @param num
	 * @return
	 */
	private static boolean isPerfectNumber(int num){
		if(num < 6)// the first perfect number is 6
			return false;
		if(!isEndWith6or8(num))//perfect number is only end with 6 or 8
			return false;
		
		/*if(!isTriangularNumber(num))
			return false;*/
		
		List list = new ArrayList();
		
		double sqr = Math.sqrt(num);
		list.add(1);
		for (int i = 2; i <= sqr; i++) {
			if(num % i == 0){
				list.add(num/i);
				list.add(i);
			}
			if(i == sqr){
				list.add(i);
				break;
			}
		}
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Integer in = (Integer) list.get(i);
			sum += in;
		}
		if(sum == num)
			return true;
		else
			return false;
	}
	
	private static boolean isEndWith6or8(int num){
		if(num == 6)
			return true;
		
		Integer integer = new Integer(num);
		String string = integer.toString();
		String lastIndex = string.substring(string.length()-1);
		if(lastIndex.equals("6") || lastIndex.equals("8"))
			return true;
		else
			return false;
	}
	private static boolean isTriangularNumber(int num){
		if(num < 1 )
			return false;
		
		int i = 0;
		while(triangularNumber(i)<=num){
			if(triangularNumber(i)==num)
				return true;
			i++;
		}
		
		return false;
	}
	/**
	 * 获取三角形数，从数列中获取。
	 * [1,3,6,10,...] 下标从0开始，例如triangularNumber(0) = 1,triangularNumber(1) = 3,triangularNumber(2)=6
	 * @param index
	 * @return
	 */
	private static int triangularNumber(int index){
		if(index < 0)
			throw new IndexOutOfBoundsException("下标越界，index>=0");
		if(index == 0)
			return 1;
		return triangularNumber(index-1)+index+1;
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
		if(array == null)
			return null;
		if(array.length == 0)
			return "";
		
		String result = String.valueOf(array[0]);
		for (int i = 1; i < array.length; i++) {
			result += seperator + array[i];
		}
		return result;
	}
	

}
