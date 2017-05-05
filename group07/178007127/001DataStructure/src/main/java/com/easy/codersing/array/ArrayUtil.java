package com.easy.codersing.array;

import org.junit.Test;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int[] dest =new int[origin.length];
		for(int i=0;i<origin.length;i++){
			dest[origin.length-1-i]=origin[i];
		}
		return dest;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	
	public static int[] removeZero(int[] oldArray){
		StringBuilder sb=new StringBuilder();
		for (int i : oldArray) {
			if(i!=0){
				sb.append(i+",");
			}
		}
		String[] strArr = sb.toString().split("[,]");
		
		return strArr2IntArr(strArr);
	}
	
	private static int[] strArr2IntArr(String[] strArr){
		int[] dest = new int[strArr.length];
		for (int i=0;i<strArr.length;i++) {
			dest[i]=Integer.parseInt(strArr[i]);
		}
		return dest;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int[] array3=new int[array1.length+array2.length];
		int j=0;
		if(isArrayContainElement(array1, 0)||isArrayContainElement(array2, 0)){
			j++;
		}
		
		for(int i1=0;i1<array1.length;i1++){
			if(!isArrayContainElement(array3, array1[i1])){
				array3[j]=array1[i1];
				j++;
			}
		}
		
		for(int i2=0;i2<array2.length;i2++){
			if(!isArrayContainElement(array3, array2[i2])){
				array3[j]=array2[i2];
				j++;
			}
		}
		
		int[] newArr=new int[j];
		System.arraycopy(array3, 0, newArr, 0, j);
		return  sort(newArr);
	}
	
	private static boolean isArrayContainElement(int[] arr,int element){
		for (int i : arr) {
			if(i==element){
				return true;
			}
		}
		return false;
	}
	
	public static int[] sort(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i]>arr[j]){
					int temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
		return arr;
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
		System.arraycopy(oldArray, 0, newArray, 0, newArray.length);
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
		StringBuilder sb=new StringBuilder();
		
		if(max==1){
			return new int[]{};
		}else{
			for(int i=1 ;i<2*max;i++){
				int num = getFibo(i);
				if(num<max){
					sb.append(getFibo(i)+",");
				}
			}
			String[] strArr=sb.toString().split("[,]");
			return strArr2IntArr(strArr);
		}
	}
	
	private static int getFibo(int i){
		if(i==1||i==2){
			return 1;
		}else{
			return getFibo(i-1)+getFibo(i-2);
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		int i,n,k=0;
		StringBuilder sb=new StringBuilder();
		for(n=2;n<=max;n++){
			i=2;
			while(i<n){
				if(n%i==0){
					break;
				}
				i++;
			}
			if(i==n){
				k++;
				//System.out.print(i+",");
				sb.append(i+",");
			}
		}
		String str=sb.toString();
		String[] strArr=str.split("[,]");
		return strArr2IntArr(strArr);
	}
	
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<max;i++){
			int temp=0;
			for(int j=1;j<i/2+1;j++){
				if(i%j==0){
					temp+=j;
				}
			}
			if(temp==i){
				//System.out.println(i+",");
				sb.append(i+",");
			}
		}
		String[] strArr=sb.toString().split("[,]");
		return strArr2IntArr(strArr);
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
		StringBuilder sb=new StringBuilder();
		for (int i : array) {
			sb.append(i+seperator);
		}
		String str=sb.toString();
		return str.substring(0, str.length()-1);
	}
	

}
