package com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by damocles on 2017/3/1.
 */
public class ArrayUtilTest {
    private ArrayUtil arrayUtil;
    private int[] oldArray = null;
    private int[] newArray = null;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void reverseArray() throws Exception {
        oldArray = new int[]{7, 9, 30, 3};
        newArray = new int[]{3, 30, 9, 7};
        arrayUtil.reverseArray(oldArray);

        Assert.assertArrayEquals(newArray, oldArray);

        oldArray = new int[]{3, 30, 9, 7, 4};
        newArray = new int[]{4, 7, 9, 30, 3};
        arrayUtil.reverseArray(oldArray);

        Assert.assertArrayEquals(newArray, oldArray);
    }

    @Test
    public void removeZero() throws Exception {
        oldArray = new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        newArray = new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};

        Assert.assertArrayEquals(newArray, arrayUtil.removeZero(oldArray));

    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7, 8};
        int[] a2 = {4, 5, 6, 7};
        newArray = new int[]{3, 4, 5, 6, 7, 8};

        Assert.assertArrayEquals(newArray, arrayUtil.merge(a1, a2));
    }

    @Test
    public void grow() throws Exception {
        oldArray = new int[]{2, 3, 6};
        newArray = new int[]{2, 3, 6, 0, 0, 0};
        int size = 3;

        Assert.assertArrayEquals(newArray, arrayUtil.grow(oldArray, size));
    }

    @Test
    public void fibonacci() throws Exception {
        newArray = new int[]{1, 1, 2, 3, 5, 8, 13};

        Assert.assertArrayEquals(newArray, arrayUtil.fibonacci(15));
    }

    @Test
    public void getPrimes() throws Exception {
        newArray = new int[]{2, 3, 5, 7, 11, 13, 17, 19};

        Assert.assertArrayEquals(newArray, arrayUtil.getPrimes(23));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        newArray = new int[]{6, 28, 496, 8128};
        System.out.println(33550336 % 27);
        Assert.assertArrayEquals(newArray, arrayUtil.getPerfectNumbers(10000));
    }

    @Test
    public void join() throws Exception {
        oldArray = new int[]{3, 8, 9, 10, 2};
        String seperator = "-";
        String exception = "3-8-9-10-2";

        Assert.assertEquals(exception, arrayUtil.join(oldArray, seperator));
    }

}