package com.coding.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ArrayUtilTest {

    @Test
    public void reverseArray() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] origin = { 0, 1, 2, 3, 4 };
        arrayUtil.reverseArray(origin);
        Assert.assertEquals("[4, 3, 2, 1, 0]", Arrays.toString(origin));
    }

    @Test
    public void removeZero() throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        int[] origin = { 0, 1, 2, 0, 4 };
        Assert.assertEquals("[1, 2, 4]", Arrays.toString(arrayUtil.removeZero(origin)));
    }
}