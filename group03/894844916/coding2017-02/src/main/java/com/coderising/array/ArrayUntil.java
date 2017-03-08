/**
 * 
 */
package com.coderising.array;

/**
 * @author patchouli
 *
 */
public class ArrayUntil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int size = origin.length;
		if (size == 0 || size == 1) {
			return;
		}
		int temp = 0;
		for (int i = 0; i < size / 2; i++) {
			temp = origin[i];
			origin[i] = origin[size - 1 - i];
			origin[size - 1 - i] = temp;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int size = oldArray.length;
		if (size == 0) {
			return new int[0];
		}
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				size--;
			}
		}
		if (size == 0) {
			return new int[0];
		}
		int[] noZero = new int[size];
		for (int i = 0, j = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				noZero[j] = oldArray[i];
				j++;
			}
		}
		return noZero;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3,5,7,8] a2 = [4,5,6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		if (array1.length == 0) {
			return array2;
		}
		if (array2.length == 0) {
			return array1;
		}
		int size = array1.length + array2.length;
		int[] merged = new int[size];
		int i=0,j=0,k=0;
		while (i<array1.length&&j<array2.length) {
			if (array1[i]<array2[j]) {
				merged[k]=array1[i];
				i++;
			}
			else if (array1[i]>array2[j]) {
				merged[k]=array2[j];
				j++;
			}
			else {
				merged[k]=array1[i];
				i++;
				j++;
			}
			k++;
		}
		if (i==array1.length-1) {
			System.arraycopy(array2, j, merged, k, array2.length-j);
			k=k+array2.length-j;
		}
		if (i==array2.length-1) {
			System.arraycopy(array1, i, merged, k, array1.length-i);
			k=k+array1.length-i;
		}
		size = k;
		int[] newArray = new int[size];
		System.arraycopy(merged, 0, newArray, 0, size);
		return newArray;
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int[] oldArray, int size) {
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1,1,2,3,5,8,13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if (max == 1) {
			return new int[0];
		}
		int[] fib = { 1, 1 };
		int size = 2;
		int capacity = 2;
		int i = 1;
		while (fib[i] < max) {
			if (size == capacity) {
				fib = grow(fib, capacity);
				capacity = fib.length;
			}
			fib[i + 1] = fib[i - 1] + fib[i];
			i++;
			size++;
		}
		int[] newFib = new int[size-1];
		System.arraycopy(fib, 0, newFib, 0, size-1);
		return newFib;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max < 2) {
			return new int[0];
		}
		int[] primes = new int[] { 2 };
		if (max ==3) {
			return primes;
		}
		int size = 1;
		int capacity = 1;
		int number = 3;
		boolean prime = true;
		while (primes[size - 1] < max) {
			for (int i = 2; i < number; i++) {
				if (number % i == 0) {
					prime = false;
					break;
				}
			}
			if (prime == true) {
				if (size == capacity) {
					primes = grow(primes, capacity);
					capacity = primes.length;
				}
				primes[size] = number;
				size++;
			}
			number++;
			prime = true;
		}
		int[] newPrimes = new int[size - 1];
		System.arraycopy(primes, 0, newPrimes, 0, size-1);
		return newPrimes;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int number = 2;
		int size = 0;
		int capacity = 10;
		int[] perfectNumbers = new int[capacity];
		while (number < max) {
			int capa = 10;
			int i = 0;
			int[] factor = new int[capa];
			int sum = 0;
			for (int j = 1; j <= number / 2; j++) {
				if (number % j == 0) {
					if (i == capa) {
						factor = grow(factor, capa);
						capa = factor.length;
					}
					factor[i] = j;
					sum += j;
					i++;
				}
			}
			if (sum == number && number != max) {
				if (size == capacity) {
					perfectNumbers = grow(perfectNumbers, capacity);
					capacity = perfectNumbers.length;
				}
				perfectNumbers[size] = number;
				size++;
			}
			number++;
		}
		int[] newPerfactNumber = new int[size];
		System.arraycopy(perfectNumbers, 0, newPerfactNumber, 0, size);
		return newPerfactNumber;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			stringBuffer.append(array[i]);
			if (i != array.length - 1) {
				stringBuffer.append(seperator);
			}
		}
		return stringBuffer.toString();
	}
}
