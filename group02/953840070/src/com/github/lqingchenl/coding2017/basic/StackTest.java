package com.github.lqingchenl.coding2017.basic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Stack Tester.
 */
public class StackTest {

    private static Stack testStack = new Stack();

    /**
     * Method: push(Object o)
     */
    @Test
    public void testPush() throws Exception {
        testStack.push(1);
        assertEquals(1, testStack.peek());
    }

    /**
     * Method: pop()
     */
    @Test
    public void testPop() throws Exception {
        testStack.push(1);
        assertEquals(1, testStack.pop());
    }

    /**
     * Method: peek()
     */
    @Test
    public void testPeek() throws Exception {
        testStack.push(1);
        assertEquals(1, testStack.peek());
        testStack.push(2);
        assertEquals(2, testStack.peek());
    }

    /**
     * Method: isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {
        testStack.push(1);
        assertEquals(false, testStack.isEmpty());
    }

    /**
     * Method: size()
     */
    @Test
    public void testSize() throws Exception {
        testStack.push(1);
        testStack.push(2);
        assertEquals(2, testStack.size());
    }

}
