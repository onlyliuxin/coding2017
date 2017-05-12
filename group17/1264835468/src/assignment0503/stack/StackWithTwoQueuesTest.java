package assignment0503.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/7.
 */
public class StackWithTwoQueuesTest {
    StackWithTwoQueues stack = new StackWithTwoQueues();

    @Test
    public void twoQueueStackTest() {

        pushAll(5, 2, 4, 1, 7);
        Assert.assertEquals(7, stack.pop());
        Assert.assertEquals(1, stack.pop());
        pushAll(0);
        Assert.assertEquals(stack.pop(), 0);


    }

    private void pushAll(int... data) {
        for (int i : data) {
            stack.push(i);
        }
    }
}