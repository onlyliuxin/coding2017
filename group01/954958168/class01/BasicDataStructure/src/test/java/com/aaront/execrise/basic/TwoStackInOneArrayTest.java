package com.aaront.execrise.basic;

import com.aaront.exercise.basic.TwoStackInOneArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwoStackInOneArrayTest {

    @Test
    public void test() {
        TwoStackInOneArray stack = new TwoStackInOneArray();
        for (int i = 0; i < 50; i++) {
            stack.push1("stack1:" + i);
        }
        for (int i = 0; i < 50; i++) {
            stack.push2("stack2:" + i);
        }
        
        for (int i = 0; i < 50; i++) {
            assertEquals("stack1:" + (50 - i - 1), stack.peek1());
            assertEquals("stack1:" + (50 - i - 1), stack.pop1());
        }
        for (int i = 0; i < 50; i++) {
            assertEquals("stack2:" + (50 - i - 1), stack.peek2());
            assertEquals("stack2:" + (50 - i - 1), stack.pop2());
        }
        
        stack.push1(50);
        stack.push1(60);
        assertEquals(60, stack.pop1());
        stack.push1(70);
        assertEquals(70, stack.pop1());
        assertEquals(50, stack.pop1());
        
        stack.push2(50);
        stack.push2(60);
        assertEquals(60, stack.pop2());
        stack.push2(70);
        assertEquals(70, stack.pop2());
        assertEquals(50, stack.pop2());
    }
}
