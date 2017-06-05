package stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiaxun
 */
public class TestStackWithTwoQueues {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        StackWithTwoQueues stack = new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Assert.assertEquals(4, stack.pop());
        Assert.assertEquals(3, stack.pop());

        stack.push(5);
        Assert.assertEquals(5, stack.pop());
        Assert.assertEquals(2, stack.pop());
        Assert.assertEquals(1, stack.pop());
    }

}
