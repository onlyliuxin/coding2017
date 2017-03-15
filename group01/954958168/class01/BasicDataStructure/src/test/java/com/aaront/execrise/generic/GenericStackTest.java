package com.aaront.execrise.generic;

import com.aaront.exercise.generic.GenericStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tonyhui
 * @since 17/2/21
 */
public class GenericStackTest {

    private GenericStack<String> stack = new GenericStack<>();

    @Before
    public void init() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
    }

    @Test
    public void testPush() {
        Assert.assertArrayEquals(stack.toArray(), new String[]{"1", "2", "3"});
    }

    @Test
    public void testPop() {
        String element1 = stack.pop();
        Assert.assertEquals(element1, "3");
        String element2 = stack.pop();
        Assert.assertEquals(element2, "2");
        Assert.assertArrayEquals(stack.toArray(), new String[]{"1"});
    }

    @Test
    public void testPeek() {
        String element1 = stack.peek();
        Assert.assertEquals(element1, "3");
        String element2 = stack.peek();
        Assert.assertEquals(element2, "3");
        Assert.assertArrayEquals(stack.toArray(), new String[]{"1", "2", "3"});
    }

}
