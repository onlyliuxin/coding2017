package com.circle.collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/2/25.
 */
public class StackTest {
    private Stack stack = null;


    @Test
    public void push() throws Exception {
        stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
    }

    @Test
    public void pop() throws Exception {
        stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        System.out.println(stack.pop());
    }

    @Test
    public void peek() throws Exception {

        stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());

    }

    @Test
    public void isEmpty() throws Exception {

        stack = new Stack();
        System.out.println(stack.isEmpty());

    }

    @Test
    public void size() throws Exception {

        stack = new Stack();
        stack.push(1);
        System.out.println(stack.size());
    }

}