package cn.ecust.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtil {
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){		
		int tem = 0;
		for(int i = 0; i < origin.length/2; i++) {
			tem = origin[i];
			origin[i] = origin[origin.length-1-i];
			origin[origin.length-1-i] = tem;
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
	    ArrayList<Integer> arr = new ArrayList<Integer>();
	    for(int i = 0; i < oldArray.length; i++) {
	    	if(oldArray[i] != 0) {
	    		arr.add(oldArray[i]);
	    	}
	    }	   
	    oldArray = null;
	    for(int i = 0; i<arr.size();i++) {
	    	oldArray[i] = arr.get(i);
	    }
		return oldArray;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if(array1.length == 0 && array2.length == 0)
			return null;
		if(array1.length == 0)
			return array2;
		if(array2.length == 0)
			return array1;	
		//a,b,c�ֱ������������ĳ���
		int a = 0,b = 0,c = 0;
		//��������3��������1,2֮��
		int []array3 = new int[array1.length + array2.length];

		while(a < array1.length || b < array2.length){
			if(a >= array1.length){ //����1�Ѿ���������
				array3[c++] = array2[b++];
				continue;
			}

			if(b >= array2.length){ //����2�Ѿ���������
				array3[c++] = array1[a++];
				continue;
			}

			if(array1[a] > array2[b]){
				array3[c++] = array2[b++];
			}else if(array1[a] < array2[b]){
				array3[c++] = array1[a++];
			}else{
				array3[c++] = array1[a++];
				b++;
			}
		}
		array3 = Arrays.copyOf(array3, c);
		return array3;
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
		int[] newArray = new int[oldArray.length+size];
		for(int i = 0 ; i < oldArray.length; i++) {
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
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int i = 1;
		int j = 1;
		arr.add(i);arr.add(j);
		int m=i+j;
		while(m<max){
			arr.add(m);
			i=j;j=m;
			m=i+j;
		}
		int[] result = new int[arr.size()];
		for(int x=0;x<arr.size();x++) {
			result[x] = arr.get(x);
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
		int[] a = {1,5,6,8,6,4,2,3,45,68,111,72,1,2,23};
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i=0;i<a.length;i++) {
			if(a[i]<max) {
				arr.add(a[i]);
				continue;
			}
		}
		int[] b = new int[arr.size()];
		for(int j= 0;j<arr.size();j++) {
			b[j] = arr.get(j);
		}
		return b;
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	
	public int[] getPerfectNumbers(int max){
		int sum = 1;
		int [] result = new int[10]; 
		int len = 0;
		for(int i = 2;i < max; i++){
			sum = 1;
			if(len >= result.length)
				result = grow(result, result.length);
			for(int j = 2; j < i; j++){
				if(i % j == 0)
					sum += j;
			}
			if(sum == i)
				result[len++] = sum;	
		}
		result = Arrays.copyOf(result, len);
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
		String result = new String();
		int len = 0;
		result = new Integer(array[0]).toString();
		for(int i = 1; i < array.length; i++){
			result += seperator;
			result += array[i];
		}
		return result;
	}

}
