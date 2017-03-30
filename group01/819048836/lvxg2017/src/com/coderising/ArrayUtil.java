package com.coderising;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		for (int i = 0; i < origin.length / 2; i++) {
			int temp = origin[i];
			origin[i] = origin[origin.length - i - 1];
			origin[origin.length - i - 1] = temp;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		int size = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				size++;
			}
		}
		int[] newArray = new int[size];
		int j = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[j++] = oldArray[i];
			}
		}
		return newArray;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public static int[] merge(int[] array1, int[] array2) {
		int[] newArr = new int[array1.length + array2.length];
		int index1 = 0;
		int index2 = 0;
		int z = 0;
		for (int i = 0; i < array2.length; i++) {
			if (array1[index1] - array2[index2] > 0) {
				newArr[z++] = array2[index2];
			}
		}

		return null;
	}

	public static int[] merge1(int[] array1, int[] array2) {
		int size = 0;
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array2.length; j++) {
				if (array1[i] == array2[j]) {
					size++;
				}
			}
		}
		int[] newArr = new int[array1.length + array2.length - size];
		int z = 0;
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array2.length; j++) {
				if (array1[i] < array2[j]) {
					newArr[z++] = array1[i];
					break;
				} else if (array1[i] > array2[j]) {
					newArr[z++] = array2[j];
					break;
				} else if (array1[i] == array2[j]) {
					newArr[z++] = array1[i];
					break;
				}

			}
		}
		System.out.println(size);
		return null;
	}

	/**
	 * 把一个已存满数据的数组oldArray的容量进行扩展，扩展后的新数据大小为OldArray,length+size
	 * 注意，老数组的元素在新数组中需要保持
	 * 
	 * @param args
	 */
	public static int[] grow(int[] oldArray, int size) {
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, size);
		return newArray;
	}

	/**
	 * 裴波那契数列为：1，1，2，3，5，8，13，21。。。 例如max = 15，则返回的数组应该为[1,1,2,3,5,8,13] max =1
	 * ,则返回空数组[]
	 * 
	 * @param args
	 */
	public static int[] fibonacci(int max) {
		if (max == 1) {
			int[] arr = new int[0];
			return arr;
		}
		int x = 1;
		int y = 1;
		int temp = 0;
		int size = 0;
		while (true) {
			temp = y;
			y = x + y;
			x = temp;
			if (y >= max) {
				break;
			}
			size++;

		}
		int[] array = new int[size + 2];
		array[0] = 1;
		array[1] = 1;
		int j = 2;
		int m = 1;
		int n = 1;
		int temp1 = 1;
		while (true) {
			temp1 = n;
			n = m + n;
			m = temp1;
			if (n >= max) {
				break;
			}
			size++;
			array[j++] = n;
		}
		return array;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23， 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 */
	public static int[] getPrimes(int max) {
		int i = 0;
		for (int j = 2; j < max; j++) {
			boolean b = false;
			for (int j2 = 2; j2 < j; j2++) {
				if (j % j2 == 0) {
					b = true;
					break;
				} 
			}
			if (b == false) {
				i++;
			}
		}
		int[] arr = new int[i];
		int z = 0;
		for (int j = 2; j < max; j++) {
			boolean b = false;
			for (int j2 = 2; j2 < j; j2++) {
				if (j % j2 == 0) {
					b = true;
					break;
				}
			}
			if (b == false) {
				arr[z++] = j;
			}
		}
		return arr;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				seperator = array[i] + "";
			} else {
				seperator = seperator + "-" + "" + array[i] + "";
			}
		} 
		return seperator;
	}
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		return null;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5, 6 };
		int[] b = { 2, 3, 4, 5, 7 };
		int[] ab = ArrayUtil.fibonacci(30);
		int array[] = { 1, 2, 3, 4 };
		String s = ArrayUtil.join(array, new String());
		System.out.println(s);
	}
}
