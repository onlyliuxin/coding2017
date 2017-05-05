package com.coderising.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public int[] reverseArray(int[] origin) {
        if (origin == null || 0 == origin.length) {
            throw new NullPointerException();
        }

        int N = origin.length;
        for (int i = 0; i < N / 2; i++) {
            int temp = origin[i];
            origin[i] = origin[N - 1 - i];
            origin[N - 1 - i] = temp;
        }

        return origin;
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
        if (oldArray == null || 0 == oldArray.length) {
            throw new NullPointerException();
        }

        int count = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (0 != oldArray[i]) {
                count++;
            }
        }

        int[] newArray = new int[count];
        int j = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (0 != oldArray[i]) {
                newArray[j] = oldArray[i];
                j++;
            }
        }
        return newArray;
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
        if (array1.length == 0 && array2.length == 0) {
            throw new NullPointerException();
        } else if (array1.length == 0) {
            return array2;
        } else if (array2.length == 0) {
            return array1;
        }
        int count = array1.length + array2.length;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]) {
                    count--;
                }
            }
        }
        int[] newArray = new int[count];
        int m = 0, n = 0;
        for (int i = 0; i < count; i++) {
            if (m == array1.length) {
                System.arraycopy(array2, n, newArray, i, array2.length - n);
                break;
            }

            if (n == array2.length) {
                System.arraycopy(array1, m, newArray, i, array1.length - m);
                break;
            }

            if (array1[m] < array2[n]) {
                newArray[i] = array1[m];
                m++;
            } else if (array1[m] == array2[n]) {
                newArray[i] = array1[m];
                m++;
                n++;
            } else {
                newArray[i] = array2[n];
                n++;
            }
        }
        return newArray;
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
        if (oldArray == null || 0 == oldArray.length) {
            int[] array = new int[size];
            return array;
        }
        int[] target = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, target, 0, oldArray.length);
        return target;
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
        if (0 == max) {
            throw new IllegalArgumentException();
        } else if (1 == max) {
            int[] array = {};
            return array;
        }

        int length = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < max; i++) {
            if (fibo(i) < max) {
                length++;
                list.add(fibo(i));
            } else {
                break;
            }
        }

        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }


    public int fibo(int N) {
        if (N <= 2) {
            return 1;
        } else {
            return fibo(N - 1) + fibo(N - 2);
        }
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        List<Integer> list = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < max; i++) {
            if (isPrime(i)) {
                count++;
                list.add(i);
            }
        }

        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public boolean isPrime(int N) {
        if (N < 2) {
            return false;
        }

        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) {
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
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < max; i++) {
            if (isPerfectNumber(i)) {
                count++;
                list.add(i);
            }
        }

        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public boolean isPerfectNumber(int N) {
        int sum = 0;
        for (int i = 1; i < N; i++) {
            if (N % i == 0) {
                sum += i;
            }
        }
        return sum == N;
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]).append(seperator);
        }
        sb.append(array[array.length - 1]);
        return sb.toString();
    }
}
