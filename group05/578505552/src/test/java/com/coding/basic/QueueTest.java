package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by songbao.yang on 2017/2/24.
 */
public class QueueTest {

    private static final int SIZE = 2000;
    private Queue queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void enQueue() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            queue.enQueue(i);
            Assert.assertEquals(i+1, queue.size());
        }
    }

    @Test
    public void deQueue1() throws Exception {
        enQueue();

        int i = 0;
        int startSize = queue.size();
        while (!queue.isEmpty()) {
            Assert.assertEquals(startSize - i, queue.size());
            Object o = queue.deQueue();
            Assert.assertEquals(SIZE - i - 1, queue.size());
            Assert.assertEquals(i, o);
            i++;
        }
    }

    @Test
    public void deQueue2() throws Exception {
        enQueue();
        int startSize = queue.size();

        for (int i = 0; i < startSize; i++) {
            queue.deQueue();
            Assert.assertEquals(startSize - 1, queue.size());
            queue.enQueue(i+1000);
            Assert.assertEquals(startSize, queue.size());
        }

    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

}