package org.wsc.coding.basic.array;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		// 折半
		for (int i = 0; i < (origin.length >> 1); i++) {
			int num = origin[i];
			origin[i] = origin[origin.length - 1 - i];
			origin[origin.length - 1 - i] = num;
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
		int count = 0;// 计数器
		/*
		 * 利用冒泡，将0元素向后排 {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
		 * {1,3,4,5,0,6,6,0,5,4,7,6,7,0,5,0} {1,3,4,5,6,6,0,5,4,7,6,7,0,5,0,0}
		 * ....
		 */
		for (int i = 0; i < oldArray.length - count; i++) {
			// 索引为i的元素为0，则依次将索引i的元素与i+1的元素对换
			if (oldArray[i] == 0) {
				for (int j = i; j < oldArray.length - 1 - count; j++) {
					int num = oldArray[j];
					oldArray[j] = oldArray[j + 1];
					oldArray[j + 1] = num;
				}
				count++;// 计数器+1
				i--;// 防止原索引i+1位置的元素为0,
			}
		}
		// 创建新数组
		int[] newArray = new int[oldArray.length - count];
		System.arraycopy(oldArray, 0, newArray, 0, newArray.length);
		return newArray;
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */
	public static int[] removeZero2(int[] oldArray) {
		int count = 0;// 计数器
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0)
				count++;// 计数器+1
		}
		// 创建新数组
		int[] newArray = new int[oldArray.length - count];
		for (int i = 0, j = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[j] = oldArray[i];
				j++;
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
		int[] newArray = new int[array1.length + array2.length];
		int i = 0, j = 0, k = 0;
		while (i < array1.length && j < array2.length) {
			// <= 都取 array1
			if (array1[i] <= array2[j]) {
				// 等于时，将array2下标++
				if (array1[i] == array2[j])
					j++;
				newArray[k++] = array1[i++];
			} else
				newArray[k++] = array2[j++];

		}
		// 将没有循环完毕的元素插入
		while (i < array1.length)
			newArray[k++] = array1[i++];
		while (j < array2.length)
			newArray[k++] = array2[j++];
		int[] result = newArray;
		// 长度缩短则新建数组
		if (k < newArray.length) {
			result = new int[k];
			for (int l = 0; l < result.length; l++)
				result[l] = newArray[l];
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
		int[] newArray = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
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
	public static int[] fibonacci(int max) {
		if (max <= 1)
			return new int[] {};
		int[] nums = new int[max];
		nums[0] = nums[1] = 1;
		int flag;
		for (flag = 0; (flag < max - 2 && nums[flag] + nums[flag + 1] < max); flag++) {
			nums[flag + 2] = nums[flag] + nums[flag + 1];
		}
		// 创建新数组
		int[] newArray = nums;
		if (newArray.length != flag + 2) {
			newArray = new int[flag + 2];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = nums[i];
			}
		}
		return newArray;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		int[] array = new int[max>>1];
		int flag = 0;
		for (int i = 2; i < max; i++) {
			int j;
			for (j = 2; j <= (i >> 1); j++) {
				if (i % j == 0)
					break;
 
			} 
			//如果大于，则证明j++有运行，已经完整对比
			if(j > i>>1)
				array[flag++] = i;

		}
		int[] newArray = array;
		if(flag < array.length){
			newArray = new int[flag];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = array[i];
			}
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
		int[] array = new int[max];
		int flag = 0;
		for (int i = 1; i < max; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0)
					sum+=j;
			} 
			//如果大于，则证明j++有运行，已经完整对比
			if(sum == i)
				array[flag++] = i;

		}
		int[] newArray = array;
		if(flag < array.length){
			newArray = new int[flag];
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = array[i];
			}
		}
		return newArray;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		String str = "";
		for (int i = 0; i < array.length; i++)
			str += i != array.length - 1 ? array[i] + seperator : array[i];
		return str;
	}
}
