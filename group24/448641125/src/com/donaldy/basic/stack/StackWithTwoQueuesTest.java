package com.donaldy.basic.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/5/4.
 */
public class StackWithTwoQueuesTest {

    @Test
    public void test() {

        StackWithTwoQueues stack = new StackWithTwoQueues();

        for (int i = 1; i <= 10; ++i) {
            stack.push(i);
        }

        for (int i = 10; i >= 1; --i) {
            Assert.assertEquals(i, stack.pop());
        }

    }
}
