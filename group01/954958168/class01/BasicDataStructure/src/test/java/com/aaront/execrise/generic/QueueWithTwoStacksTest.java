package com.aaront.execrise.generic;

import com.aaront.exercise.generic.QueueWithTwoStacks;
import org.junit.Assert;
import org.junit.Test;

public class QueueWithTwoStacksTest {

    @Test
    public void test() {
        QueueWithTwoStacks<String> queue = new QueueWithTwoStacks<>();
        Assert.assertNull(queue.deQueue());
        Assert.assertTrue(queue.isEmpty());
        for (int i = 0; i < 100; i++) {
            queue.enQueue("item" + i);
        }
        for (int i = 0; i < 50; i++) {
            Assert.assertEquals("item" + i, queue.deQueue());
        }
        queue.enQueue("item100");
        queue.enQueue("item101");
        Assert.assertEquals(52, queue.size());
        int i = 50;
        while (!queue.isEmpty()) {
            Assert.assertEquals("item" + i++, queue.deQueue());
        }
    }
}