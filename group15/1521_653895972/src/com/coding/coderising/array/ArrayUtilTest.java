package com.coding.coderising.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wanc on 2017/2/28.
 */
public class ArrayUtilTest {

    @Test
    public void testReverseArray() throws Exception {
        int[] arr = {7, 9, 30, 3};
        SimpleArrayUtil.reverseArray(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------置换 end-----------------------------");
    }


    @Test
    public void testRemoveZero() throws Exception {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int newArr[] = SimpleArrayUtil.removeZero(oldArr);
        System.out.println(Arrays.toString(newArr));
        System.out.println("----------------------去零 end-----------------------------");
    }

    @Test
    public void testMerge() throws Exception {
        int arr1[] = {3, 5, 7, 8};
        int arr2[] = {4, 5, 6, 7};
        int arr3[] = SimpleArrayUtil.merge(arr1, arr2);
        System.out.println(Arrays.toString(arr3));
        System.out.println("----------------------merge end-----------------------------");
    }

    @Test
    public void testGrow() throws Exception {
        int arr1[] = {3, 5, 7, 8};
        int[] newArr = SimpleArrayUtil.grow(arr1, 3);
        System.out.println(Arrays.toString(newArr));
        System.out.println("----------------------扩展 end-----------------------------");
    }

    @Test
    public void testFibonacci() throws Exception {
        int[] arr = SimpleArrayUtil.fibonacci(15);
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------斐波那契 end-----------------------------");
    }

    @Test
    public void testGetPrimes() throws Exception {
        int[] arr = SimpleArrayUtil.getPrimes(23);
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------素数 end-----------------------------");
    }

    @Test
    public void testGetPerfectNumbers() throws Exception {
        int[] newArr = SimpleArrayUtil.getPerfectNumbers(50);
        System.out.println(Arrays.toString(newArr));
        System.out.println("----------------------完数 end-----------------------------");
    }

    @Test
    public void testJoin() throws Exception {
        int arr1[] = {3, 5, 7, 8};
        System.out.println(SimpleArrayUtil.join(arr1, "-"));
        System.out.println("----------------------Join end-----------------------------");
    }
}