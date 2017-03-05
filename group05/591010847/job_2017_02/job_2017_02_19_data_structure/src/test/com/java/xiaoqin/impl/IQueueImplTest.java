package test.com.java.xiaoqin.impl;

import com.java.xiaoqin.impl.LinkedListImpl;
import com.java.xiaoqin.interfaces.IQueue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * IQueueImplTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 26, 2017</pre>
 */
public class IQueueImplTest {

    private IQueue<Integer> mQueue;

    @Before
    public void before() throws Exception {
        mQueue = new LinkedListImpl<>();
    }

    @After
    public void after() throws Exception {
        mQueue = null;
    }

    /**
     * Method: enQueue(T t)
     */
    @Test
    public void enQueueT() throws Exception {
        for (int i = 0; i < 10; i++) {
            mQueue.enQueue(i);
        }
        System.out.println(mQueue.toString());
    }

    /**
     * Method: deQueue
     */
    @Test
    public void deQueue() throws Exception {
        for (int i = 0; i < 10; i++) {
            mQueue.enQueue(i);
        }
        Assert.assertEquals(mQueue.deQueue(), (Integer) 0);
        Assert.assertEquals(mQueue.deQueue(), (Integer) 1);
    }

    /**
     * Method: isEmpty
     */
    @Test
    public void isEmpty() throws Exception {
        Assert.assertEquals(true, mQueue.isEmpty());
        for (int i = 0; i < 10; i++) {
            mQueue.enQueue(i);
        }
        Assert.assertEquals(false, mQueue.isEmpty());
    }

    /**
     * Method:  size();
     */
    @Test
    public void size() throws Exception {
        for (int i = 0; i < 10; i++) {
            mQueue.enQueue(i);
        }
        Assert.assertEquals(10, mQueue.size());
    }

} 
