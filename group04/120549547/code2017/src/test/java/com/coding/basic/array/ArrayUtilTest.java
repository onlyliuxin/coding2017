package com.coding.basic.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by bobi on 2017/4/4.
 * at code2017
 */
public class ArrayUtilTest {

    private static int[] arr;

    @Before
    public void setUp() throws Exception {

        arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

    }

    private static void arrPrint(int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Test
    public void reverseArray() throws Exception {
        ArrayUtil.reverseArray(arr);
        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(9-i, arr[i]);
        }
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        oldArr = ArrayUtil.removeZero(oldArr);

        Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, oldArr);

    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7,8};
        int[] a2 = {4, 5, 6,7} ;

        int[] a3 = ArrayUtil.merge(a1, a2);
        Assert.assertArrayEquals(new int[]{3,4,5,6,7,8}, a3);
    }

    @Test
    public void grow() throws Exception {
        int[] a1 = {3, 5, 7,8};
        a1 = ArrayUtil.grow(a1, 3);

        Assert.assertArrayEquals(new int[]{3,5,7,8,0,0,0}, a1);
    }

    @Test
    public void fibonacci() throws Exception {
       int[] arr =  ArrayUtil.fibonacci1(100);
        arrPrint(arr);
    }

    @Test
    public void getPrimes() throws Exception {
        int[] a1 = ArrayUtil.getPrimes(100);
        arrPrint(a1);
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int[] a1 = ArrayUtil.getPerfectNumbers(10000);
        arrPrint(a1);
    }

    @Test
    public void join() throws Exception {
       int[] a1 = {3,4,5,6,7};
        String str = ArrayUtil.join(a1, "-");
        System.out.println("str = " + str);

    }

}