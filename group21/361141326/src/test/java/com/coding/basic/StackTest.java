package com.coding.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mortimer on 2017/2/26.
 *
 */
public class StackTest {

    @Test
    public void testPush() {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(11);
        stack.push(12);
        stack.push(13);

        Assert.assertEquals(4, stack.size());
    }

    @Test
    public void testPop() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        Integer pop = stack.pop();
        Assert.assertEquals(Integer.valueOf(9), pop);
    }

    @Test
    public void testPeek() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        Integer peek = stack.peek();
        Assert.assertEquals(Integer.valueOf(99), peek);
    }
}
