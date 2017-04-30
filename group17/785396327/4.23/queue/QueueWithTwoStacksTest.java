package queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gongxun on 2017/4/26.
 */
public class QueueWithTwoStacksTest {

    private QueueWithTwoStacks queue;

    @Before
    public void startUp() {
        queue = new QueueWithTwoStacks();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void enQueueTest() {
        System.out.println(queue);
    }

    @Test
    public void deQueueTest() {
        queue.deQueue();
        System.out.println(queue);
    }
}
