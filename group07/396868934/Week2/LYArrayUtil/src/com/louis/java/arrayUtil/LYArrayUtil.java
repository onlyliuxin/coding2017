package com.louis.java.arrayUtil;

import java.util.ArrayList;
import java.util.List;

public class LYArrayUtil {

	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		if (origin == null) return null;
		int length = origin.length;
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[length - 1 - i] = origin[i];
		}
		return list;
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		if (oldArray == null) return null;
		int unZeroCount = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				unZeroCount++;
			}
		}
		int[] array = new int[unZeroCount];
		int index = 0;
		for (int i = 0; i < oldArray.length; i++) {
			int num = oldArray[i];
			if (num != 0) {
				array[index] = num;
				index++;
			}
		}
		return array;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		ArrayList<Integer> result = new ArrayList<Integer>();
		int index1 = 0, index2 = 0;
		while (index1 < array1.length && index2 < array2.length) {
			int num1 = array1[index1];
			int num2 = array2[index2]; 
			if (num1 < num2) {
				result.add(new Integer(num1));
				index1++;
			} else if (num1 > num2) {
				result.add(new Integer(num2));
				index2++;
			} else if (num1 == num2) {
				result.add(new Integer(num1));
				index1++;
				index2++;
			}
		}
		while (index1 < array1.length) {
			result.add(new Integer(array1[index1++]));
		}
		while (index2 < array2.length) {
			result.add(new Integer(array2[index2++]));
		}
		int[] newResult = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			newResult[i] = result.get(i).intValue();
		}
		return newResult;
	}
	
	
	/**
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		int[] newArray = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
		return newArray;
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		ArrayList<Integer> array = new ArrayList<Integer>();
		int lastValue = 0;
		for (int i = 1; i < max;) {
			array.add(new Integer(i));
			int temp = i;
			i = i + lastValue;
			lastValue = temp;
		}
		int[] result = new int[array.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = array.get(i).intValue();
		}
		return result;
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] prime = new int[max + 1];
        ArrayList<Integer> list = new ArrayList<Integer>();
    
        // ����Ƕ��ѭ�� ����ֵ��������
        for (int j = 2; j * j <= max; j++) {
            for (int k = 2*j; k <= max; k++) {
                if (k % j == 0) {
                    //�������� �����Ӧ��ֵΪ1
                    prime[k] = 1;
                }
            }
        }
        for (int i = 2; i < max; i++) {
            //��ΪJAVA����Ĭ�ϸ�ֵΪ������0�������������������ľ��������Ƕ��ѭ����������Ӧ��ֵ��û�з�����
            if (prime[i] == 0) {
                list.add(new Integer(i));
            }
        }
    //��list ת��Ϊ���鷵��
        int[] p = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            p[i] = (Integer) list.get(i);
        }
        return p;
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return	
	 */
	public int[] getPerfectNumbers(int max){ 
		if(max <= 1)
			throw new RuntimeException("��������");
		if(max <= 5){
			int[] result = {};
			return result;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int input = 2; input<max; input++){
			int sum = 1;
			for(int i=2; i<=input/2.0; i++){
				if(input % i == 0){
					sum += i;
				}
			}
			if(sum == input)
				list.add(input);
		}
		int[] result = new int[list.size()];
		for(int i=0; i<list.size(); i++)
			result[i] = list.get(i);
		return result;
	}
	
	/**
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		String s = "";
		for(int i=0; i<array.length-1; i++){
			s = s + (array[i]) + "-"; 
		}
		s = s + array[array.length-1];
		return s;
	}
}
