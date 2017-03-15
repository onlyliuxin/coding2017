package com.work.week02;

/**
 * �ڶ������ݽṹ��ҵ
 * @author denghuaijun
 *
 */
public class ArrayUtil {

	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int[] dest = new int[origin.length];
		for(int i=0;i<origin.length;i++){
			dest[origin.length-1-i] = origin[i];
		}
		return dest;
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int[] tempArray = new int[oldArray.length];
		int j = 0;
		for(int i=0; i<oldArray.length; i++){
			if(oldArray[i] != 0){
				tempArray[j] = oldArray[i];
				j++;
			}
		}
		int[] newArray = new int[j];
		System.arraycopy(tempArray, 0, newArray, 0, j);
		return newArray;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int[] dest = new int[array1.length + array2.length];
		int i = 0;			//���array1
		int j = 0;			//���array2
		int k = 0;			//���dest
		//array1��array2ͬʱ����ʱ
		while(i < array1.length && j < array2.length){
			if(array1[i] < array2[j]){
				dest[k++] = array1[i++];
				continue;
			}
			if(array1[i] == array2[j]){
				dest[k++] = array1[i++];
				j++;
				continue;
			}
			if(array1[i] > array2[j]){
				dest[k++] = array2[j++];
				continue;
			}
		}
		//ֻʣarray1ʱ
		while(i < array1.length){
			dest[k++] = array1[i++];
		}
		//ֻʣarray2ʱ
		while(j < array2.length){
			dest[k++] = array2[j++];
		}
		return  removeZero(dest);
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
		int newLength = oldArray.length + size;
		int[] newArray = new int[newLength];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		int[] dest = null;
		if(max == 1){
			return dest;
		}
		dest = new int[max+3];
		dest[0] = dest[1] = 1;
		for(int i=2; i<dest.length && (dest[i-1] + dest[i-2])<=max; i++){
			dest[i] = dest[i-1] + dest[i-2];
		}
		
		return removeZero(dest);
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		int[] dest = null;
		if(max < 2){
			return dest;
		}
		dest = new int[max];
		int k = 0;						//���dest
		for(int i=2;i<=max;i++){	
			boolean flag = true;		//����Ƿ��ܱ�����������
			for(int j=2;j<i;j++){		//i=2ʱ flag�������
				if(i%j == 0){			//i�ܱ�����������
					flag = false;
					break;
				}
			}
			if(flag){
				dest[k++] = i;
			}
		}
		return removeZero(dest);
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int[] dest = new int[max]; 
		int k = 0;					//���dest
		for(int i=1; i<=max; i++){
			//�ж�i�Ƿ�Ϊ����
			int sum = 0;
			for(int j=1;j<i;j++){
				if(i%j == 0){		//���������
					sum += j;
				}
			}
			if(sum == i){		//����֮��=����
				dest[k++] = i;
			}
		}
		return removeZero(dest);
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
		StringBuilder temp = new StringBuilder();
		for(int i=0; i<array.length; i++){
			temp.append(array[i]).append(seperator);
		}
		String dest = temp.toString();
		return dest.substring(0, dest.lastIndexOf(seperator));
	}
	
	public static void main(String[] args) {
		int[] arr = getPerfectNumbers(1000);
		for (int i : arr) {
			System.out.println(i);
		}
	}
}
