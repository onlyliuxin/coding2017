package com.circle.collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class QueueTest {

    private Queue queue = null;

    @Test
    public void enQueue() throws Exception {
        queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(4);
        System.out.println(queue.deQueue());
    }

    @Test
    public void deQueue() throws Exception {

        queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println(queue.deQueue());
    }

    @Test
    public void isEmpty() throws Exception {
        queue = new Queue();
//        queue.enQueue(1);
//        queue.enQueue(2);
        System.out.println(queue.isEmpty());
    }

    @Test
    public void size() throws Exception {

        queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println(queue.size());
    }

}