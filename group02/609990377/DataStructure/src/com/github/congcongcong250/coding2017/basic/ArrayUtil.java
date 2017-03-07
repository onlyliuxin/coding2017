package com.github.congcongcong250.coding2017.basic;

import java.util.Arrays;
import java.util.ArrayList;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		
		int[] copy = origin.clone();
		int j, last = copy.length - 1;

		for (int i = last; i >= 0; i--) {
			j = last - i;
			origin[i] = copy[j];
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
		/*
		 * Method 1, traverse oldArray and count non-zero value, create new
		 * int[count], traverse again oldArray and insert to new one
		 */

		/*
		 * Method 2, traverse olArray and insert non-zero value to an List<int>,
		 * then List.toArray().
		 */

		// Method 3
		if(oldArray == null){
			return null;
		}
		int[] tmp = new int[oldArray.length];
		int count = 0;

		for (int v : oldArray) {
			if (v != 0) {
				tmp[count] = v;
				count++;
			}
		}

		return Arrays.copyOf(tmp, count);
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		if (array1 == null) {
			if (array2 == null) {
				return null;
			}
			return array2;
		} else if (array2 == null) {
			return array1;
		}

		int l1 = array1.length, l2 = array2.length;
		int[] whole = new int[l1 + l2];

		int dupValue = 0;
		int dupCount = 0;
		boolean isDup = false;

		int j = 0;
		// Traverse array1
		for (int i = 0; i < l1; i++) {

			// Get rid of duplicate value in array1
			if (isDup) {
				if (array1[i] == dupValue) {
					dupCount++;
					continue;
				} else {
					isDup = false;
				}
			}

			while (j < l2) {
				if (array1[i] > array2[j]) {
					// If int from array1 is larger, add int from array2 first
					whole[i + j - dupCount] = array2[j];
					j++;
					continue;
				} else if (array1[i] == array2[j]) {
					// If equals, skip int from array2, and set duplicate value
					isDup = true;
					dupValue = array1[i];
					dupCount++;
					j++;
					continue;
				} else if (array1[i] < array2[j]) {
					// If smaller, break and add from in array1
					break;
				}
			}
			whole[i + j - dupCount] = array1[i];
		}

		// Deal with left over in array2
		while (j < l2) {
			whole[l1 + j - dupCount] = array2[j];
			j++;
		}

		return Arrays.copyOf(whole, l1 + l2 - dupCount);
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
		checkGrowSize(size);
		if (oldArray == null) {
			return null;
		}
		int newlength = oldArray.length + size;
		int[] res = new int[newlength];
		res = Arrays.copyOf(oldArray, newlength);

		return res;
	}

	private void checkGrowSize(int size) {
		if (size < 0) {
			throw new IndexOutOfBoundsException(
					"Negative size is not allowed in grow()");
		}
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if (max <= 1) {
			return new int[0];
		}

		// Fib(47) == 2971215073 > INT_MAX;
		int[] tmp = new int[46];
		tmp[0] = 1;
		tmp[1] = 1;

		int next = 1 + 1;
		int i = 1;
		while (next < max) {
			i++;
			tmp[i] = next;
			next = tmp[i] + tmp[i - 1];
		}

		return Arrays.copyOf(tmp, i + 1);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max <= 2) {
			return new int[0];
		}

		ArrayList<Integer> primeList = new ArrayList<Integer>();
		primeList.add(2);

		for (int candidate = 3; candidate < max; candidate += 2) {
			// For every number smaller than max
			int ceiling = (int) Math.floor(Math.sqrt(candidate));
			for (Integer prime : primeList) {
				// Divided by every prime number smaller than sqrt(candidate)
				if (candidate % prime == 0) {
					// When reminder equals 0, candidate is not a prime
					break;
				}
				if (prime > ceiling) {
					// When every prime number <= sqrt(candidate), add candidate to primelist
					primeList.add(candidate);
					break;
				}
			}
		}

		// Transfer primelist to int array
		int[] res = arrayFromList(primeList);

		return res;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		if (max <= 2) {
			return new int[0];
		}

		ArrayList<Integer> perfectList = new ArrayList<Integer>();

		for (int candidate = 2; candidate < max; candidate++) {
			int sum = 1;
			int ceiling = (int) Math.floor(Math.sqrt(candidate));
			if (Math.sqrt(candidate) == ceiling) {
				sum += ceiling;
			}
			// For each number smaller than max
			for (int divisor = 2; divisor <= ceiling; divisor++) {
				if (candidate % divisor == 0) {
					sum += divisor;
					sum += candidate / divisor;
				}
				if (sum > candidate) {
					break;
				}
			}
			if (sum == candidate) {
				perfectList.add(candidate);
			}
		}

		// Transfer primelist to int array
		int[] res = arrayFromList(perfectList);

		return res;
	}

	private int[] arrayFromList(ArrayList<Integer> list) {
		int[] r = new int[list.size()];
		int j = 0;
		for (Integer v : list) {
			r[j++] = v;
		}
		return r;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String res = "";
		if (array == null || array.length == 0) {
			return res;
		}
		res += array[0];
		for (int i = 1; i < array.length; i++) {
			res += seperator + array[i];
		}
		return res;
	}

}
