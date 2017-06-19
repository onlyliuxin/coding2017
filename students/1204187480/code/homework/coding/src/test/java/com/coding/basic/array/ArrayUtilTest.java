package com.coding.basic.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class ArrayUtilTest {

    private ArrayUtil creatArrayUtil(){
        return new ArrayUtil();
    }

    @Test
    public void reverseArray() throws Exception {
        int[] origin = new int[]{1, 2, 3};
        int[] destArray = new int[]{3, 2, 1};
        creatArrayUtil().reverseArray(origin);
        Assert.assertArrayEquals(destArray, origin);
    }

    @Test
    public void removeZero() throws Exception {
        int[] origin = new int[]{1, 2, 3, 0, 10};
        int[] destArray = new int[]{1, 2, 3, 10};
        int[] retArray = creatArrayUtil().removeZero(origin);
        Assert.assertArrayEquals(destArray, retArray);
    }

    @Test
    public void merge() throws Exception {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{2, 3};

        int[] newArray = creatArrayUtil().merge(a, b);
        info(newArray);
        assertArrayEquals(new int[]{1, 2, 3}, newArray);
    }

    @Test
    public void grow() throws Exception {
        assertArrayEquals(new int[]{1, 2, 0, 0}, creatArrayUtil().grow(new int[]{1, 2}, 2));
    }

    @Test
    public void fibonacci() throws Exception {
        assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8}, creatArrayUtil().fibonacci(10));
    }

    @Test
    public void getPrimes() throws Exception {
        int max = Double.valueOf(Math.pow(2, 4)).intValue();
        assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13}, creatArrayUtil().getPrimes(max));
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int max = Double.valueOf(Math.pow(2, 8)).intValue();
        assertArrayEquals(new int[]{6, 28}, creatArrayUtil().getPerfectNumbers(max));

    }

    @Test
    public void join() throws Exception {
        assertEquals("1_2_3_10", creatArrayUtil().join(new int[]{1, 2, 3, 10}, "_"));
    }

    private void info(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}