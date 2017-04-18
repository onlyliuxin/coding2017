package com.coderising.array;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayUtil
 *
 * Created by Korben on 26/02/2017.
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     */
    public static void reverseArray(int[] origin) {
        ensureNotNull(origin);

        int length = origin.length;
        for (int i = 0; i < length / 2; i++) {
            int tmp = origin[i];
            origin[i] = origin[length - i - 1];
            origin[length - i - 1] = tmp;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     */
    public static int[] removeZero(int[] oldArray) {
        ensureNotNull(oldArray);

        int nonZeroCount = 0;
        for (int i : oldArray) {
            if (i != 0) {
                nonZeroCount++;
            }
        }

        int newArr[] = new int[nonZeroCount];
        int newArrIndex = 0;
        for (int i : oldArray) {
            if (i != 0) {
                newArr[newArrIndex++] = i;
            }
        }

        return newArr;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     */
    public static int[] merge(int[] array1, int[] array2) {
        ensureNotNull(array1);
        ensureNotNull(array2);

        int maxArraySize = array1.length + array2.length;
        int[] mergedArray = new int[maxArraySize];

        int index1 = 0;
        int index2 = 0;
        int mergedIndex = -1;
        for (int i = 0; i < maxArraySize; i++) {
            if (index1 == array1.length) {
                System.arraycopy(array2, index2, mergedArray, mergedIndex + 1, array2.length - index2);
                mergedIndex += array2.length - index2;
                break;
            } else if (index2 == array2.length) {
                System.arraycopy(array1, index1, mergedArray, mergedIndex + 1, array1.length - index1);
                mergedIndex += array1.length - index1;
                break;
            } else {
                int compare = Integer.compare(array1[index1], array2[index2]);
                if (compare < 0) {
                    mergedArray[++mergedIndex] = array1[index1++];
                } else if (compare > 0) {
                    mergedArray[++mergedIndex] = array2[index2++];
                } else {
                    mergedArray[++mergedIndex] = array1[index1++];
                    index2++;
                }
            }
        }

        // 清除数组多余部分
        if (mergedIndex + 1 < maxArraySize) {
            int[] resultArray = new int[mergedIndex + 1];
            System.arraycopy(mergedArray, 0, resultArray, 0, mergedIndex + 1);
            return resultArray;
        }

        return mergedArray;
    }

    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     */
    public static int[] grow(int[] oldArray, int size) {
        ensureNotNull(oldArray);

        if (size < 0) {
            throw new IllegalArgumentException("size must > 0");
        }

        int[] newArray = new int[oldArray.length + size];

        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        for (int i = oldArray.length; i < newArray.length; i++) {
            newArray[i] = 0;
        }

        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     */
    public static int[] fibonacci(int max) {
        if (max == 1) {
            int[] array = new int[1];
            array[1] = 1;
            return array;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 1; ; i++) {
            int fibonacciNumber = getFibonacciNumber(i);
            if (fibonacciNumber <= max) {
                list.add(fibonacciNumber);
            } else {
                break;
            }
        }

        return list2Array(list);
    }

    private static int getFibonacciNumber(int index) {
        if (index == 1) {
            return 1;
        }
        if (index == 2) {
            return 1;
        }
        return getFibonacciNumber(index - 2) + getFibonacciNumber(index - 1);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     */
    public static int[] getPrimes(int max) {
        List<Integer> primeList = new ArrayList<>();
        if (max <= 1) {
            return new int[0];
        }

        if (max >= 2) {
            primeList.add(2);
        }

        // 所有偶数都不是素数, 所以这里采用 i += 2
        for (int i = 3; i < max; i += 2) {
            if (isPrimeNumber(i, primeList)) {
                primeList.add(i);
            }
        }

        return list2Array(primeList);
    }

    private static boolean isPrimeNumber(int number, List<Integer> primeList) {
        for (Integer prime : primeList) {
            if (number % prime == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     */
    public static int[] getPerfectNumbers(int max) {
        if (max <= 1) {
            return new int[0];
        }

        List<Integer> perfectNumberList = new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (isPerfectNumber(i)) {
                perfectNumberList.add(i);
            }
        }
        return list2Array(perfectNumberList);
    }

    private static boolean isPerfectNumber(int number) {
        int sum = 1;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
    }

    /**
     * 用separator 把数组 array给连接起来
     * 例如array= [3,8,9], separator = "-"
     * 则返回值为"3-8-9"
     */
    public static String join(int[] array, String separator) {
        ensureNotNull(array);

        if (separator == null) {
            throw new NullPointerException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]);
            if (i != array.length - 1) {
                stringBuilder.append(separator);
            }
        }

        return stringBuilder.toString();
    }

    private static int[] list2Array(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private static void ensureNotNull(int[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
    }
}