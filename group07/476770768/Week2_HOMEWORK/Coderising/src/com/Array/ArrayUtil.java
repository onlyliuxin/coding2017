package com.Array;

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
		if (origin == null)
			return;
		int head = 0;
		int tail = origin.length - 1;
		int tmp;
		while (head < tail) {
			tmp = origin[tail];
			origin[tail] = origin[head];
			origin[head] = tmp;
			head++;
			tail--;
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
		int nonZeroNum = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				nonZeroNum++;
			}
		}
		int cnt = 0;
		int[] newArray = new int[nonZeroNum];
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[cnt++] = oldArray[i];
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
		int[] resultArray = new int[array1.length + array2.length];
		int cnt1 = 0;
		int cnt2 = 0;
		int cntResult = 0;
		resultArray[cntResult++] = array1[0]<array2[0]?array1[0]:array2[0];
		//��array1[0]��array2[0]����Сֵ����resultArray�����ÿ���ʱ���Ѿ�����
		//������ֵ�һ��ֵΪ0����resultArray[0]�ĳ�ʼֵ��ͬ�����
		while(cnt1 < array1.length && cnt2 < array2.length){
			if(array1[cnt1] > array2[cnt2]){
				if(resultArray[cntResult-1] != array2[cnt2]){
					//array2[cnt2]�е���С����û���ظ�
					//����resultArray��
					resultArray[cntResult++] = array2[cnt2++];
				}else{
					//array2[cnt2]�е���С�������ظ�
					cnt2++;
				}
			}else{
				if(resultArray[cntResult-1] != array1[cnt1]){
					//array1[cnt1]�е���С����û���ظ�
					//����resultArray��
					resultArray[cntResult++] = array1[cnt1++];
				}else{
					//array1[cnt1]�е���С�������ظ�
					cnt1++;
				}
			}
		}
		
		//��Ϊ������������ʣ�ಿ�ַ���resultArray
		if(cnt1 == array1.length){
			//array2��ʣ��
			while(cnt2 < array2.length){
				//��array2��ʣ����������ظ��ؼ���resultArray��
				if(resultArray[cntResult-1] != array2[cnt2]){
					resultArray[cntResult++] = array2[cnt2++];
				}else{
					cnt2++;
				}
			}		
		}else{
			while(cnt1 < array1.length){
				//��array1��ʣ����������ظ��ؼ���resultArray��
				if(resultArray[cntResult-1] != array1[cnt1]){
					resultArray[cntResult++] = array1[cnt1++];
				}else{
					cnt1++;
				}
			}
		}
		return Arrays.copyOf(resultArray, cntResult);
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
		for(int i=0; i<oldArray.length; i++){
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
		if(max <= 1) return null;
		int[] result = new int[1000];
		result[0] = 1;
		result[1] = 1;
		int index = 2;//index of result[]
		int tmpResult = 2;//current result
		while(tmpResult < max){
			result[index++] = tmpResult;
			tmpResult = result[index-2] + result[index-1];
		}
		return Arrays.copyOf(result, index);
	}

	/**
	 * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		if(max <= 1) return null;
		int[] result = new int[max];
		int index = 0;
		for(int currentNum=2; currentNum<max; currentNum++){
			boolean isPrime = true;
			for(int j = 2; j<currentNum; j++){
				if(currentNum%j == 0){
					//i is not prime
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				result[index++] = currentNum;
			}
		}
		return Arrays.copyOf(result, index);
	}

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * 
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		if(max < 1) return null;
		int[] result = new int[max];
		int index = 0;
		for(int currentNum=1; currentNum<max; currentNum++){
			int sum = 0;
			for(int i=1; i<currentNum; i++){
				//get sum of factor for each number
				if(isFactor(i, currentNum)){
					sum +=i;
				}
			}
			if(sum == currentNum){
				result[index++] = currentNum;
			}
		}
		return Arrays.copyOf(result, index);
	}
	
	public boolean isFactor(int a, int num){
		return num%a == 0;
	}

	/**
	 * ��seperator ������ array���������� ����array= [3,8,9], seperator = "-" �򷵻�ֵΪ"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		if(array == null || seperator == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(array[0]));
		for(int i=1; i<array.length; i++){
			sb.append(seperator);
			sb.append(Integer.toString(array[i]));
		}
		return sb.toString();
	}

}
