package com.coderising.array;

import java.io.BufferedReader;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param originint oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] newArray = new int[origin.length] ;
		for(int i = 0;i<origin.length;i++){
			newArray[i] = origin[origin.length-i-1];
		}
		origin = newArray;
		for(int i=0;i<origin.length;i++){
			System.out.println(origin[i]);
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
		int[] newarray = null;
		int[] newarrayc = new int[1];
		int len = 0;
		for(int i = 0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				newarray = new int[len+1];
				//System.out.println(i);
				System.arraycopy(newarrayc, 0, newarray, 0, newarrayc.length);
				newarray[len++]=oldArray[i];
				newarrayc = new int[newarray.length];
				for(int m = 0;m<newarray.length;m++){
					newarrayc[m] = newarray[m];
				}
			}
			
		}
		return newarray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	// 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	public int[] merge(int[] array1, int[] array2){
		int[] newArray = new int[1];
		int[] newArrayc = new int[1];
		int len = 0;
		int ygs = 0;
		int bi = 0;
		bi = array1[0]>array2[0]?array1[0]:array2[0];
		for(int i =0;i<array1.length;i++){
			if(array1[i]>array2[i]){
				bi = array1[i];
				newArray = new int[len+1];
				System.arraycopy(newArrayc, 0, newArray, 0, newArrayc.length);
				newArray[len++]=array1[i];
				newArrayc = new int[newArray.length];
				for(int m = 0;m<newArray.length;m++){
					newArrayc[m] = newArray[m];
				}
				}else if(array1[i]<array2[i]){
					newArray = new int[len+1];
					System.arraycopy(newArrayc, 0, newArray, 0, newArrayc.length);
					newArray[len++]=array1[i];
					newArrayc = new int[newArray.length];
					for(int m = 0;m<newArray.length;m++){
						newArrayc[m] = newArray[m];
					}
				}
		}
		return  newArray;
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
//	public int[] fibonacci(int max){
//		int[] returnarray = null;
//		if(max<=1) return returnarray;
//		else{
//			returnarray[0]=1;
//			returnarray[1]=1;
//		}
//		for(int len = 2;returnarray[len-2]+returnarray[len-1]<max;len++){
//			returnarray[len]=returnarray[len-2]+returnarray[len-1];
//		}
//		return returnarray;
//	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){

		int[] returnarray =null;
		int[] returnarrayc =new int[1];
		int len=0;
		for(int i = 2;i<=max;i++){
			int g = 1;
			for(int j = 2;j<i;j++){
				if(i!=2){
				if(i%j==0){
					g++;
				}
				}
			}
			if(g==1){
				returnarray = new int [len+1];
				System.arraycopy(returnarrayc, 0, returnarray, 0, len);
				returnarray[len++]=i;
				returnarrayc = new int[returnarray.length];
				for(int m = 0;m<returnarray.length;m++){
					returnarrayc[m] = returnarray[m];
				}
			}
		}
		return returnarray;
	}
	
	/**  28 =1+2+4+7+14
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	
	public  int[] getPerfectNumbers(int max){
		int[] returnarray =null;
		int[] returnarrayc =new int[1];
		int test = 0;
		int len=0;
		for(int i = 1;i<=max;i++){
			int sum=0;
			for(int j = 1;j<i;j++){
				if(i%j==0){
					sum=sum+j;
				}
				}
			if(sum==i){
				returnarray = new int [len+1];
				System.arraycopy(returnarrayc, 0, returnarray, 0, len);
				returnarray[len++]=i;
				returnarrayc = new int[returnarray.length];
				for(int m = 0;m<returnarray.length;m++){
					returnarrayc[m] = returnarray[m];
				}
			}
			
			
			
		}
		return returnarray;
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
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<array.length;i++){
			sb.append(array[i]);
			sb.append(seperator);
		}
		String returns = sb.substring(0, sb.length()-1).toString();
		return returns;
	}
	public static void main(String [] args){
		ArrayUtil au = new ArrayUtil();
//		for(int i = 0;i<au.getPerfectNumbers(1000).length;i++){
//			System.out.println(au.getPerfectNumbers(1000)[i]);	
//		}
//		for(int i = 0;i<au.getPrimes(28).length;i++){
//			System.out.println(au.getPrimes(28)[i]);	
//		}
//		int[] a = new int[3];
//		a[0]=1;
//		a[1]=2;
//		a[2]=3;
//		for(int i = 0;i<au.grow(a, 3).length;i++){
//			System.out.println(au.grow(a, 3)[i]);	
//		}
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
//		for(int i = 0;i<au.removeZero(oldArr).length;i++){
//			System.out.println(au.removeZero(oldArr)[i]);	
//		}reverseArray
	//	au.reverseArray(oldArr);
//		int[] a1 = {3, 5, 7,8}; 
//		int[] a2 = {4, 5, 6,7};
//		au.merge(a1, a2);
	}	

}
