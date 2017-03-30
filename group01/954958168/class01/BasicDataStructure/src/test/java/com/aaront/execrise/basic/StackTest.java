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
        Assert.assertArrayEquals(new Object[]{1, 2, 3}, stack.toArray());
    }

    @Test
    public void testPop() {
        Object element1 = stack.pop();
        Assert.assertEquals(3, element1);
        Object element2 = stack.pop();
        Assert.assertEquals(2, element2);
        Assert.assertArrayEquals(new Object[]{1}, stack.toArray());
    }

    @Test
    public void testPeek() {
        Object element1 = stack.peek();
        Assert.assertEquals(3, element1);
        Object element2 = stack.peek();
        Assert.assertEquals(3, element2);
        Assert.assertArrayEquals(new Object[]{1, 2, 3}, stack.toArray());
    }

}
