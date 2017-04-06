package com.coding.weak1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/12 0012.
 */
public class StackTest {

    @Test
    public void testPush() throws Exception {
        Stack stack = new Stack();
        Assert.assertEquals(stack.size(), 0);
        stack.push("java");
        stack.push("c++");
        Assert.assertEquals(stack.size(), 2);
        Assert.assertEquals(stack.peek(), "c++");
    }

    @Test
    public void testPop() throws Exception {
        Stack stack = new Stack();
        stack.push("java");
        stack.push("c++");
        stack.push("c#");
        stack.push("php");
        stack.push("python");
        Assert.assertEquals(stack.pop(), "python");
        Assert.assertEquals(stack.pop(), "php");
        Assert.assertEquals(stack.pop(), "c#");
        Assert.assertEquals(stack.pop(), "c++");
        Assert.assertEquals(stack.pop(), "java");
        Assert.assertEquals(stack.size(), 0);
    }

    @Test
    public void testPeek() throws Exception {
        Stack stack = new Stack();
        stack.push("java");
        stack.push("c++");
        stack.push("c#");
        stack.push("php");
        stack.push("python");
        Assert.assertEquals(stack.peek(), "python");
        Assert.assertEquals(stack.peek(), "python");
        Assert.assertEquals(stack.size(), 5);
    }

    @Test
    public void testIsEmpty() throws Exception {
        Stack stack = new Stack();
        Assert.assertEquals(stack.isEmpty(), true);
        stack.push(1);
        Assert.assertEquals(stack.isEmpty(), false);
    }

    @Test
    public void testSize() throws Exception {
        Stack stack = new Stack();
        stack.push("java");
        stack.push("c++");
        stack.push("c#");
        stack.push("php");
        stack.push("python");
        Assert.assertEquals(stack.size(), 5);
    }
}