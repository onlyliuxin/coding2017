package com.code.coderising.array;

import org.junit.Test;
import java.util.Arrays;

/**
 * Created by Mori on 2017/3/2.
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果 a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        int n = origin.length;
        for (int i = 0, j = n >> 1; i < j; i++) {
            int temp = origin[i];
            origin[i] = origin[n - i - 1];
            origin[n - i - 1] = temp;
        }
    }

    @Test
    public void testReverseArray() {
        int[] a = {7, 9, 30, 3};
        System.out.println("原数组:" + Arrays.toString(a));
        reverseArray(a);
        System.out.println("reverseArray后:" + Arrays.toString(a));
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
        Arrays.sort(oldArray);
        int[] newArr = null;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != 0) {
                newArr = new int[oldArray.length - i];
                System.arraycopy(oldArray, i, newArr, 0, oldArray.length - i);
                break;
            }
        }
        return newArr;
    }

    @Test
    public void testRemoveZero() {
        int[] a = {7, 9, 0, 30, 0, 0, 3};
        System.out.println("原数组:" + Arrays.toString(a));
        int[] newArr = removeZero(a);
        System.out.println("removeZero后:" + Arrays.toString(newArr));
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
        int m = array1.length;
        int n = array2.length;
        int[] newArr = new int[m + n];
        int i = 0, j = 0, k = 0;
        for (; i < m && j < n; ) {
            if (array1[i] < array2[j]) {
                newArr[k++] = array1[i++];
            } else {
                newArr[k++] = array2[j++];
            }

        }
        while (i < m) {
            newArr[k++] = array1[i++];
        }
        while (j < n) {
            newArr[k++] = array2[j++];
        }
        if (newArr.length < 1)
            return null;
        int slow = 1;
        int count = 0;
        for (int fast = 1; fast < newArr.length; fast++) {
            if (newArr[fast] != newArr[slow - 1]) {
                newArr[slow++] = newArr[fast];
            } else {
                count++;
            }
        }
        int[] arr = new int[newArr.length - count];
        System.arraycopy(newArr, 0, arr, 0, newArr.length - count);
        return arr;
    }

    @Test
    public void testMerge() {
        int[] a1 = {3, 5, 7, 8, 10, 10, 10, 10, 10};
        int[] a2 = {4, 5, 6, 7, 10, 12};
        int[] newArray = merge(a1, a2);
        System.out.println("merge:" + Arrays.toString(newArray));
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
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    @Test
    public void testGrow() {
        int[] arr = {2, 3, 6};
        int[] newArray = grow(arr, 3);
        System.out.println("grow:" + Arrays.toString(newArray));
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
        if (max == 1) {
            return new int[0];
        }
        int a = 1;
        int b = 1;
        int temp;
        StringBuilder str = new StringBuilder("1,1,");
        while (a + b < max) {
            temp = b;
            b = a + b;
            a = temp;
            str.append(b + ",");
        }
        String[] arr = str.toString().split(",");
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = Integer.valueOf(arr[i]);
        }
        return newArray;
    }

    @Test
    public void testFibonacci() {
        int[] newArray = fibonacci(500);
        System.out.println("Fibonacci:" + Arrays.toString(newArray));
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        StringBuilder str = new StringBuilder();
        for (int i = 2; i < max; i++) {
            if (i < 4) {
                str.append(i + ",");
                continue;
            } else if (i % 2 == 0) {
                continue;
            } else if (i < 9) {
                str.append(i + ",");
                continue;
            } else if (i % 3 == 0) {
                continue;
            } else {
                int f = 5;
                boolean flag = true;
                while (f <= i/f) {
                    if (i % f == 0) {
                        flag = false;
                        break;
                    } else if (i % (f + 2) == 0) {
                        flag = false;
                        break;
                    }
                    f += 6;
                }
                if (flag) {
                    str.append(i + ",");
                    continue;
                }
            }
        }
        String[] arr = str.toString().split(",");
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = Integer.valueOf(arr[i]);
        }
        return newArray;
    }

    @Test
    public void testGetPrimes() {
        int[] newArray = getPrimes(5000);
        System.out.println("getPrimes:" + Arrays.toString(newArray));
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {
        if(max<7){
            return new int[0];
        }
        boolean flag = true;
        StringBuilder str = new StringBuilder("6,");
        for (int i = 6; i < max; ) {
            int sum = 1;
            if (i % 3 == 1 && i % 9 == 1) {
                //如果以8结尾，那么就肯定是以28结尾
                if(!flag){
                    if(i%100!=28){
                        i += 8;
                        flag = true;
                        continue;
                    }
                }
                    for (int j = 2; j <= i / j; j++) {
                        if (i % j == 0) {
                            sum += j;
                            sum += i / j;
                        }
                    }
                    if (sum == i) {
                        str.append(i+",");
                    }
            }
            if (flag) {
                i += 2;
                flag = false;
            } else {
                i += 8;
                flag = true;
            }
        }
        String[] arr = str.toString().split(",");
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = Integer.valueOf(arr[i]);
        }
        return newArray;
    }

    @Test
    public void testGetPerfectNumbers() {
        int[] newArray = getPerfectNumbers(33550337);
        System.out.println("getPerfectNumbers:" + Arrays.toString(newArray));
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
        if (array == null)
            return "null";
        int n = array.length - 1;
        if (n == -1) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == n) {
                return b.toString();
            }
            b.append(seperator);
        }
    }

    @Test
    public void testJoin() {
        int[] a1 = {3, 5, 7};
        System.out.println(join(a1, "-"));
    }

}
