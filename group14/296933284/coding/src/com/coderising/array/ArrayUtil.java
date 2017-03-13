package com.coderising.array;

import com.coding.basic.ArrayList;

import java.util.Arrays;


/**
 * Created by Tennyson on 2017/3/1.
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a , 对该数组的值进行置换
     * 例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
     * 如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin) {
        for (int i = 0, j = origin.length - 1; i < origin.length / 2; i++, j--) {
            int temp = origin[j];
            origin[j] = origin[i];
            origin[i] = temp;
        }
    }

    /**
     * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * @param oldArray
     * @return
     */
    public int[] removeZero(int[] oldArray) {

        int i;
        // 获取数组中第一个值为 0 的元素的位置
        for (i = 0; i < oldArray.length; i++)
            if (oldArray[i] == 0)
                break;
        // 从第一个值为 0 的元素开始，用之后的非零元素覆盖之前值为 0 的元素，并记录非零元素的个数
        for (int j = i + 1; j < oldArray.length; j++)
            if (oldArray[j] != 0)
                oldArray[i++] = oldArray[j];

        // 复制数组
        int[] newArray = new int[i];
        System.arraycopy(oldArray, 0, newArray, 0, i);

        return newArray;
    }

    /**
     * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
     * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
     * @param array1
     * @param array2
     * @return
     */
    public int[] merge(int[] array1, int[] array2) {
        int i = 0, j = 0;
        ArrayList arrayList = new ArrayList();

        // 比较两个数组的元素值，将较小的值存入arrayList
        while (i < array1.length && j < array2.length) {

            if (array1[i] == array2[j]) {
                arrayList.add(array1[i]);
                i++;
                j++;
            } else if (array1[i] < array2[j]) {
                arrayList.add(array1[i++]);
            } else if (array1[i] > array2[j]) {
                arrayList.add(array2[j++]);
            }
        }

        // 剩下一个数组还未比较完, 将其剩余元素存入arrayList
        while (i < array1.length) arrayList.add(array1[i++]);
        while (j < array2.length) arrayList.add(array2[j++]);

        return  ArrayUtil.toArray(arrayList);
    }
    /**
     * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
     * 注意，老数组的元素在新数组中需要保持
     * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
     * [2,3,6,0,0,0]
     * @param oldArray
     * @param size
     * @return
     */
    public int[] grow(int [] oldArray,  int size) {
        int[] newArray = new int[oldArray.length + size];

        return newArray = Arrays.copyOf(oldArray, newArray.length);
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
     * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
     * max = 1, 则返回空数组 []
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(1);

        int i, num;
        for (i = 2; (num = (int) (arrayList.get(i - 1)) + (int) (arrayList.get(i - 2))) < max; i++)
            arrayList.add(num);

        return ArrayUtil.toArray(arrayList);
    }

    /**
     * 返回小于给定最大值max的所有素数数组
     * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {

        ArrayList arrayList = new ArrayList();

        for (int i = 2; i < max; i++) {
            int k = (int) Math.sqrt(i), j;
            for (j = 2; j <= k; j++)
                if (i % j == 0) break;

            // 若是 j >= k + 1 表示 i 没有被整除 即 i 为素数
            if (j >= k + 1)
                arrayList.add(i);
        }

        return ArrayUtil.toArray(arrayList);
    }

    /**
     * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
     * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
     * @param max
     * @return
     */
    public int[] getPerfectNumbers(int max) {

        ArrayList arrayList = new ArrayList();

        for (int i = 6; i < max; i += 2) {

            if (i == 6) {
                arrayList.add(i);
                continue;
            }

            int factorSum = 0;

            if (i % 3 == 1 && i % 9 == 1) {

                boolean flag = false;

                if (i % 10 == 6 ) {
                    flag = true;
                }

                if (i % 10 == 8) {
                    if (i % 100 == 28) {
                        flag = true;
                    }
                }

                if (flag) {

                    for (int j = 1; j <= (i / 2); j++) {

                        if (i % j == 0) {
                            factorSum += j;
                        }
                    }
                }

            }

            if (factorSum == i) {
                arrayList.add(i);
            }

        }

        return ArrayUtil.toArray(arrayList);
    }

    // 将ArrayList对象转换为数组返回
    private static int[] toArray(ArrayList arrayList) {
        int[] array = new int[arrayList.size()];

        for (int i = 0; i < array.length; i++)
            array[i] = (Integer) arrayList.get(i);

        return array;
    }

    /**
     * 用seperator 把数组 array给连接起来
     * 例如array= [3,8,9], seperator = "-"
     * 则返回值为"3-8-9"
     * @param array
     * @param seperator
     * @return
     */
    public String join(int[] array, String seperator){

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < array.length - 1; i++)
            stringBuffer.append("" + array[i] + seperator);

        stringBuffer.append(array[array.length - 1]);

        return stringBuffer.toString();
    }


}
