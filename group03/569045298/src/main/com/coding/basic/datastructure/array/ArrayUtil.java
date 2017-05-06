package com.coding.basic.datastructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     */
    public int[] reverseArray(int[] origin) {
        if (origin == null) {
            return null;
        }
        for (int i = 0; i < origin.length / 2; i++) {
            int temp = origin[i];
            origin[i] = origin[origin.length - i - 1];
            origin[origin.length - i - 1] = temp;
        }
        return origin;
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     */
    public int[] removeZero(int[] oldArray) {
        if (oldArray == null) {
            return null;
        }
        int[] newArray = new int[oldArray.length];
        int index = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                newArray[index++] = oldArray[i];
            }
        }
        return Arrays.copyOf(newArray, index);
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     */
    public int[] merge(int[] array1, int[] array2) {
        if (array1 == null && array2 == null) {
            return null;
        }
        if (array1 == null || array2 == null) {
            return array1 == null ? array2 : array1;
        }
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < array1.length; i++) {
            set.add(array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            set.add(array2[i]);
        }
        return set2Array(set);
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     */
    public int[] grow(int[] oldArray, int size) {
        if (oldArray == null) {
            return null;
        }
        if (size < 0) {
            throw new IndexOutOfBoundsException();
        }
        int[] newArray = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     */
    public int[] fibonacci(int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            int f = fibonacci2(i);
            if (f < max) {
                list.add(f);
            } else {
                break;
            }
        }
        return list2Array(list);
    }

    public int fibonacci2(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci2(n - 1) + fibonacci2(n - 2);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     */
    public int[] getPrimes(int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list2Array(list);
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     */
    public int[] getPerfectNumbers(int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            int temp = 0;
            for (int n = 1; n < i / 2 + 1; n++) {
                if (i % n == 0) {
                    temp += n;
                }
            }
            if (temp == i) {
                list.add(i);
            }
        }
        return list2Array(list);
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     */
    public String join(int[] array, String seperator) {
        if (array == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                stringBuilder.append(seperator);
            }
            stringBuilder.append(array[i]);
        }
        return stringBuilder.toString();
    }

    private int[] list2Array(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private int[] set2Array(Set<Integer> set) {
        int[] result = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            result[index++] = iterator.next();
        }
        return result;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
