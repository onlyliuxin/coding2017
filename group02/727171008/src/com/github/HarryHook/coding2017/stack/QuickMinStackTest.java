package com.github.HarryHook.coding2017.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QuickMinStackTest {
    QuickMinStack stack;

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
	stack = new QuickMinStack();
	// 栈为空，抛出异常
	expectedEx.expect(Exception.class);
	stack.pop();

	stack.push(5);
	stack.push(3);
	stack.push(2);
	stack.push(4);
	stack.push(1);
	assertEquals(1, stack.findMin());
	assertEquals(1, stack.pop());
	assertEquals(2, stack.findMin());
	assertEquals(4, stack.pop());
	assertEquals(2, stack.findMin());
	assertEquals(2, stack.pop());
	assertEquals(3, stack.findMin());
	assertEquals(3, stack.pop());
	assertEquals(5, stack.findMin());
	assertEquals(5, stack.pop());

	expectedEx.expect(Exception.class);
	stack.pop();

    }

}
