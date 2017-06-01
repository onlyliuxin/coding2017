package com.coderising.api;

import org.junit.Test;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class ComputeTest {

    @Test
    public void testDivisionExactly(){
        System.out.println( 7 >> 1);
        System.out.println( -5 >> 2);
        System.out.println( -5 << 2);
    }

    @Test
    public void testSqrt() {
        System.out.println(Math.sqrt(10));
    }
}
