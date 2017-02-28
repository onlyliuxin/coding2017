package com.coderising.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ArrayUtil Test
 */
public class ArrayUtilTest {
    ArrayUtil arrayUtil = null;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
    }

    @After
    public void tearDown() throws Exception {
        arrayUtil = null;
    }

    @Test
    public void reverseArray() throws Exception {
        int[] a = {7, 9, 30, 3};
        arrayUtil.reverseArray(a);
        assertArrayEquals(new int[]{3, 30, 9, 7,}, a);
        a = new int[]{7, 9, 30, 3, 4};
        arrayUtil.reverseArray(a);
        assertArrayEquals(new int[]{4, 3, 30, 9, 7}, a);

    }

    @Test
    public void removeZero() throws Exception {
        int[] oldArr = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] newArr = arrayUtil.removeZero(oldArr);
        assertEquals(12, newArr.length);
    }

    @Test
    public void merge() throws Exception {
        int[] a = {3, 5, 7, 8};
        int[] b = {4, 5, 6,7};
        int[] result = arrayUtil.merge(a,b);
        assertArrayEquals(new int[]{3,4,5,6,7,8},result);
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = {2,3,6};
        int[] newArray = arrayUtil.grow(oldArray,3);
        assertArrayEquals(new int[]{2,3,6,0,0,0},newArray);
    }

    @Test
    public void fibonacci() throws Exception {
        int[] a = arrayUtil.fibonacci(1);
        assertArrayEquals(new int[]{},a);
        a = arrayUtil.fibonacci(15);
        assertArrayEquals(new int[]{1,1,2,3,5,8,13},a);
    }

    @Test
    public void getPrimes() throws Exception {
        int[] a = arrayUtil.getPrimes(23);
        assertArrayEquals(new int[]{2,3,5,7,11,13,17,19},a);
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int[] a = arrayUtil.getPerfectNumbers(100);
        assertArrayEquals(new int[]{6,28},a);
    }

    @Test
    public void join() throws Exception {
        int[] a = {3,8,9};
        assertEquals("3-8-9",arrayUtil.join(a,"-"));
    }

}