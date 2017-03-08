package com.circle.algorithm;

import java.util.Arrays;

/**
 * Created by keweiyang on 2017/3/1.
 */
public class ArrayUtil {

    /**
     * 给定一个整形数组a，对该数组的值进行置换
     * 例如：a=[7,9,30,3],置换后为[3,30,9,7]
     * 如果 a=[7,9,30,3,4],置换后为【4，3，30，9，7】
     *
     * @param origin
     */
    public void reverseArray(int[] origin) {
        int first = 0;
        int last = origin.length - 1;

        while (first < last) {
            swap(origin, first, last);
            first++;
            last--;
        }

    }

    private void swap(int[] origin, int first, int last) {
        int temp = origin[last];
        origin[last] = origin[first];
        origin[first] = temp;
    }


    /**
     * 现有如下一个数组：int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
     * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
     * {1，3，4，5，6，6，5，4，7，6，7，5}
     *
     * @param oldArray
     * @return
     */
    public int[] removeZero(int[] oldArray) {
        int size = 0;
        for (int i : oldArray) {
            if (i != 0) {

                size++;
            }
        }
        int[] newArray = new int[size];

        int currentIndex = 0;
        for (int i : oldArray) {
            if (i != 0) {

                newArray[currentIndex++] = i;
            }
        }
        return newArray;
    }

    /**
     * 斐波那契数列为：1，1，2，3，5，8，13，21.。。。,给定一个最大值，返回小于该值的数列
     * 例如：max=15,则返回的数组应该为[1,1,2,3,5,8,13]
     * max =1,则返回孔数组[]
     *
     * @param max
     * @return
     */
    public int[] fibonacci(int max) {

        int[] array = new int[max];
        int i = 1;
        while (max > fun(i)) {

            array[i] = fun(i);
            i++;
        }


        return removeZero(array);
    }

    private int fun(int i) {
        if (i == 1 || i == 2) {
            return 1;
        }
        return fun(i - 1) + fun(i - 2);
    }

    /**
     * 返回小于给定最大值max的所有素数(质数)数组
     * 例如max=23，返回的数组为[2,3,5,7,11,13,17,19]
     *
     * @param max
     * @return
     */
    public int[] getPrimes(int max) {
        int[] arr = new int[max];
        int k = 0;
        if (max < 2) {
            return null;
        } else {
            for (int i = 2; i < max; i++) {
                boolean flag = false;
                int j = i-1;
                while (j > 1) {
                    if (i % j == 0) {
                        flag = true;
                        break;
                    }
                    j--;

                }
                if (!flag) {
                    arr[k++] = i;
                }


            }
        }
       /* for (int i : arr) {
            System.out.println(i);
        }*/
        return removeZero(arr);
    }

    /**
     * 用seperator把数组array给连接起来
     * 例如array=[3,8,9],seperator="-"
     * 则返回值为"3-8-9"
     *
     * @param array
     * @param seperator
     * @return
     */
    public String join(int[] array, String seperator) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }


}
