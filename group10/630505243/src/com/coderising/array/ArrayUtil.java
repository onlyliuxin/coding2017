package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int arrLength = origin.length;
		int[] newArray = new int[arrLength];
		//判断数组不为空
		if (arrLength > 0) {
			for (int i = 0; i < arrLength; i++) {
				newArray[i] = origin[arrLength-1-i];
			}
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
		int position = 0;
		int zeroCount = 0;
		int[] rtnArr = null;
		int[] newArray = new int[oldArray.length];
		if(oldArray.length>0){
			for(int i=0;i<oldArray.length;i++){
				if(oldArray[i]!=0){
					newArray[position] = oldArray[i];
					position++;
				}else{
					zeroCount++;
				}
			}
		}
		if(zeroCount>0){
			rtnArr =new int[oldArray.length-zeroCount];
			System.arraycopy(newArray, 0, rtnArr, 0, rtnArr.length);
		}
		return rtnArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		//去重
		int repeatCount=0;
		int len = array1.length-array2.length>0?array2.length:array1.length;
		for(int i=0;i<len;i++){
			for(int j=0;j<len;j++){
				if(array1.length<=len && array2.length<=len && array1[i]==array2[j]){
					array1[i]=-1;
					repeatCount++;
				}
			}
		}
		for(int i=0;i<array1.length;i++){
			if(array1[i]==-1){
				if(i+1<array1.length)
					array1[i]=array1[i+1];
			}
		}
		int[] temp = new int[array1.length-repeatCount];
		System.arraycopy(array1, 0, temp, 0, temp.length);
		
		//合并
		int[] newArray = new int[temp.length+array2.length];
		System.arraycopy(array1, 0, newArray, 0, temp.length);
		System.arraycopy(array2, 0, newArray, temp.length, array2.length);
		
		//排序
		for(int i=0;i<newArray.length;i++){
			for(int j=0;j<newArray.length;j++){
				if(newArray[i]<newArray[j]){
					int temp1 = newArray[j];
					newArray[j] = newArray[i];
					newArray[i] = temp1;
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
		if(max==1)
			return new int[]{};
		int[] fbncArr = new int[max];
		int len = 0;
		for(int i=1;i<max;i++){
			if(fibonacciImpl(i)<max){
				fbncArr[i-1]=fibonacciImpl(i);
				len++;
			}else{
				break ;
			}
		}
		int[] rtnArr = new int[len];
		System.arraycopy(fbncArr, 0, rtnArr, 0, len);
		return rtnArr;
	}
	private static int fibonacciImpl(int a){
		if(a==0)
			return 0;
		if(a==1)
			return 1;
		if(a>1)
			return fibonacciImpl(a-1)+fibonacciImpl(a-2);
		return -1;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		int[] primes = new int[max];
		int arrCount = 0;
		for(int i=1;i<max;i++){
			if(isPrime(i)){
				primes[arrCount] = i;
				arrCount++;
			}
		}
		if(arrCount<max-1){
			int[] rtnArr = new int[arrCount	];
			System.arraycopy(primes, 0, rtnArr, 0, arrCount);
			return rtnArr;
		}
		return primes;
	}
	private static boolean isPrime(int a){
		if(a==1)
			return false;
		if(a<3){
			return true;
		}else{
			for(int i=3;i<a;i++){
				for(int j=2;j<i;j++){
					if(a%j==0)
						return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int[] arrays = new int[max];
		int count = 0;
		for(int i=3;i<max;i++){
			int sum = 0;
			for(int j=1;j<i;j++){
				if(i%j==0){
					sum +=j;
				}
			}
			if(sum==i){
				arrays[count]= i;
				count++;
			}
		}
		if(count<max){
			int[] rtnArr = new int[count];
			System.arraycopy(arrays, 0, rtnArr, 0, count);
			return rtnArr;
		}
		return arrays;
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
		StringBuffer sb = new StringBuffer();
		if(array.length>0){
			for(int i=0;i<array.length;i++){
				sb.append(array[i]);
				if(i<array.length-1){
					sb.append(seperator);
				}
			}
		}
		return sb.toString();
	}
	

}
