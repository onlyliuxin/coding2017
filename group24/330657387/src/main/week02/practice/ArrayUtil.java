package main.week02.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		if (origin.length <= 1) {
			return;
		}
		int i = 0, j = origin.length - 1;
		while (j > i) {
			origin[i] = origin[i] ^ origin[j];
			origin[j] = origin[i] ^ origin[j];
			origin[i] = origin[i] ^ origin[j];
			i++;
			j--;
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
		int i = 0;
		for (int j = 0; j < oldArray.length; j++) {
			if (0 != oldArray[j]) {
				oldArray[i++] = oldArray[j];
			}
		}
		int[] newArray = new int[i];
		System.arraycopy(oldArray, 0, newArray, 0, newArray.length);
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

	public int[] merge(int[] array1, int[] array2) {
		if (array1.length == 0) {
			return array2;
		}
		if (array2.length == 0) {
			return array1;
		}
		int n = 0;
		int[] merge = new int[array1.length + array2.length];
		int i = 0, j = 0;
		while (i < array1.length || j < array2.length) {
			if (array1[i] == array2[j]) {
				merge[n] = array1[i];
				n++;
				i++;
				j++;
				if (i == array1.length) {
					System.arraycopy(array2, j, merge, n, array2.length - j);
					n += array2.length - j;
					break;
				}
				if (j == array2.length) {
					System.arraycopy(array1, i, merge, n, array1.length - i);
					n += array1.length - i;
					break;
				}
				continue;
			}
			if (array1[i] < array2[j]) {
				merge[n] = array1[i];
				n++;
				i++;
				if (i == array1.length) {
					System.arraycopy(array2, j, merge, n, array2.length - j);
					n += array2.length - j;
					break;
				}
			} else {
				merge[n] = array2[j];
				n++;
				j++;
				if (j == array2.length) {
					System.arraycopy(array1, i, merge, n, array1.length - i);
					n += array1.length - i;
					break;
				}
			}
		}
		int[] res = new int[n];
		System.arraycopy(merge, 0, res, 0, n);
		return res;
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
		int[] res = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, res, 0, oldArray.length);
		return res;
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
			return new int[0];
		}
		if(max == 2){
			return new int[]{1,1};
		}
		int[] arr = new int[max];
		arr[0] = arr[1] = 1;
		int count = 2;
		for(; count < max; count++){
			arr[count] = arr[count-1] + arr[count-2];
			if(arr[count] >= max){
				break;
			}
		}
		
		return Arrays.copyOf(arr, count);
		
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(1);
//		int index = 1;
//		while (list.get(index) + list.get(index - 1) < max) {
//			list.add(list.get(index) + list.get(index - 1));
//			index++;
//		}
//		Iterator<Integer> iter = list.iterator();
//		int[] res = new int[list.size()];
//		int i = 0;
//		while (iter.hasNext()) {
//			res[i++] = iter.next();
//		}
//		return res;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max <= 2) {
			return new int[0];
		}
		int n = 2;
		int arr[] = new int[max];
		int j = 0;
		a: for (; n < max; n++) {
			for (int i = 2; i < n / 2 + 1; i++) {
				if (n % i == 0)
					continue a;
			}
			arr[j++] = n;
		}
		int[] res = new int[j];
		System.arraycopy(arr, 0, res, 0, j);
		return res;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		if (max <= 6) {
			return new int[0];
		}
		int n = 6, sum = 0, j = 0;
		int[] arr = new int[max];
		for (; n < max; n++, sum = 0) {
			for (int i = 1; i < n / 2 + 1; i++) {
				if (n % i == 0)
					sum += i;

			}
			if (sum == n)
				arr[j++] = n;
		}
		int[] res = new int[j];
		System.arraycopy(arr, 0, res, 0, j);
		return res;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		if (array.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < array.length - 1; i++) {
			sb.append(array[i]);
			sb.append(seperator);
		}
		sb.append(array[i]);
		return sb.toString();
	}

}
