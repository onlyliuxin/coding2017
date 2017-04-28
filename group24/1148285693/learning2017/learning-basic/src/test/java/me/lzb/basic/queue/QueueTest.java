package me.lzb.basic.queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by LZB on 2017/4/27.
 */
public class QueueTest {


    @Test
    public void queueWithTwoStacksTest() {
        QueueWithTwoStacks q = new QueueWithTwoStacks();
        Assert.assertTrue(q.isEmpty());
        Assert.assertEquals(0, q.size());

        Object o;


        q.enQueue("a");
        Assert.assertEquals("[a]1", q.toString() + q.size());

        q.enQueue("b");
        Assert.assertEquals("[a,b]2", q.toString() + q.size());

        q.enQueue("c");
        Assert.assertEquals("[a,b,c]3", q.toString() + q.size());

        o = q.deQueue();
        Assert.assertEquals("[b,c]2", q.toString() + q.size());
        Assert.assertEquals("a", o.toString());

        q.enQueue("d");
        Assert.assertEquals("[b,c,d]3", q.toString() + q.size());


        o = q.deQueue();
        Assert.assertEquals("[c,d]2", q.toString() + q.size());
        Assert.assertEquals("b", o.toString());

        o = q.deQueue();
        Assert.assertEquals("[d]1", q.toString() + q.size());
        Assert.assertEquals("c", o.toString());

        o = q.deQueue();
        Assert.assertEquals("[]0", q.toString() + q.size());
        Assert.assertEquals("d", o.toString());
    }

    public void circleQueueTest() {
        CircleQueue<String> q = new CircleQueue<>(5);
        Assert.assertTrue(q.isEmpty());
        Assert.assertEquals(0, q.size());

        q.enQueue("a");
        Assert.assertEquals("[a]1", q.toString() + q.size());

        q.enQueue("b");
        Assert.assertEquals("[a,b]2", q.toString() + q.size());

        q.enQueue("c");
        Assert.assertEquals("[a,b,c]3", q.toString() + q.size());

        q.enQueue("d");
        Assert.assertEquals("[a,b,c,d]4", q.toString() + q.size());

        q.enQueue("e");
        Assert.assertEquals("[a,b,c,d,e]5", q.toString() + q.size());


        q.enQueue("f");
        Assert.assertEquals("[b,c,d,e,f]5", q.toString() + q.size());


        Object o;

        o = q.deQueue();
        Assert.assertEquals("[c,d,e,f]4", q.toString() + q.size());
        Assert.assertEquals("b", o.toString());


        o = q.deQueue();
        Assert.assertEquals("[d,e,f]3", q.toString() + q.size());
        Assert.assertEquals("c", o.toString());

        q.enQueue("g");
        Assert.assertEquals("[d,e,f,g]4", q.toString() + q.size());

        o = q.deQueue();
        Assert.assertEquals("[e,f,g]3", q.toString() + q.size());
        Assert.assertEquals("d", o.toString());

        o = q.deQueue();
        Assert.assertEquals("[f,g]2", q.toString() + q.size());
        Assert.assertEquals("e", o.toString());

        o = q.deQueue();
        Assert.assertEquals("[g]1", q.toString() + q.size());
        Assert.assertEquals("f", o.toString());

        o = q.deQueue();
        Assert.assertEquals("[]0", q.toString() + q.size());
        Assert.assertEquals("g", o.toString());
    }
}
