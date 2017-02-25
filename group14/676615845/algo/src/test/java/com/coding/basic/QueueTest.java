package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mark on 17/2/25.
 */
public class QueueTest {

    private Queue queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue();
    }

    @After
    public void tearDown() throws Exception {
        queue = null;
    }

    @Test
    public void enQueue() throws Exception {
        queue.enQueue("first");
        queue.enQueue("second");
        queue.enQueue("third");
        Assert.assertEquals("first", queue.deQueue());
        Assert.assertEquals("second", queue.deQueue());
        Assert.assertEquals("third", queue.deQueue());
    }

    @Test
    public void deQueue() throws Exception {

    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertEquals(true, queue.isEmpty());
        queue.enQueue("first");
        Assert.assertEquals(false, queue.isEmpty());
        queue.deQueue();
        Assert.assertEquals(true, queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(0, queue.size());
        queue.enQueue("first");
        Assert.assertEquals(1, queue.size());
        queue.deQueue();
        Assert.assertEquals(0, queue.size());
    }

}