package org.korben.queue;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * KQueue Test
 *
 * Created by Korben on 19/02/2017.
 */
public class KQueueTest {
    private KQueue<Integer> queue;

    @Before
    public void init() {
        queue = new KArrayQueue<>();
    }

    @Test
    public void add() throws Exception {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
    }

    @Test
    public void offer() throws Exception {
        for (int i = 0; i < 100; i++) {
            queue.offer(i);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void remove() throws Exception {
        for (int i = 0; i < 9; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 9; i++) {
            Assert.assertEquals(i, queue.remove().intValue());
        }
        queue.remove();
    }

    @Test
    public void poll() throws Exception {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, queue.poll().intValue());
        }
        Assert.assertNull(queue.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void element() throws Exception {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
            Assert.assertEquals(queue.element().intValue(), 0);
        }
        init();

        queue.element();
    }

    @Test
    public void peek() throws Exception {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
            Assert.assertEquals(queue.peek().intValue(), 0);
        }
        init();

        Assert.assertNull(queue.peek());
    }
}
