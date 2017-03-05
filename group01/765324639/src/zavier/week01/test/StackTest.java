package zavier.week01.test;

import java.util.EmptyStackException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import zavier.week01.basic.Stack;


public class StackTest {

    private Stack stack = new Stack();

    @Before
    public void setUp() {
        for (int i = 0; i < 500; i++) {
            stack.push(i);
        }
    }

    @Test
    public void testPush() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        Assert.assertEquals(600, stack.size());
    }

    @Test(expected = EmptyStackException.class)
    public void testPop() {
        for (int i = 0; i < 500; i++) {
            Assert.assertEquals(499 - i, stack.pop());
        }
        stack.pop();
    }

    @Test
    public void testPeek() {
        Assert.assertEquals(499, stack.peek());
        Assert.assertEquals(499, stack.peek());
        stack.pop();
        Assert.assertEquals(498, stack.peek());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertFalse(stack.isEmpty());
        Assert.assertTrue(new Stack().isEmpty());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(500, stack.size());
        stack.pop();
        Assert.assertEquals(499, stack.size());
    }

}
