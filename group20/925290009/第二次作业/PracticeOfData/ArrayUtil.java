package org.Ralf.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;

public class ArrayUtil {

	public static void reverseArray(int[] origin) {
		/**
		 * ����һ����������a , �Ը������ֵ�����û� ���磺 a = [7, 9 , 30, 3] , �û���Ϊ [3, 30, 9,7] ���
		 * a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
		 * 
		 * @param origin
		 * @return
		 * 
		 */
		
		for (int i = 0; i < origin.length / 2; i++) {
			int temp = origin[i];
			origin[i] = origin[origin.length - i - 1];
			origin[origin.length - i - 1] = temp;
		}
		
	}

	/**
	 * ���������µ�һ�����飺 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ�� {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return int[]
	 */
	public static int[] removeZero(int[] oldArr) {

		if (oldArr == null) {
			return null;
		}
		int[] newArr = new int[oldArr.length];
		int size = 0;

		for (int i = 0; i < oldArr.length; i++) {
			if (oldArr[i] != 0) {
				newArr[size] = oldArr[i];
				size++;
			}
		}
		return Arrays.copyOf(newArr, size);

	}

	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 , ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ������� ���� a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] �� a3 Ϊ[3,4,5,6,7,8] , ע�⣺ �Ѿ��������ظ�
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public static int[] merge(int[] array1, int[] array2) {

		// method
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < array1.length; i++) {
			if (!arrayList.contains(array1[i])) {
				arrayList.add(array1[i]);
			}
		}
		for (int i = 0; i < array2.length; i++) {
			if (!arrayList.contains(array2[i])) {// ����list��index�ҵ����������������ж��Ƿ������Ԫ��
				arrayList.add(array2[i]);
			}
		}
		int[] newArr = new int[arrayList.size()];
		// arrayList.toArray(newArr);
		for (int i = 0; i < arrayList.size(); i++) {
			newArr[i] = arrayList.get(i);
		}
		Arrays.sort(newArr);// ����ð�����򣬲������򣬿������򷨵�ʵ��
		return newArr;
	}

	/**
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ���� ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
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
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21...... ������һ�����ֵ�� ����С�ڸ�ֵ������ ���磬 max = 15 ,
	 * �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13] max = 1, �򷵻ؿ����� []
	 * 
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max) {
		int[] newArray = {};
		if (max == 1) {
			return newArray;
		}
		newArray = new int[2 * max];
		int size = 0;
		int a = 1;
		int b = 1;
		newArray[size++] = a;
		newArray[size++] = b;
		while (b <= max) {
			int temp = b;
			b = a + b;
			newArray[size++] = b;
			a = temp;
		}

		return Arrays.copyOf(newArray, size - 1);
	}

	/**
	 * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		if (max < 2) {
			return null;
		}
		int[] aar = new int[max];
		int size = 0;
		for (int i = 2; i < max; i++) {
			if (isPrime(i)) {
				aar[size++] = i;
			}
		}
		return Arrays.copyOf(aar, size);
	}

	private static boolean isPrime(int aar) {
		boolean flag = true;
		for (int i = 2; i <= Math.sqrt(aar); i++) {
			if (aar % i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) {
		if (max < 1) {
			return null;
		}
		int[] arr = new int[max];
		int size = 0;
		for (int i = 1; i <= max; i++) {
			if (isPerfectNumber(i)) {
				arr[size++] = i;
			}
		}
		return Arrays.copyOf(arr, size);
	}

	private static boolean isPerfectNumber(int num) {
		int sum = 0;
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				sum += i;
			}

		}
		if (sum == num) {
			return true;
		} else
			return false;
	}

	/**
	 * ��seperator ������ array���������� ����array= [3,8,9], seperator = "-" �򷵻�ֵΪ"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {
		if (array.length < 1) {
			return null;
		}
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < array.length - 1; i++) {
			string.append(array[i]).append(seperator);
		}
		string.append(array[array.length - 1]);
		return string.toString();
	}
}
