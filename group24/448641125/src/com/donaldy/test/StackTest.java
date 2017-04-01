package com.donaldy.test;

import com.donaldy.basic.Stack;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by donal on 2017/3/11.
 */
public class StackTest {

    private Stack stack;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        stack = new Stack();
        for (int i = 0; i < 3; ++i)
            stack.push(i);
    }


    @Test
    public void testPeek() {
        assertEquals(false, stack.isEmpty());
        assertEquals(3, stack.size());
        assertEquals(2, (int)stack.peek());
    }

    @Test
    public void testPop() {
        assertEquals(2, (int)stack.pop());
    }

    @Test
    public void testRuntimeException() {
        for (int i = 0; i < 3; ++i)
            stack.pop();
        thrown.expect(RuntimeException.class);
        stack.pop();
    }

}
