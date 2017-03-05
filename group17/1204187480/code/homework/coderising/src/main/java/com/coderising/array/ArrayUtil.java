package com.coderising.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayUtil {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
	    int length = origin.length;
	    int mid = length >> 1;
		for (int i = 0; i < mid; i++) {
		    int hIndex = length - 1 -i;
		    int l = origin[i];
		    int h = origin[hIndex];
		    origin[hIndex] = l;
		    origin[i] = h;
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
	    int removeValue = 0;
	    return removeValue(oldArray, removeValue);
	}

    private int[] removeValue(int[] oldArray, int removeValue) {
	    int length = oldArray.length;
	    int[] dest = new int[length];
	    int j = 0;
	    for(int i = 0; i < length; i++) {
	        if (i != 0) {
	            dest[j++] = oldArray[i];
            }
        }

        int retLenth = --j;
        int[] retArray = new int[retLenth];
	    System.arraycopy(dest, 0, retArray, 0, retLenth);
        return retArray;
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
		int length = length1 + length2;
	    int[] newArray = new int[length];

        int l1 = array1[array1.length - 1];
        int l2 = array2[array2.length - 1];
	    return findAndSetLeastWithOutDuplicate(array1, array2, 0, 0, 0, 0, newArray);
	}

    /**
     * todo 优化递归出口判断, 优化三个条件判断为一个
     * @param array1
     * @param array2
     * @param i
     * @param j
     * @param k
     * @param duplicate
     * @param newArray
     * @return
     */
    private int[] findAndSetLeastWithOutDuplicate(int[] array1, int[] array2, int i, int j, int k, int duplicate, int[] newArray) {

	        if (i == array1.length && j < array2.length) {
	            System.arraycopy(array2, j, newArray, k, array2.length - j);
                return copyLastValues(newArray, duplicate);
            }
            if (j == array2.length && i < array1.length) {
                System.arraycopy(array1, i, newArray, k, array1.length - i);
                return copyLastValues(newArray, duplicate);
            }
            if (j == array2.length && i == array1.length) {
                return copyLastValues(newArray, duplicate);
            }

            int v1 = array1[i];
            int v2 = array2[j];
            if (v1 < v2) {
                newArray [k] = v1;
                return findAndSetLeastWithOutDuplicate(array1, array2, ++i, j,  ++k, duplicate, newArray);
            } else if (v1 > v2){
                newArray [k] = v2;
                return findAndSetLeastWithOutDuplicate(array1, array2, i, ++j,  ++k, duplicate, newArray);
            } else {
                newArray [k] = v2;
                return findAndSetLeastWithOutDuplicate(array1, array2, ++i, ++j,  ++k, ++duplicate, newArray);
            }

    }

    private int[] copyLastValues(int[] newArray, int duplicate) {
        int[] retArray = new int[newArray.length - duplicate];
        System.arraycopy(newArray, 0, retArray, 0, retArray.length);
        return retArray;
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
		return null;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		return null;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		return null;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		return null;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public String join(int[] array, String seperator){
		return null;
	}
	

}
