package com.aaront.execrise;

import com.aaront.exercise.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/27
 */
public class ArrayUtilTest {

    private ArrayUtil arrayUtil = new ArrayUtil();

    @Test
    public void testReverseArray() {
        int[] origin = new int[]{7, 9, 30, 3};
        arrayUtil.reverseArray(origin);
        Assert.assertArrayEquals(new int[]{3, 30, 9, 7}, origin);
        origin = new int[]{7, 9, 30, 3, 4};
        arrayUtil.reverseArray(origin);
        Assert.assertArrayEquals(new int[]{4, 3, 30, 9, 7}, origin);
    }

    @Test
    public void testRemoveZero() {
        int[] origin = new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] newArray = arrayUtil.removeZero(origin);
        Assert.assertArrayEquals(new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5}, newArray);
    }

    @Test
    public void testMerge() {
        int[] a1 = new int[]{3, 5, 7, 8};
        int[] a2 = new int[]{4, 5, 6, 7, 9, 10};
        int[] merge = arrayUtil.merge(a1, a2);
        Assert.assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8, 9, 10}, merge);
    }

    @Test
    public void testGrow() {
        int[] grow = arrayUtil.grow(new int[]{2, 3, 6}, 3);
        Assert.assertArrayEquals(new int[]{2, 3, 6, 0, 0, 0}, grow);
    }

    @Test
    public void testFibonacci() {
        int[] fibonacci = arrayUtil.fibonacci(15);
        Assert.assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, fibonacci);
        fibonacci = arrayUtil.fibonacci(1);
        Assert.assertArrayEquals(new int[]{}, fibonacci);
    }

    @Test
    public void testGetPrimes() {
        int[] primes = arrayUtil.getPrimes(23);
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19}, primes);
    }

    @Test
    public void testGetPerfectNumbers() {
        int[] perfectNumbers = arrayUtil.getPerfectNumbers(1000);
        Assert.assertArrayEquals(new int[]{6,28,496}, perfectNumbers);
    }

    @Test
    public void testJoin() {
        String join = arrayUtil.join(new int[]{3, 8, 9}, "-");
        Assert.assertEquals("3-8-9", join);
    }
}
