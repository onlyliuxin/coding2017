package com.circle.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/3/5.
 */
public class ArrayUtilTest {

    ArrayUtil util = new ArrayUtil();

    @Test
    public void reverseArray() throws Exception {
        int[] origin = new int[]{7, 9, 30, 3, 4};
        util.reverseArray(origin);
        for (int i : origin) {
            System.out.println(i);
        }
    }

    @Test
    public void removeZero() throws Exception {
        int[]  origin = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int[] newArray = util.removeZero(origin);
        for (int i : newArray) {
            System.out.println(i);

        }

    }

    @Test
    public void fibonacci() throws Exception {
        int[] newArray = util.fibonacci(15);
        for (int i : newArray) {
            System.out.println(i);
        }
    }

    @Test
    public void getPrimes() throws Exception {
        int[] newArray = util.getPrimes(23);
        for (int i : newArray) {
            System.out.println(i);
        }

    }

    @Test
    public void join() throws Exception {
        int[]  origin = new int[]{3,8,9};
        System.out.println(util.join(origin, "-"));
    }

}