package com.coding.weak1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/12 0012.
 */
public class QueueTest {

    @Test
    public void testEnQueue() throws Exception {
        Queue queue = new Queue();
        queue.enQueue("java");
        queue.enQueue("python");
        Assert.assertEquals(queue.size(), 2);
        Assert.assertEquals(queue.isEmpty(), false);
        Assert.assertEquals(queue.deQueue(), "java");
    }

    @Test
    public void testDeQueue() throws Exception {
        Queue queue = new Queue();
        queue.enQueue("java");
        queue.enQueue("python");
        Assert.assertEquals(queue.size(), 2);
        Assert.assertEquals(queue.isEmpty(), false);
        Assert.assertEquals(queue.deQueue(), "java");
        Assert.assertEquals(queue.deQueue(), "python");
    }

    @Test
    public void testIsEmpty() throws Exception {
        Queue queue = new Queue();
        Assert.assertEquals(queue.isEmpty(), true);
        queue.enQueue("java");
        Assert.assertEquals(queue.isEmpty(), false);
    }

    @Test
    public void testSize() throws Exception {
        Queue queue = new Queue();
        Assert.assertEquals(queue.size(), 0);
        queue.enQueue("java");
        Assert.assertEquals(queue.size(), 1);
    }
}