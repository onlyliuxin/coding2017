package com.bruce.homework0305.array;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Bruce.Jiao on 2017/3/2.
 */
public class JuintArrayUtil extends TestCase {

    @Test
    public void testReverse(){
        ArrayUtil au = new ArrayUtil();
        int[] demo0 = {};
        int[] demo1 = {6};
        int[] demo = {7, 9, 30, 3, 4, 6};
        int[] nullArray = au.reverseArray(null);
        int[] reverseArray0 = au.reverseArray(demo0);
        int[] reverseArray1 = au.reverseArray(demo1);
        int[] reverseArray = au.reverseArray(demo);
        System.out.println(Arrays.toString(nullArray));
        System.out.println(Arrays.toString(reverseArray0));
        System.out.println(Arrays.toString(reverseArray1));
        System.out.println(Arrays.toString(reverseArray));
    }

    @Test
    public void testRemoveZero(){
        ArrayUtil au = new ArrayUtil();
        int[] one = {0};
        int[] many = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        System.out.println(Arrays.toString(au.removeZero(one)));
        System.out.println(Arrays.toString(au.removeZero(many)));
        System.out.println(Arrays.toString(au.removeZero(null)));
        System.out.println(Arrays.toString(au.removeZero(new int[0])));
    }

    @Test
    public void testMerge(){
        ArrayUtil au = new ArrayUtil();
        int[] arr1 = {3,4,5,6,7,8,9};
        int[] arr2 = {1,3,5,6,7,9,10,12,13};
        int[] arr3 = null;
        int[] arr4 = new int[0];
        System.out.println(Arrays.toString(au.merge(arr1,arr2)));
        System.out.println(Arrays.toString(au.merge(arr1,arr3)));
        System.out.println(Arrays.toString(au.merge(arr2,arr4)));
    }

    @Test
    public void testGrow(){
        ArrayUtil au = new ArrayUtil();
        int[] arr = {3,4,5,6,7,8,9};
        System.out.println(Arrays.toString(au.grow(arr,5)));
    }

    @Test
    public void testFibonacci(){
        ArrayUtil au = new ArrayUtil();
        System.out.println(Arrays.toString(au.fibonacci(15)));
    }

    @Test
    public void testPrimes(){
        ArrayUtil au = new ArrayUtil();
        System.out.println(Arrays.toString(au.getPrimes(23)));
    }

    @Test
    public void testPerfectNumbers(){
        ArrayUtil au = new ArrayUtil();
        System.out.println(Arrays.toString(au.getPerfectNumbers(23)));
    }

    @Test
    public void testJoin(){
        ArrayUtil au = new ArrayUtil();
        int[] array = {1,6,8,8,8,8,8,8,8,8,8,};
        System.out.println(au.join(array,"-"));
    }
}
