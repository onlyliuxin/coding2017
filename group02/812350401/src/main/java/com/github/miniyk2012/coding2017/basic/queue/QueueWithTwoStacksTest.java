package com.github.miniyk2012.coding2017.basic.queue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* QueueWithTwoStacks Tester. 
* 
* @author <Authors name> 
* @since <pre>May 3, 2017</pre> 
* @version 1.0 
*/ 
public class QueueWithTwoStacksTest {
    QueueWithTwoStacks<String> queue;

    @Before
    public void before() throws Exception {
        queue = new QueueWithTwoStacks<>();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testFunction() {
        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");
        Assert.assertEquals(4, queue.size());
        Assert.assertFalse(queue.isEmpty());

        Assert.assertEquals("a", queue.deQueue());
        Assert.assertEquals("b", queue.deQueue());
        Assert.assertEquals("c", queue.deQueue());
        Assert.assertEquals(1, queue.size());
        Assert.assertFalse(queue.isEmpty());

        queue.enQueue("e");
        Assert.assertEquals("d", queue.deQueue());
        Assert.assertEquals("e", queue.deQueue());
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());

    }



} 
