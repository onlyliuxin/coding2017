package com.coding.basic.datastructure.stack;


import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class StackUtilTest {

    @Test
    public void testReverse() {
        Stack<Integer> s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(null);
        Assert.assertEquals("[1, 2, 3, 4, 5, null]", s.toString());
        StackUtil.reverse(s);
        Assert.assertEquals("[null, 5, 4, 3, 2, 1]", s.toString());
    }

    @Test
    public void testRemove() {
        Stack<Integer> s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        StackUtil.remove(s, 3);
        Assert.assertEquals("[1, 2]", s.toString());
        s = new Stack();
        s.push(1);
        StackUtil.remove(s, 2);
        Assert.assertEquals("[1]", s.toString());
        s = new Stack();
        s.push(1);
        StackUtil.remove(s, 1);
        Assert.assertEquals("[]", s.toString());
        s = new Stack();
        s.push(1);
        s.push(null);
        s.push(2);
        s.push(3);
        StackUtil.remove(s, 2);
        Assert.assertEquals("[1, null, 3]", s.toString());
        s = new Stack();
        s.push(1);
        s.push(null);
        s.push(2);
        s.push(3);
        StackUtil.remove(s, null);
        Assert.assertEquals("[1, 2, 3]", s.toString());
    }

    @Test
    public void testGetTop() {
        Stack<Integer> s = new Stack();
        s.push(1);
        s.push(2);
        s.push(null);
        s.push(3);
        s.push(4);
        s.push(5);
        {
            Object[] values = StackUtil.getTop(s, 4);
            Assert.assertEquals("[1, 2, null, 3, 4, 5]", s.toString());
            Assert.assertEquals(5, values[0]);
            Assert.assertEquals(4, values[1]);
            Assert.assertEquals(3, values[2]);
            Assert.assertEquals(null, values[3]);
        }
    }

    @Test
    public void testIsValidPairs() {
        Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
        Assert.assertFalse(StackUtil.isValidPairs("}])"));
    }

}
