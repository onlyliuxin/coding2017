package com.byhieg.coding2017test;

import com.byhieg.coding2017.Stack;
import junit.framework.TestCase;

/**
 * Created by byhieg on 17/2/22.
 * Mail to byhieg@gmail.com
 */
public class StackTest extends TestCase {
    Stack stack = new Stack();

    public void testPush() throws Exception {
        stack.push(1);
        stack.push("31231");
        stack.push(null);
        stack.push(Integer.MAX_VALUE + 1000);
        stack.push(Integer.MIN_VALUE - 1000000);
        stack.push(true);
        stack.push('a');
    }

    public void testPop() throws Exception {
        int a = 1;
        for (int i = 0; i < 10; i++) {
            stack.push(a + i);
        }
        int size = stack.size();
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public void testPeek() throws Exception {
        char a = 'a';
        for (int i = 0; i < 10; i++) {
            stack.push(a + i);
        }

        System.out.println("size的大小是" + stack.size());
        System.out.println(stack.peek());
    }

    public void testIsEmpty() throws Exception {
        System.out.println(stack.isEmpty());
        stack.push(1);
        System.out.println(stack.isEmpty());

    }


}