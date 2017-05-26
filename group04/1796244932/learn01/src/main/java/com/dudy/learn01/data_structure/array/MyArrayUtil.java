package com.dudy.learn01.data_structure.array;

import java.util.*;

public class MyArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		//
		for (int i = 0; i < origin.length / 2; i++) {
			int temp = origin[i];
			origin[i] = origin[origin.length-1-i];
			origin[origin.length-1-i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int count = 0;


		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] == 0){
				count++;
			}
		}

		int resArray[]  = new int[oldArray.length - count];
		int j = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] > 0){
				resArray[j++] = oldArray[i];
			}
		}

		return resArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){

		Set set = new HashSet();

		for (int i = 0;i<array1.length; i++){
			set.add(array1[i]);
		}

		for (int i = 0;i<array2.length; i++){
			set.add(array2[i]);
		}

		int resArray[] = new int[set.size()];
		int j = 0;
		for (Iterator it= set.iterator();it.hasNext();){
			resArray[j++] = (int)it.next();
		}

		return resArray;
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

		int[] dest = new int[oldArray.length + size];

		System.arraycopy(oldArray,0,dest,0,oldArray.length);
		return dest;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static Integer[] fibonacci(int max){
	    if(max == 1){
	        return  new Integer[0];
        }

        List<Integer> restList = new ArrayList();
	    restList.add(1);
	    restList.add(1);

        while ((restList.get(restList.size() -2 ) + restList.get(restList.size() -1) )< max){
            restList.add(restList.get(restList.size() -2) + restList.get(restList.size() -1) );
        }

		return restList.toArray(new Integer[]{});
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static Integer[] getPrimes(int max){

	    List<Integer> list = new ArrayList<>();

	    for (int i = 1; i< max;i++){
	        if (isPrime(i)){
	            list.add(i);
            }
        }
		return list.toArray(new Integer[]{});
	}

    private static boolean isPrime(int num) {

	    boolean flag = true;
	    for (int i = 2; i< num ;i++){
	        if(num % i == 0){
	            flag = false;
            }
        }

        return  flag;

    }

    /**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static Integer[] getPerfectNumbers(int max){

	    List<Integer> list = new ArrayList();
	    for (int i = 1; i< max; i++){
	        int tmp = 0;
	        for (int j = 1; j < i/2 + 1 ;j++){
	            if (i % j == 0)
	                tmp += j;
            }

            if(tmp == i){
                list.add(i);
            }
        }


		return list.toArray(new Integer[]{});
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public static String join(int[] array, String seperator){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i< array.length - 1 ; i++){
			builder.append(array[i]).append(seperator);
		}
		builder.append(array[array.length-1]);
		return builder.toString();
	}
	

}
