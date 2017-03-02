package xdx.homework.first;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import xdx.homework.first.Queue;

/**
 * Queue Tester.
 *
 * @author <Authors name>
 * @since <pre>二月 25, 2017</pre>
 * @version 1.0
 */
public class QueueTest {

    private Queue<String> defaultQueue;

    @Before
    public void before() throws Exception {

        defaultQueue = new Queue<>();
        defaultQueue.enQueue("赵");
        defaultQueue.enQueue("钱");
        defaultQueue.enQueue("孙");
        defaultQueue.enQueue("李");
        defaultQueue.enQueue("周");
        defaultQueue.enQueue("吴");
        defaultQueue.enQueue("郑");
        defaultQueue.enQueue("王");
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: enQueue(E data)
     *
     */
    @Test
    public void testEnQueue() throws Exception {

        Queue<String> testQueue = new Queue<>();
        testQueue.enQueue("刘备");
        testQueue.enQueue("关羽");
        testQueue.enQueue("张飞");
        System.out.println(testQueue);
    }

    /**
     *
     * Method: deQueue()
     *
     */
    @Test
    public void testDeQueue() throws Exception {

        Queue<String> testQueue = new Queue<>();
        testQueue.enQueue("刘备");
        testQueue.enQueue("关羽");
        testQueue.enQueue("张飞");
        System.out.println("删除前:" + testQueue);
        System.out.println("删除:" + testQueue.deQueue());
        System.out.println("删除后:" + testQueue);
    }

    /**
     *
     * Method: length()
     *
     */
    @Test
    public void testLength() throws Exception {
        Assert.assertEquals(8, defaultQueue.length());
        System.out.println("defaultQueue长度:" + defaultQueue.length());
    }

    /**
     *
     * Method: isEmpty()
     *
     */
    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(!defaultQueue.isEmpty());
    }

    /**
     *
     * Method: clear()
     *
     */
    @Test
    public void testClear() throws Exception {

        Queue<String> testQueue = new Queue<>();
        testQueue.enQueue("刘备");
        testQueue.enQueue("关羽");
        testQueue.enQueue("张飞");

        testQueue.clear();
        Assert.assertTrue(testQueue.isEmpty());
    }

    /**
     *
     * Method: getFront()
     *
     */
    @Test
    public void testGetFront() throws Exception {
        Assert.assertEquals("赵", defaultQueue.getFront());
        Assert.assertNotEquals("钱", defaultQueue.getFront());
    }

    /**
     *
     * Method: getRear()
     *
     */
    @Test
    public void testGetRear() throws Exception {
        Assert.assertEquals("王", defaultQueue.getRear());
        Assert.assertNotEquals("钱", defaultQueue.getFront());
    }

    /**
     *
     * Method: toString()
     *
     */
    @Test
    public void testToString() throws Exception {
        System.out.println("defaultQueue内容:" + defaultQueue.toString());
    }


}
