package com.aaront.execrise.generic;

import com.aaront.exercise.generic.GenericQueue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/21
 */
public class GenericQueueTest {
    private GenericQueue<String> queue = new GenericQueue<>();

    @Before
    public void init() {
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
    }

    @Test
    public void testEnqueue() {
        Assert.assertArrayEquals(queue.toArray(), new String[]{"1", "2", "3"});
    }

    @Test
    public void testDequeue() {
        queue.deQueue();
        queue.deQueue();
        Assert.assertArrayEquals(queue.toArray(), new String[]{"3"});
    }
}
