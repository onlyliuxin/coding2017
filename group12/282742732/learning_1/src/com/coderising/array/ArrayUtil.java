package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return 
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		int[] newOrigin = new int[origin.length];
		
		for (int i = 0; i < newOrigin.length; i++) {
			newOrigin[i] = origin[origin.length-1-i];
		}
		return newOrigin;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		
		int count = 0;
		
		
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				count++;
			} 
		}
		int newArray[] = new int[oldArray.length-count];
		int index = 0;
		
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[index] = oldArray[i];
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
	
	public int[] merge(int[] array1, int[] array2){
		
		int length = array1.length + array2.length;
		
		int TEMP[]= new int[length];
		System.arraycopy(array1, 0, TEMP, 0, array1.length);
		System.arraycopy(array2, 0, TEMP, array1.length, array2.length);
		
		
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array2.length; j++) {
				if (array1[i]==array2[j]) {
					length--;
				} 
			}
		}
		
		
		
		for (int i = 0; i < TEMP.length; i++) {
			System.out.println(TEMP[i]);
		}
		
		
		int head = 0;
		for (int i = 0; i < TEMP.length -1;i++) {
			for (int j = i+1; j < TEMP.length; j++) {
				if(TEMP[i]>TEMP[j]){
					int tempE=TEMP[i];
					TEMP[i] = TEMP[j];
					TEMP[j] = tempE;
				}
			}
		}
		
		int NewArray[] = new int[length];
		int num = 0;
		for (int i = 0; i < TEMP.length;i++) {
			if (i<TEMP.length-1 && TEMP[i]!=TEMP[i+1] ) {
				NewArray[num]=TEMP[i];
				num++;
			}else if(i==TEMP.length - 1){
				NewArray[num]=TEMP[i];
				num++;
			}
			
		}
		
		
		for (int i = 0; i < TEMP.length; i++) {
			System.out.println(TEMP[i]);
		}
		
		return  NewArray;
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
		
		int newArray[] = new int[oldArray.length+size];
		
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i]=oldArray[i];
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
		
		int length = 0;
		
		int number1 = 1;
		int number2 = 1;
		int number3 = 1;
		
		
		while (number3<max) {
			if (number2 == 1) {
				length = 2;
			}else{
				length++;
			}
			number3 = number1 + number2;
			number1 = number2;
			number2 = number3;
			

		}
		int Array[] = new int[length];
		
		length = 1;
		number1 = 1;
		number2 = 1;
		number3 = 1;
		Array[0]= 1;
		
		while (number3<max) {
			number1 = number2;
			number2 = number3;
			Array[length]= number2;
			length++;
			number3 = number1 + number2;
		}
		
		
		return Array;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		
		int length = 0;
		int[] Primes;
		if (max==3) {
			Primes = new int[]{2};
		}
		
		boolean isPrimes;
		for (int i = 2; i < max; i++) {
			isPrimes = true;
			for (int j = 2; j < i; j++) {
				if (i%j==0 ) {
					isPrimes = false;
					break;
				}
			}
			
			if (isPrimes) {
				length++;
			}
			
		}
		
		Primes = new int[length];
		
		int num =0;
		
		for (int i = 2; i < max; i++) {
			isPrimes = true;
			for (int j = 2; j < i; j++) {
				if (i%j==0 ) {
					isPrimes = false;
					break;
				}
			}
			
			if (isPrimes) {
				Primes[num] = i;
				num++;
			}
			
		}
		
		
		return Primes;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		
		
		
		int[] PerfectNumbers;
		
		int length = 0;
		
		for (int i = 1; i < max; i++) {
			int count=0;
			for (int j = 1; j < i; j++) {
				if (i%j==0) {
					count = count + j;
				}
			}
			
			if (i==count) {
				length++;
			}

		}
		
		PerfectNumbers = new int[length];
		int num = 0;
		for (int i = 1; i < max; i++) {
			int count=0;
			for (int j = 1; j < i; j++) {
				if (i%j==0) {
					count = count + j;
				}
			}
			
			if (i==count) {
				PerfectNumbers[num] = i;
				num++;
			}

		}

		return PerfectNumbers;
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
		
		StringBuffer strbur = new StringBuffer();
		
		for (int i = 0; i < array.length; i++) {
			if (i==array.length-1) {
				strbur.append(array[i]);
			}else {
				strbur.append(array[i]).append(seperator);
			}
		}
		return strbur.toString();
	}
	
	
	public static void main(String[] args) {
		ArrayUtil arrryUtil = new ArrayUtil();
		
//		int[] origin = {7, 9 , 30, 3};
//		origin = arrryUtil.reverseArray(origin);
//		for (int i = 0; i < origin.length; i++) {
//			System.out.println(origin[i]);
//		}
		
		
//		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} ;
//		int[] removeZero = arrryUtil.removeZero(oldArr);
//		for (int i = 0; i < removeZero.length; i++) {
//			System.out.println(removeZero[i]);
//		}
		
//		int[] a1 = {3, 5, 7,8};   
//		int[] a2 = {4, 5, 6,7};   
//		int[] mergeNew = arrryUtil.merge(a1, a2);	
//		for (int i = 0; i < mergeNew.length; i++) {
//			System.out.println(mergeNew[i]);
//		}		
		
		
//		int[] oldArray = {2,3,6};
//		int[] grow = arrryUtil.grow(oldArray, 3);
//		for (int i = 0; i < grow.length; i++) {
//			System.out.println(grow[i]);
//		}
		
//		int[] fibonacci = arrryUtil.fibonacci(15);
//		for (int i = 0; i < fibonacci.length; i++) {
//			System.out.println(fibonacci[i]);
//		}
		
//		 int[] Primes = arrryUtil.getPrimes(23);
//	     for (int i = 0; i < Primes.length; i++) {
//			System.out.println(Primes[i]);
//		}
	     
//		 int[] PerfectNumbers = arrryUtil.getPerfectNumbers(2300);
//	     for (int i = 0; i < PerfectNumbers.length; i++) {
//			System.out.println(PerfectNumbers[i]);
//		}
		
	     int[] array= {3,8,9};
	     String seperator = "-";
	     String join = arrryUtil.join(array, seperator);
	     System.out.println(join);
	     
	}
}
