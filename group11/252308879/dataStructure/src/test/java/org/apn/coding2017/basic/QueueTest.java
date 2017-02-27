package org.apn.coding2017.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pan on 2017/2/26.
 */
public class QueueTest {

    Queue queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

    }

    @Test
    public void enQueue() throws Exception {
        queue.enQueue(1);
        System.out.println(queue);
    }

    @Test
    public void deQueue() throws Exception {
        queue.deQueue();
        System.out.println(queue);
    }

    @Test
    public void isEmpty() throws Exception {
        System.out.println(queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        System.out.println(queue.size());
    }

}