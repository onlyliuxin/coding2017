package org.learning.container;

import java.util.Arrays;

import org.comm.util.StringUtil;

public class ArrayUtil {

	/**
	 * 给定一个数组a，对该数组的值进行反转
	 * 例如： a= [7,9,30,3] ,置换后：[3,30,9,7]
	 * 如果：a= [7,9,30,3,4], 置换后[4,3,30,9,7] 
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
	 * 现在有如下一个数组， int [] oldArray = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组值为0的项去掉，将不为0的值存入到一个新的数组，生产的数组为
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
	 * 冒泡排序
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
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int [] newArr = new int[array1.length+array2.length];
		//1整合数据
		int array1L = removeZero(array1).length;
		int array2L = removeZero(array2).length;
		int [] array3 = new int[array1L+array2L];
		System.arraycopy(array1, 0, array3, 0, array1L);
		System.arraycopy(array2, 0, array3, array1L, array2L);
		//2排序
		sort(array3);
		//3 去重
		for(int i=0;i<array3.length;i++){
			if(array3[i] == 0){
				array3 = replace(array3,newArr,i);
			}
		}
		return  removeZero(array3);
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
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
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		int[] result = null;
		if (max == 1) return result = new int[0];
		if (max == 2) return result = new int[]{1,1};
		
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
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
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
	 * 判断num 是否为素数
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
	 * 获取一个数字的因数 
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
	 * 获取一个整型数组 中值的和
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
	 * 判断 num  是否 是完数
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
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
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
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		//Arrays.toString()后， 逗号(,)后 有空格
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
		printStr("原值");
		printArr(a);
		printStr("转换后的值");
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
