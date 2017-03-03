package com.github.lqingchenl.coding2017.basic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Queue Tester.
 */
public class QueueTest {

    private static Queue testQueue = new Queue();

    /**
     * Method: enQueue(Object o)
     */
    @Test
    public void testEnQueue() throws Exception {
        testQueue.enQueue(1);
        assertEquals(1, testQueue.deQueue());
    }

    /**
     * Method: deQueue()
     */
    @Test
    public void testDeQueue() throws Exception {
        testQueue.enQueue(1);
        testQueue.enQueue(2);
        assertEquals(1, testQueue.deQueue());
        assertEquals(2, testQueue.deQueue());
    }

    /**
     * Method: isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {
        testQueue.enQueue(1);
        assertEquals(1, testQueue.deQueue());
        assertEquals(true, testQueue.isEmpty());
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        testQueue.enQueue(1);
        testQueue.enQueue(2);
        assertEquals(2, testQueue.size());
    }


} 
