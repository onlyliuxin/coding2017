package com.coding.basic.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class ArrayQueueTest {
    private ArrayQueue<Integer> arrayQueue;

    @Before
    public void init(){
        arrayQueue = new ArrayQueue<>(6);
        for (int i = 0; i < 5; i++) {
            arrayQueue.add(i);
        }

    }
    @Test
    public void add() throws Exception {
        Assert.assertTrue(arrayQueue.add(5));


        Assert.assertEquals(0, arrayQueue.peek().intValue());
        Assert.assertEquals(0, arrayQueue.poll().intValue());
        Assert.assertEquals(1, arrayQueue.poll().intValue());

        for (int i = 0; i < 4; i++) {
            arrayQueue.remove();
        }
        Assert.assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void offer() throws Exception {
        Assert.assertTrue(arrayQueue.offer(5));
        Assert.assertFalse(arrayQueue.offer(6));
    }

    @Test
    public void remove() throws Exception {
        arrayQueue.remove();
        arrayQueue.remove();
        arrayQueue.remove();
        arrayQueue.remove();

        arrayQueue.add(5);
        arrayQueue.add(6);
        arrayQueue.add(7);
        arrayQueue.add(8);
        arrayQueue.add(9);


        Assert.assertEquals(4, arrayQueue.remove().intValue());
        Assert.assertEquals(5, arrayQueue.remove().intValue());
        Assert.assertEquals(6, arrayQueue.remove().intValue());
        Assert.assertEquals(7, arrayQueue.remove().intValue());
        Assert.assertEquals(8, arrayQueue.remove().intValue());
        Assert.assertEquals(9, arrayQueue.remove().intValue());
    }


}