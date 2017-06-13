package week1.com.coding.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week1.com.coding.basic.Queue;

public class QueueTest
{
    Queue queue = null;
    
    @Before
    public void before()
    {
        queue = new Queue();
        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        queue.enQueue("e");
    }
    
    // @Test
    public void testSize()
    {
        Assert.assertEquals(5, queue.size());
    }
    
    @Test
    public void testDeQueue()
    {
        int i = 0;
        while (i < queue.size())
        {
            System.out.println(queue.deQueue());
        }
    }
}
