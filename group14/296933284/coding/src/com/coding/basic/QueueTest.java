package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by damocles on 2017/3/7.
 */
public class QueueTest {
    private Queue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue<>();

        queue.enQueue("javascript");
        queue.enQueue("java");
        queue.enQueue("c++");
        queue.enQueue("c");
    }

    @After
    public void tearDown() throws Exception {
        queue = null;
    }

    @Test
    public void enQueue() throws Exception {
        queue.enQueue("php");

        Assert.assertEquals(5, queue.size());
    }

    @Test
    public void deQueue() throws Exception {
        Assert.assertEquals("javascript",queue.deQueue());
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertFalse(queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(4, queue.size());
    }

}