package com.coderising.array;

import java.util.Arrays;

public class ArrayUtil {

	/**
	 * ����һ����������a , �Ը������ֵ�����û� ���磺 a = [7, 9 , 30, 3] , �û���Ϊ [3, 30, 9,7] ��� a =
	 * [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		for (int i = origin.length - 1, j = 0; i >= origin.length / 2; i--, j++) {
			int num = origin[j];
			origin[j] = origin[i];
			origin[i] = num;
		}
	}

	/**
	 * ���������µ�һ�����飺 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬 ���ɵ�������Ϊ�� {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		int count = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				count++;
			}
		}
		int[] newArray = new int[count];
		for (int i = 0, j = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[j] = oldArray[i];
				j++;
			}
		}
		return newArray;
	}

	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 , ����һ���µ�����a3, 
	 * ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 *  ���� a1 = [3, 5, 7,8] 
	 *      a2 = [4, 5, 6,7] 
	 *      �� a3 Ϊ[3,4,5,6,7,8] , ע�⣺ �Ѿ��������ظ�
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		/*int i = 0, j = 0;
		int flagNum = 0;
		String s = new String();
		int index = array1.length;
		int length = array2.length;
		int[] arr = array2;
		if (array1.length > array2.length) {
			index = array2.length;
			length = array1.length;
			arr = array1;
		}
		while (i < array1.length && j < array2.length) {
			if (array1[i] == array2[j]) {
				flagNum = array1[i];
				s += array1[i] + "/";
				i++;
				j++;
			} else if (array1[i] < array2[j]) {
				flagNum = array1[i];
				s += array1[i] + "/";
				i++;
			} else if (flagNum != array2[j]) {
				flagNum = array2[j];
				s += array2[j] + "/";
				j++;
			} else {
				s += array1[i] + "/";
				break;
			}
			if (i == index) {
				i--;
			}
			if (j == index) {
				j--;
			}
		}

		for (int k = index; k < length; k++) {
			s += arr[k] + "/";
		}*/
		
		//
		String s = new String();
		for (int i = 0; i < array1.length; i++) {
			s += array1[i] + "/";
		}
		for (int i = 0; i < array2.length; i++) {
			if (Arrays.binarySearch(array1, array2[i]) < 0) {
				s += array2[i] + "/";
			}
		}
		
		String[] array = s.split("/");
		int[] newArray = new int[array.length];
		for (int k = 0; k < newArray.length; k++) {
			newArray[k] = Integer.parseInt(array[k]);
		}
		Arrays.sort(newArray);
		return newArray;
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
	public int[] grow(int[] oldArray, int size) {
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
	public int[] fibonacci(int max) {
		if (max == 1) {
			return new int[] {};
		}
		int num1 = 1;
		int num2 = 1;
		int sum = 0;
		String s = num1 + " " + num2;
		while (num1 + num2 < max) {
			sum = num1 + num2;
			num1 = num2;
			num2 = sum;
			s += " " + sum;
		}
		String[] array = s.split(" ");
		int[] fibArray = new int[array.length];
		int i = 0;
		for (String ss : array) {
			fibArray[i] = Integer.parseInt(ss);
			i++;
		}
		return fibArray;
	}

	/**
	 * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if (max <= 2) {
			return new int[] {};
		}
		int i = 3;
		String s = "2";
		while (i < max) {
			boolean flag = true;
			for (int j = 2; j <= i / 2; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				s += "/" + i;
			}
			i++;
		}
		String[] numStr = s.split("/");
		int[] primesArray = new int[numStr.length];
		for (int j = 0; j < primesArray.length; j++) {
			primesArray[j] = Integer.parseInt(numStr[j]);
		}
		return primesArray;
	}

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		String s = new String();
		for (int i = 1; i <= max; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i) {
				s += i + "/";
			}
		}
		String[] numStr = s.split("/");
		int[] perfectArray = new int[numStr.length];
		for (int j = 0; j < perfectArray.length; j++) {
			perfectArray[j] = Integer.parseInt(numStr[j]);
		}
		return perfectArray;
	}

	/**
	 * ��seperator ������ array���������� ����array= [3,8,9], seperator = "-" �򷵻�ֵΪ"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String s = "";
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				s += array[i];
				break;
			} 
				s += array[i] + seperator;
		}
		return s;
	}
	
}