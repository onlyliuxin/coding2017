package com.coderising.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author haipop Date: 17-3-2 Time: 下午3:13
 */
public class ArrayUtil {

    /**
     * <pre>
     * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 
     * 置换后为 [3, 30, 9,7] 如果 a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * </pre>
     */
    public void reverseArray(int[] origin) {
        if (origin == null || Objects.equals(origin.length, 0)) {
            return;
        }
        int left = 0;
        int right = origin.length - 1;
        int tmp;
        while (left < right) {
            tmp = origin[left];
            origin[left] = origin[right];
            origin[right] = tmp;
            left++;
            right++;
        }
    }

    /**
     * <pre>
     * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * </pre>
     */
    public int[] removeZero(int... oldArray) {
        if (oldArray == null || Objects.equals(oldArray.length, 0)) {
            return new int[0];
        }
        int[] newArray = new int[oldArray.length];
        int index = 0;
        for (int ele : oldArray) {
            if (!Objects.equals(0, ele)) {
                newArray[index++] = ele;
            }
        }
        return newArray;
    }

    /**
     * <pre>
     * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 
     * 并且仍然是有序的 例如 a1 = [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3
     * 为[3,4,5,6,7,8] , 注意： 已经消除了重复
     * </pre>
     */
    public int[] merge(int[] array1, int[] array2) {
        int left = 0;
        int right = 0;
        int[] result = new int[array1.length + array2.length];
        int index = 0;
        while (left < array1.length && right < array2.length) {
            if (array1[left] <= array2[right]) {
                result[index++] = array1[left++];
            } else {
                result[index++] = array2[right++];
            }
        }
        if (left < array1.length - 1) {
            for (int i = left; i < array1.length; i++) {
                result[index++] = array1[i];
            }
        }
        if (right < array2.length - 1) {
            for (int i = left; i < array2.length; i++) {
                result[index++] = array2[i];
            }
        }
        return result;
    }

    /**
     * <pre>
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size 注意，
     * 老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size =
     * 3,则返回的新数组为 [2,3,6,0,0,0]
     * </pre>
     **/
    public int[] grow(int[] oldArray, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("数组扩容的大小必须大于0");
        }
        int targetLength;
        if (oldArray == null) {
            targetLength = size;
        } else {
            targetLength = oldArray.length + size;
        }
        return buildResult(targetLength, oldArray, 0);
    }

    private int[] buildResult(int targetLength, int[] origin, final int defaultValue) {
        int[] result = new int[targetLength];
        if (origin == null) {
            for (int i = 0; i < targetLength; i++) {
                result[i] = defaultValue;
            }
            return result;
        }
        for (int i = 0; i < targetLength; i++) {
            if (i < origin.length) {
                result[i] = origin[i];
                continue;
            }
            result[i] = defaultValue;
        }
        return result;
    }

    /**
     * <pre>
     * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
     * </pre>
     */
    public Integer[] fibonacci(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("上限不能小于0");
        }
        if (max == 0) {
            return null;
        }
        int begin = 0;
        int next = 1;
        int tmp = 0;
        List<Integer> result = new LinkedList<Integer>();
        while (tmp < max) {
            tmp = begin + next;
            begin = next;
            next = tmp;
            result.add(tmp);
        }
        return (Integer[]) result.toArray();
    }

    /**
     * <pre>
     * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * </pre>
     */
    public Integer[] getPrimes(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("上限不能小于0");
        }
        if (max == 0) {
            return null;
        }
        List<Integer> result = new LinkedList<Integer>();
        int[] keyList = new int[] { 2, 3, 5, 7, 11 };
        for (int i = 0; i < max; i++) {
            boolean flag = true;
            for (int key : keyList) {
                flag &= (key % keyList[i] == 0);
            }
            if (flag) {
                result.add(i);
            }
        }
        return (Integer[]) result.toArray();
    }

    /**
     * <pre>
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * </pre>
     */
    public Integer[] getPerfectNumbers(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("上限不能小于0");
        }
        if (max == 0) {
            return null;
        }
        List<Integer> result = new LinkedList<Integer>();
        for (int i = 1; i <= max; i++) {
            int count = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    count += j;
                }
            }
            if (count == i) {
                result.add(count);
            }
        }
        return (Integer[]) result.toArray();
    }

    /**
     * <pre>
     * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
     * </pre>
     */
    public String join(int[] array, String seperator) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder cache = new StringBuilder();
        cache.append(array[0]).append(seperator);
        for (int i = 1; i < array.length; i++) {
            cache.append(seperator).append(array[i]);
        }
        return cache.toString();
    }

}
