package com.github.chaoswang.learning.java.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int length = origin.length;
		for(int i=0;i<length/2;i++){
			int tmp = origin[i];
			origin[i] = origin[length - 1 - i];
			origin[length - 1 - i] = tmp;
		}
		return origin;
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		List<Integer> list = new ArrayList<Integer>();
		for(int value : oldArray){
			if(value != 0){
				list.add(value);
			}
		}
		return returnByIntArray(list);
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		TreeSet<Integer> ts1 = new TreeSet<Integer>(Arrays.asList(convertToIntegerArray(array1)));
		TreeSet<Integer> ts2 = new TreeSet<Integer>(Arrays.asList(convertToIntegerArray(array2)));
		ts2.addAll(ts1);
		return  returnByIntArray(ts2);
	}
	
	private static Integer[] convertToIntegerArray(int[] array){
		Integer[] returnArray = new Integer[array.length];
		for(int i=0;i<array.length;i++){
			returnArray[i] = array[i];
		}
		return returnArray;
	}
	
	private static int[] returnByIntArray(Collection<Integer> collection){
		int[] returnArray = new int[collection.size()];
		int i = 0;
		for(Iterator<Integer> it = collection.iterator(); it.hasNext();){
			returnArray[i] = it.next();
			i++;
		}
		return returnArray;
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
	public static int[] grow(int [] oldArray,  int size){
		int[] returnArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, returnArray, 0, oldArray.length);
		return returnArray;
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max <= 1){
			return new int[0];
		}
		Integer[] init = {1,1};
		LinkedList<Integer> result = new LinkedList<Integer>(Arrays.asList(init));
		for(int tmp = -1; tmp <= max;){
			tmp = generateFibonacci(result);
		}
		result.removeLast();
		return returnByIntArray(result);
	}
	
	private static int generateFibonacci(LinkedList<Integer> result){
		int a = result.getLast();
		int b = result.get(result.size()-2);
		result.add(a + b);
		return result.getLast();
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		List<Integer> list = new LinkedList<Integer>();
		for(int i=2;i<max;i++){
			if(isPrime(i)){
				list.add(i);
			}
		}
		return returnByIntArray(list);
	}
	
	private static boolean isPrime(int number) {
		for(int i=2;i<number;i++){
			if(number%i == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		List<Integer> list = new LinkedList<Integer>();
		for(int i=2;i<=max;i++){
			if(isPerfectNumber(i)){
				list.add(i);
			}
		}
		return returnByIntArray(list);
	}
	
	private static boolean isPerfectNumber(int number) {
		int sum = 0;
		for(int i=1;i<number;i++){
			if(number%i == 0){
				sum += i;
			}
		}
		
		if(sum == number){
			return true;
		}
		return false;
	}
	
	/**
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		StringBuffer sb = new StringBuffer();
		for(int number : array){
			sb.append(number);
			sb.append(seperator);
		}
		String returnStr = sb.toString();
		return returnStr.substring(0, returnStr.length() - seperator.length());
	}
	

}