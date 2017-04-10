package assignment0226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		int mid = origin.length / 2;
		for (int i = 0; i < mid; i++) {
			int temp = origin[i];
			int reversePosition = origin.length - 1;
			origin[i] = origin[reversePosition];
			origin[reversePosition] = temp;
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
		int count = 0;

		for (int i : oldArray) {
			if (i != 0)
				count++;
		}
		int[] newArray = new int[count];
		int currentPos = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0)
				newArray[currentPos++] = oldArray[i];
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
		TreeSet<Integer> set = new TreeSet<>();
		for (Integer integer : array1) {
			set.add(integer);
		}
		for (Integer integer : array2) {
			set.add(integer);
		}
		int[] result = new int[set.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = set.pollFirst();
		}
		return result;
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
		return Arrays.copyOf(oldArray, size);
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max) {
		if (max <= 1)
			return new int[0];
		List<Integer> fList = new ArrayList<>();
		fList.add(1);
		fList.add(1);
		int last = fList.size() - 1;
		while (fList.get(last) < max) {
			fList.add(fList.get(last) + fList.get(last - 1));
			last++;
		}
		int[] result = new int[fList.size() - 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = fList.get(i);
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
		boolean[] isPrime = new boolean[max];
		List<Integer> primes = new ArrayList<>();
		for (int i = 0; i < isPrime.length; i++) {
			isPrime[i] = true;
		}
		for (int i = 2; i * i < max; i++) {
			for (int j = i; i * j < max; j++)
				isPrime[i * j] = false;
		}
		for (int i = 2; i < isPrime.length; i++) {
			if (isPrime[i])
				primes.add(i);
		}
		int[] result = new int[primes.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = primes.get(i);
		}
		return result;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		int sum = 0;
		ArrayList<Integer> perfectNumbers = new ArrayList<>();
		for (int i = 1; i < max; i++) {
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i)
				perfectNumbers.add(i);
			sum = 0;
		}

		int[] result = new int[perfectNumbers.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = perfectNumbers.get(i);
		}
		return result;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param seperator
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i : array) {
			stringBuilder.append(i + seperator);
		}
		stringBuilder.delete(stringBuilder.length() - seperator.length(), stringBuilder.length());

		return stringBuilder.toString();
	}
}
