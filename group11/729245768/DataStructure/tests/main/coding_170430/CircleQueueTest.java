package main.coding_170430;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peterchen on 2017/5/4.
 */
public class CircleQueueTest extends TestCase {
    @Test
    public void testIsEmpty() throws Exception {
        CircleQueue<Integer> queue = new CircleQueue<>();
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        CircleQueue<Integer> queue = new CircleQueue<>();
        Assert.assertEquals(queue.size(),0);
        queue.enQueue(12);
        queue.enQueue(23);
        Assert.assertEquals(queue.size(),2);
    }

    @Test
    public void testEnQueue() throws Exception {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for(int i=0;i<9;i++){
            queue.enQueue(i);
        }
        Assert.assertEquals(queue.size(),9);
        queue.deQueue();
        queue.enQueue(10);
    }

    @Test
    public void testDeQueue() throws Exception {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for(int i=0;i<5;i++){
            queue.enQueue(i);
        }
        Assert.assertEquals(queue.size(),5);
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        Assert.assertEquals(queue.size(),2);
        for(int i=10;i<17;i++){
            queue.enQueue(i);
        }
        Assert.assertEquals(queue.size(),9);
    }

}