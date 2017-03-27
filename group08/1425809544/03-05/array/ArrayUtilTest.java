package com.util_1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 14258 on 2017/2/28.
 */
public class ArrayUtilTest {
    ArrayUtil arrayUtil = new ArrayUtil();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReverseArray() throws Exception {

        int[] testArr = {7, 9, 30, 3};

        arrayUtil.reverseArray(testArr);
    }

    @Test
    public void testRemoveZero() throws Exception {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] newArr = arrayUtil.removeZero(oldArr);
        for (int s : newArr) {
            System.out.println(s);
        }

    }

    @Test
    public void testMerge() throws Exception {
        int[] a1 = {3, 5, 7};
        int[] a2 = {4, 5, 6, 7};
        int[] newArr = arrayUtil.merge(a1, a2);
        for (int s : newArr) {
            System.out.println(s);
        }
    }

    @Test
    public void testGrow() throws Exception {
        int[] oldArray = {2, 3, 6};

        int[] newArr = arrayUtil.grow(oldArray, 3);

        for (int s : newArr) {
            System.out.println(s);
        }
    }

    @Test
    public void testFibonacci() throws Exception {

        int[] newArr = arrayUtil.fibonacci(16);
        System.out.print("[");
        for (int i : newArr) {
            System.out.print(i+",");
        }
        System.out.print("]");
    }

    @Test
    public void testGetPrimes() throws Exception {
        int[] prime =  arrayUtil.getPrimes(23);

        for (int i :prime){
            System.out.print(i+" ");
        }
    }

    @Test
    public void testGetPerfectNumbers() throws Exception {
        int[] prime =  arrayUtil.getPerfectNumbers(10000);

        for (int i :prime){
            System.out.print(i+" ");
        }
    }

    @Test
    public void testJoin() throws Exception {
        int[] array = {3, 8, 9};
        String s = arrayUtil.join(array, "-");
        System.out.println(s);
    }
}