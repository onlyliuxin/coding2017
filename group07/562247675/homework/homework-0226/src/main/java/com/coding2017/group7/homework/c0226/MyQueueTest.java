package com.coding2017.group7.homework.c0226;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyQueueTest {

    private MyQueue myQueue = new MyQueue();
    private final Object[] elements = {1, 2, 3};
    private final int mySize = elements.length;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mySize; i++) {
            myQueue.enQueue(i + 1);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (int i = myQueue.size(); i > 0; i--) {
            myQueue.deQueue();
        }
    }

    @Test
    public void enQueue() throws Exception {
        myQueue.enQueue(-1);
        Object o = 0;
        for (int i = myQueue.size(); i > 0; i--) {
            o = myQueue.deQueue();
        }
        Assert.assertTrue(o.equals(-1));
    }

    @Test
    public void deQueue() throws Exception {
        myQueue.enQueue(-1);
        Object o = myQueue.deQueue();
        Assert.assertTrue(o.equals(elements[0]));
    }

}