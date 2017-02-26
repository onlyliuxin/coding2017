package org.korben.stack;

import java.util.EmptyStackException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * KStack测试
 *
 * Created by Korben on 18/02/2017.
 */
public class KStackTest {
    private KStack<Integer> stack;
    private int initTestSize;

    @Before
    public void init() {
        stack = new KStack<>();
        initTestSize = 5;

        for (int i = 0; i < initTestSize; i++) {
            stack.push(i);
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(initTestSize, stack.size());
    }

    @Test
    public void push() throws Exception {
        stack.push(9);
    }

    @Test(expected = EmptyStackException.class)
    public void pop() throws Exception {
        for (int i = 0; i < initTestSize; i++) {
            Integer value = stack.pop();
            Assert.assertEquals(value.intValue(), initTestSize - 1 - i);
        }

        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void peek() throws Exception {
        Assert.assertEquals(initTestSize - 1, stack.peek().intValue());
        for (int i = 0; i < initTestSize; i++) {
            stack.pop();
        }
        stack.peek();
    }

    @Test
    public void empty() throws Exception {
        Assert.assertFalse(stack.empty());
        for (int i = 0; i < initTestSize; i++) {
            stack.pop();
        }
        Assert.assertTrue(stack.empty());
    }

    @Test
    public void search() throws Exception {
        for (int i = 0; i < initTestSize; i++) {
            Assert.assertEquals(i, stack.search(i));
        }
    }
}