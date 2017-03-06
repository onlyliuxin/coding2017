package org.xukai.common;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xukai
 * @desc
 * @date 2017-02-20-下午 4:36
 */
public class QueueTest {

    @Test
    public void testEnQueue() throws Exception {
        Queue queue = new Queue();
        Assert.assertTrue(queue.isEmpty());
        queue.enQueue("0");
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertTrue(queue.deQueue().equals("0"));
        Assert.assertTrue(queue.deQueue().equals("1"));
        Assert.assertTrue(queue.size() == 2);
        queue.deQueue();
        queue.deQueue();
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testDeQueue() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }
}