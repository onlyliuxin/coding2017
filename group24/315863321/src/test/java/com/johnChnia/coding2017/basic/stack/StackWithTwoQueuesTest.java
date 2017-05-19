package com.johnChnia.coding2017.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by john on 2017/5/7.
 */
public class StackWithTwoQueuesTest {
    StackWithTwoQueues stq;
    @Before
    public void setUp() throws Exception {
        stq = new StackWithTwoQueues();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPushAndPop() throws Exception {
        for (int i = 0; i < 6; i++) {
            stq.push(i);
        }
        Assert.assertEquals("[5, 4, 3, 2, 1, 0, ]", stq.toString());
        stq.pop();
        Assert.assertEquals("[4, 3, 2, 1, 0, ]", stq.toString());
    }


}