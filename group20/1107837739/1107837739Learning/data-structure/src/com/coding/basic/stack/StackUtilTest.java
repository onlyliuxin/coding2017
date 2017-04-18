package com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Korben on 09/04/2017.
 */
public class StackUtilTest {

    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
    }

    @Test
    public void reverse() throws Exception {
        StackUtil.reverse(stack);

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, stack.pop());
        }

        stack.push(1);
        StackUtil.reverse(stack);
        Assert.assertEquals(1, stack.pop());
    }

    @Test
    public void remove() throws Exception {
        StackUtil.remove(stack, 1);
        Assert.assertEquals(stack.size(), 4);
        for (int i = 4; i >= 0; i--) {
            if (i == 1) {
                continue;
            }
            Assert.assertEquals(i, stack.pop());
        }

        stack.push(1);
        Assert.assertEquals(stack.size(), 1);
    }

    @Test
    public void getTop() throws Exception {
        Object[] top = StackUtil.getTop(stack, 3);
        Assert.assertEquals(top.length, 3);
        Assert.assertEquals(top[0], 4);
        Assert.assertEquals(top[1], 3);
        Assert.assertEquals(top[2], 2);

        Assert.assertEquals(stack.size(), 5);

        stack.push(1);
        Assert.assertEquals(stack.size(), 6);
        stack.pop();

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(4 - i, stack.pop());
        }
    }

    @Test
    public void isValidPairs() throws Exception {
        {
            String str = "([e{d}f])";
            Assert.assertTrue(StackUtil.isValidPairs(str));
        }

        {
            String str = "([b{x]y})";
            Assert.assertFalse(StackUtil.isValidPairs(str));
        }
    }
}