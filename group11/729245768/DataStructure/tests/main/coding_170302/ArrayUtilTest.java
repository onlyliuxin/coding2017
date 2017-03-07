package main.coding_170302;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by peter on 2017/3/2.
 */
public class ArrayUtilTest extends TestCase {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReverseArray() throws Exception {
        int[] array1 = {1,2,3,4,5,6,7};
        int[] array2 = {7,6,5,4,3,2,1};
        ArrayUtil util = new ArrayUtil();
        util.reverseArray(array1);
        Assert.assertArrayEquals(array1,array2);
    }

    @Test
    public void testRemoveZero() throws Exception {
        int[] array1 = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int[] arry2 = {1,3,4,5,6,6,5,4,7,6,7,5};
        Assert.assertArrayEquals(new ArrayUtil().removeZero(array1),arry2);
}

    @Test
    public void testMerge() throws Exception {
        int[] array1 = {3,5,7,8};
        int[] array2 = {4,5,6,7};
        int[] array = {3,4,5,6,7,8};
        Assert.assertArrayEquals(array,new ArrayUtil().merge(array1,array2));
    }

    @Test
    public void testGrow() throws Exception {
        int[] array1 ={2,3,6};
        int[] array2 = {2,3,6,0,0,0};
        Assert.assertArrayEquals(array2,new ArrayUtil().grow(array1,3));
    }

    @Test
    public void testFibonacci() throws Exception {
        int[] array1 = {1,1,2,3,5,8,13};
        Assert.assertArrayEquals(array1,new ArrayUtil().fibonacci(15));
    }

    @Test
    public void testGetPrimes() throws Exception {
        int[] array1 = {2,3,5,7,11,13,17,19};
        Assert.assertArrayEquals(array1,new ArrayUtil().getPrimes(23));
    }

    @Test
    public void testGetPerfectNumbers() throws Exception {
        int[] array1 = {6};
        Assert.assertArrayEquals(array1,new ArrayUtil().getPerfectNumbers(10));
    }

    @Test
    public void testJoin() throws Exception {
        int[] array1 = {3,8,9};
        Assert.assertEquals("3-8-9",new ArrayUtil().join(array1,"-"));
    }

}