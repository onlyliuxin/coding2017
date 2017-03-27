package com.coderising.array;
import java.util.*;
public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin.length > 0 && origin != null){
			int temp;
            for (int i = 0; i < origin.length / 2; i++) {
                temp = origin[i];
                origin[i] = origin[origin.length-1 - i];
                origin[origin.length-1 - i] = temp;
            }
		}
		else {
			throw new IndexOutOfBoundsException("原数组有错" );
			}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		if(oldArray.length == 0){
			return oldArray;
		}
		int index = 0;
		for(int i =0; i< oldArray.length; i++){
			if(oldArray[index] != 0){
				oldArray[index] = oldArray[i];
				index++;
			}
		}
		 return Arrays.copyOf(oldArray, index);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
	    int length1 = array1.length;  
	    int length2 = array2.length;  
	    int newArrayLength = length1 + length2;  
	    int[] result = new int[newArrayLength];  
	    int i = 0, j = 0, k = 0;   //i:用于标示1数组    j：用来标示2数组  k：用来标示传入的数组  
	  
	    while (i < length1 && j < length2) {  
	        /* 去重复元素 */  
	        if (array1[i] < array2[j]) {  
	            result[k++] = array1[i++];  
	        } else if (array1[i] == array2[j]) {  
	            result[k++] = array1[i];  
	            //在某个位置上2个值相等的话，取哪个都一样，  
	            // 然后这个相等的位置的2个值都直接向后移动1，继续比较  
	            j++;  
	            i++;  
	        } else {  
	            result[k++] = array2[j++];  
	        }  
	    }  	  
	    /* 后面while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入结果数组 */  
	    while (i < length1) {  
	        result[k++] = array1[i++];  
	    }  
	  
	    while (j < length2) {  
	        result[k++] = array2[j++];  
	    }  
	    return Arrays.copyOf(result, k);
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
		int[] newArr = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, newArr, 0, oldArray.length);
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
		int[] array = new int[]{};
        for(int i=1; getfb(i)<max;i++){
            array = grow(array, 1);
            array[i - 1] = getfb(i);
        }
        return array;
	}
    //获取斐波那契数列中的元素
	public static int getfb(int i) {
		if (i == 1 || i == 2) {
			return 1;
		}
		return getfb(i - 1) + getfb(i - 2);
}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		 int[] prime = new int[]{2};
	        if (max < 1) throw new IllegalArgumentException("没有比1小的素数");
	        int j = 1;
	        for (int i = 3; i < max; i++) {
	            int k = 2;
	            //找素数
	            while (i > k) {
	                if (i % k == 0)
	                    break;
	                k++;
	            }
	            //扩容
	            prime = grow(prime, 1);
	            prime[j++] = i;
	        }
	        return prime;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
	        int[] array = new int[0];
	        int j = 0;
	        for (int i = 1; i < max; i++) {
	            if (isPerfect(i)) {
	                //加入到数组中
	                array = grow(array, 1);
	                array[j] = i;
	                j++;
	            }
	        }
	        return array;
	    }

	    //判断一个数是否是“完数”
	private static boolean isPerfect(int max) {
	        int i = 1;
	        int n = 0;
	        while (i < max) {
	            if (max % i == 0) {
	                n += i;
	            }
	            i++;
	        }
	        if (n == max)
	            return true;
	        return false;
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
		if (array == null)
            return null;
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s += array[i];
            if (i != array.length - 1)
                s += seperator;
        }
        return s;   
        }
	
}
	

