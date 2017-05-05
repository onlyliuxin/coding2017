package com.util_1;

import java.util.*;

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
        int[] oldArr = origin;
        int newLength = oldArr.length;
        int[] newArr = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            newArr[newLength - i - 1] = oldArr[i];
        }
        for (int s : newArr) {
            System.out.println(s);
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
        int[] oldArr = oldArray;
        int oldLength = oldArr.length;
        int[] newArr = new int[oldLength];
        int index = 0;
        int zeroCount = 0;
        for (int i = 0; i < oldLength; i++) {
            if (oldArr[i] == 0) {
                zeroCount++;
            } else {
                newArr[index++] = oldArr[i];
            }
        }
        int[] newArrs = Arrays.copyOf(newArr, index);
        return newArrs;
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
        int newLength = array1.length + array2.length;
        int[] newArr = new int[newLength];
        int index = 0;
        for (int i = 0; i < array1.length; i++) {
            newArr[index++] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            newArr[index++] = array2[i];
        }
        //冒泡排序
        for (int i = 0; i < newArr.length; i++) {
            for (int j = i + 1; j < newArr.length; j++) {
                if (newArr[i] > newArr[j]) {
                    int temp = newArr[i];
                    newArr[i] = newArr[j];
                    newArr[j] = temp;
                }
            }
        }
        //数组去重
        boolean[] b = new boolean[newArr.length];
        int counts = newArr.length;
        for (int i = 0; i < newArr.length; i++) {
            for (int j = i + 1; j < newArr.length; j++) {
                if (newArr[i] == newArr[j] && b[i] == false) {
                    b[j] = true;
                    counts--;
                }
            }
        }
        int[] result = new int[counts];
        int j = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (b[i] == false) {
                result[j] = newArr[i];
                j++;
            }
        }
        return result;
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
        int oldLength = oldArray.length;
        int newLength = oldLength + size;
        int[] result = Arrays.copyOf(oldArray, newLength);
        return result;
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
        int a = 1, b = 1, c = 2;
        int[] arr = new int[max + 1];
        int i = 2;
        if (max == 1) {
            return Arrays.copyOf(arr, 0);
        } else if (max <= 0) {
            throw new IllegalArgumentException("不能输入<=0的参数：" + max);
        } else {
            arr[0] = 1;
            arr[1] = 1;
            do {
                c = a + b;
                a = b;
                b = c;
                arr[i++] = c;
            } while (c < max);
        }

        if (arr[i - 1] >= max) {
            return Arrays.copyOf(arr, i - 1);
        }
        return Arrays.copyOf(arr, i);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {

        class IsPrime {
            // 判断某整数是否为素数
            public boolean isPrimes(int n) {
                if (n < 2) {
                    return false;
                }
                for (int i = 2; i * i <= n; i++) {
                    if (n % i == 0) {
                        return false;
                    }
                }
                return true;

            }
        }
        List list = new ArrayList();
        IsPrime isPrime = new IsPrime();
        for (int i = 2; i < max; i++) {
            if (isPrime.isPrimes(i)) {
                list.add(i);
            }
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = (int) list.get(i);
        }

        return arr;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        //保存每组的分解因子
        List list = new ArrayList();
        List pm = new ArrayList();
        int sum = 0;
        //除数
        for (int i = 2; i < max; i++) {
            //被除数
            sum=0;
            for (int j = 1; j < i / 2 + 1; j++) {
                if (i % j == 0) {
                    list.add(j);
                    sum += j;
                }
            }

            if (sum == i) {
                pm.add(i);
            }

            list.clear();
        }

        int[] pmaArr = new int[pm.size()];
        for (int i = 0; i < pm.size(); i++) {
            pmaArr[i] = (int) pm.get(i);
        }
        return pmaArr;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param
     * @return
     */

    public String join(int[] array, String seperator) {

        String s = new String();
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                s += array[i] + seperator;
            } else {
                s += array[i];
            }
        }
        return s;
    }


}
