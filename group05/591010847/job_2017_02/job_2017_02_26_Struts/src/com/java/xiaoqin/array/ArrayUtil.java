package com.java.xiaoqin.array;

import java.util.Objects;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        if (null != origin) {
            int[] temp = new int[origin.length];
            System.arraycopy(origin, 0, temp, 0, origin.length);
            for (int index = origin.length; index > 0; index--) {
                origin[origin.length - index] = temp[index - 1];
            }
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     *
     * @param oldArray
     * @return
     */

    public int[] removeZero(int[] oldArray) {
        int size = 0;
        int[] newArray = new int[oldArray.length];
        for (int indexO = 0; indexO < oldArray.length; indexO++) {
            if (0 == oldArray[indexO]) {
                continue;
            }
            newArray[size++] = oldArray[indexO];
        }
        int[] returnArray = new int[size];
        System.arraycopy(newArray, 0, returnArray, 0, returnArray.length);
        return returnArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     *
     * @param array1
     * @param array2
     * @return
     */

    public int[] merge(int[] array1, int[] array2) {
        int[] mergeArray = new int[array1.length + array2.length];
        int size = 0;
        for (int index = 0, max = Math.max(array1.length, array2.length); index < max; index++) {
            Object arrObj1 = null;
            Object arrObj2 = null;
            if (index < array1.length) {
                arrObj1 = array1[index];
            }
            if (index < array2.length) {
                arrObj2 = array2[index];
            }
            if (null != arrObj1 && Objects.equals(arrObj1, arrObj2)) {
                arrObj2 = null;
            }
            for (int indexMerge = 0; indexMerge < size; indexMerge++) {
                if (Objects.equals(arrObj1, mergeArray[indexMerge])) {
                    arrObj1 = null;
                }
                if (Objects.equals(arrObj2, mergeArray[indexMerge])) {
                    arrObj2 = null;
                    break;
                }
            }
            if (null != arrObj1 && null != arrObj2 && (int) arrObj1 > (int) arrObj2) {
                arrObj1 = (int) arrObj1 + (int) arrObj2;
                arrObj2 = (int) arrObj1 - (int) arrObj2;
                arrObj1 = (int) arrObj1 - (int) arrObj2;
            }
            if (null != arrObj1) {
                mergeArray[size++] = (int) arrObj1;
            }
            if (null != arrObj2) {
                mergeArray[size++] = (int) arrObj2;
            }
        }
        int[] resultArr = new int[size];
        System.arraycopy(mergeArray, 0, resultArr, 0, size);
        return resultArr;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     *
     * @param oldArray
     * @param size
     * @return
     */
    public int[] grow(int[] oldArray, int size) {
        int[] newArray = new int[oldArray.length + size];
        for (int indexOld = 0; indexOld < oldArray.length; indexOld++) {
            newArray[indexOld] = oldArray[indexOld];
        }
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {
        int[] newArray = new int[0];
        if (max > 1) {
            int size = 0;
            newArray = new int[max];
            if (max >= 1) {
                for (int n = 1; n < Integer.MAX_VALUE; n++) {
                    int fibonacci = mFibonacci(n);
                    if (fibonacci < max) {
                        newArray[size++] = fibonacci;
                    } else {
                        break;
                    }
                }
            }
            int[] resultArr = new int[size];
            System.arraycopy(newArray, 0, resultArr, 0, size);
            newArray = resultArr;
        }
        return newArray;
    }

    private int mFibonacci(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return mFibonacci(n - 1) + mFibonacci(n - 2);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int[] newArray = new int[max];
        int size = 0;
        for (int index = 2; index < max; index++) {
            if (isPrimes(index)) {
                newArray[size++] = index;
            }
        }
        int[] resultArr = new int[size];
        System.arraycopy(newArray, 0, resultArr, 0, size);
        return resultArr;
    }

    private boolean isPrimes(int number) {
        int i = 1;
        while (++i < number) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        int[] newArray = new int[max];
        int size = 0;
        for (int i = 6; i < max; i++) {
            int total = 0;
            for (int index = 1, length = i / 2; index <= length; index++) {
                if (i % index == 0) {
                    total += index;
                }
            }
            if (i == total) {
                newArray[size++] = i;
            }
        }
        int[] resultArr = new int[size];
        System.arraycopy(newArray, 0, resultArr, 0, size);
        return resultArr;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param seperator
     * @return
     */
    public String join(int[] array, String seperator) {
        StringBuilder arrBuilder = new StringBuilder();
        for (int arr : array) {
            if (arrBuilder.length() > 0) {
                arrBuilder.append(seperator);
            }
            arrBuilder.append(arr);
        }
        return arrBuilder.toString();
    }


}
