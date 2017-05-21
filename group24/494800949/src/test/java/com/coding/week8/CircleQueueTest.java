package com.coding.week8;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class CircleQueueTest {
    private CircleQueue<Integer> queue;

    @Before
    public void setup(){
        queue = new CircleQueue<>();

    }
    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(queue.isEmpty());
        queue.enQueue(10);
        Assert.assertTrue(!queue.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        queue.enQueue(10);
        queue.enQueue(2);
        Assert.assertEquals(queue.size(), 2);
        queue.deQueue();
        Assert.assertEquals(queue.size(), 1);
    }

    @Test
    public void testEnQueue() throws Exception {
        for (int i = 0; i < 11; i++) {
            queue.enQueue(i*i);
        }
        Assert.assertEquals((int)queue.deQueue(), 0);
        Assert.assertEquals((int)queue.deQueue(), 1);
        Assert.assertEquals((int)queue.deQueue(), 4);
        Assert.assertEquals((int)queue.deQueue(), 9);
        Assert.assertEquals((int)queue.deQueue(), 16);
        Assert.assertEquals((int)queue.deQueue(), 25);
        Assert.assertEquals((int)queue.deQueue(), 36);
        Assert.assertEquals((int)queue.deQueue(), 49);
        Assert.assertEquals((int)queue.deQueue(), 64);
        Assert.assertEquals((int)queue.deQueue(), 81);
        Assert.assertEquals((int)queue.deQueue(), 100);
        Assert.assertEquals(queue.deQueue(), null);
    }


}