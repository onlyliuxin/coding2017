package com.coding2017.group7.homework.c0226;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyStackTest {
    private MyStack myStack = new MyStack();
    private Object[] elements = new Object[]{1};
    private final int mySize = elements.length;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < mySize; i++) {
            myStack.push(i + 1);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (int i = myStack.size(); i > 0; i--) {
            myStack.pop();
        }
    }

    @Test
    public void push() throws Exception {
        myStack.push(-1);
        Assert.assertTrue(myStack.pop().equals(-1));
    }

    @Test
    public void pop() throws Exception {
        for (int i = myStack.size(); i > 0; i--) {
            myStack.pop();
        }
        Assert.assertEquals(myStack.size(), 0);
    }

    @Test
    public void peek() throws Exception {
        for (int i = 0; i < myStack.size(); i++) {
            Object peek = myStack.peek();
            Assert.assertTrue(peek.equals(elements[i]));
        }
        Assert.assertEquals(myStack.size(), mySize);
    }

}