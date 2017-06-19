package code09;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yyglider on 2017/5/4.
 */
public class StackWithTwoQueuesTest {
    @Test
    public void pop() throws Exception {
        StackWithTwoQueues stack = new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        Assert.assertEquals(4,stack.pop());
        Assert.assertEquals(3,stack.pop());
        Assert.assertEquals(2,stack.pop());
        Assert.assertEquals(1,stack.pop());

    }

}