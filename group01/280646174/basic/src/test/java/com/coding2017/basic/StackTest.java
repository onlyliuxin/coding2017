package com.coding2017.basic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaitao.li on 17/2/21.
 */
public class StackTest {
    @Test
    public void push() throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        Assert.assertTrue(stack.size() == 2);
        Assert.assertTrue(stack.peek().equals(2));
        Assert.assertTrue(stack.pop().equals(2));
        Assert.assertTrue(stack.pop().equals(1));
        Assert.assertTrue(stack.isEmpty());
    }

}