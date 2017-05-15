package com.github.wdn.coding2017.coderising.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class ArrayUtilTest {
    ArrayUtil arrayUtil = new ArrayUtil();

    @Test
    public void testReverseArray(){
        int[] pre = new int[]{7, 9 , 30, 3};
        arrayUtil.reverseArray(pre);
        Assert.assertArrayEquals(pre, new int[]{3, 30, 9, 7});
    }
    @Test
    public void testRemoveZero(){
        int[] arr = new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        Assert.assertArrayEquals(arrayUtil.removeZero(arr),new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5});
    }
    @Test
    public void testMerge(){
        int[] a1 = new int[]{3, 5, 7, 8};
        int[] a2 = new int[]{4, 5, 6, 7};
        Assert.assertArrayEquals(arrayUtil.merge(a1, a2), new int[]{3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testGrow(){
        int[] result = arrayUtil.grow(new int[]{1,2,3},3);
        Assert.assertArrayEquals(result,new int[]{1, 2, 3, 0, 0, 0});
    }
    @Test
    public void testFibonacci(){
        int[] result = arrayUtil.fibonacci(25);
        Assert.assertArrayEquals(result,new int[]{1, 1, 2, 3, 5, 8, 13, 21});
    }
    @Test
    public void testGetPrimes(){
        int[] result = arrayUtil.getPrimes(25);
        Assert.assertArrayEquals(result,new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23});
    }
    @Test
    public void testGetPerfectNumbers(){
        int[] result = arrayUtil.getPerfectNumbers(100000);
        Assert.assertArrayEquals(result,new int[]{6, 28, 496, 8128});
    }
    @Test
    public void testJoin(){
        String result = arrayUtil.join(new int[]{3, 8, 9},"-");
        Assert.assertEquals(result,"3-8-9");
    }
}
