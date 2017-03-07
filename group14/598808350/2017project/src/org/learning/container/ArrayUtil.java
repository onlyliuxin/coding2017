package org.learning.container;

import java.util.Arrays;

import org.comm.util.StringUtil;

public class ArrayUtil {

	/**
	 * ����һ������a���Ը������ֵ���з�ת
	 * ���磺 a= [7,9,30,3] ,�û���[3,30,9,7]
	 * �����a= [7,9,30,3,4], �û���[4,3,30,9,7] 
	 * @param origin
	 * 
	 */
	public static int [] reverseArray(int [] sourceArray){
		int length = sourceArray.length;
		int [] newArray = new int[length];
		for(int i=length;i>0;i--){
			newArray[length -i ] = sourceArray[i-1];
		}
		return newArray;
		
	}
	
	/**
	 * ����������һ�����飬 int [] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * Ҫ����������ֵΪ0����ȥ��������Ϊ0��ֵ���뵽һ���µ����飬����������Ϊ
	 * [1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5]
	 * @param oldArray
	 * @return
	 */
	public static int[] removeZero(int [] oldArray){
		int oldLength = oldArray.length;
		int [] newArray = new int[oldLength];
		int index = 0;
		for(int i=0;i<oldLength;i++){
			if(oldArray[i] > 0){
				newArray[index] = oldArray[i];
				index ++ ;
			}
		}
		int [] dest = new int[index];
		System.arraycopy(newArray, 0, dest, 0, index);
		return dest;
	}
	/**
	 * ð������
	 * @param array1
	 * @return
	 */
	public static int[] sort(int [] arr){
		//int[] a1 = new int[]{5, 3, 7,8,1,3,42,2,6};
		
		for(int i=0;i<arr.length;i++){
			for(int j=(i+1);j<arr.length;j++){
				int a = arr[i];
				int b = arr[j];
				if(a>b){
					arr[i]=b;
					arr[j]=a;
				}else if(a == b){
					arr[j]=0;
				}
			}
		}
		
		return arr;
	}
	
	public static int[] replace(int[] sourceArr,int[] resultArr,int index){
		System.arraycopy(sourceArr, 0, resultArr, 0, index);
		System.arraycopy(sourceArr, index+1, resultArr, index, sourceArr.length-(index+1));
		return resultArr;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int [] newArr = new int[array1.length+array2.length];
		//1��������
		int array1L = removeZero(array1).length;
		int array2L = removeZero(array2).length;
		int [] array3 = new int[array1L+array2L];
		System.arraycopy(array1, 0, array3, 0, array1L);
		System.arraycopy(array2, 0, array3, array1L, array2L);
		//2����
		sort(array3);
		//3 ȥ��
		for(int i=0;i<array3.length;i++){
			if(array3[i] == 0){
				array3 = replace(array3,newArr,i);
			}
		}
		return  removeZero(array3);
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
		if(size <0) throw new IndexOutOfBoundsException();
		int length = oldArray.length;
		int [] newArray = new int[length+size];
		System.arraycopy(oldArray, 0, newArray, 0, length);
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
		int[] result = null;
		if (max == 1) return result = new int[0];
		if (max == 2) return result = new int[]{1};
		
		int a =1,b=1,c=0;
		int aIndex = 0;
		int bIndex = 0;
		int cIndex = 3;
		result = new int[max];
		result[0] =1;
		result[1] =1;
		int rIndex = 2;
		for(int i=0;i<max;i++){
			
			if(cIndex == 3){
				cIndex = 0;
				aIndex = 3;
				c = a+b;
				if(c>=max){break;}
				//printStr(c+",");
				result[rIndex] = c;
				rIndex +=1;
			}else if(aIndex == 3){
				aIndex = 0;
				bIndex = 3;
				a = b+c;
				if(a>=max){break;}
				//printStr(c+",");
				result[rIndex] = a;
				rIndex +=1;
			}else if(bIndex == 3){
				cIndex = 3;
				bIndex = 0;
				b = c+a;
				if(b>=max){break;}
				//printStr(c+",");
				result[rIndex] = b;
				rIndex +=1;
			}
		}
		
		
		return removeZero(result);
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		int [] result = new int[max];
		result[0] =2;
		result[1] =3;
		result[2] =5;
		result[3] =7;
		int index = 4;
		for(int i = 8;i<max;i++){
			if(isPrimes(i)){
				result[index] = i;
				index+=1;
			}
		}
		return removeZero(result);
	}
	/**
	 * �ж�num �Ƿ�Ϊ����
	 * @param num
	 * @return
	 */
	public static boolean isPrimes(int num){
		boolean flag0 = num%2 == 0;
		boolean flag1 = num%3 == 0;
		boolean flag2 = num%5 == 0;
		boolean flag3 = num%7 == 0;
		if(!flag0 && !flag1 && !flag2 && !flag3) return true;
		return false;
	}
	
	/**
	 * ��ȡһ�����ֵ����� 
	 * @param num
	 * @return
	 */
	public static int[] getYinzi(int num){
		int [] res = new int[num];
		int index =0;
		for(int i=1;i<num;i++){
			if(num%i == 0){
				res[index] = i;
				index +=1;
			} 
		}
		return removeZero(res);
	}
	/**
	 * ��ȡһ���������� ��ֵ�ĺ�
	 * @param sour
	 * @return
	 */
	public static int sum(int[] sour){
		int sum = 0;
		for(int num : sour){
			sum+= num;
		}
		return sum;
	}
	/**
	 * �ж� num  �Ƿ� ������
	 * @param num
	 * @return
	 */
	public static boolean isPerfectNumber(int num){
		int [] res = getYinzi(num);
		int sum = sum(res);
		if(sum == num) return true;
		return false;
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int[] res = new int[max];
		int index=0;
		for(int i=0;i<max;i++){
			if(isPerfectNumber(i)) {
				res[index] = i;
				index+=1;
			}
		}
		
		return removeZero(res);
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
		//Arrays.toString()�� ����(,)�� �пո�
		String str = Arrays.toString(array);
		str = str.replaceAll(", ", seperator);
		return str.substring(1,str.length()-1);
	}
	
	
	
	public static void main(String[] args) {
		int [] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		StringUtil.printArr(removeZero(oldArray));
		//int [] a= new int[]{7,9,30,3} ;
		/*int [] a =new int[]{7,9,30,3,4};
		int[] arr = reverseArray(a);
		printStr("ԭֵ");
		printArr(a);
		printStr("ת�����ֵ");
		printArr(arr);*/
		/*int [] oldArray = new int []{2,3,6};
		int size = 3;
		printArr(grow(oldArray,size));*/
		/*printArr(fibonacci(13));*/
		/*printArr(getPrimes(23));*/
		//printArr(getYinzi(6));
		/*int num = 496;
		int [] res = getYinzi(num);
		printArr(res);
		int sum = sum(res);
		printlnStr(sum);*/
		int num = 497;
		StringUtil.printArr(getPerfectNumbers(num));
		/*int [] a =new int[]{7,9,30,3,4};
		String seperator= "-";
		printlnStr(join(a,seperator));*/
		
		/*int[] a1 = new int[]{3, 5, 7,8}; 
		int[] a2 = new int[]{4, 5, 6,7,9};
		int [] a3 = merge(a1, a2);
		StringUtil.printArr(a3);*/
		//int[] a1 = new int[]{3, 5, 7,8,1,3,42,2,6};
		//printArr(sort(a1));
		
	}

}
