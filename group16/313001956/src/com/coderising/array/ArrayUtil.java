
package com.coderising.array;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.coding.basic.ArrayList;

public class ArrayUtil {

	/**
	 * ����һ����������a , �Ը������ֵ�����û� ���磺 a = [7, 9 , 30, 3] , �û���Ϊ [3, 30, 9,7] ��� a =
	 * [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int size = origin.length;
		if (size == 0) {
			return;
		}
		int semi = size / 2;
		int temp;
		for (int i = 0; i < semi; i++) {
			temp = origin[i];
			origin[i] = origin[size - 1 - i];
			origin[size - 1 - i] = temp;
		}
	}

	/**
	 * ���������µ�һ�����飺 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ�� {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public int[] removeZero(int[] oldArray) {
		ArrayList arrayList = new ArrayList();
		int size = oldArray.length;
		for (int i = 0; i < size; i++) {
			if (oldArray[i] != 0)
				arrayList.add(oldArray[i]);
		}

		return arrayListToArray(arrayList);
	}

	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 , ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ������� ���� a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] �� a3 Ϊ[3,4,5,6,7,8] , ע�⣺ �Ѿ��������ظ�
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2) {
		ArrayList arraylist = new ArrayList();
		int size1 = array1.length;
		int size2 = array2.length;
		int j = 0;
		for (int i = 0; i < size1; i++) {
			if (j >= size2)
				arraylist.add(array1[i]);
			else {
				for (; j < size2; j++) {
					if (array1[i] < array2[j]) {
						arraylist.add(array1[i]);
						break;
					} else if (array1[i] == array2[j]) {
						arraylist.add(array2[j]);
						j++;
						break;
					} else {
						arraylist.add(array2[j]);
					}
				}
			}
		}
		return arrayListToArray(arraylist);
	}

	private int[] arrayListToArray(ArrayList arraylist) {
		int newSize = arraylist.size();
		int[] newArray = new int[newSize];
		for (int i = 0; i < newSize; i++)
			newArray[i] = Integer.parseInt(arraylist.get(i).toString());
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
		int newsize = oldArray.length + size;
		int[] newArray = new int[newsize];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
		int array[] = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(1);
		arraylist.add(1);
		if (max == 1)
			return null;
		int temp = 1;
		for (int i = 1; (temp = Integer.parseInt(arraylist.get(i).toString())
				+ Integer.parseInt(arraylist.get(i - 1).toString())) <= max; i++) {

			arraylist.add(temp);
		}

		return arrayListToArray(arraylist);
	}

	/**
	 * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		ArrayList al = new ArrayList();
		if (max == 1) {
			return null;
		} else if (max == 2) {
			al.add(2);
		} else {
			for (int i = 2; i < max; i++) {
				for (int j = 2; j <= Math.sqrt(max); j++) {
					if (i % j == 0)
						break;
				}
				al.add(i);
			}
		}
		return arrayListToArray(al);
	}

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		ArrayList al = new ArrayList();
		int num = 0;
		for (int i = 1; i < max; i++) {
			num = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0)
					num += j;
			}
			if (num == i)
				al.add(i);
		}
		return arrayListToArray(al);
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
		int lenth = array.length;
		for (int i = 0; i < lenth; i++) {
			if (i == 0)
				s += i;
			else {
				s += seperator + i;
			}
		}
		return s;
	}

}
