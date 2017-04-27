package com.aaront.execrise.basic;

import com.aaront.exercise.basic.Queue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/21
 */
public class QueueTest {
    private Queue queue = new Queue();

    @Before
    public void init() {
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
    }

    @Test
    public void testEnqueue() {
        Assert.assertArrayEquals(queue.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testDequeue() {
        queue.deQueue();
        queue.deQueue();
        Assert.assertArrayEquals(queue.toArray(), new Object[]{3});
    }
}
