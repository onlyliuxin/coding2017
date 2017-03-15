package com.ace.homework2;

import java.util.Arrays;

import com.ace.coding.ArrayList;

public class ArrayUtil {
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
        for(int i = 0; i < origin.length/2; i++){
            int temp = origin[i];
            origin[i] = origin[origin.length - 1 - i];
            origin[origin.length - 1 - i] = temp;
        }

    }
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int newLength = 0;
		for(int i:oldArray){
			if(i != 0){
				newLength++;
			}
		}
		int[] newArray = new int[newLength];
		int newArrayIndex = 0;
		for(int j = 0; j < oldArray.length; j++){
			if(oldArray[j] != 0){
				newArray[newArrayIndex] = oldArray[j];
				newArrayIndex++;
			}
		}
		return newArray;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		ArrayList arrayList = new ArrayList();
		for (int i = 0; i < array1.length; i++) {
			arrayList.add(array1[i]);
		}
		for (int j = 0; j < array2.length; j++) {
			if(!arrayList.contains(array2[j])){
				arrayList.add(array2[j]);
			}
		}
		int[] newArray = new int[arrayList.size()];
		for (int k = 0; k < newArray.length; k++) {
			newArray[k] = (int)arrayList.get(k);
		}
		Arrays.sort(newArray);
		return newArray;
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
		int newLength = oldArray.length + size;
		int[] newArray = new int[newLength];
		for(int i = 0; i < newLength; i++){
			if(i < oldArray.length){
				newArray[i] = oldArray[i];
			} else {
				newArray[i] = 0;
			}
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
		int num = 1;
		ArrayList arrayList = new ArrayList();
		while(getFibonacci(num) < max){
			arrayList.add(getFibonacci(num));
			num++;
		}
		int[] newArray = toArray(arrayList);
		return newArray;
	}
	
	private int getFibonacci(int num){
		if(num == 1){
			return 1;
		} else if(num == 2){
			return 1;
		} else {
			return getFibonacci(num-1) + getFibonacci(num-2);
		}
	}
	
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList arrayList = new ArrayList();
		arrayList.add(2);
		for(int i = 3; i <= max; i++){
			int temp = (int)Math.sqrt(i)+1;
			for(int j = 2; j <= temp; j++){
				if(i % j ==0){
					break;
				}
				if(j == temp){
					arrayList.add(i);
				}
			}
		}
		int[] newArray = toArray(arrayList);
		return newArray;
	}
	
	
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList arrayList = new ArrayList();
		for (int i = 2; i <= max; i++) {
			if(getPerfectNumber(i)){
				arrayList.add(i);
			}
		}
		int[] newArray = toArray(arrayList);
		return newArray;
	}
	
	public boolean getPerfectNumber(int num){
		ArrayList arrayList = new ArrayList();
		for(int i = 1; i <= num/2 ; i++){
			if(num % i == 0){
				arrayList.add(i);
			}
		}
		int sum = 0;
		for(int j = 0; j < arrayList.size(); j++){
			sum = sum + (int)arrayList.get(j);
		}
		return sum == num;
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
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < array.length; i++){
			if(i<array.length - 1){
				stringBuilder.append(array[i]+seperator);
			} else {
				stringBuilder.append(array[i]);
			}
		}
		return stringBuilder.toString();
	}
	
	public void printArray(int[] origin){
		for (int i = 0; i < origin.length; i++) {
            if(i < origin.length - 1){
                System.out.print(origin[i] + ", ");
            } else {
                System.out.println(origin[i]);
            }
        }
	}
	
	private int[] toArray(ArrayList arrayList){
		int[] newArray = new int[arrayList.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = (int)arrayList.get(i);
		}
		return newArray;
		
	}

}