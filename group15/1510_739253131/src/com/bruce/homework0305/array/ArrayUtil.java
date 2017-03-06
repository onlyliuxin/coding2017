package com.bruce.homework0305.array;

import com.bruce.homework0226.LinkedListV00;

import java.util.*;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		if(origin != null && origin.length > 0) {
            int temp;
            for (int i = 0; i < origin.length / 2; i++) {
                temp = origin[i];
                origin[i] = origin[origin.length-1 - i];
                origin[origin.length-1 - i] = temp;
            }
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
        int[] result = null;
		if(oldArray != null && oldArray.length >= 0){
		    int index = 0;
            LinkedList linkedList = new LinkedList();
		    for(int i = 0;i<oldArray.length;i++){
		        if(oldArray[i] != 0){
		            linkedList.add(oldArray[i]);
                    index++;
                }
            }
            result = new int[index];
            for(int i = 0;i<index;i++){
                result[i] = (int)linkedList.get(i);
            }
        }
	    return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
        LinkedList<Integer> list = new LinkedList<>();
        getElementFromArray(array1,list);
        getElementFromArray(array2,list);
        Collections.sort(list);
        int[] result = new int[list.size()];
        for(int n = 0;n<list.size();n++){
            result[n] = list.get(n);
        }
        return result;
	}

	private void getElementFromArray(int[] array,LinkedList list){
        if(array != null){
            for(int i:array){
                if(list.contains(i)){
                    continue;
                }
                list.add(i);
            }
        }
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
	    if(oldArray == null){
	        return null;
        }
		int[] result = new int[oldArray.length+size];
        System.arraycopy(oldArray,0,result,0,oldArray.length);
        for(int i = 0;i<size;i++){
            result[oldArray.length+i] = 0;
        }
	    return result;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		int[] result = null;
		if (max <= 0 || max > Integer.MAX_VALUE) {
		    return result;//抛出异常
        } else if (max == 1) {
        	result = new int[0];
		} else {
			ArrayList<Integer> list = new ArrayList();
			list.add(1);
			list.add(1);
			for (int i = 2;i<Integer.MAX_VALUE;i++) {
				if((list.get(i-2) + list.get(i-1)) > max){
					break;
				}
				list.add(i,(list.get(i-2)+list.get(i-1)));
			}
			result = new int[list.size()];
			for (int j=0;j<list.size();j++) {
				result[j] = list.get(j);
			}
		}
		return result;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] result = null;
		if (max <= 0 || max > Integer.MAX_VALUE) {
			return result;//抛出异常
		} else if(max == 1){
			result = new int[0];
		}else {
			ArrayList<Integer> list = new ArrayList<>();
			for (int i=2 ; i<=max ; i++) {
				if(isPrimes(i)){
					list.add(i);
				}
			}
			result = new int[list.size()];
			for(int m = 0 ; m<list.size() ; m++){
				result[m] = list.get(m);
			}
		}
		return result;
	}

	//判断一个数是否是素数，true：是素数；false：不是素数。
	private boolean isPrimes(int value){
		for(int i=2 ; i <= Math.sqrt(value) ; i++){
			if(value % i == 0){
				return false;
			}
		}
		return true;
	}
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] result = null;
		if(max <= 0 || max > Integer.MAX_VALUE){
			return result;
		} else {
			LinkedList<Integer> list = new LinkedList<>();
			for(int i = 1 ; i < max ; i++){
				if (isPerfectNumber(i)) {
					list.add(i);
				}
			}
			result = new int[list.size()];
			for (int i = 0 ; i < list.size() ; i++) {
				result[i] = list.get(i);
			}
		}
		return result;
	}

	//判断一个数是否是完数，true：是完数；false：不是完数。
	public boolean isPerfectNumber(int value){
		int sum = 0;
		for(int i = 1 ; i < value ; i++){
			if(value % i == 0){
				sum += i;
			}
		}
		return sum == value;
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
		StringBuffer sb = new StringBuffer();
		if (array != null && array.length >= 1) {
			for (int i = 0 ; i < array.length ; i++) {
				sb.append(array[i]);
				if (i < array.length-1) {
					sb.append(seperator);
				}
			}
		}
		return sb.toString();
	}
	

}
