package com.johnChnia.coding2017.basic;

import com.johnChnia.coding2017.basic.queue.Queue;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by john on 2017/3/11.
 */
public class QueueTest {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    Queue<Integer> queue3;

    @Before
    public void setUp() throws Exception {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
        queue3 = new Queue<>();

    }

    @Test
    public void testAdd() throws Exception {
        for (int i = 0; i < 3; i++) {
            queue1.add(i);
        }
        System.out.println(queue1);
        assertThat(queue1.peek(), equalTo(0));

    }

    @Test
    public void testRemove() throws Exception {
        for (int i = 0; i < 3; i++) {
            queue2.add(i);
        }
        assertThat(queue2.remove(), equalTo(0));
        assertThat(queue2.remove(), equalTo(1));
        assertThat(queue2.remove(), equalTo(2));
        assertThat(queue2.size(), equalTo(0));
    }

    @Test
    public void testPeek() throws Exception {
        for (int i = 0; i < 3; i++) {
            queue3.add(i);
        }
        assertThat(queue3.peek(), equalTo(0));
        assertThat(queue3.size(), equalTo(3));
    }

}