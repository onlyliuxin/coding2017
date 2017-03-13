package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int N = origin.length;
		for(int i = 0; i < N/2; i++){
			int temp = origin[i];
			origin[i] = origin[N-i-1];
			origin[N-i-1] = temp;
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
		int N = oldArray.length;
		int[] newArray = new int[N];
		int j = 0;
		for(int i = 0; i < N; i++){
			if(oldArray[i] != 0){
				newArray[j] = oldArray[i];
				j++;
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
	
	public int[] merge(int[] array1, int[] array2){
		int N = array1.length + array2.length;
		int[] array3 = new int[N];
		System.arraycopy(array1, 0, array3, 0, array1.length);
		for(int i = 0; i < N; i++){
			for(int j = 0; j < array2.length; j++){
				if(array3[i] > array2[j]){
					System.arraycopy(array3, i , array3, i+1, N-i-1);
					array3[i] = array2[j];
				}
			}		
		}
		return  array3;
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
		int[] newArray = new int[oldArray.length + size];
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
	public int[] fibonacci(int max){
		int[] zero = new int[0];
		int[] array = new int[100];
		array[0] = 1;
		array[1] = 1;
		int i = 1;
		
		if(max == 1){
			return zero;
		}else{
			while(array[i] <= max){
				i++;
				if(i > array.length){
					grow(array, i*2);
				}
				array[i+1] = array[i] + array[i-1];
			}
			return array;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		boolean[] prime = new boolean[max + 1];
		int[] zero = new int[0];
		int[] array = new int[max];
		int q = 1;
		if(max == 1 || max ==2){
			return zero;
		}else{
			for(int i = 3; i < max; i++)
				if(i % 2 == 0){
					prime[i] = false;
				}else{
					prime[i] = true;
				}
			
			for(int i = 3; i < Math.sqrt(max); i++){//因子;若n是合数，则其所有因子都不超过sqrt(n)
				if(prime[i])
					for(int j = 2 * i; j<= max; j += i){//其倍数
						prime[j] = false;
					}
				}
			array[0] = 2;
			for(int p = 0; p < max; p++){
				if(prime[p] == true){
					array[q] = p;
					q++;
				}
			}
			return array;
		}
	}
		/*int[] zero = new int[0];
		int[] array = new int[100];
		int k = 0;
		
		if(max == 1 || max == 2){
			return zero;
		}else{
			for(int n = 2; n <= max; n++){
				int isPrime = 1;
				for(int i = 2; i < n; i++){
					if(n % i == 0){
						isPrime = 0;
						break;
					}
					if(isPrime == 1){
						array[k] = n;
						k++;
					}
				}
			}
			return array;
		}*/
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] perfectNumbers = new int[max];
		int n = 0;
		for(int i = 1; i < max; i++){//i:要判断的数
			int sum = 0;
			for(int j = 1; j < i; j++){//j:可能因子
				if(i % j == 0){
					sum += j;
				}
			}
			if(sum == i){
				perfectNumbers[n] = i;
				n++;
			}
		}
		return perfectNumbers;
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
		String s = "";
		for(int i = 0; i < array.length; i++){
			s = s + array[i] + seperator;
		}
		return s;
	}
	

}