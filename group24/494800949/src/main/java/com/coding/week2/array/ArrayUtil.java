package com.coding.week2.array;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] newArr = new int[origin.length];
		for(int i = origin.length - 1,j = 0; i >= 0; i--,j++){
			newArr[j] = origin[i];
		}
        System.arraycopy(newArr, 0, origin, 0, origin.length);
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
        int[] newArr = new int[oldArray.length];
        int j = 0;
        for(int i = 0; i < oldArray.length; i++){
            if(oldArray[i] != 0)
                newArr[j++] = oldArray[i];
        }
        int[] newArr1 = new int[j];
        System.arraycopy(newArr, 0, newArr1, 0, j);
		return newArr1;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
        int len = array1.length > array2.length ? array1.length : array2.length;
        int[] newArr = new int[len];
        for(int i = 0; i < array1.length; i++){
            for(int j : array2){
                if(array1[i] == j){
                    newArr[i] = array1[i];
                }
            }
        }
        int[] newArr2 = new int[array1.length - newArr.length];
        for(int i = 0; i < array1.length; i++){
            for(int j : newArr){
                if(array1[i] == j)
                    continue;
            }
        }
        int[] newArr1 = new int[array1.length + array2.length];
        System.arraycopy(array2, 0, newArr1, 0, array2.length);
        System.arraycopy(newArr, 0, newArr1, array2.length-1, newArr.length);
        bubuleSort(newArr1);
        return  newArr1;
	}

    public void bubuleSort(int[] newArr1){
        for(int i = 0; i < newArr1.length; i++){
            for(int j = 0; j < newArr1.length; j++)
                if(newArr1[i] < newArr1[j]){
                    int temp = newArr1[i];
                    newArr1[i] = newArr1[j] ;
                    newArr1[j] = temp;
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
        int[] newArr = new int[oldArray.length + size];
        for(int i = 0; i < newArr.length; i++){
            if (i < oldArray.length){
                newArr[i] = oldArray[i];
            }else
                newArr[i] = 0;
        }

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
        //1 得到小于max的斐波那契数
        //2

        if( max == 1)
            return  new int[]{};
        int[] arr = new int[3];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = arr[0] + arr[1];
        if(max == 2)
            return arr;
        int i = 2;
        while (max > arr[i]){
            if(i+1 >= arr.length){
                int capacity = arr.length + (arr.length >> 1);
                int[] newArr = new int[capacity];
                System.arraycopy(arr, 0, newArr, 0, arr.length);
                arr = newArr;
            }
            arr[++i] = arr[i - 1] + arr[i - 2];
            if(arr[i] < 0){
                System.out.println(Arrays.toString(arr));
                throw new OutOfMemoryError();
            }
        }

		return removeZero(arr);
	}



	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
        if(max < 2)
            return new int[]{};
        int[] arr = new int[max];
        for(int i = max; i >= 2; i--){
            if(isPrime(i)){
                arr[i] = i;
            }
        }
		return removeZero(arr);
	}

    private boolean isPrime(int value){
        for(int i = 2; i < Math.sqrt(value); i++){
            if(value % i == 0)
                return false;
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
        int[] ints = new int[max+1];
        for(int i = 1; i <= max; i++){
            if(isPerfectNum(i)){
                ints[i] = i;
            }
        }
		return removeZero(ints);
	}


    private boolean isPerfectNum(int value){
        if(value == 1)
            return false;
        int sum = 0;
        for(int i = 1; i <= Math.sqrt(value); i++){
            if(value % i == 0){
                sum += i + value / i;
            }
        }
        return sum-value == value;
    }
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param
	 * @return
	 */
	public String join(int[] array, String seperator){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < array.length; i++){
            builder.append(array[i]);
            if(i != array.length - 1)
                builder.append(seperator);
        }
        return builder.toString();
	}
	

}
