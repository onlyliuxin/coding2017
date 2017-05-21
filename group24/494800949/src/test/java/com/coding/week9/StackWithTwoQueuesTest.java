package com.coding.week9;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class StackWithTwoQueuesTest {




    @Test
    public void testPush() throws Exception {
        StackWithTwoQueues stackWithTwoQueues = new StackWithTwoQueues();
        stackWithTwoQueues.push(1);
        stackWithTwoQueues.push(2);
        stackWithTwoQueues.push(3);
        stackWithTwoQueues.push(4);
        stackWithTwoQueues.push(5);
        Assert.assertEquals(stackWithTwoQueues.pop(), 5);
        Assert.assertEquals(stackWithTwoQueues.pop(), 4);
        Assert.assertEquals(stackWithTwoQueues.pop(), 3);
        Assert.assertEquals(stackWithTwoQueues.pop(), 2);
        Assert.assertEquals(stackWithTwoQueues.pop(), 1);
    }

}