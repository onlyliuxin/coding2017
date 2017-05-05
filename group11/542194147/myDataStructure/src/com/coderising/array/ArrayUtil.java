package com.coderising.array;

import java.util.Arrays;

/**
 * Array集合工具类
 * @author 小摩托
 *
 */
public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		if(origin.length==0){
			throw new RuntimeException("数组为空");
		}
		int[]exchange=new int[origin.length];
		for(int i=origin.length-1;i>=0;i--){
		exchange[origin.length-1-i]=origin[i];	
		}
		return exchange;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int exchange[]={};
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				int newExchange[]=new int[exchange.length+1];
				if(i==0){
					newExchange[i]=oldArray[i];
				}else{
					for(int j=0;j<exchange.length;j++){
						newExchange[j]=exchange[j];
					}
					newExchange[exchange.length]=oldArray[i];
				}
				exchange=newExchange;
			}
		}
		return exchange;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] a1, int[] a2){
		for(int i=0;i<a2.length;i++){
			int newA1[]=new int [a1.length+1];
			for(int k=0;k<a1.length;k++){
				newA1[k]=a1[k];
			}
			newA1[a1.length]=a2[i];
			a1=newA1;
		}
		Arrays.sort(a1);
		return  a1;
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
		int newArray[]=new int[oldArray.length+size];
		for(int i=0;i<newArray.length;i++){
			if(i<oldArray.length){
				newArray[i]=oldArray[i];
			}else{
				newArray[i]=0;
			}
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
		int i=1;
		while(getFibonacci(i)<max){
			i++;
		}
		int fibonacci[]=new int[i];
		for(int k=0;k<i;k++){
			fibonacci[k]=getFibonacci(k);
		}
		return fibonacci;
	}
	private int getFibonacci(int n){
		if(n<2){return n;}
		if(n==2){return 1;}
		else{return getFibonacci(n-1)+getFibonacci(n-2);}
	}
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int primes[]={};
		for(int i=1;i<max;i++){
			int j=2;
			while(j<i){
				if(i%j!=0){
					j++;
				}else{
					break;
				}
			}
			if(j==i){
				if(i==2){
					int newPrimes[]=new int[primes.length+1];
					newPrimes[primes.length]=2;
					primes=newPrimes;
				}else{
					int newPrimes[]=new int[primes.length+1];
					for(int k=0;k<primes.length;k++){
						newPrimes[k]=primes[k];
					}
					newPrimes[primes.length]=i;
					primes=newPrimes;
				}
				
			}
		}
		return primes;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int perfect[]={};
		int value=0;
		for(int i=1;i<max;i++){
			for(int j=1;j<i;j++){
				if(i%j==0){
					value+=j;
				}
			}
			if(i==value){
				int newPerfect[]=new int[perfect.length+1];
				for(int k=0;k<perfect.length;k++){
					newPerfect[k]=perfect[k];
				}
				newPerfect[perfect.length]=i;
				perfect=newPerfect;
			}
			value=0;		
		}
		return perfect;
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
		StringBuffer sb=new StringBuffer();
		if(array.length==0||array==null){
			throw new RuntimeException("数组为空");
		}
		for(int i=0;i<array.length;i++){
			if(array.length==i+1){
				sb.append(Integer.valueOf(array[i]));
			}else{
				sb.append(Integer.valueOf(array[i]).toString()).append(seperator);
			}
		}
		return sb.toString();
	}

}
