package com.github.HarryHook.coding2017.array;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
     * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * 
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
	if (origin == null || origin.length == 0) {
	    return;
	}

	for (int i = 0, j = origin.length - 1; i < j; i++, j--) {
	    int t = origin[i];
	    origin[i] = origin[j];
	    origin[j] = t;
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

	int count = 0; // 统计非零元素个数
	int b[] = new int[oldArray.length];
	// 先统计非零元素个数，并将非零元素存入一个和原数组同样大小的新数组
	for (int i = 0; i < oldArray.length; i++) {
	    if (oldArray[i] != 0) {
		b[count++] = oldArray[i];
	    }
	}
	// 将非零元素copy到新数组
	return Arrays.copyOf(b, count);

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
	    return array2;
	}
	if (array2 == null) {
	    return array1;
	}
	int[] newArray = new int[array1.length + array2.length];
	// 应该让a1，a2两个数组先进行比较 比较后插入元素
	int i = 0; // array1下标
	int j = 0; // array2下标
	int count = 0; // array3下标
	while (i < array1.length && j < array2.length) {
	    if (array1[i] < array2[j]) {
		newArray[count++] = array1[i++];
	    } else if (array1[i] > array2[j]) {
		newArray[count++] = array2[j++];
	    } else if (array1[i] == array2[j]) {
		newArray[count++] = array1[i++] = array2[j++];
	    }
	}
	while (j == array2.length && i < array1.length) {
	    newArray[count++] = array1[i++];
	}
	while (i == array1.length && j < array2.length) {
	    newArray[count++] = array2[j++];
	}
	return Arrays.copyOf(newArray, count);
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     * 
     * @param oldArray
     * @param size
     * @return
     * @throws Exception
     */
    public int[] grow(int[] oldArray, int size) {
	if (oldArray == null) {
	    return null;
	}
	if (size < 0) {
	    throw new IndexOutOfBoundsException("size小于0");
	}
	int[] newArray = new int[oldArray.length + size];
	System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
	if (max == 1) {
	    return new int[0];
	}
	if (max == 2) {
	    return new int[] { 1, 1 };
	}
	// 先将max设置为数组长度,但会浪费空间
	int[] a = new int[max];
	a[0] = 1;
	a[1] = 1;
	int count = 2;
	for (int i = 2; i < max; i++) {
	    a[i] = a[i - 1] + a[i - 2];
	    if (a[i] >= max) {
		break;
	    } else {
		count++;
	    }
	}

	return Arrays.copyOf(a, count);
    }

    /**
     * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * 
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
	/*
	 * 思路：先生成素数数组，数组的最大值小于max
	 */
	// max小于3时，返回空数组
	if (max < 3) {
	    return new int[0];
	}
	int[] array = new int[max];
	int count = 0;

	for (int n = 2; n < max; n++) {
	    if (isPrime(n)) {
		array[count++] = n;
	    }
	}

	return Arrays.copyOf(array, count);
    }

    private boolean isPrime(int n) {
	// 判断当前n是不是素数
	int i = 2;
	while (i < n) {
	    if (n % i == 0)
		break;
	    if (n % i != 0)
		i++;
	}
	return i == n;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * 
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
	if (max < 0) {
	    return null;
	}
	int[] array = new int[max];
	int count = 0;

	for (int n = 2; n < max; n++) {
	    int sum = 0;
	    for (int i = 1; i < n; i++) {
		if (n % i == 0) {
		    sum += i;
		}
	    }
	    if (sum == n) {
		array[count++] = n;
	    }
	}

	return Arrays.copyOf(array, count);
    }

    /**
     * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
     * 
     * @param array
     * @param s
     * @return
     */
    public String join(int[] array, String seperator) {
	if (array == null) {
	    return null;
	}
	if (array.length == 0) {
	    return "";
	}

	StringBuilder buffer = new StringBuilder();
	for (int i = 0; i < array.length; i++) {
	    buffer.append(array[i]);
	    if (i < array.length - 1) {
		buffer.append(seperator);
	    }
	}

	return buffer.toString();
    }

}
