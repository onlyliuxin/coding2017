package org.xukai.common;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xukai
 * @desc
 * @date 2017-02-20-下午 4:20
 */
public class StackTest {

    @Test
    public void testPush() throws Exception {
        Stack stack = new Stack();
        Assert.assertTrue(stack.isEmpty());
        stack.push("0");
        stack.push("1");
        stack.push("2");
        stack.push("3");
        Assert.assertTrue(!stack.isEmpty());
        Assert.assertTrue(stack.peek().equals("3"));
        Assert.assertTrue(stack.pop().equals("3"));
        Assert.assertTrue(stack.size() == 3);
        stack.pop();
        stack.pop();
        stack.pop();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void testPop() throws Exception {

    }

    @Test
    public void testPeek() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }
}