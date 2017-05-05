package coderising.array;

import java.util.Arrays;

public class ArrayUtil {

	public static void main(String[] args) {
		int[] a1 = {3, 5, 7, 8};
		int[] a2 = {4, 5, 6, 7, 9, 10};
		ArrayUtil aa = new ArrayUtil();
		int[] o = aa.merge(a1, a2);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}

	}

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
	 * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
	 * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 *
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int[] temp = new int[origin.length];
		for (int i = origin.length - 1; i >= 0; i--) {
			temp[origin.length - 1 - i] = origin[i];
		}
		for (int i = 0; i < origin.length; i++) {
			origin[i] = temp[i];
		}

	}

	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
	 * {1,3,4,5,6,6,5,4,7,6,7,5}
	 *
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int[] result;
		int zeroNumber = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0) {
				zeroNumber++;
			}
		}
		result = new int[oldArray.length - zeroNumber];
		for (int i = 0, j = 0; j < result.length; i++) {
			if (oldArray[i] != 0) {
				result[j] = oldArray[i];
				j++;
			}
		}
		return result;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 *
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		int[] result = Arrays.copyOf(array1, array1.length);
		outer:
		for (int i = 0; i < array2.length; i++) {
			for (int j = 0; j < array1.length; j++) {
				if (array1[j] == array2[i]) {
					continue outer;
				}
			}
			result = Arrays.copyOf(result, result.length + 1);
			result[result.length - 1] = array2[i];
		}

		for (int i = 0; i < result.length - 1; i++) {
			for (int j = 0; j < result.length - i - 1; j++) {
				if (result[j] > result[j+1]) {
					int temp = result[j];
					result[j] = result[j+1];
					result[j+1] = temp;
				}
			}
		}


		return result;
	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 *
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int[] oldArray, int size) {
		int[] result = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			result[i] = oldArray[i];
		}
		for (int i = oldArray.length; i < result.length; i++) {
			result[i] = 0;
		}
		return result;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 *
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		int[] initial = new int[]{1, 1};
		if (max == 1) {
			return new int[1];
		} else {
			while (max > initial[initial.length - 1] + initial[initial.length - 2]) {
				initial = addOne(initial);
			}
		}
		return initial;
	}

	public int[] addOne(int[] current) {
		int[] result = Arrays.copyOf(current, current.length + 1);
		result[current.length] = result[current.length - 1] + result[current.length - 2];
		return result;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 *
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int[] result = new int[1];
		outer:
		for (int i = 2; i < max; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					continue outer;
				}
			}
			if (result.length == 1 && result[0] == 0) {
				result[0] = i;
			} else {
				result = Arrays.copyOf(result, result.length + 1);
				result[result.length - 1] = i;
			}
		}
		return result;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 *
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		int[] result = new int[1];
		for (int i = 1; i < max; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				if (result.length == 1 && result[0] == 0) {
					result[0] = i;
				} else {
					result = Arrays.copyOf(result, result.length + 1);
					result[result.length - 1] = i;
				}
			}
		}
		return result;
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 *
	 * @param array
	 * @param
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				sb.append(array[i]);
			} else {
				sb.append(array[i]);
				sb.append(seperator);
			}
		}
		return sb.toString();
	}


}