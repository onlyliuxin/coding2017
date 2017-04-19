package algorithm;

import datastructure.basic.ArrayList;

import java.util.stream.Stream;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int head = 0;
		int rear = origin.length - 1;
		for (; head < rear; ++head, --rear) {
			if (origin[head] != origin[rear]) {
				int t = origin[head];
				origin[head] = origin[rear];
				origin[rear] = t;
			}
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray) {
		int count = 0;
		int[] tempArray = new int[oldArray.length];
		for (int i : oldArray) {
			if (i != 0) {
				tempArray[count++] = i;
			}
		}
		int[] newArray = new int[count];
		System.arraycopy(tempArray, 0, newArray, 0, count);
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2) {
		int[] tempArray = new int[array1.length + array2.length];
		int count = 0;
		int p1 = 0, p2 = 0;

		while (p1 < array1.length && p2 < array2.length) {
			if (array1[p1] < array2[p2]) {
				if (count == 0 || array1[p1] != tempArray[count - 1]) {
					tempArray[count++] = array1[p1];
				}
				p1++;
			} else if (array1[p1] > array2[p2]) {
				if (count == 0 || array2[p2] != tempArray[count - 1]) {
					tempArray[count++] = array2[p2++];
				}
				p2++;
			} else {
				if (count == 0 || array1[p1] != tempArray[count - 1]) {
					tempArray[count++] = array1[p1++];
				}
				p1++;
				p2++;
			}
		}
		while (p1 < array1.length) {
			if (count == 0 || array1[p1] != tempArray[count - 1]) {
				tempArray[count++] = array1[p1];
			}
			p1++;
		}
		while (p2 < array2.length) {
			if (count == 0 || array2[p2] != tempArray[count - 1]) {
				tempArray[count++] = array2[p2];
			}
			p2++;
		}

		int[] newArray = new int[count];
		System.arraycopy(tempArray, 0, newArray, 0, count);
		return newArray;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.size + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size) {
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
	public int[] fibonacci(int max) {
		if (max <= 1) {
			return new int[0];
		}

		ArrayList list = new ArrayList();
		list.add(1);
		list.add(1);
		while (true) {
			int a = (int) (list.get(list.size() - 1));
			int b = (int) (list.get(list.size() - 2));
			if (a + b < max) {
				list.add(a + b);
			} else {
				break;
			}
		}
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			result[i] = (int) list.get(i);
		}
		return result;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max <= 2) {
			return new int[0];
		}

		ArrayList list = new ArrayList();
		for (int i = 2; true; ++i) {
			if (i >= max) {
				break;
			} else if (isPrime(i)) {
				list.add(i);
			}
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			result[i] = (int) list.get(i);
		}
		return result;
	}

	private boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); ++i) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		if (max <= 6) {
			return new int[0];
		}

		ArrayList list = new ArrayList();
		for (int i = 2; true; ++i) {
			if (i >= max) {
				break;
			} else if (isPerfect(i)) {
				list.add(i);
			}
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			result[i] = (int) list.get(i);
		}
		return result;
	}

	private boolean isPerfect(int n) {
		int sum = 1;
		for (int i = 2; i <= n / 2; ++i) {
			if (sum > n) {
				return false;
			}
			if (n % i == 0) {
				sum += i;
			}
		}
		return sum == n;
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public String join(int[] array, String seperator) {
		if (array.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(array[0]);
		for (int i = 1; i < array.length; ++i) {
			builder.append(seperator).append(array[i]);
		}
		return builder.toString();
	}
}
