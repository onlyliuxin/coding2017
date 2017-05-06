package com.coding2017.basic.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by kaitao.li on 2017/4/15.
 */
public class StackUtilTest {

    private Stack mockStack() {
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        return s;
    }

    @Test
    public void testReverse() {
        Stack s = mockStack();
        StackUtil.reverse(s);
        Assert.assertArrayEquals(s.toArray(), new Integer[] { 5, 4, 3, 2, 1 });
    }

    @Test
    public void testRemove() {
        Stack s = mockStack();
        StackUtil.remove(s, 3);
        Assert.assertArrayEquals(s.toArray(), new Integer[] {1, 2, 4, 5});
    }

    @Test
    public void testGetTop() {
        Stack s = mockStack();
        Object[] top = StackUtil.getTop(s, 2);
        Assert.assertArrayEquals(s.toArray(), new Integer[]{1, 2, 3, 4, 5});
        Assert.assertArrayEquals(top, new Integer[] {5, 4});
    }

    @Test
    public void testIsValidPairs() {
        Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
    }
}