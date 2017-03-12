package com.coderising.array;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayUtilTest {
    @Test
    public void reverseArray() throws Exception {
        int[] nullArr = new int[0]; ArrayUtil.reverseArray(nullArr);
        assertArrayEquals(new int[0], nullArr);

        int[] arr = {2,3,4,5}; ArrayUtil.reverseArray(arr);
        assertArrayEquals(new int[]{5,4,3,2}, arr);
    }

    @Test
    public void removeZero() throws Exception {
        int[] nullArr = new int[0];
        assertArrayEquals(new int[0], ArrayUtil.removeZero(nullArr));

        int[] arr = {2,0,3,0,0,4,0,5};
        assertArrayEquals(new int[] {2,3,4,5}, ArrayUtil.removeZero(arr));
    }

    @Test
    public void merge() throws Exception {
        int[] arr1 = {1,3,4,6,7};
        int[] arr2 = {2,4,7,9,10};
        int[] arr3 = {3,5,8,10,11};
        int[] arr4 = ArrayUtil.merge(arr1,arr2);
        int[] arr5 = ArrayUtil.merge(arr3,arr4);
        assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9,10,11}, arr5);

        int[] arr6 = new int[0];
        int[] arr7 = {2,3,4,5};
        assertArrayEquals(new int[] {2,3,4,5}, ArrayUtil.merge(arr6,arr7));
    }

    @Test
    public void grow() throws Exception {
        int[] nullArr = new int[0];
        assertEquals(2, ArrayUtil.grow(nullArr,2).length);

        int[] arr = {2,3,4};
        assertEquals(5, ArrayUtil.grow(arr,2).length);
    }

    @Test
    public void fibonacci() throws Exception {
        assertArrayEquals(new int[0], ArrayUtil.fibonacci(0));
        assertArrayEquals(new int[] {1,1}, ArrayUtil.fibonacci(1));
        assertArrayEquals(new int[] {1,1,2,3,5,8,13}, ArrayUtil.fibonacci(13));
        assertArrayEquals(new int[] {1,1,2,3,5,8,13}, ArrayUtil.fibonacci(15));
    }

    @Test
    public void getPrimes() throws Exception {
        assertArrayEquals(new int[0], ArrayUtil.getPrimes(0));
        assertArrayEquals(new int[]{2}, ArrayUtil.getPrimes(2));
        assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, ArrayUtil.getPrimes(19));
        assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, ArrayUtil.getPrimes(20));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        assertArrayEquals(new int[0], ArrayUtil.getPerfectNumbers(0));
        assertArrayEquals(new int[]{6}, ArrayUtil.getPerfectNumbers(6));
        assertArrayEquals(new int[]{6,28,496}, ArrayUtil.getPerfectNumbers(1000));
        assertArrayEquals(new int[]{6,28,496,8128}, ArrayUtil.getPerfectNumbers(10000));

    }

    @Test
    public void join() throws Exception {
        int[] nullArr = new int[0];
        assertEquals("", ArrayUtil.join(nullArr, "."));

        int[] arr = {2,3,4,5};
        assertEquals("2-3-4-5",ArrayUtil.join(arr,"-"));
    }

}