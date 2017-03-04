package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mortimer on 2017/2/26.
 *
 */
public class QueueTest {

    @Test
    public void testEnQueue() {
        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }

        System.out.println(queue);
        Integer data = queue.deQueue();
        System.out.println(queue);
        Assert.assertEquals(data, Integer.valueOf(9));
    }
}
