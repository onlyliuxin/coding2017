package com.tiaozaoj;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		int len = origin.length;
		int temp = 0;
		for(int i=0;i<len/2;i++){
			temp = origin[i];
			origin[i] = origin[len-i-1];
			origin[len-i-1] = temp;
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
	
	public int[] removeZero(int[] oldArray){
		int i,j=0;
		int[] newArr = new int[oldArray.length];
		for(i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){
				newArr[j] = oldArray[i];
				j++;
			}
		}
		return Arrays.copyOfRange(newArr, 0,j);
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int i=0,j=0,k=0;
		int len1 = array1.length;
		int len2 = array2.length;
		int[] mergeArr = new int[len1+len2];
		while(true){
			if(i == len1 || j == len2)
				break;
			if(array1[i]<array2[j]){
				mergeArr[k++] = array1[i++];
			}else if(array1[i]>array2[j]){
				mergeArr[k++] = array2[j++];
			}else{
				mergeArr[k++] = array1[i++];
				j++;
			}
		}
		for(;i<len1;i++){
			mergeArr[k++] = array1[i];
		}
		for(;j<len2;j++){
			mergeArr[k++] = array1[j];
		}
		return  Arrays.copyOfRange(mergeArr, 0,k);
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
		int[] newArr = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0,newArr, 0,oldArray.length);
		return newArr;
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		int k = 1;
		if(max == 1){
			return new int[0];
		}
		int[] fib = new int[max];
		fib[0] = 1;
		fib[1] = 1;
		while(fib[k] < max)
		{
			k++;
			fib[k] = fib[k-1]+fib[k-2];			
		}
		return  Arrays.copyOfRange(fib,0,k);
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] a = new int[max];
		int k=0;
        for (int z = 1; z<max; z++){
                int j = 1;
                for ( int i = 1; i<z; i++){
                    if( z%i == 0){
                         j++;
                    }
                } 
                //����1���������ܱ���������������ֻ�ܱ��������Σ�
                if(j == 2){
                    a[k++] = z;
                }
        } 
		return Arrays.copyOfRange(a,0,k);
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] a = new int[max];
		int k = 0;
		for (int i = 1; i <= max; i++) {  			 
            int temp = 0;// ��������֮�ͱ���    
            for (int n = 1; n < i / 2 + 1; n++) {  
                if (i % n == 0) {  
                    temp += n;// �ܱ������ĳ����򱻼ӵ�temp��  
                }  
            }  
            if (temp == i) {// �������֮����ԭ����ȵĻ���˵��������  
                a[k++] = i;  
            }  
        }  
		return Arrays.copyOfRange(a,0,k);
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
		if(array.length<=1)
			return array.toString();
		String desc = "";
		for (int i = 0; i < array.length; i++) {
			desc += array[i]+"-";
		}
		return desc.substring(0,desc.length()-1);
	}
}