package com.array.utils;

import org.junit.Assert;
import org.junit.Test;

public class TestArrayUtil {

    @Test
    public void testReverseArray1() {
        int[] array = {1, 2, 3, 4, 5};
        int[] trueAns = {5, 4, 3, 2, 1};
        ArrayUtil.reverseArray(array);
        Assert.assertArrayEquals(trueAns, array);
    }

    @Test
    public void testReverseArray2() {
        int[] array = {};
        int[] trueAns = {};
        ArrayUtil.reverseArray(array);
        Assert.assertArrayEquals(trueAns, array);
    }

    @Test
    public void testRemoveZero1() {
        int[] origin = {0, 0, 0, 3, 5, 0, 4, 5, 0};
        int[] trueAns = {3, 5, 4, 5};
        Assert.assertArrayEquals(trueAns, ArrayUtil.removeZero(origin));
    }

    @Test
    public void testRemoveZero2() {
        int[] origin = {};
        int[] trueAns = {};
        Assert.assertArrayEquals(trueAns, ArrayUtil.removeZero(origin));
    }

    @Test
    public void testMerge1() {
        int[] array1 = {3, 5, 7, 8};
        int[] array2 = {4, 5, 6, 7, 10};
        int[] trueAns = {3, 4, 5, 6, 7, 8, 10};
        Assert.assertArrayEquals(trueAns, ArrayUtil.merge(array1, array2));
    }

    @Test
    public void testMerge2() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {};
        int[] trueAns = {1, 2, 3, 4};
        Assert.assertArrayEquals(trueAns, ArrayUtil.merge(array1, array2));
    }

    @Test
    public void testMerge3() {
        int[] array1 = {};
        int[] array2 = {4, 5, 6, 7};
        int[] trueAns = {4, 5, 6, 7};
        Assert.assertArrayEquals(trueAns, ArrayUtil.merge(array1, array2));
    }

    @Test
    public void testGrow1() {
        int[] origin = {1, 2, 3};
        int[] trueAns = {1, 2, 3, 0, 0, 0};
        Assert.assertArrayEquals(trueAns, ArrayUtil.grow(origin, 3));
    }

    @Test
    public void testGrow2() {
        int[] origin = {};
        int[] trueAns = {0, 0};
        Assert.assertArrayEquals(trueAns, ArrayUtil.grow(origin, 2));
    }

    @Test
    public void testFibonacci1() {
        int[] trueAns = {};
        Assert.assertArrayEquals(trueAns, ArrayUtil.fibonacci(1));
    }

    @Test
    public void testFibonacci2() {
        int[] trueAns = {1, 1, 2, 3, 5, 8, 13, 21, 34};
        Assert.assertArrayEquals(trueAns, ArrayUtil.fibonacci(55));
    }

    @Test
    public void getPrimes1() {
        int[] trueAns = {2, 3, 5, 7, 11, 13, 17, 19};
        Assert.assertArrayEquals(trueAns, ArrayUtil.getPrimes(23));
    }

    @Test
    public void getPrimes2() {
        int[] trueAns = {};
        Assert.assertArrayEquals(trueAns, ArrayUtil.getPrimes(2));
    }

    @Test
    public void getPerfectNumbers1() {
        int[] trueAns = {};
        Assert.assertArrayEquals(trueAns, ArrayUtil.getPerfectNumbers(6));
    }

    @Test
    public void getPerfectNumbers2() {
        int[] trueAns = {6, 28, 496};
        Assert.assertArrayEquals(trueAns, ArrayUtil.getPerfectNumbers(8128));
    }

    @Test
    public void testJoin1() {
        int[] array = {};
        Assert.assertEquals("", ArrayUtil.join(array, ", "));
    }

    @Test
    public void testJoin2() {
        int[] array = {1};
        String trueAns = "1";
        Assert.assertEquals(trueAns, ArrayUtil.join(array, ", "));
    }

    @Test
    public void testJoin3() {
        int[] array = {1, 2, 3};
        String trueAns = "1, 2, 3";
        Assert.assertEquals(trueAns, ArrayUtil.join(array, ", "));
    }
}
