package com.coderising.api;

import org.junit.Test;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class CycleTest {

    /**
     * checkIndex will be excuted in each cycle
     */
    @Test
    public void  testForSize() {
        int[] arr = new int[]{1, 2, 3, 4, 54};
        for (int i = 0; checkIndex(i, arr); i++ ) {

        }
    }

    private boolean checkIndex(int i, int[] arr) {
        System.out.println(i);
        return i < arr.length;
    }
}
