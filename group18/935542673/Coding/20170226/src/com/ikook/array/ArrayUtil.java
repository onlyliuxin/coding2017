package com.ikook.array;

/**
 * @author ikook   QQ号码: 935542673
 */
public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin) {
		int size = origin.length;
		int[] newArray = new int[size];

		for (int i = 0; i < size; i++) {
			newArray[i] = origin[size - i - 1];
		}

		for (int i = 0; i < size; i++) {
			origin[i] = newArray[i];
		}
		return origin;
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */
	public int[] removeZero(int[] oldArray) {
		
		int i,j = 0;
		int[]  tempArray = new int[oldArray.length];
		for(i = 0; i < oldArray.length; i++) {
			if(oldArray[i] != 0) {
				tempArray[j] = oldArray[i];
				j++;
			}
		}
		
		int[] newArray = new int[j];
		for (int x = 0; x < j; x++) {
			newArray[x] = tempArray[x];
		}

		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组,a1和a2,创建一个新的数组a3,使得a3 包含a1和a2 的所有元素，并且仍然是有序的
	 *  例如 a1 = [3,5,7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2) {
		// 创建新数组，并合并数组给新数组
		int length = array1.length + array2.length;
		int[] array3 = new int[length];

		for (int i = 0; i < array1.length; i++)
			array3[i] = array1[i];
		for (int i = 0; i < array2.length; i++)
			array3[array1.length + i] = array2[i];

		// 冒泡排序
		int temp = 0;
		for (int i = array3.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (array3[j] > array3[j + 1]) {
					temp = array3[j];
					array3[j] = array3[j + 1];
					array3[j + 1] = temp;
				}
			}
		}

		// 去掉重复元素
		int x = 0;
		for (int i = 1; i < length; i++) {
			if (array3[i] != array3[x]) {
				array3[++x] = array3[i];
			}
		}

		int[] newArray3 = new int[x + 1];
		for (int i = 0; i < x + 1; i++) {
			newArray3[i] = array3[i];
		}

		return newArray3;
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
		int oldLength = oldArray.length;
		int newLength = oldLength + size;
		int[] newArray = new int[newLength];
		for (int i = 0; i < oldLength; i++) {
			newArray[i] = oldArray[i];
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
	public int[] fibonacci(int max) {

		if (max < 2) {
			int[] fibo = {};
			return fibo;
		} else if (max == 2) {
			int[] fibo = { 1, 1 };
			return fibo;
		} else {
			int[] fibo = { 1, 1 };
			int last = 2;
			for (int length = 2; last < max;) {
				fibo = grow(fibo, 1);
				length++;
				fibo[length - 1] = fibo[length - 2] + fibo[length - 3];
				last = fibo[length - 1] + fibo[length - 2];
			}
			return fibo;
		}
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		int length = 1;
		int[] perimes = { 2 };
		for (int number = 3; number < max; number++) {
			if (isPrimesNumber(number)) {
				perimes = grow(perimes, 1);
				perimes[length++] = number;
			}
		}
		return perimes;
	}
	/**
	 * 判断是否为素数
	 * 
	 * @param number
	 * @return
	 */
	private boolean isPrimesNumber(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
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
		if(max <= 6) {
			int[] perfect = {};
			return perfect;
		} else {
			int length = 0;
			int[] perfect = {};
			for(int number = 6; number < max; number++) {
				if(isPerfectNumber(number)) {
					perfect = grow(perfect, 1);
					perfect[length++] = number;
				}
			}
			return perfect;
		}
	}
	/**
	 * 判断是否为完数
	 * @param number
	 * @return
	 */
	private boolean isPerfectNumber(int number) {
		int a = 0;
		for (int i = 1; i < number; i++) {
			if (number % i == 0) {
				a = a + i;
			}
		}
		if (a == number) {
			return true;
		}
		return false;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String s = array[0] + "";
		for (int i = 1; i < array.length; i++) {
			s += seperator + array[i];
		}
		return s;
	}

}
