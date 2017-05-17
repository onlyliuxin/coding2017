package main.coding_170430;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peterchen on 2017/5/5.
 */
public class QueueWithTwoStacksTest extends TestCase {
    @Test
    public void testIsEmpty() throws Exception {
            QueueWithTwoStacks<Integer> twoStacks = new QueueWithTwoStacks<>();
             Assert.assertTrue(twoStacks.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        QueueWithTwoStacks<Integer> twoStacks = new QueueWithTwoStacks<>();
        twoStacks.enQueue(10);
        twoStacks.enQueue(5);
        Assert.assertEquals(twoStacks.size(),2);
    }

    @Test
    public void testEnQueue() throws Exception {
        QueueWithTwoStacks<Integer> twoStacks = new QueueWithTwoStacks<>();
        twoStacks.enQueue(10);
        twoStacks.enQueue(5);
        twoStacks.enQueue(15);
    }

    @Test
    public void testDeQueue() throws Exception {
        QueueWithTwoStacks<Integer> twoStacks = new QueueWithTwoStacks<>();
        twoStacks.enQueue(10);
        twoStacks.enQueue(5);
        twoStacks.enQueue(15);
        twoStacks.enQueue(1);
        twoStacks.enQueue(51);
        twoStacks.enQueue(12);
        twoStacks.enQueue(11);
        twoStacks.enQueue(50);
        twoStacks.enQueue(150);
        while (!twoStacks.isEmpty()){
            System.out.print(" "+twoStacks.deQueue());
        }
    }

}