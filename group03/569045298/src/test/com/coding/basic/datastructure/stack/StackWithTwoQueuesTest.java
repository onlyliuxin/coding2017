package com.coding.basic.datastructure.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zt
 * 2017/5/6 18:48
 */
public class StackWithTwoQueuesTest {

    @Test
    public void pop() {
        StackWithTwoQueues<Integer> stackWithTwoQueues = new StackWithTwoQueues<>();
        stackWithTwoQueues.push(1);
        stackWithTwoQueues.push(2);
        stackWithTwoQueues.push(3);
        stackWithTwoQueues.push(4);
        stackWithTwoQueues.push(5);
        Assert.assertEquals(new Integer(5), stackWithTwoQueues.pop());
        Assert.assertEquals(new Integer(4), stackWithTwoQueues.pop());
        stackWithTwoQueues.push(6);
        Assert.assertEquals(new Integer(6), stackWithTwoQueues.pop());
        Assert.assertEquals(new Integer(3), stackWithTwoQueues.pop());
    }

}