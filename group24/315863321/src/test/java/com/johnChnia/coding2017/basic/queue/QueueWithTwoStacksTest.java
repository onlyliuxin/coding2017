package com.johnChnia.coding2017.basic.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by john on 2017/5/6.
 */
public class QueueWithTwoStacksTest {
    QueueWithTwoStacks<Integer> qwts;

    @Before
    public void setUp() throws Exception {
        qwts = new QueueWithTwoStacks<>();
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testEnQueueAndDeQueue() throws Exception {
        for (int i = 0; i < 10; i++) {
            qwts.enQueue(i);
        }
        qwts.deQueue();
        qwts.deQueue();
        qwts.deQueue();
        Assert.assertEquals("3→4→5→6→7→8→9", qwts.toString());
    }


}