package array;

import java.util.Arrays;
import java.util.BitSet;

import collection.Iterator;
import collection.concrete.ArrayList;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {

		int temp;
		int index = origin.length - 1;
		int numbersToReverse = origin.length / 2;
		for (int i = 0; i < numbersToReverse; i++) {
			temp = origin[i];
			origin[i] = origin[index - i];
			origin[index - i] = temp;
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

		BitSet check = new BitSet(oldArray.length);
		boolean isZero;
		for (int i = 0; i < oldArray.length; i++) {
			isZero = (oldArray[i] == 0) ? true : false;
			check.set(i, isZero);
		}

		int newSize = oldArray.length - check.cardinality();
		int[] newArr = new int[newSize];

		int nextIndex = check.nextClearBit(0);
		for (int i = 0; i < newSize; i++) {
			newArr[i] = oldArray[nextIndex];
			nextIndex = check.nextClearBit(nextIndex + 1);
		}

		return newArr;
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

		int len1 = array1.length;
		int len2 = array2.length;
		int len3 = array1[len1 - 1] < array2[len2 - 1] ? array2[len2 - 1] + 1 : array1[len1 - 1] + 1;
		int[] newArr = new int[len3];
		initialArray(newArr, -1);
		for (int i = 0; i < len1; i++) {
			newArr[array1[i]] = 0;
		}
		for (int i = 0; i < len2; i++) {
			newArr[array2[i]] = 0;
		}
		int mergedLength = 0;
		for (int i = 0; i < len3; i++) {
			if (newArr[i] != -1)
				newArr[mergedLength++] = i;
		}
		return Arrays.copyOf(newArr, mergedLength);
	}

	public static void initialArray(int[] arr, int j) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = j;
		}
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

		int[] newArr = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			newArr[i] = oldArray[i];
		}
		return newArr;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		if (max == 1)
			return new int[0];
		int[] result = new int[max];
		result[0] = result[1] = 1;
		int count = 0;
		for (int i = 2, j = 0; j < max; i++) {
			result[i] = result[i - 1] + result[i - 2];
			j = result[i];
			count++;
		}
		return Arrays.copyOf(result, ++count);
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {

		String temp = "";
		for (int i = 0; i < max; i++) {
			if (isPrime(i)) {
				temp += i + " ";
			}
		}
		String[] tempArr = temp.split(" ");
		int[] result = new int[tempArr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt(tempArr[i]);
		}

		return result;
	}

	public static boolean isPrime(int num) {

		if (num <= 1)
			return false;

		if (num == 2)
			return true;

		for (int i = 2; i <= Math.sqrt(num) + 1; i++) {
			if (num % i == 0)
				return false;
		}

		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {

		int count = 0;
		ArrayList<Integer> myList = new ArrayList<Integer>();
		for (int i = 1; i < max; i++) {
			if (isPerfectNum(i)) {
				count++;
				myList.add(i);
			}
		}
		int[] result = new int[count];
		Iterator<Integer> iterator = myList.iterator();
		for (int i = 0; i < count; i++) {
			result[i] = iterator.next();
		}
		return result;
	}

	public static boolean isPerfectNum(int num) {

		int sum = 0;
		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0)
				sum += i;
		}

		return (num == sum) ? true : false;

	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1)
				sb.append(seperator);
		}
		return sb.toString();
	}

}