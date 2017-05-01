package com.donaldy.basic.queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/4/24.
 */
public class QueueWithTwoStacksTest {

    @Test
    public void test() {
        QueueWithTwoStacks qwStack = new QueueWithTwoStacks();

        for (int i = 1; i <= 5; ++i) {
            qwStack.enQueue(i);
        }

        for (int i = 1; i <= 5; ++i) {
            Assert.assertEquals(i, (int)qwStack.deQueue());
        }
    }
}
