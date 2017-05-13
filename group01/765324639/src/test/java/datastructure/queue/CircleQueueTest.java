package datastructure.queue;

import org.junit.Assert;
import org.junit.Test;

public class CircleQueueTest {

    @Test
    public void test() {
        CircleQueue<String> circleQueue = new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            circleQueue.enQueue("circleQueue" + i);
        }
        Assert.assertEquals(10, circleQueue.size());
        Assert.assertFalse(circleQueue.isEmpty());
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals("circleQueue" + i, circleQueue.deQueue());
        }
        Assert.assertEquals(5, circleQueue.size());
        Assert.assertFalse(circleQueue.isEmpty());
        for (int i = 5; i< 10; i++) {
            Assert.assertEquals("circleQueue" + i, circleQueue.deQueue());
        }
        Assert.assertEquals(0, circleQueue.size());
        Assert.assertTrue(circleQueue.isEmpty());
        
        for (int i = 0; i < 6; i++) {
            circleQueue.enQueue("item" + i);
        }
        Assert.assertEquals(6, circleQueue.size());
        Assert.assertFalse(circleQueue.isEmpty());
        for (int i = 0; i < 6; i++) {
            Assert.assertEquals("item" + i, circleQueue.deQueue());
        }
        Assert.assertEquals(0, circleQueue.size());
        Assert.assertTrue(circleQueue.isEmpty());
    }
}
