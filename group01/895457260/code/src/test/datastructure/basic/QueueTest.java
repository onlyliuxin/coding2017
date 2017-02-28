package test.datastructure.basic;

import datastructure.exception.EmptyQueueException;
import datastructure.basic.Queue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Queue Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 24, 2017</pre>
 */
public class QueueTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private Queue getQueue() {
        Queue queue = new Queue(5);
        for (int i = 1; i <= 5; ++i) {
            queue.enQueue(i);
        }
        return queue;
    }

    private void assertQueue(Queue queue, Object[] actual) {
        Class<Queue> clazz = Queue.class;
        Object[] array = null;
        int head = 0;
        int rear = 0;
        Method mapIndex = null;
        try {
            Field arrayField = clazz.getDeclaredField("array");
            Field headField = clazz.getDeclaredField("head");
            Field rearField = clazz.getDeclaredField("rear");
            mapIndex = clazz.getDeclaredMethod("mapIndex", int.class);
            arrayField.setAccessible(true);
            headField.setAccessible(true);
            rearField.setAccessible(true);
            mapIndex.setAccessible(true);
            array = (Object[]) arrayField.get(queue);
            head = (int) headField.get(queue);
            rear = (int) rearField.get(queue);
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        int size = queue.size();
        Object[] excepted = new Object[size];
        int pos = 0;
        try {
            while (head < rear) {
                excepted[pos++] = array[(int) mapIndex.invoke(queue, head)];
                head++;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Assert.assertArrayEquals(excepted, actual);
    }

    /**
     * Method: enQueue(Object o)
     */
    @Test
    public void testEnQueue() throws Exception {
//TODO: Test goes here...
        Queue queue = getQueue();
        for (int i = 6; i <= 10; ++i) {
            queue.enQueue(i);
        }
        assertQueue(queue, new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    /**
     * Method: deQueue()
     */
    @Test
    public void testDeQueue() throws Exception {
//TODO: Test goes here...
        Queue queue = getQueue();
        int count = queue.size() + 2;
        Object[] values = new Object[count];
        boolean[] exceptions = new boolean[count];
        for (int i = 0; i < count; ++i) {
            try {
                values[i] = queue.deQueue();
            } catch (EmptyQueueException e) {
                exceptions[i] = true;
            }
        }
        Assert.assertArrayEquals(values, new Object[]{1, 2, 3, 4, 5, null, null});
        Assert.assertArrayEquals(exceptions, new boolean[]{false, false, false, false, false, true, true});
        assertQueue(queue, new Object[0]);
    }
} 
