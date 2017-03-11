package com.github.FelixCJF.coding2017.coderising.array;

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
		int array[] = new int[origin.length];
		for (int i = 0; i<origin.length; i++) {
			array[i] = origin[origin.length - 1 - i];
		}
		System.arraycopy(array, 0, origin, 0, origin.length);
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int zero = 0;
		//统计原来数组0的个数
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				zero ++;
			}
		}
		int[] newArr = new int[oldArray.length - zero];
		int count = 0;
		for (int i = 0; i<oldArray.length; i ++) {
			if (oldArray[i] != 0) {
				newArr[count] = oldArray[i];
				count ++;
			}
		}
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		//创建新数组
		int length1 = array1.length;
		int length2 = array2.length;
		int length = length1 + length2;
		int[] newArr = new int[length];
		//插入
		for (int i = 0; i < length1; i++) {
			newArr[i] = array1[i];
		}
		for (int i = 0; i < length2; i++) {
			newArr[length1 - 1 + i] = array2[i];
		}
		//排序
		for (int i = 0; i < length-1; i ++){
			if (newArr[i] > newArr[i+1]) {
				int temp = newArr[i];
				newArr[i] = newArr[i+1];
				newArr[i + 1] = temp;
			}
		}
		return  newArr;
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
		int newArr[] = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArr, 0, oldArray.length);
		return newArr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		int[] newArr;
		int f1 = 0;
		int f2 = 1;
		int f = 0;
		if (max < 2) {
			return newArr = new int[0];
		}
		ArrayList list = new ArrayList();
		for (int i = 2; f < max; i++) {
			list.add(f2);
			f = f1 + f2;
			f1 = f2;
			f2 = f;
		}
		newArr = new int[list.size()];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = (int) list.get(i);
		}
		return newArr;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
	
		ArrayList list = new ArrayList();

		for (int i = 1; i < max; i++) {
			if (isPrime(i)) {
				list.add(i);
			}
		}
		int[] newArr = new int[list.size()];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = (int) list.get(i);
		}
		return newArr;
	}
	//判断是否为素数
	private boolean isPrime(int a) {  
		  
        boolean flag = true;  
  
        if (a < 2) {// 素数不小于2  
            return false;  
        } else {  
  
            for (int i = 2; i <= Math.sqrt(a); i++) {  
  
                if (a % i == 0) {// 若能被整除，则说明不是素数，返回false  
  
                    flag = false;  
                    break;// 跳出循环  
                }  
            }  
        }  
        return flag;  
    }  
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] newArr;
		if (max == 0) {
			return newArr = new int[0];
		}
		ArrayList list = new ArrayList();
		for (int i = 1; i < max; i++) {
			if (isWanshu(i)) {
				list.add(i);
			}
		}
		newArr = new int[list.size()];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = (int) list.get(i);
		}
		return newArr;
	}
	//判断一个数是不是完数
	private boolean isWanshu(int n)  
	{  
	    boolean flag=false;  
	    int i,sum=0;  
	    for(i=1;i<=n/2;i++)  
	    {  
	        if(n%i==0)  
	        {  
	            sum+=i;  
	        }  
	    }  
	    if(sum==n)  
	    {  
	        flag=true;  
	    }  
	    return flag;  
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
		String string = "";
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				string += array[i];
			} else {
				string += array[i] + seperator;
			}
		}
		return string;
	}
	

}
