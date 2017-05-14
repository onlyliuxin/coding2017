package com.johnChnia.coding2017.basic.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by john on 2017/5/6.
 */
public class CircleQueueTest {

    CircleQueue cq1;

    @Before
    public void setUp() throws Exception {
        cq1 = new CircleQueue();
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testEnQueueAndDeQueue() throws Exception {
        for (int i = 0; i < 10; i++) {
            cq1.enQueue(i);
        }
        cq1.deQueue();
        cq1.deQueue();
        cq1.enQueue(100);
        cq1.enQueue(120);
        Assert.assertEquals("(100, 120, 2, 3, 4, 5, 6, 7, 8, 9, )", cq1.toString());
    }

}