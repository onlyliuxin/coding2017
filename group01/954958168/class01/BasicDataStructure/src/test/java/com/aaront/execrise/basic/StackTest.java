package com.aaront.execrise.basic;

import com.aaront.exercise.basic.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/21
 */
public class StackTest {

    private Stack stack = new Stack();

    @Before
    public void init() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void testPush() {
        Assert.assertArrayEquals(stack.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testPop() {
        Object element1 = stack.pop();
        Assert.assertEquals(element1, 3);
        Object element2 = stack.pop();
        Assert.assertEquals(element2, 2);
        Assert.assertArrayEquals(stack.toArray(), new Object[]{1});
    }

    @Test
    public void testPeek() {
        Object element1 = stack.peek();
        Assert.assertEquals(element1, 3);
        Object element2 = stack.peek();
        Assert.assertEquals(element2, 3);
        Assert.assertArrayEquals(stack.toArray(), new Object[]{1, 2, 3});
    }

}
