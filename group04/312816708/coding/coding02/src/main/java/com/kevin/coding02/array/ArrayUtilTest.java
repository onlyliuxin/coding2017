package com.kevin.coding02.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YinWenBing on 2017/3/5.
 */
public class ArrayUtilTest {
    ArrayUtil arrayUtil = new ArrayUtil();

    public void testReverseArray() {
        int[] origin = new int[]{7, 9, 30, 3, 4};
        arrayUtil.reverseArray(origin);

        Assert.assertEquals(4, origin[0]);
    }

    public void testRemoveZero() {
        int[] oldArray = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] newArray = arrayUtil.removeZero(oldArray);

        Assert.assertEquals(6, newArray[4]);
    }


    public void testMerge() {
        int[] array1 = {3, 5, 7, 8};
        int[] array2 = {4, 5, 6, 7};

        int[] newArray = arrayUtil.merge(array1, array2);

        Assert.assertEquals(8, newArray[newArray.length - 1]);
    }


    public void testGrow() {
        int[] oldArray = {2, 3, 6};
        int size = 3;

        int[] newArray = arrayUtil.grow(oldArray, size);
        Assert.assertEquals(0, newArray[newArray.length - 1]);
    }

    @Test
    public void testGetPrimes() {
        int max = 23;
        int[] newArray = arrayUtil.getPrimes(max);

        Assert.assertEquals(19, newArray[newArray.length - 1]);
    }
}
