package com.byhieg.coding2017test;

import com.byhieg.coding2017.Queue;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/22.
 * Mail to byhieg@gmail.com
 */
public class QueueTest extends TestCase {
    Queue queue = new Queue();

    public void testEnQueue() throws Exception {
        queue.enQueue(1);
        queue.enQueue("true");
        queue.enQueue(true);
        queue.enQueue(null);
        queue.enQueue(-12341);
        queue.enQueue(Integer.MIN_VALUE - 10000);

    }

    public void testDeQueue() throws Exception {
        for (int i = 0 ; i < 10 ; i++) {
            queue.enQueue(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }


}