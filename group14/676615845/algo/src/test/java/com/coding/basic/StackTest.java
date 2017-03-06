package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mark on 17/2/25.
 */
public class StackTest {

    private Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = new Stack();
    }

    @After
    public void tearDown() throws Exception {
        stack = null;
    }

    @Test
    public void push() throws Exception {
        stack.push("first");
        stack.push("second");
        Assert.assertEquals("second", stack.pop());
        Assert.assertEquals("first", stack.pop());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void pop() throws Exception {
        
    }

    @Test
    public void peek() throws Exception {
        stack.push("first");
        stack.push("second");
        Assert.assertEquals("second", stack.peek());
        stack.pop();
        Assert.assertEquals("first", stack.peek());
    }

    @Test
    public void isEmpty() throws Exception {
        Assert.assertEquals(true, stack.isEmpty());
        stack.push("first");
        Assert.assertEquals(false, stack.isEmpty());
        stack.pop();
        Assert.assertEquals(true, stack.isEmpty());
        
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(0, stack.size());
        stack.push("first");
        Assert.assertEquals(1, stack.size());
        stack.push("second");
        Assert.assertEquals(2, stack.size());
        stack.pop();
        stack.pop();
        Assert.assertEquals(0, stack.size());
    }

}