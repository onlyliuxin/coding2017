package test.com.coding.basic;

import com.coding.basic.Iterator;
import com.coding.basic.Queue;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  QueueTest
 */
public class QueueTest {
    @Test
    public void enQueue() throws Exception {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        assertEquals(1, queue.deQueue());
    }

    @Test
    public void deQueue() throws Exception {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        assertEquals(1, queue.deQueue());
    }

    @Test
    public void isEmpty() throws Exception {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        assertEquals(false, queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        assertEquals(4 , queue.size());
    }

    @Test
    public void iterator() throws Exception {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(4);
        Iterator iterator = queue.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

}