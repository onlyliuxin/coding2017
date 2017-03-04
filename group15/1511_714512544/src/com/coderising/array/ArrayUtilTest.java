package com.coderising.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 *  ArrayList测试
 */
public class ArrayUtilTest {
    private ArrayUtil util = null;

    @Before
    public void setUp() throws Exception {
       util = new ArrayUtil();
    }

    @After
    public void tearDown() throws Exception {
        util = null;
    }

    @Test
    public void reverseArray() throws Exception {
        int[] arr = new int[]{7, 9, 30, 3, 4};
        util.reverseArray(arr);
        assertArrayEquals(new int[]{4,3, 30 , 9,7}, arr);
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int[] newArr = util.removeZero(oldArr);
        assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, newArr);
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7,8};
        int[] a2 = {4, 5, 6,7};
        assertArrayEquals(new int[]{3,4,5,6,7,8}, util.merge(a1, a2));
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = {2,3,6};
        int[] newArr = util.grow(oldArray, 3);
        assertArrayEquals(new int[]{2,3,6,0,0,0}, newArr);
    }

    @Test
    public void fibonacci() throws Exception {
        assertArrayEquals(new int[]{1,1,2,3,5,8,13}, util.fibonacci(15) );
    }

    @Test
    public void getPrimes() throws Exception {
        assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, util.getPrimes(23));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        assertArrayEquals(new int[]{6} , util.getPerfectNumbers(7));
    }

    @Test
    public void join() throws Exception {
        int[] array= {3,8,9};
        assertEquals("3-8-9" ,util.join(array, "-"));
    }

}