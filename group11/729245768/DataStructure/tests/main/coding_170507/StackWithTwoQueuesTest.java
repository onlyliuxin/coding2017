package main.coding_170507;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peterchen on 2017/5/5.
 */
public class StackWithTwoQueuesTest extends TestCase {
    @Test
    public void testPush() throws Exception {
        StackWithTwoQueues twoQueues = new StackWithTwoQueues();
        twoQueues.push(1);
        twoQueues.push(2);
        twoQueues.push(3);
        Assert.assertEquals(3,twoQueues.pop());
        Assert.assertEquals(2,twoQueues.pop());
    }

    @Test
    public void testPop() throws Exception {
        StackWithTwoQueues twoQueues = new StackWithTwoQueues();
        twoQueues.push(15);
        twoQueues.pop();
        twoQueues.push(20);
        twoQueues.push(30);
        Assert.assertEquals(30,twoQueues.pop());
        Assert.assertEquals(20,twoQueues.pop());
    }

}