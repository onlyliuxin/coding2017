package com.coderising.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damocles on 2017/3/1.
 */
public class ArrayUtilTest {
    private ArrayUtil arrayUtil;
    private int[] oldArray = null;
    private int[] newArray = null;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
    }

    @After
    public void tearDown() throws Exception {
        if (newArray != null)
            for (int i = 0; i < newArray.length; i++)
                System.out.print(newArray[i] + "\t");

        System.out.println();
    }


    @Test
    public void reverseArray() throws Exception {
        oldArray = new int[]{7, 9, 30, 3};

        arrayUtil.reverseArray(oldArray);

        for (int i = 0; i < oldArray.length; i++)
            System.out.print(oldArray[i] + "\t");

        oldArray = new int[]{3, 30, 9, 7, 4};

        System.out.println();

        arrayUtil.reverseArray(oldArray);

        for (int i = 0; i < oldArray.length; i++)
            System.out.print(oldArray[i] + "\t");
    }

    @Test
    public void removeZero() throws Exception {
        oldArray = new int[]{1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};

        newArray = arrayUtil.removeZero(oldArray);
    }

    @Test
    public void merge() throws Exception {
        int[] a1 = {3, 5, 7, 8}, a2 = {4, 5, 6, 7};

        newArray = arrayUtil.merge(a1, a2);
    }

    @Test
    public void grow() throws Exception {
        oldArray = new int[]{2, 3, 6};
        int size = 3;

        newArray = arrayUtil.grow(oldArray, size);

    }

    @Test
    public void fibonacci() throws Exception {
        newArray = arrayUtil.fibonacci(15);
    }

    @Test
    public void getPrimes() throws Exception {
        newArray = arrayUtil.getPrimes(23);
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        newArray = arrayUtil.getPerfectNumbers(500);
    }

    @Test
    public void join() throws Exception {
        oldArray = new int[]{3, 8, 9, 10, 2};
        String seperator = "-";

        String str = arrayUtil.join(oldArray, seperator);

        System.out.println(str);
    }

}