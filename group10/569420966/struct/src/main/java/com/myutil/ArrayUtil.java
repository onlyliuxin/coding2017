package com.myutil;

import java.util.Arrays;

/**
 * 数组工具类
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果   a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin 源数组
     */
    public static void reverseArray(int[] origin) {
        if (origin == null) {
            return;
        }
        int originLen = origin.length;
        int[] copyArray = new int[originLen];
        System.arraycopy(origin, 0, copyArray, 0, originLen);
        for (int i = 0; i < originLen; i++) {
            origin[i] = copyArray[originLen - 1 - i];
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     *
     * @param oldArray 老数组
     * @return 去除零后的数组
     */

    public static int[] removeZero(int[] oldArray) {
        if (oldArray == null) {
            return null;
        }
        int size = 0;
        for (int i : oldArray) {
            size += i == 0 ? 0 : 1;
        }
        int[] newArray = new int[size];
        int newIndex = 0;
        for (int old : oldArray) {
            if (old != 0) {
                newArray[newIndex++] = old;
            }
        }
        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     *
     * @param array1 第一个数组
     * @param array2 第二个数组
     * @return 合并后的数组
     */

    public static int[] merge(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            return null;
        }
        int len1 = array1.length;
        int len2 = array2.length;
        int[] newArray = new int[len1 + len2];
        int size = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < len1 && j < len2) {
            if (array1[i] < array2[j]) {
                if (k == 0 || newArray[k] != array2[i]) {
                    newArray[k++] = array1[i++];
                    size++;
                } else {
                    i++;
                }
            } else if (array1[i] > array2[j]) {
                if (k == 0 || newArray[k] != array2[j]) {
                    newArray[k++] = array2[j++];
                    size++;
                } else {
                    j++;
                }
            } else {
                if (k == 0 || newArray[k] != array2[i]) {
                    newArray[k++] = array2[j];
                    i++;
                    j++;
                    size++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        while (i < len1) {
            if (k == 0 || newArray[k] != array1[i]) {
                newArray[k++] = array1[i++];
                size++;
            } else {
                i++;
            }
        }
        while (j < len2) {
            if (k == 0 || newArray[k] != array2[j]) {
                newArray[k++] = array2[j++];
                size++;
            } else {
                j++;
            }
        }
        int[] resizeArray = new int[size];
        System.arraycopy(newArray, 0, resizeArray, 0, size);
        return resizeArray;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     *
     * @param oldArray 老数组
     * @param size     需扩容大小
     * @return 扩容后数组
     */
    public int[] grow(int[] oldArray, int size) {
        if (oldArray == null || size <= 0) {
            return oldArray;
        }
        int[] newArray = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max 最大值
     * @return 小于最大值的fibonacci数列的数组
     */
    public static int[] fibonacci(int max) {
        ArrayList<Integer> fibonacciList = new ArrayList<>();
        int a1 = 1;
        int a2 = 1;
        int t;
        if (a1 < max) {
            fibonacciList.add(a1);
        }
        while (a2 < max) {
            fibonacciList.add(a2);
            t = a2;
            a2 = a1 + a2;
            a1 = t;
        }
        int[] fibonacciArray = new int[fibonacciList.size()];
        for (int i = 0; i < fibonacciList.size(); i++) {
            fibonacciArray[i] = fibonacciList.get(i);
        }
        return fibonacciArray;
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max 最大值
     * @return 小于最大值的所有素数
     */
    public static int[] getPrimes(int max) {
        ArrayList<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                primeList.add(i);
            }
        }
        int[] primeArray = new int[primeList.size()];
        for (int i = 0; i < primeList.size(); i++) {
            primeArray[i] = primeList.get(i);
        }
        return primeArray;
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max 最大值
     * @return 小于最大值的所有完数
     */
    public static int[] getPerfectNumbers(int max) {
        ArrayList<Integer> perfectList = new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPrefectNumber(i)) {
                perfectList.add(i);
            }
        }
        int[] perfectArray = new int[perfectList.size()];
        for (int i = 0; i < perfectList.size(); i++) {
            perfectArray[i] = perfectList.get(i);
        }
        return perfectArray;
    }

    private static boolean isPrefectNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return num == sum;
    }

    /**
     * 用separator 把数组 array给连接起来
     * 例如array= [3,8,9], separator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array     数组
     * @param separator 分隔符
     * @return 用分隔符连接的数字字符串
     */
    public static String join(int[] array, String separator) {
        if (array == null || separator == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i).append(separator);
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getPerfectNumbers(1000)));
    }
}