package zavier.week01.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import zavier.week01.basic.Queue;

public class QueueTest {

    private Queue queue = new Queue();

    @Before
    public void setUp() {
        for (int i = 0; i < 500; i++) {
            queue.enQueue(i);
        }
    }

    @Test
    public void testEnQueue() {
        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
        }
        Assert.assertEquals(600, queue.size());
    }

    @Test
    public void testDeQueue() {
        for (int i = 0; i < 500; i++) {
            Assert.assertEquals(i, queue.deQueue());
        }
        Assert.assertNull(queue.deQueue());
        Assert.assertNull(queue.deQueue());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertFalse(queue.isEmpty());
        Queue q = new Queue();
        Assert.assertTrue(q.isEmpty());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(500, queue.size());
    }

}
