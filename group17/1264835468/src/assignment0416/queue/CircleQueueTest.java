package assignment0416.queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/6.
 */
public class CircleQueueTest {
    CircleQueue<Integer> queue = new CircleQueue<>();

    @Test
    public void queueTest() throws Exception {
        Assert.assertTrue(queue.isEmpty());
        queue.enQueue(0);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        Assert.assertEquals(7, queue.size());
        Assert.assertEquals(0, queue.deQueue().intValue());
        Assert.assertEquals(1, queue.deQueue().intValue());
        queue.enQueue(7);
        queue.enQueue(8);
        queue.enQueue(9);
        queue.enQueue(10);
        queue.enQueue(11);
        Assert.assertTrue(queue.isFull());
        Assert.assertEquals(2, queue.deQueue().intValue());
        queue.enQueue(12);
        Assert.assertEquals("[3, 4, 5, 6, 7, 8, 9, 10, 11, 12]", queue.toString());
    }

}