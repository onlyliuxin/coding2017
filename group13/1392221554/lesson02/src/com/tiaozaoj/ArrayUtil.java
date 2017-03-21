package com.tiaozaoj;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
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
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
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
	 * 给定两个已经排序好的整形数组， a1和a2 ,创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
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
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
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
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
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
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
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
                //除了1和他本身不能被其他的数整除（只能被整除两次）
                if(j == 2){
                    a[k++] = z;
                }
        } 
		return Arrays.copyOfRange(a,0,k);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] a = new int[max];
		int k = 0;
		for (int i = 1; i <= max; i++) {  			 
            int temp = 0;// 定义因子之和变量    
            for (int n = 1; n < i / 2 + 1; n++) {  
                if (i % n == 0) {  
                    temp += n;// 能被整除的除数则被加到temp中  
                }  
            }  
            if (temp == i) {// 如果因子之和与原数相等的话，说明是完数  
                a[k++] = i;  
            }  
        }  
		return Arrays.copyOfRange(a,0,k);
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
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