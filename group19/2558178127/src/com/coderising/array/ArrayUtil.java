package com.coderising.array;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		if (origin == null || origin.length <= 1) {
			return;
		}
		int tem = 0;
		for (int i = 0, len = origin.length; i < len / 2; i++) {
			tem = origin[i];
			origin[i] = origin[len - i + 1];
			origin[len - i + 1] = tem;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		if(oldArray == null){
			return null;
		}
		int [] ret = new int[oldArray.length];
		int cou = 0; 
		for(int i=0;i<oldArray.length;i++){
			if(0==oldArray[i]){
				cou++;
				continue;
			}
			for(int j =0;j<ret.length;j++){
				if(ret[j]==0){
					ret[j]=oldArray[i];
					break;
				}
			}
			
		}
		int[] retarr = new int[oldArray.length-cou];
		System.arraycopy(ret, 0,retarr,0,retarr.length);
		return retarr;
	}
	
	public static void main(String[]args){
		fibonacci(15);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int[] temint = new int[array1.length + array2.length];
		System.arraycopy(array1, 0,temint,0,array1.length);
		System.arraycopy(array2, 0,temint,array1.length-1,array2.length);
		int index[] = new int[temint.length];
		for (int i = 0; i < index.length; i++) {
			index[i] = i;
		}
		for (int i = 0; i < temint.length-1; i++) {
			for (int j = i+1; j < temint.length; j++) {
				if(temint[i]>temint[j]){
					int temp = temint[i];
					int p = index[i];
					temint[i] = temint[j];
					index[i] = index[j];
					temint[j] = temp;
					index[j] = p; 
				}
			}
		}
		
		return  null;
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
		int[] res = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0,res,0,oldArray.length);
		return res;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		 int[] a = new int[max];
		 int[] retf = null;
	        if (max < 3) {
	        	retf =new int[]{1,1};
	        } else if (max >= 3) {
	            a[0] = a[1] = 1;
	            for (int i = 2; i <  max; i++) {
	                a[i] = a[i - 1] + a[i - 2];
	                if(a[i]>max){
	                	break;
	                }
	            }
	            retf = removeZero(a);
	        }	       
	        return retf;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int[] prime = new int[max];
		int sum = 0;
		for (int i = 2; i <= max; i++) {// 从2开始是因为，1既不是素数也不是合数
			boolean sign = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {// 能被除了1和自己整除的数肯定不是素数，因此只要有一个就可以跳过循环
					sign = false;
					continue;
				}
			}
			if (sign) {
				prime[i] = i;
			}
		}
		int[] retf = null;
		retf = removeZero(prime);
		return retf;
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
		StringBuffer sbf = new StringBuffer("");
		for(int i=0;i<array.length;i++){
			int maa = array[i];
			if(i == array.length-1){
				break;
			}
			sbf.append(maa).append(seperator);
		}
		return sbf.toString();
	}
	

}
