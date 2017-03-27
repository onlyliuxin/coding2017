package cn.net.pikachu.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Created by pikachu on 17-2-25.
 */
public class QueueTest {
    Queue queue;
    @Before
    public void before(){
        queue = new Queue();
    }
    @Test
    public void testEnQueue(){
        for (int i = 0; i < 4; i++) {
            queue.enQueue(i);
        }
        Assert.assertEquals("[0,1,2,3]",queue.toString());
        Assert.assertEquals(4,queue.size());
    }
    @Test(expected= NoSuchElementException.class)
    public void testDeQueue(){
        for (int i = 0; i < 4; i++) {
            queue.enQueue(i);
        }
        for (int i=0;i<4;i++) {
            Assert.assertEquals(i,queue.deQueue());
        }
        queue.deQueue();

    }
    @Test
    public void testIsEmpty(){
        Assert.assertEquals(true,queue.isEmpty());
        queue.enQueue(1);
        Assert.assertEquals(false,queue.isEmpty());
        queue.deQueue();
        Assert.assertEquals(true,queue.isEmpty());
    }
    @Test
    public void testSize(){
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(i,queue.size());
            queue.enQueue(i);
        }
        for (int i = 4; i > 0; i--) {
            Assert.assertEquals(i,queue.size());
            queue.deQueue();
        }
        Assert.assertEquals(0,queue.size());
    }
}
