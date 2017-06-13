package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by damocles on 2017/3/7.
 */
public class StackTest {
    private Stack<String> stack;

    @Before
    public void setUp() throws Exception {
        stack = new Stack<>();

        stack.push("javascript");
        stack.push("java");
        stack.push("c++");
        stack.push("c");
    }

    @After
    public void tearDown() throws Exception {
        stack = null;
    }

    @Test
    public void push() throws Exception {
        stack.push("php");
        Assert.assertEquals("php", stack.pop());
    }

    @Test
    public void pop() throws Exception {
        Assert.assertEquals("c", stack.pop());
    }

    @Test
    public void peek() throws Exception {
        Assert.assertEquals("c", stack.peek());
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(4, stack.size());
    }

}