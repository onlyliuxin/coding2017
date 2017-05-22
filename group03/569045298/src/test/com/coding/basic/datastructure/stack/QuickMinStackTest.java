package com.coding.basic.datastructure.stack;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by zt
 * 2017/5/6 16:16
 */
public class QuickMinStackTest {

    @Test
    public void findMin() throws Exception {
        QuickMinStack<Integer> stack = new QuickMinStack<>();
        stack.push(9);
        stack.push(2);
        stack.push(3);
        stack.push(2);
        stack.push(7);
        stack.push(0);
        stack.push(1);
        Assert.assertEquals(new Integer(0), stack.findMin());
        stack.pop();
        Assert.assertEquals(new Integer(0), stack.findMin());
        stack.pop();
        Assert.assertEquals(new Integer(2), stack.findMin());
        stack.pop();
        Assert.assertEquals(new Integer(2), stack.findMin());
        stack.push(-1);
        Assert.assertEquals(new Integer(-1), stack.findMin());
    }

}