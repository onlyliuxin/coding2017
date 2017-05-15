package com.johnChnia.coding2017.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by john on 2017/5/7.
 */
public class TwoStackInOneArrayTest {
    TwoStackInOneArray twoStackInOneArray;

    @Before
    public void setUp() throws Exception {
        twoStackInOneArray = new TwoStackInOneArray();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPushAndPopAndPeek() throws Exception {
        twoStackInOneArray.push1(0);
        twoStackInOneArray.push1(1);
        twoStackInOneArray.push2(2);
        twoStackInOneArray.push2(3);
        twoStackInOneArray.push1(4);
        twoStackInOneArray.push1(5);
        twoStackInOneArray.push2(6);
        twoStackInOneArray.push2(7);
        twoStackInOneArray.push2(8);
        Assert.assertEquals("(0, 1, 4, 5, 8, 7, 6, 3, 2, )",
                twoStackInOneArray.toString());
        twoStackInOneArray.pop1();
        twoStackInOneArray.pop2();
        Assert.assertEquals("(0, 1, 4, 7, 6, 3, 2, )",
                twoStackInOneArray.toString());

        Assert.assertEquals(4,
                twoStackInOneArray.peek1());
        Assert.assertEquals(7,
                twoStackInOneArray.peek2());
    }

}