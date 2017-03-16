package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mark on 17/2/24.
 */
public class ArrayTest {
    private Object[] src;

    @Before
    public void setUp() throws Exception {
        src = new Object[10];
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void grow() throws Exception {
        src = Array.grow(src, 20);
        Assert.assertEquals(30, src.length);
    }

}