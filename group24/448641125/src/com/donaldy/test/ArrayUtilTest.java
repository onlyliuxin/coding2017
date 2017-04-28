package com.donaldy.test;

import com.donaldy.basic.ArrayUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by donal on 2017/3/13.
 */
public class ArrayUtilTest {

    private ArrayUtil arrayUtil;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void before() {
        arrayUtil = new ArrayUtil();
    }

    @Test
    public void testReverseArray() {
        int []A = {7, 9, 30, 3};
        int []B = {7, 9, 30, 3, 4};
        arrayUtil.reverseArray(A);
        assertArrayEquals(new int[] {3, 30, 9, 7}, A);
        arrayUtil.reverseArray(B);
        assertArrayEquals(new int[] {4, 3, 30 , 9, 7}, B);
    }

    @Test
    public void testRuntimeException() {
        thrown.expect(RuntimeException.class);
        arrayUtil.reverseArray(null);
    }

    @Test
    public void testRemoveZero() {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int newArr[] = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5};
        assertArrayEquals(newArr, arrayUtil.removeZero(oldArr));
    }

    @Test
    public void testMerge() {
        int [] arr1 = {3, 5, 7, 8};
        int [] arr2 = {4, 5, 6, 7};
        int [] answer = new int[]{3,4,5,6,7,8};
        assertArrayEquals(answer, arrayUtil.merge(arr1, arr2));
    }

    @Test
    public void testGrow() {
        int [] oldArray = {2, 3, 6};
        int [] newArray = {2, 3, 6, 0, 0, 0};
        assertArrayEquals(newArray, arrayUtil.grow(oldArray, 3));
    }

    @Test
    public void testFibonacci() {
        int [] testArr = {1, 1, 2, 3, 5, 8, 13};
        int [] testArr2 = {1, 1};
        assertArrayEquals(testArr, arrayUtil.fibonacci(15));
        assertArrayEquals(testArr2, arrayUtil.fibonacci(2));
    }

    @Test
    public void testGetPrimes() {
        int [] testArr = {2, 3, 5, 7, 11, 13, 17, 19};
        assertArrayEquals(testArr, arrayUtil.getPrimes(23));
    }

    @Test
    public void testGetPerfectNumbers() {
        int [] testArr = {6};
        assertArrayEquals(testArr, arrayUtil.getPerfectNumbers(10));
    }

    @Test
    public void testJoin() {
        int [] testArr = {3, 8, 9};
        assertEquals("3-8-9", arrayUtil.join(testArr, "-"));
    }
}
