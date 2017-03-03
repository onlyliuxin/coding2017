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
        int[] arr = new int[]{2,3,1,6,7,5,4,8};
        System.out.println(Arrays.toString(arr));
        util.reverseArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void removeZero() throws Exception {
        int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int[] newArr = util.removeZero(oldArr);
        System.out.println(Arrays.toString(newArr));
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7,8};
        int[] a2 = {4, 5, 6,7};
        System.out.println(Arrays.toString(util.merge(a1, a2))); //[3,4,5,6,7,8]
    }

    @Test
    public void grow() throws Exception {
        int[] oldArray = {2,3,6};
        assertEquals(3, oldArray.length);
        int[] newArr = util.grow(oldArray, 3);
        System.out.println(Arrays.toString(newArr));
        assertEquals(6, newArr.length);
    }

    @Test
    public void fibonacci() throws Exception {
        System.out.println(Arrays.toString(util.fibonacci(15)));
    }

    @Test
    public void getPrimes() throws Exception {
        System.out.println(Arrays.toString(util.getPrimes(23)));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        System.out.println(Arrays.toString(util.getPerfectNumbers(100)));
    }

    @Test
    public void join() throws Exception {
        int[] array= {3,8,9};
        System.out.println(util.join(array, "-"));

    }

}