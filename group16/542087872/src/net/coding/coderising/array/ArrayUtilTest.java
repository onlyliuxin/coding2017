package net.coding.coderising.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyuan on 02/03/2017.
 */
public class ArrayUtilTest {

    @Test
    public void testReverseArray() {
        int[] nums = {1, 2, 3};
        new ArrayUtil().reverseArray(nums);
        Assert.assertArrayEquals(nums, new int[]{3, 2, 1});
    }
    // removeZero
    @Test
    public void testRemoveZero() {
        int[] nums = {0, 1, 0, 2, 3};
        int[] ans = new ArrayUtil().removeZero(nums);
        Assert.assertArrayEquals(ans, new int[]{1, 2, 3});
    }

    // merge
    @Test
    public void testMerge() {
        int[] nums1 = {1, 3, 9};
        int[] nums2 = {2, 4, 5};
        int[] ans = new ArrayUtil().merge(nums1, nums2);
        Assert.assertArrayEquals(ans, new int[]{1, 2, 3, 4, 5, 9});
    }

    // grow
    @Test
    public void testGrow() {
        int[] nums = {1, 3, 9};
        int[] ans = new ArrayUtil().grow(nums, 2);
        Assert.assertArrayEquals(ans, new int[]{1, 3, 9, 0, 0});
    }

    // fibonacci
    @Test
    public void testFibonacci() {
        int[] ans = new ArrayUtil().fibonacci(10);
        Assert.assertArrayEquals(ans, new int[]{1, 1, 2, 3, 5, 8});
    }


    // getPrimes
    @Test
    public void testgetPrimes() {
        int[] ans = new ArrayUtil().getPrimes(10);
        Assert.assertArrayEquals(ans, new int[]{1, 2, 3, 5, 7});
    }


    // getPerfectNumbers
    @Test
    public void testGetPerfectNumbers() {
        int[] ans = new ArrayUtil().getPerfectNumbers(10);
        Assert.assertArrayEquals(ans, new int[]{6});
    }


    // join

    @Test
    public void testJoin() {
        String ans = new ArrayUtil().join(new int[]{1, 3, 4}, "-");
        Assert.assertEquals(ans, "1-3-4");
    }



}
