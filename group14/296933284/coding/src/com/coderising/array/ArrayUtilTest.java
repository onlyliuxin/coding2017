package com.coderising.array;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damocles on 2017/3/1.
 */
public class ArrayUtilTest {

    @Test
    public void reverseArray() throws Exception {
        int[] a = {7, 9 , 30, 3};
        ArrayUtil arrayUtil = new ArrayUtil();

        arrayUtil.reverseArray(a);

        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + "\t");

        int[] b = {3, 30, 9, 7, 4};

        System.out.println();

        arrayUtil.reverseArray(b);

        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + "\t");
    }

    @Test
    public void removeZero() throws Exception {
        int[] oldArr = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};

        ArrayUtil arrayUtil = new ArrayUtil();
        int[] newArr = arrayUtil.removeZero(oldArr);

        for (int i = 0; i < newArr.length; i++)
            System.out.print(newArr[i] + "\t");
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7,8}, a2 = {4, 5, 6,7};
        ArrayUtil arrayUtil = new ArrayUtil();

        int[] a3 = arrayUtil.merge(a1, a2);

        for (int i = 0; i < a3.length; i++)
            System.out.print(a3[i] + "\t");
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = {2, 3, 6};
        int size = 3;

        ArrayUtil arrayUtil = new ArrayUtil();
        int[] newArray = arrayUtil.grow(oldArray, size);

        for (int i = 0; i < newArray.length; i++)
            System.out.print(newArray[i] + "\t");
    }

    @Test
    public void fibonacci() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] newArray = arrayUtil.fibonacci(15);

        for (int i = 0; i < newArray.length; i++)
            System.out.print(newArray[i] + "\t"); // 1	1	2	3	5	8	13
    }

    @Test
    public void getPrimes() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] newArray = arrayUtil.getPrimes(23);

        for (int i = 0; i < newArray.length; i++)
            System.out.print(newArray[i] + "\t"); // [2,3,5,7,11,13,17,19]
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] newArray = arrayUtil.getPerfectNumbers(500);

        for (int i = 0; i < newArray.length; i++)
            System.out.print(newArray[i] + "\t"); // [6	,28	,496]

    }

    @Test
    public void join() throws Exception {
        int[] array= {3, 8, 9, 10, 2};
        String seperator = "-";

        ArrayUtil arrayUtil = new ArrayUtil();
        String str = arrayUtil.join(array, seperator);

        System.out.println(str);
    }

}