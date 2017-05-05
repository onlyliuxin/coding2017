package com.coding.array;

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
        int length = origin.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[i] = origin[length -1 - i];
        }
//        origin = Arrays.copyOf(temp, origin.length);
        for (int i = 0; i< length; i++) {
            origin[i] = temp[i];
        }
    }


    /**
     *  现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray
     * @return
     */

    public int[] removeZero(int[] oldArray){
        int locationPointer = 0;//记录0出现位置的指针
        int indexPonter = 0;//记录0后面的那个非零的数出现位置的指针
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] == 0) {
                locationPointer = i;
                for (int j = i; j < oldArray.length; j++) {
                    if (oldArray[j] != 0) {
                        indexPonter = j;
                        break;
                    }
                }
            }
            oldArray[locationPointer] = oldArray[indexPonter];
            if (indexPonter != 0) {
                oldArray[indexPonter] = 0;
            }
        }
        int i;
        for (i = 0; i < oldArray.length; i++) {
            if (oldArray[i] == 0) {
                break;
            }
        }
        int[] newArray = new int[i];
        System.arraycopy(oldArray, 0, newArray, 0, newArray.length);

        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */

    public int[] merge(int[] array1, int[] array2){
        int length = array1.length + array2.length;
        int[] newArray = new int[length];
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] < array2[j]) {
                    newArray[i] = array1[i];
                } else {
                    newArray[i] = array2[j];
                }
            }
        }
        return  newArray;
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
        int length = oldArray.length + size;
        int[] newArray = new int[length];
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
    public int[] fibonacci(int max){
        if (max == 1) {
            return new int[0];
        }
        int a = 0;
        int b = 1;
        int c;
        int[] arr = new int[max];
        arr[0] = 1;
        int i = 0;
        do {
            c = a + b;
            a = b;
            b = c;
            arr[i++] = c;
        } while (c < max);
        int[] result = new int[i];
        System.arraycopy(arr, 0, result, 0, i);
        return result;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max){
         // 素数的定义： 只能被1和他本身整除的数
        //因为1不是素数，所以循环从2开始
        boolean flag = true;
        int[] primesTemp = new int[max];
        int index = 0;
        for (int i = 2; i < max; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j== 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                primesTemp[index] = i;
                index++;
            }
            flag = true;
        }
        int[] primes= new int[index];
        System.arraycopy(primesTemp, 0, primes, 0, primes.length);

        return primes;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max){
        int[] temp = new int[max];
        int index = 0;
        for (int i = 1; i < max; i++) {
            int sum = 0;
            for (int j = 1; j < i / 2 + 1; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                temp[index] = i;
                index++;
            }
        }
        int[] result = new int[index];
        System.arraycopy(temp, 0, result, 0, result.length);

        return result;
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
        String str = "";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                str = str + seperator + array[i];
            } else {
                str = str + array[i];
            }
        }
        return str;
    }

}