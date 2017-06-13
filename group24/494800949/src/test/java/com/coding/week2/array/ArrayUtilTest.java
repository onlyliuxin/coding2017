package com.coding.week2.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public class ArrayUtilTest {


    private ArrayUtil arrayUtil;

    private int[] ints = new int[]{4, 9, 10, 3, 5, 0, 10, 12, 0, 9};

    @Before
    public void setup(){
        arrayUtil = new ArrayUtil();
    }

    @Test
    public void testReverseArray() throws Exception {
        arrayUtil.reverseArray(ints);
        Assert.assertEquals(ints[0], 9);
        Assert.assertEquals(ints[1], 0);
        Assert.assertEquals(ints[2], 12);
        Assert.assertEquals(ints[3], 10);
        Assert.assertEquals(ints[4], 0);
    }

    @Test
    public void testRemoveZero() throws Exception {
        int[] newInts = ints.clone();
        int[] newArr = arrayUtil.removeZero(newInts);
        System.out.println(Arrays.toString(newArr));
    }

    @Test
    public void testMerge() throws Exception {
        int[] ints1 = new int[]{3, 4, 9, 20};
        int[] ints2 = new int[]{4, 6, 7, 12};
        int[] mergeArr = arrayUtil.merge(ints1, ints2);
        System.out.println(Arrays.toString(mergeArr));
    }

    @Test
    public void testGrow() throws Exception {
        int[] newInts = arrayUtil.grow(ints, 5);

        Assert.assertEquals(newInts.length, 15);
        Assert.assertEquals(newInts[14], 0);
        Assert.assertEquals(newInts[13], 0);
        Assert.assertEquals(newInts[12], 0);
        Assert.assertEquals(newInts[11], 0);
        Assert.assertEquals(newInts[10], 0);
    }

    @Test
    public void testFibonacci() throws Exception {
        int[] ints = arrayUtil.fibonacci(100);
        Assert.assertArrayEquals(ints, new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144});
    }

    @Test
    public void testGetPrimes() throws Exception {
        int[] ints = arrayUtil.getPrimes(100);
        System.out.println(Arrays.toString(arrayUtil.getPrimes(100)));
        Assert.assertArrayEquals(ints, new int[]{2, 3, 4, 5, 7, 9, 11, 13, 17, 19, 23, 25, 29, 31, 37, 41, 43, 47, 49, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97});
    }

    @Test
    public void testGetPerfectNumbers() throws Exception {
        int[] ints = arrayUtil.getPerfectNumbers(10000);
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(arrayUtil.getPerfectNumbers(100000)));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        Assert.assertArrayEquals(ints, new int[]{6, 28, 496, 8128});
    }

    @Test
    public void testJoin() throws Exception {
        System.out.println(arrayUtil.join(ints, "|"));
        Assert.assertEquals("4+9+10+3+5+0+10+12+0+9", arrayUtil.join(ints, "+"));
        Assert.assertEquals("4|9|10|3|5|0|10|12|0|9", arrayUtil.join(ints, "|"));
    }

}