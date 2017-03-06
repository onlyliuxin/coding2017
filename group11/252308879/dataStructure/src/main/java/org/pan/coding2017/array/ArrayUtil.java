package org.pan.coding2017.array;

import java.util.Arrays;

/**
 * Created by QiPan on 2017/2/27.
 */
public class ArrayUtil {


    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     *
     * @param origin
     * @return
     */
    public static void reverseArray(int[] origin) {

        // 如果是null, 或者长度小于等于1， 直接返回
        if (origin == null || origin.length <= 1) {
            return;
        }
        for (int i = 0; i < origin.length / 2; i++) {
            int tmp = origin[i];
            origin[i] = origin[origin.length - 1 - i];
            origin[origin.length - 1 - i] = tmp;
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

        if (oldArray == null) {
            return oldArray;
        }
        int[] newArray = null;
        int count = 0; //统计被移出的数组的个数
        for (int i = 0; i < oldArray.length; i++) {
            int num = oldArray[i];
            if (num == 0) {
                count++;
                System.arraycopy(oldArray, i + 1, oldArray, i, oldArray.length - i - 1);
                i--;
            }
        }
        if (count == 0) {
            newArray = oldArray;
        } else {
            newArray = new int[oldArray.length - count];
            System.arraycopy(oldArray, 0, newArray, 0, oldArray.length - count);
        }
        return newArray;
    }

    /**
     * 不用JavaAPI来做
     *
     * @param oldArray
     * @return
     */
    public static int[] removeZero_2(int[] oldArray) {

        if (oldArray == null) {
            return oldArray;
        }
        int count = 0;
        for (int num : oldArray) {
            if (num == 0) {
                count++;
            }
        }
        int[] newArray = new int[oldArray.length - count];
        for (int i = 0, j = 0; i < oldArray.length; i++, j++) {
            int num = oldArray[i];
            if (num == 0) {
                j--;
            } else {
                newArray[j] = num;
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
    public static int[] merge(int[] array1, int[] array2) {
        //先初始化一个array3，但不是最后返回的数组
        int[] array3 = new int[array1.length + array2.length];
        int i = 0, j = 0;
        int array3Size = 0; // 统计实际上合并数组后的大小
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {// 如果array1中元素小，则插入到array3中
                array3[array3Size++] = array1[i];
                ++i;
            } else if (array1[i] > array2[j]) {//如果array2中元素小，则插入到array3中
                array3[array3Size++] = array2[j];
                ++j;
            } else {//否则随便插入一个,但是计数要同时加1
                array3[array3Size++] = array1[i];
                ++i;
                ++j;
            }
        }

        if (i == array1.length) { //如果array1中全部循环完毕了，那么需要去处理array2中剩余的元素
            for (int n = j; n < array2.length; n++) {
                array3[array3Size++] = array2[n];
            }
        } else if (j == array2.length) {// 如果array2中全部循环完毕,那么需要去处理array1中剩余的元素
            for (int n = i; n < array1.length; n++) {
                array3[array3Size++] = array1[n];
            }
        }
        int[] returnResultArray = new int[array3Size];
        System.arraycopy(array3, 0, returnResultArray, 0,
                array3Size);
        return returnResultArray;
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
        if (oldArray == null) {
            oldArray = new int[size];
        }
        oldArray = Arrays.copyOf(oldArray, oldArray.length + size);
        return oldArray;
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
        if (max <= 1) {
            return new int[0];
        }
        int[] arrays = new int[max / 2];
        int firstNum = 1; //第一个数字
        int secondNum = 1; // 第二个数字
        int arraySize = 0;
        arrays[arraySize++] = 1; // 初始化第一位
        while (secondNum < max) {
            arrays[arraySize++] = secondNum;
            int tmpNum = secondNum; // 保存第二个数，得会需要付给第一个数
            secondNum = firstNum + secondNum; // 为前两个数之和
            firstNum = tmpNum; // 第一个数，后移
        }
        return Arrays.copyOf(arrays, arraySize);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        int[] returnResultArray = new int[max + 1];
        int arraySize = 0;
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                returnResultArray[arraySize++] = i;
            }

        }
        if (arraySize == returnResultArray.length) {
            return returnResultArray;
        }
        return Arrays.copyOf(returnResultArray, arraySize);
    }

    private static boolean isPrime(final int number) {
        if (number < 2) {
            return false;
        }
        // 因为不可能将一个数除与所有小于它的数字，只要检查到N的平方根就好了。
        // 但直接开根号还有个精度的问题。这个可能会产生误差。 索性将判断条件写成 i*i<=number
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {//查看所有小于number平方根的数，能够被整除
                return false;
            }
        }
        // 如果一个都没有，那么就是素数
        return true;
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     *
     * @param max
     * @return
     */
    public static int[] getPerfectNumbers(int max) {
        int[] array = new int[max];
        int arraySize = 0;
        for (int n = 0; n < max; n++) {
            int fac,// 被除的因子
                    sum,// 用来统计因子之和
                    num;// 除数的因子，中间变量
            for (sum = 1, num = n, fac = 2; fac < num; fac++) {

                if (n % fac == 0) {// 如果余数为0，那么说明有因子
                    sum += fac;    // 统计因子和
                    num = n / fac; // num=等于除数的最大因子
                    if (num == fac)  // 如果最大和最小相等跳出循环
                        break;
                    sum += num; // 再统计因子
                }
            }

            if (sum == n) { //因子和与整数相等，那么就是一个完美数
                if (n != 1) {
                    System.out.println(n + "是一个完全数，其因子为:");
                }
                for (fac = 1; fac < n; fac++) {
                    if (n % fac == 0) {// 列出所有的因子
                        System.out.print(fac + " ");
                    }
                }
                System.out.println();
                array[arraySize++] = n; // 放到数组中
            }
        }
        return Arrays.copyOf(array, arraySize);
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
        if (array == null) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                str.append(array[i]);
                continue;
            }
            str.append(array[i]).append(seperator);
        }
        return str.toString();
    }

}
