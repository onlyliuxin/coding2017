package coding.basic;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author shane
 * @Time 2017/2/26 17:24
 * @Email stevenchenguang@gmail.com
 * @Desc ...
 */
public class QueueTest extends AbstractTest {

    private static Queue queue;

    @Before
    public void before() {
        queue = new Queue();

        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        queue.enQueue("e");

        printStar();
        System.out.println("Before Test data :" + queue);
        printHyphen();
    }

    @After
    public void after() {
        printHyphen();
        System.out.println("After Test data : " + queue);
        printStar();
    }

    @Test
    public void testDeQueueAndIsEmpty() {
        Assert.assertEquals("a", queue.deQueue());

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        Assert.assertEquals(true, queue.isEmpty());

        try {
            queue.deQueue();
        } catch (RuntimeException e) {
            Assert.assertEquals("Queue is empty", e.getMessage());
        }
    }

    @Test
    public void testSize() {
        Assert.assertEquals(5, queue.size());
    }
}
