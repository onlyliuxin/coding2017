package com.coding.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yrs on 2017/4/8.
 */
public class StackUtilTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReverse() throws Exception {
        Stack s = new Stack();
        for (int i=1; i<=5; i++) {
            s.push(i);
        }
        StackUtil.reverse(s);
        for (int i=1; i<=5; i++) {
            Assert.assertEquals(i, s.pop());
        }
    }

    @Test
    public void testRemove() throws Exception {
        Stack s = new Stack();
        for (int i=1; i<=5; i++) {
            s.push(i);
        }
        StackUtil.remove(s, 0);
        Assert.assertEquals(5, s.size());

        StackUtil.remove(s, 3);
        Assert.assertEquals(4, s.size());
        s.pop();
        s.pop();
        Assert.assertEquals(2, s.peek());
    }

    @Test
    public void testGetTop() throws Exception {
        Stack s = new Stack();
        for (int i=1; i<=5; i++) {
            s.push(i);
        }
        Object[] result = StackUtil.getTop(s, 2);
        Assert.assertEquals(result[0], 5);
        Assert.assertEquals(result[1], 4);
        Assert.assertEquals(s.peek(), 5);
    }

    @Test
    public void testIsValidPairs() throws Exception {
        String s = "([e{d}f])";
        Assert.assertEquals(true, StackUtil.isValidPairs(s));
        s = "([b{x]y})";
        Assert.assertEquals(false, StackUtil.isValidPairs(s));
    }
}