package com.coding.week8;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class QueueWithTwoStacksTest {

    private QueueWithTwoStacks<Integer> queue;

    @Before
    public void setup(){
        queue = new QueueWithTwoStacks<>();
    }
    @Test
    public void testEnQueue() throws Exception {
        queue.enQueue(1);
        queue.enQueue(5);
        queue.enQueue(8);

        Assert.assertEquals((int) queue.deQueue(), 1);
        queue.enQueue(10);
        Assert.assertEquals((int) queue.deQueue(), 5);
        Assert.assertEquals((int) queue.deQueue(), 8);
        Assert.assertEquals((int)queue.deQueue(), 10);

    }
}