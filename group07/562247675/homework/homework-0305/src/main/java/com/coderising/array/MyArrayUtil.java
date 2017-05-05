package com.coderising.array;

import java.util.Arrays;

public final class MyArrayUtil {

    public static void main(String[] args) {
        int[] reverse1 = {7, 9, 30, 3};
        int[] reverse2 = {7, 9, 30, 3, 4};
        reverseArray(reverse1);
        reverseArray(reverse2);
        System.out.println("reverseArray: " + Arrays.toString(reverse1) + " " + Arrays.toString(reverse2));
        int[] removeZero = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        System.out.println("removeZero: " + Arrays.toString(removeZero(removeZero)));
        int[] merge1 = {3, 5, 7, 8};
        int[] merge2 = {4, 5, 6, 7};
        System.out.println("merge: " + Arrays.toString(merge(merge1, merge2)));
        System.out.println("merge: " + Arrays.toString(merge(merge2, merge1)));
        int[] grow = {2, 3, 6};
        System.out.println("grow: " + Arrays.toString(grow(grow, 3)));
        System.out.println("fibonacci: f(15)=" + Arrays.toString(fibonacci(15)) + " f(1)=" + Arrays.toString(fibonacci(1)));
        System.out.println("getPrimes: g(23)=" + Arrays.toString(getPrimes(23)));
        System.out.println("getPerfectNumbers: g(500)=" + Arrays.toString(getPerfectNumbers(500)));
        System.out.println("getPerfectNumbers: j(f(50))=" + join(fibonacci(50), "-"));

    }

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public static void reverseArray(int[] origin) {
        int len = origin.length;
        for (int i = 0; i < len / 2; i++) {
            int j = len - 1 - i;
            int temp = origin[i];
            origin[i] = origin[j];
            origin[j] = temp;
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
    public static int[] removeZero(int[] oldArray) {
        int length = oldArray.length;
        // 空间换时间复杂度 O(2*n-m) n为数组长度 m为0的个数
        int[] src = new int[length];
        int last = 0;
        for (int i = 0; i < length; i++) {
            int m = oldArray[i];
            if (m != 0) {
                src[last++] = m;
            }
        }
        int[] dest = new int[last];
        System.arraycopy(src, 0, dest, 0, last);
        return dest;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     *
     * @param array1
     * @param array2
     * @return
     */
    public static int[] merge(int[] array1, int[] array2) {
        int[] src = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0; //i=数组1游标 j=数组2游标 k=目标数组下标
        // 时间复杂度 O(n+m-k) n为数组1长度 m为数组2长度 k为重复数组个数
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) { //大小比较入目标数组
                src[k++] = array1[i++];
            } else if (array1[i] > array2[j]) {
                src[k++] = array2[j++];
            } else { //等值同时移动，但只入1个目标值
                src[k++] = array1[i++];
                j++;
            }
        }
        // 只要1个数组下表越界，就直接添加另1个数组尾部所有有序值
        while (i < array1.length) {
            src[k++] = array1[i++];
        }
        while (j < array2.length) {
            src[k++] = array2[j++];
        }
        int[] dest = new int[k];
        System.arraycopy(src, 0, dest, 0, k);
        return dest;
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
    public static int[] grow(int[] oldArray, int size) {
        int[] newArr = new int[oldArray.length + size];
        System.arraycopy(oldArray, 0, newArr, 0, oldArray.length);
        return newArr;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     *
     * @param max
     * @return
     */
    public static int[] fibonacci(int max) {
        if (max < 2) { // 额外出口 f(1) = []
            return new int[]{};
        }
        if (max == 2) { //递归出口 f(2) = [1, 1]
            return new int[]{1, 1};
        }
        // f(3) = [1,1,2]
        // f(5) = f(4) = [1,1,2,3]
        // f(8) = f(7) = f(6) = [1,1,2,3,5]

        // 递归拿上个数列最后的两个数
        int[] src = fibonacci(max - 1);
        // 判断这两个数的和值是否在max范围内
        int m = src[src.length - 1] + src[src.length - 2];
        if (m < max) {
            int[] dest = new int[src.length + 1];
            System.arraycopy(src, 0, dest, 0, src.length);
            dest[src.length] = m;
            return dest;
        } else {
            return src;
        }
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        if (max <= 2) { //特殊出口 g(1) = []
            return new int[]{};
        }
        if (max == 3) { //递归出口 g(3) = [2]
            return new int[]{2};
        }
        // g[3] = [2]
        // g[5] = g[4] = [2,3]
        // g[7] = g[6] = [2,3,5]
        // g[11] = g[10] = g[9] = g[8] = [2,3,5,7]

        // 递归拿上个数组最后的素数
        int[] src = getPrimes(max - 1);
        // 找上个素数和最大值之间的素数
        int before = src[src.length - 1];
        int next = before;
        int start = 1 + before;
        int end = max;
        for (int num = start; num < end; num++) {
            int i = 2;
            for (; i <= num / 2; i++) {
                if (num % i == 0) {
                    break;
                }
            }
            if (i > num / 2) {
                next = num;
                break;
            }
        }
        // 判断这个值是否存在
        if (next > before) {
            int[] dest = new int[src.length + 1];
            System.arraycopy(src, 0, dest, 0, src.length);
            dest[src.length] = next;
            return dest;
        } else {
            return src;
        }
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        if (max <= 6) { //特殊出口
            return new int[]{};
        }
        if (max == 7) { //递归出口
            return new int[]{6};
        }
        // 递归拿上个数组最后的完数
        int[] src = getPerfectNumbers(max - 1);
        // 找上个完数和最大值之间的完数
        int before = src[src.length - 1];
        int next = before;
        int start = 1 + before;
        int end = max;
        for (int num = start; num < end; num++) {
            int count = 0;
            for (int i = 1; i <= num / 2; i++) {
                if (num % i == 0) {
                    count += i;
                }
            }
            if (count == num) {
                next = num;
                break;
            }
        }
        // 判断这个值是否存在
        if (next > before) {
            int[] dest = new int[src.length + 1];
            System.arraycopy(src, 0, dest, 0, src.length);
            dest[src.length] = next;
            return dest;
        } else {
            return src;
        }
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
    public static String join(int[] array, String seperator) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sBuilder.append(array[i]).append(seperator);
        }
        if (sBuilder.length() > 0) {
            sBuilder.deleteCharAt(sBuilder.length() - 1);
        }
        return sBuilder.toString();
    }


}
