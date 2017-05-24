package me.lzb.basic.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by LZB on 2017/5/4.
 */
public class StackTest {

    @Test
    public void QuickMinStackTest() {

        QuickMinStack stack = new QuickMinStack();
        stack.push(1);
        stack.push(3);
        stack.push(0);
        stack.push(2);

        Assert.assertEquals(0, stack.findMin());
        Assert.assertEquals(2, stack.pop());
        Assert.assertEquals(0, stack.findMin());
        Assert.assertEquals(0, stack.pop());
        Assert.assertEquals(1, stack.findMin());
    }

    @Test
    public void StackWithTwoQueuesTest() {
        StackWithTwoQueues stack = new StackWithTwoQueues();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Assert.assertEquals("[1,2,3,4]", stack.toString());
        Assert.assertEquals("4", String.valueOf(stack.pop()));
        Assert.assertEquals("[1,2,3]", stack.toString());
        Assert.assertEquals("3", String.valueOf(stack.pop()));
        Assert.assertEquals("[1,2]", stack.toString());
    }

    @Test
    public void TwoStackInOneArrayTest() {
        TwoStackInOneArray stack = new TwoStackInOneArray();


        stack.push1(1);
        stack.push1(2);
        stack.push1(3);
        stack.push1(4);
        stack.push1(5);
        stack.push1(6);
        Assert.assertEquals(6, stack.size1());

        stack.push2(1);
        stack.push2(2);
        stack.push2(3);
        stack.push2(4);
        stack.push2(5);
        stack.push2(6);


        Assert.assertEquals("[1,2,3,4,5,6]|[1,2,3,4,5,6]", stack.toString());

        Assert.assertEquals(6, stack.size1());

        Assert.assertEquals(6, stack.peek1());

        Assert.assertEquals(6, stack.peek2());


        Assert.assertEquals(6, stack.pop1());

        Assert.assertEquals(6, stack.pop2());

        Assert.assertEquals("[1,2,3,4,5]|[1,2,3,4,5]", stack.toString());

        Assert.assertEquals(5, stack.pop1());
        Assert.assertEquals(4, stack.pop1());

        Assert.assertEquals(5, stack.pop2());

        Assert.assertEquals("[1,2,3]|[1,2,3,4]", stack.toString());

    }

}
