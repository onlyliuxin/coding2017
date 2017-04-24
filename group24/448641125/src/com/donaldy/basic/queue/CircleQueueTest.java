package com.donaldy.basic.queue;

import org.junit.Assert;
import org.junit.Test;



/**
 * Created by DonaldY on 2017/4/24.
 */
public class CircleQueueTest {

    @Test
    public void test() {
        CircleQueue<Integer> cirQueue = new CircleQueue<>();

        for (int i = 1; i <= 10; ++i) {
            cirQueue.enQueue(i);
        }

        Assert.assertEquals(10, cirQueue.size());

        for (int i = 0; i < 10; ++i) {
            Assert.assertEquals(i + 1, (int)cirQueue.getElement(i));
        }


        for (int i = 0; i <= 5; ++i) {
            Assert.assertEquals(i + 1, (int)cirQueue.deQueue());
        }

        Assert.assertEquals(4, cirQueue.size());

    }
}
