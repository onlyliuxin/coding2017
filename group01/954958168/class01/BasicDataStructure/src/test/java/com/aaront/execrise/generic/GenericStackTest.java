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
        Assert.assertArrayEquals(new String[]{"1", "2", "3" }, stack.toArray());
    }

    @Test
    public void testPop() {
        String element1 = stack.pop();
        Assert.assertEquals("3", element1);
        String element2 = stack.pop();
        Assert.assertEquals("2", element2);
        Assert.assertArrayEquals(new String[]{"1" }, stack.toArray());
    }

    @Test
    public void testPeek() {
        String element1 = stack.peek();
        Assert.assertEquals("3", element1);
        String element2 = stack.peek();
        Assert.assertEquals("3", element2);
        Assert.assertArrayEquals(new String[]{"1", "2", "3" }, stack.toArray());
    }

}
