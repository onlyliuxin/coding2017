package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Stack Test
 */
public class StackTest {
    Stack<String> stack;
    @Before
    public void setUp() throws Exception {
        stack = new Stack<>();
    }

    @After
    public void tearDown() throws Exception {
        stack = null;
    }

    @Test
    public void push() throws Exception {
        stack.push("A");
        assertEquals("A",stack.pop());
    }

    @Test
    public void pop() throws Exception {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals("C",stack.pop());
        assertEquals("B",stack.pop());
        assertEquals("A",stack.pop());
        assertEquals(0,stack.size());


    }

    @Test
    public void peek() throws Exception {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals("C",stack.peek());

    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(true,stack.isEmpty());
        stack.push("A");
        assertEquals(false,stack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals(3,stack.size());
    }

}