package com.github.miniyk2012.coding2017.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


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
	stack = new StackWithTwoQueues<Integer>();
	assertEquals(true, stack.isEmpty());

	stack.push(1);
	stack.push(2);
	stack.push(3);
	assertEquals(3, stack.size());
	assertEquals(false, stack.isEmpty());
	assertEquals(3, stack.pop());
	stack.push(43);
	assertEquals(43, stack.pop());
	assertEquals(2, stack.pop());
	assertEquals(1, stack.pop());

	assertEquals(0, stack.size());
	assertEquals(true, stack.isEmpty());

	expectedEx.expect(Exception.class);
	stack.pop();
    }

}
