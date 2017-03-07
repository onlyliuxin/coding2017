package com.basic.coding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {
	/**

	 * ����һ����������a , �Ը������ֵ�����û�

		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]

		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]

	 * @param origin

	 * @return

	 */

	public void reverseArray(int[] origin){
		/**
		 * ����С�ڵ���һ���������ԭ���ĳ���
		 */
		if(origin.length>1){
			int Arrlength = (origin.length%2==0)?origin.length/2:(origin.length-1)/2;
			for(int i = 0;i<Arrlength;i++){
				int a = origin[i];
				int b = origin[origin.length-1-i];
				origin[i] = b;
				origin[origin.length-1-i] = a;
			}
		}
	}

	

	/**

	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   

	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   

	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
		134566547675
	 * @param oldArray

	 * @return

	 */

	

	public int[] removeZero(int[] oldArray){
		int len = 0;
		int[] temp = new int[oldArray.length];
		for(int x: oldArray){
			if(x!=0){
				temp[len++] = x;
			}
		}
		oldArray = new int[len];
		for(int i = 0;i < len;i ++){
			oldArray[i] = temp[i];
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
		/**
		 * �Ⱥϲ���������
		 */
		int[] newArray = new int[array1.length+array2.length];
		System.arraycopy(array1,0,newArray,0,array1.length);
		System.arraycopy(array2,0,newArray,array1.length,array2.length);
		
		Set<Integer> set = new HashSet<Integer>();
		for(int x:newArray){
			set.add(x);
		}
		/**
		 * ��ʱset�Ѿ���һ�������ظ����ݵļ���
		 */
		//int[] noRepeat =  (int[])set.toArray(new int[set.size()]);
		//Object[] array = set.toArray();
		Integer[] array = set.toArray(new Integer[set.size()]);
		Arrays.sort(array);
		int[] sortArray=new int[array.length];
		for(int i=0;i<array.length;i ++)
		{
			sortArray[i]=array[i].intValue();
		}
		return sortArray;
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
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}

	

	/**

	 * 쳲���������Ϊ��0,1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������

	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [0,1��1��2��3��5��8��13]

	 * max = 1, �򷵻ؿ����� []

	 * @param max

	 * @return

	 */

	public int[] fibonacci(int max){
		int index = 0;
		if(max>1){
			for(int i = 1;i<max;i++){
				if(getFibo(i,0,1)>=max){
					index = i-1;
					break;
				}
			}
			int[] newArray = new int[index];
			for(int j = 0;j<index;j++){
				newArray[j] = getFibo(j+1,0,1);
			}
			return newArray;
			
		}
		return null;

	}
	public static int[] record = null;
	/**
	 * 
	 * @param i
	 * @param result1 Ĭ��Ϊ0
	 * @param result2 Ĭ��Ϊ1
	 * @return223 135   212 123
	 */
	private static int getFibo(int i,int result1,int result2) {
		if (i<=1)
			return result1;
		else{
			return getFibo(i-1,result2,result1+result2);
		}
    }

	

	/**

	 * ����С�ڸ������ֵmax��������������

	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]

	 * @param max

	 * @return

	 */

	public int[] getPrimes(int max){
		int size = 0;
		boolean flag = false;
		int[] oldArray = new int[max];
		for(int i=2;i<max;i++){
			flag = true;
			for(int j =2;j<i;j++){
				if(i%j==0){
					flag = false;
					break;
				}
			}
			if(flag){
				oldArray[size++]=i;
			}
		}
		int[] newArray = new int[size];
		for(int k = 0;k<size;k++){
			newArray[k] = oldArray[k];
		}
		return newArray;

	}

	

	/**

	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3

	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������

	 * @param max

	 * @return

	 */

	public int[] getPerfectNumbers(int max){
		int size = 0;
		
		int[] oldArray = new int[max];
		for(int i = 1;i < max ;i++){
			int num = 0;
			for(int j = 1;j < i;j++){
				if(i%j==0){
					num+=j;
				}
			}
			if(num==i){
				oldArray[size++] = i;
			}
		}
		int[] newArray = new int[size];
		//System.out.println(size);
		for(int k=0;k<size;k++){
			newArray[k] = oldArray[k];
		}
		return newArray;
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
		if(array!=null){
			String str = "";
			for(int i = 0;i<array.length;i++){
				if(i!=array.length-1){
					str += (array[i]+seperator);
				}else{
					str += array[i];
				}
			}
			return str;
		}
		return null;
	}
}
