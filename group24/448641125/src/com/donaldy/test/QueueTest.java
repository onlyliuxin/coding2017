package com.donaldy.test;

import com.donaldy.basic.Queue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


/**
 * Created by donal on 2017/3/11.
 */
public class QueueTest {

    private Queue queue;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        queue = new Queue();
        for (int i = 0 ; i < 3; ++i)
            queue.enQueue(i);
    }

    @Test
    public void testRuntimeException() {
        assertEquals(3, queue.size());
        assertEquals(false, queue.isEmpty());
        for (int i = 0; i < 3; ++i)
            assertEquals(i, (int) queue.deQueue());
        thrown.expect(RuntimeException.class);
        queue.deQueue();
    }
}
