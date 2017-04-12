package com.coding.basic.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ArrayUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 14, 2017</pre>
 */
public class ArrayUtilTest {

    int[] testArray ;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
        testArray = new int[]{};
    }

    /**
     * Method: reverseArray(final int[] origin)
     */
    @Test
    public void testReverseArray() throws Exception {
        testArray = new int[]{1,3,5,7,9,4,6};
        ArrayUtil.reverseArray(testArray);
        Assert.assertArrayEquals(new int[]{6,4,9,7,5,3,1},testArray);
    }


    /**
     * Method: removeZero(int[] oldArray)
     */
    @Test
    public void testRemoveZero() throws Exception {
        testArray = new int[]{1,3,0,7,0,4,6};
        int[] newArray = ArrayUtil.removeZero(testArray);
        Assert.assertArrayEquals(new int[]{1,3,7,4,6}, newArray);
    }

    /**
     * Method: merge(int[] array1, int[] array2)
     */
    @Test
    public void testMerge() throws Exception {
        int[] testArray1 = new int[]{1,3,6,8,9};
        int[] testArray2 = new int[]{2,3,3,10,12};

        int[] mergedArray = ArrayUtil.merge(testArray1,testArray2);
        Assert.assertArrayEquals(new int[]{1,2,3,3,3,6,8,9,10,12},mergedArray);
    }

    /**
     * Method: grow(int[] oldArray, int size)
     */
    @Test
    public void testGrow() throws Exception {
        testArray = new int[]{1,2,3,4,5,6};
        int[] grewArray = ArrayUtil.grow(testArray,4);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6,0,0,0,0},grewArray);

    }

    /**
     * Method: fibonacci(int max)
     */
    @Test
    public void testFibonacci() throws Exception {
        int[] fibArray = ArrayUtil.fibonacci(20);
        Assert.assertArrayEquals(new int[]{1,1,2,3,5,8,13},fibArray);
    }

    /**
     * Method: getPrimes(int max)
     */
    @Test
    public void testGetPrimes() throws Exception {
        testArray = ArrayUtil.getPrimes(23);
        Assert.assertArrayEquals(new int[]{2,3,5,7,11,13,17,19},testArray);
    }

    /**
     * Method: getPerfectNumbers(int max)
     */
    @Test
    public void testGetPerfectNumbers() throws Exception {
        testArray = ArrayUtil.getPerfectNumbers(1000);
        Assert.assertArrayEquals(new int[]{6,28,496},testArray);
    }

    /**
     * Method: join(int[] array, String seperator)
     */
    @Test
    public void testJoin() throws Exception {
        testArray = new int[]{1,2,3,5,7,9,12};
        String seperated = ArrayUtil.join(testArray,"-");
        Assert.assertEquals("1-2-3-5-7-9-12",seperated);
    }


} 
