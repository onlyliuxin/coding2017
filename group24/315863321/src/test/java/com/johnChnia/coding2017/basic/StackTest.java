package com.johnChnia.coding2017.basic;

import com.johnChnia.coding2017.basic.stack.Stack;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

/**
 * Created by john on 2017/3/10.
 */
public class StackTest {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    Stack<Integer> stack3;
    Stack<Integer> stack4;

    @Before
    public void setUp() throws Exception {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        stack3 = new Stack<>();
        stack4 = new Stack<>();
    }

    @Test
    public void testPush() throws Exception {
        for (int i = 0; i < 6; i++) {
            stack1.push(i);
        }
        assertThat(stack1.peek(), equalTo(5));

    }

    @Test
    public void testPop() throws Exception {
        for (int i = 0; i < 6; i++) {
            stack2.push(i);
        }
        assertThat(stack2.pop(), equalTo(5));
        assertThat(stack2.size(), equalTo(5));

    }

    @Test
    public void testPeek() throws Exception {
        for (int i = 0; i < 6; i++) {
            stack3.push(i);
        }
        assertThat(stack3.peek(), equalTo(5));
        assertThat(stack3.size(), equalTo(6));

    }

    @Test()
    public void testEmpty() throws Exception {
        for (int i = 0; i < 2; i++) {
            stack4.push(i);
        }
        assertFalse(stack4.empty());
        stack4.pop();
        stack4.pop();
        assertTrue(stack4.empty());
    }

}