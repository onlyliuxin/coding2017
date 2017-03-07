package com.coderising.array;

import java.util.Arrays;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

public class ArrayUtil {

	/**
	 * ����һ����������a , �Ը������ֵ�����û� ���磺 a = [7, 9 , 30, 3] , �û���Ϊ [3, 30, 9,7] ��� a =
	 * [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		int[] copy = Arrays.copyOf(origin, origin.length);
		for (int i = 0; i < copy.length; i++) {
			origin[i] = copy[origin.length - i];
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
		ArrayList list = new ArrayList();
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				list.add(oldArray[i]);
			}
		}

		int[] newArray = new int[list.size()];
		Iterator it = list.iterator();
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = Integer.parseInt(list.get(i).toString());
		}

		return null;
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
		ArrayList list = new ArrayList();
		int i = 0, j = 0;

		while (i < array1.length && j < array2.length) {
			if (array1[i] < array2[j]) {
				list.add(array1[i]);
				i++;
			} else {
				list.add(array1[j]);
				j++;
			}
		}

		if (i < array1.length) {
			for (; i < array1.length; i++) {
				list.add(array1[i]);
			}
		} else {
			for (; j < array2.length; j++) {
				list.add(array2[i]);
			}
		}

		int[] mergedArr = new int[list.size()];
		for (int m = 0; m < list.size(); m++) {
			mergedArr[m] = Integer.parseInt(list.get(m).toString());
		}

		return null;
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
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		for (int i = oldArray.length - 1; i < newArray.length - 1; i++) {
			newArray[i] = 0;
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
		if(max == 1) {
			return new int[0];
		}
		
		int a = 1;
		int b = 1;
		ArrayList list = new ArrayList();
		list.add(a);
		list.add(b);
		int i = 2;
		while (a < 15) {
			int next = Integer.parseInt(list.get(i - 1).toString())
					+ Integer.parseInt(list.get(i - 2).toString());
			list.add(next);
			i++;
		}
		
		int[] arrInt = new int[list.size()];
		for(int j = 0; j < list.size(); j++) {
			arrInt[j] = Integer.parseInt(list.get(j).toString());
		}
		
		return arrInt;
	}

	/**
	 * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		ArrayList list = new ArrayList();
		for(int i = 0; i < max; i++) {
			if(isPrime(i)) {
				list.add(i);
			}
		}
		
		
		return null;
	}
	
	   
    private static boolean isPrime(int n) {  
        if (n <= 1) {  
            return false;  
        }  
        int k = (int) Math.sqrt(n);  
        for (int i = 2; i <= k; i++) {  
            if(n % i == 0) {  
                return false;  
            }  
        }  
        return true;  
    }  

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		return null;
	}

	/**
	 * ��seperator ������ array���������� ����array= [3,8,9], seperator = "-" �򷵻�ֵΪ"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		String retString = "";
		for (int i = 0; i < array.length; i++) {
			retString += seperator + array[i];
		}

		retString = retString.substring(1);

		return retString;
	}

}