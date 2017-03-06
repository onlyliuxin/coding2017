package org.apn.coding2017.basic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pan on 2017/2/26.
 */
public class StackTest {

    Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = new Stack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void isEmpty() throws Exception {
        System.out.println(stack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        System.out.println(stack.size());
    }

    @Test
    public void push() throws Exception {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
    }

    @Test
    public void pop() throws Exception {
        stack.pop();
        System.out.println(stack);
    }

}