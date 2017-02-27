package com.coding2017.basic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaitao.li on 17/2/21.
 */
public class QueueTest {
    @Test
    public void enQueue() throws Exception {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        Assert.assertTrue(queue.size() == 2);
        Assert.assertTrue(queue.deQueue().equals(1));
        Assert.assertTrue(queue.deQueue().equals(2));
        Assert.assertTrue(queue.isEmpty());
    }

}