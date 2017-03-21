package week2.array;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		if (origin == null) {
			return;
		}
		int len = origin.length;
		int forLen = len / 2;
		int temp;
		for (int i = 0; i < forLen; i++) {
			temp = origin[i];
			origin[i] = origin[len - 1 - i];
			origin[len - 1 - i] = temp;
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
		if (oldArray == null) {
			return null;
		}
		int[] newArray = new int[oldArray.length];
		int index = 0;
		for (int x : oldArray) {
			if (x != 0) {
				newArray[index] = x;
				index++;
			}
		}
		return Arrays.copyOf(newArray, index);
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
		if (array1 == null && array2 == null) {
			return null;
		} else if (array1 == null) {
			return array2;
		} else if (array2 == null) {
			return array1;
		}
		int[] newArray = new int[array1.length + array2.length];
		int newIndex = 0;
		int index1 = 0;
		int index2 = 0;
		int len1 = array1.length;
		int len2 = array2.length;
		int x;
		int y;
		while (index1 < len1 && index2 < len2) {
			x = array1[index1];
			y = array2[index2];
			if (x < y) {
				newArray[newIndex] = x;
				index1++;
			} else if (x == y) {
				newArray[newIndex] = x;
				index1++;
				index2++;
			} else {
				newArray[newIndex] = y;
				index2++;
			}
			newIndex++;
		}
		for (int i = index1; i < len1; i++) {
			newArray[newIndex] = array1[i];
			newIndex++;
		}
		for (int i = index2; i < len2; i++) {
			newArray[newIndex] = array2[i];
			newIndex++;
		}
		return Arrays.copyOf(newArray, newIndex);
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
		if (oldArray == null) {
			return null;
		}
		if (size < 0) {
			size = 0;
		}
		return Arrays.copyOf(oldArray, oldArray.length + size);
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
			return null;
		}
		int now = 2;
		int index = 1;
		int[] fiboArray = new int[] { 1, 1 };
		while (now < max) {
			now = fiboArray[index] + fiboArray[index - 1];
			if (index + 2 > fiboArray.length) {
				fiboArray = Arrays.copyOf(fiboArray, fiboArray.length * 2);
			}
			fiboArray[++index] = now;
		}
		return Arrays.copyOf(fiboArray, index);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public int[] getPrimes(int max) throws Exception {
		if (max < 2) {
			return null;
		}
		int[] primes = new int[] { 2 };
		int index = 0;
		int newPrimes = 3;
		while (newPrimes < max) {
			if (index + 2 > primes.length) {
				primes = Arrays.copyOf(primes, primes.length * 2);
			}
			primes[++index] = newPrimes;

			boolean foundPrime = false;
			while (!foundPrime) {
				newPrimes += 2;
				foundPrime = true;
				int mid = newPrimes / 2 + 1;
				for (int i = 3; i <= mid; i++) {
					if (newPrimes % i == 0) {
						foundPrime = false;
						break;
					}
				}
			}
		}

		return Arrays.copyOf(primes, ++index);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int perfectNumber = 6;
		if (max < perfectNumber) {
			return null;
		}
		int[] perfectNumbers = new int[] { perfectNumber };

		while (perfectNumber < max) {
			perfectNumber++;
			int sum = 0;
			for (int i = 1; i < perfectNumber; i++) {
				if (perfectNumber % i == 0) {
					sum += i;
				}
			}
			if (sum == perfectNumber) {
				int[] newArr = new int[perfectNumbers.length + 1];
				System.arraycopy(perfectNumbers, 0, newArr, 0, perfectNumbers.length);
				perfectNumbers = newArr;
				perfectNumbers[perfectNumbers.length - 1] = perfectNumber;
			}
		}
		return perfectNumbers;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		if (array == null || array.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(array[0]);
		for (int i = 1; i < array.length; i++) {
			sb.append(seperator + array[i]);
		}
		return sb.toString();
	}

}
