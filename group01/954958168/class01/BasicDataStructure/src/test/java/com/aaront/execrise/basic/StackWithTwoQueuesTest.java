package com.aaront.execrise.basic;

import com.aaront.exercise.basic.StackWithTwoQueues;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackWithTwoQueuesTest {

    @Test
    public void test() {
        StackWithTwoQueues stack = new StackWithTwoQueues();
        for (int i = 0; i < 500; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 500; i++) {
            assertEquals(500 - i - 1, stack.pop());
        }
        
        stack.push(50);
        stack.push(60);
        assertEquals(60, stack.pop());
        stack.push(70);
        assertEquals(70, stack.pop());
        assertEquals(50, stack.pop());
    }
}