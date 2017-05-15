package com.github.HarryHook.coding2017.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sun.java2d.StateTrackable;

public class StackWithTwoQueuesTest {
    StackWithTwoQueues stack;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void test() {
	stack = new StackWithTwoQueues();
	// 栈为空，抛出异常
	expectedEx.expect(Exception.class);
	stack.pop();

	stack.push(1);
	stack.push(2);
	stack.push(3);
	assertEquals(3, stack.pop());
	stack.push(43);
	assertEquals(43, stack.pop());
	assertEquals(2, stack.pop());
	assertEquals(1, stack.pop());

	expectedEx.expect(Exception.class);
	stack.pop();
    }

}
