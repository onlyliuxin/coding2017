package com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayUtilTest {
    private ArrayUtil arrayUtil = null;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void reverseArray() throws Exception {
        int[] a = {7, 9, 30, 3};
        int[] b = {3, 30, 9, 7};
        assertArrayEquals(arrayUtil.reverseArray(a), b);
    }

    @Test
    public void removeZero() throws Exception {
        int[] oldArr = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] newArray = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
        assertArrayEquals(arrayUtil.removeZero(oldArr), newArray);
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        int[] newArray = {3, 4, 5, 6, 7, 8};
        assertArrayEquals(arrayUtil.merge(a1, a2), newArray);
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = {2, 3, 6};
        int size = 3;
        int[] newArray = {2, 3, 6, 0, 0, 0};
        assertArrayEquals(arrayUtil.grow(oldArray, size), newArray);
    }

    @Test
    public void fibonacci() throws Exception {
        int max = 15;
        int[] array = {1, 1, 2, 3, 5, 8, 13};
        assertArrayEquals(arrayUtil.fibonacci(max), array);
    }

    @Test
    public void getPrimes() throws Exception {
        int[] array = {2, 3, 5, 7, 11, 13, 17, 19};
        int max = 23;
        assertArrayEquals(arrayUtil.getPrimes(max), array);
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int max = 100;
        int[] array = {6, 28};
        assertArrayEquals(arrayUtil.getPerfectNumbers(max), array);
    }

    @Test
    public void join() throws Exception {
        int[] array = {3, 8, 9};
        String seperator = "-";
        String str = "3-8-9";
        Assert.assertEquals(arrayUtil.join(array, seperator), str);
    }

}