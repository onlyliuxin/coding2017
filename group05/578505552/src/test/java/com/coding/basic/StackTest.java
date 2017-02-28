package com.coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by songbao.yang on 2017/2/24.
 *
 */
public class StackTest {

    private Stack stack;

    public static final int SIZE = 100;

    @Before
    public void setUp() throws Exception {
        stack = new Stack();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void push() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            stack.push(i);
            Assert.assertEquals(i+1, stack.size());
        }
        System.out.println();
    }

    @Test
    public void pop() throws Exception {
        push();
        int beginSize = stack.size();
        for (int i = 0; i < beginSize; i++) {
            Object ele = stack.pop();
            Assert.assertEquals(beginSize-i-1, stack.size());
            Assert.assertEquals(beginSize-i-1, ele);
        }
    }

    @Test
    public void peek() throws Exception {

    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

}