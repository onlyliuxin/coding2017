package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Queue Test
 */
public class QueueTest {

    Queue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue<>();
    }

    @After
    public void tearDown() throws Exception {
        queue = null;
    }

    @Test
    public void enQueue() throws Exception {
        queue.enQueue("A");
        assertEquals("A",queue.deQueue());
    }

    @Test
    public void peek() throws Exception {
        assertEquals(null,queue.peek());
        queue.enQueue("A");
        assertEquals("A",queue.peek());
    }

    @Test
    public void deQueue() throws Exception {
        queue.enQueue("A");
        queue.enQueue("B");
        assertEquals("A",queue.deQueue());

    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(true,queue.isEmpty());
        queue.enQueue("A");
        assertEquals(false,queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        queue.enQueue("A");
        queue.enQueue("B");
        assertEquals(2,queue.size());
    }

}