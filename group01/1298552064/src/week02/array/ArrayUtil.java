package week02.array;

import java.util.Arrays;

public class ArrayUtil {

	// 工具类，不予许创建实例
	private ArrayUtil() {
	}

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		if (origin != null && origin.length > 0) {
			int temp = 0;

			// 数组首尾元素置换
			for (int i = 0; i < origin.length / 2; i++) {
				temp = origin[i];
				origin[i] = origin[origin.length - i - 1];
				origin[origin.length - i - 1] = temp;
			}
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
		int[] newArray = null;
		if (oldArray != null) {
			newArray = new int[oldArray.length];
			int size = 0;
			for (int i = 0; i < oldArray.length; i++) {
				if (oldArray[i] != 0) {
					newArray[size] = oldArray[i];
					size++;
				}
			}
			newArray = Arrays.copyOf(newArray, size);
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
		int[] newArray = null;
		if (array1 != null && array2 != null) {
			int size = 0;

			// index1、index2表示array1和array2数组的比较索引
			int index1 = 0, index2 = 0;
			newArray = new int[array1.length + array2.length];

			while (index1 < array1.length && index2 < array2.length) {
				if (array1[index1] == array2[index2]) {
					newArray[size++] = array1[index1];
					index1++;
					index2++;
				} else if (array1[index1] < array2[index2]) {
					// 数组array1去重
					if (size > 0 && array1[index1] == newArray[size - 1]) {
						size--;
					}
					newArray[size++] = array1[index1];
					index1++;
				} else {
					// 数组array2去重
					if (size > 0 && array2[index2] == newArray[size - 1]) {
						size--;
					}
					newArray[size++] = array2[index2];
					index2++;
				}
			}

			// 将数组array1剩下的元素放入
			while (index1 < array1.length) {
				newArray[size++] = array1[index1++];
			}

			// 将数组array2剩下的元素放入
			while (index2 < array2.length) {
				newArray[size++] = array2[index2++];
			}

			// 合并后有序数组
			newArray = Arrays.copyOf(newArray, size);
		}
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
	public static int[] grow(int[] oldArray, int size) {
		int[] newArray = null;
		if (oldArray != null) {
			newArray = new int[oldArray.length + size];
			for (int i = 0; i < oldArray.length; i++) {
				newArray[i] = oldArray[i];
			}
		}
		return newArray;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max) {

		// 计算方法：f(n) = f(n-1) + f(n-2) 采用数组计算
		int[] result = null;
		if (max <= 1) {
			result = new int[] {};
		} else {
			int i = 2;
			result = new int[max];
			result[0] = result[1] = 1;
			for (; i < max; i++) {
				if (result[i - 1] + result[i - 2] < max) {
					result[i] = result[i - 1] + result[i - 2];
				} else {
					break;
				}
			}
			result = Arrays.copyOf(result, i);
		}
		return result;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		int[] newArray = new int[] {};
		if (max > 2) {
			newArray = new int[max];
			int size = 0, j = 0;
			for (int i = 2; i < max; i++) {
				for (j = 2; j < i / 2 + 1; j++) {
					if (i % j == 0) {
						break;
					}
				}

				if (j == i / 2 + 1) {
					newArray[size++] = i;
				}
			}
			newArray = Arrays.copyOf(newArray, size);
		}
		return newArray;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		int[] newArray = new int[] {};
		if (max > 0) {
			newArray = new int[max];
			int size = 0, sum = 0;
			for (int i = 1; i < max; i++) {
				sum = 0;
				for (int j = 1; j < i / 2 + 1; j++) {
					if (i % j == 0) {
						sum += j;
					}
				}
				if (i == sum) {
					newArray[size++] = i;
				}
			}
			newArray = Arrays.copyOf(newArray, size);
		}
		return newArray;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param seperator
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		String joinResult = null;
		if (array != null) {
			joinResult = "";
			for (int i = 0; i < array.length; i++) {
				joinResult += array[i] + seperator;
			}
			joinResult = joinResult.equals("") ? "" : joinResult.substring(0, joinResult.length() - 1);
		}
		return joinResult;
	}

	public static void main(String[] args) {
		int[] a = new ArrayUtil().getPerfectNumbers(1000);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}

		// [2,3,5,7,11,13,17,19]
		a = new ArrayUtil().getPrimes(20);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
