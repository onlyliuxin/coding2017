package tong.java.two;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		if(checkArrray(origin)){
			return;
		}
		int length = origin.length;
		for (int i = 0; i < length / 2; i++) {
			int a = origin[length - 1 - i];
			origin[length - 1 - i] = origin[i];
			origin[i] = a;
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
		if(checkArrray(oldArray)){
			return null;
		}
		List<Integer> newArrayList = new ArrayList<Integer>();
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArrayList.add(oldArray[i]);
			}
		}
		int[] newArray = IntegerToIntArray(newArrayList);
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
		return null;
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
		} else {
			ArrayList<Integer> newArrayList = new ArrayList<Integer>();
			int i = 0;
			while (fibo(i) < max) {
				newArrayList.add(fibo(i));
				i++;
			}
			int[] newArray = IntegerToIntArray(newArrayList);
			return newArray;
		}

	}

	/**
	 * 返回索引为i处的斐波那契数
	 * 
	 * @param i
	 * @return
	 */
	public int fibo(int i) {
		if (i == 0) {
			return 1;
		} else if (i == 1) {
			return 1;
		} else {
			return fibo(i - 1) + fibo(i - 2);
		}
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		ArrayList<Integer> newArrayList = new ArrayList<Integer>();
		newArrayList.add(2);
		for (int i = 3; i < max; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					break;
				}
				if (j + 1 >= i) {
					newArrayList.add(i);
				}
			}
		}
		int[] newArray = IntegerToIntArray(newArrayList);
		return newArray;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		ArrayList<Integer> newArrayList = new ArrayList<Integer>();
		for (int i = 0; i < max; i++) {
			if (isPerfectNumber(i)) {
				newArrayList.add(i);
			}
		}
		return IntegerToIntArray(newArrayList);
	}

	/**
	 * 判断一个数是否为完数
	 * 
	 * @param num
	 * @return
	 */
	public boolean isPerfectNumber(int num) {
		int[] params = getPara(num);
		int plus = 0;
		for (int i = 0; i < params.length; i++) {
			plus = plus + params[i];
		}
		if (num == plus) {
			return true;
		} else {
			return false;

		}
	}

	/**
	 * 获取一个数的所有因子
	 * 
	 * @param num
	 * @return
	 */
	public int[] getPara(int num) {
		ArrayList<Integer> paras = new ArrayList<Integer>();
		paras.add(1);
		for (int i = 2; i < num / 2; i++) {
			if (num % i == 0) {
				if (!(paras.contains(i) && paras.contains(num / i))) {
					paras.add(i);
					paras.add(num / i);
				}
			}
		}
		int[] newArray = IntegerToIntArray(paras);
		return newArray;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if (i != array.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	/**
	 * 将一个泛型为Integer的集合转为int[]数组
	 * 
	 * @param list
	 * @return
	 */
	public int[] IntegerToIntArray(List<Integer> list) {
		int[] newArray = new int[list.size()];
		for (int j = 0; j < list.size(); j++) {
			newArray[j] = list.get(j).intValue();
		}
		return newArray;
	}

	public boolean checkArrray(int[] origin) {
		if (origin.length == 0 || origin.length == 1) {
			return true;
		} else {
			return false;
		}
	}

}
