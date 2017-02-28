package com.github.lhpmatlab.coding2017.basic; 

import org.junit.Test; 
import org.junit.Before; 
import static  org.junit.Assert.*;

/** 
* MyQueue Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 26, 2017</pre> 
* @version 1.0 
*/ 
public class MyQueueTest {
    private MyQueue<String> queue;

    @Before
    public void init() throws Exception {
        queue = new MyQueue<>();
    }

    /**
    *
    * Method: enQueue(T t)
    *
    */
    @Test
    public void testEnQueue() throws Exception {
        queue.enQueue("1");
        assertEquals("size ", queue.size(), 1);
    }

    /**
    *
    * Method: deQueue()
    *
    */
    @Test
    public void testDeQueue() throws Exception {
        queue.enQueue("1");
        queue.enQueue("2");
//        queue.deQueue();
        assertEquals("dequeue element ",queue.deQueue(),"1");
        assertEquals("size ", queue.size(), 1);

    }

    /**
    *
    * Method: isEmpty()
    *
    */
    @Test
    public void testIsEmpty() throws Exception {
        assertEquals("isEmpty method",queue.isEmpty(),true);
    }

    /**
    *
    * Method: size()
    *
    */
    @Test
    public void testSize() throws Exception {
        queue.enQueue("1");
        queue.enQueue("2");
        assertEquals("size method", queue.size(),2);
    }


} 
