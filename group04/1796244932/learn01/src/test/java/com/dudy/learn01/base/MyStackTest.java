package com.dudy.learn01.base;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by dudy on 2017/2/20.
 */
public class MyStackTest {
    @Test
    public void push() throws Exception {

        MyStack stack = new MyStack();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        Object pop = stack.pop();
        System.out.println( "size :" + stack.size() + "pop:" + pop);
        Object peek = stack.peek();
        System.out.println( "size :" + stack.size() + "peek: " + peek);
    }

    @Test
    public void pop() throws Exception {

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

    @Test
    public  void stackTest(){
        Stack<String>  stack = new Stack<String>();
        stack.push("1");
        stack.push("2");
        stack.peek();
        stack.pop();
        System.out.println(stack.size());

    }

}