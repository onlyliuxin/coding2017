package com.coderising.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by huitailang on 17/3/3.
 * 工具类测试
 */
public class ArrayUtilTest {
    @Test
    public void testReverseArray() {
        int[] origin = {7, 9, 30, 3};
        int[] newArray = {3, 30, 9, 7};

        ArrayUtil.reverseArray(origin);

        print(origin);

        Assert.assertArrayEquals(newArray, origin);
    }

    @Test
    public void testRemoveZero() {
        int[] oldArray = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] expectdArray = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};

        print(ArrayUtil.removeZero(oldArray));

        Assert.assertArrayEquals(expectdArray, ArrayUtil.removeZero(oldArray));
    }

    @Test
    public void testMerge() {
        int[] array1 = {3, 5, 7, 8};
        int[] array2 = {4, 5, 6, 7};
        int[] expectedArray = {3, 4, 5, 6, 7, 8};

        System.out.println(Arrays.toString(ArrayUtil.merge(array1, array2)));

        Assert.assertArrayEquals(expectedArray, ArrayUtil.merge(array1, array2));
    }

    @Test
    public void testGrow() {
        int[] oldArray = {2, 3, 6};
        int[] expectedArray = {2, 3, 6, 0, 0, 0};
        Assert.assertArrayEquals(expectedArray, ArrayUtil.grow(oldArray, 3));
    }

    @Test
    public void testFibonacci() {
        int[] expectedArray = {1, 1, 2, 3, 5, 8, 13};

        print(ArrayUtil.fibonacci(13));

        Assert.assertArrayEquals(expectedArray, ArrayUtil.fibonacci(13));
    }

    @Test
    public void testGetPrimes() {
        int[] expectedArray = {2, 3, 5, 7, 11, 13, 17, 19};
        Assert.assertArrayEquals(expectedArray, ArrayUtil.getPrimes(23));
    }

    @Test
    public void testIsPerfectNumber() {
        Assert.assertTrue(ArrayUtil.isPerfectNumber(6));
    }

    @Test
    public void testGetPerfectNumbers() {
        print(ArrayUtil.getPerfectNumbers(15));
    }

    @Test
    public void testJoin() {
        int[] origin = {3, 8, 9};
        String seperator = "-";
        String str = "3-8-9";

        Assert.assertEquals(str, ArrayUtil.join(origin, seperator));
    }

    private void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
