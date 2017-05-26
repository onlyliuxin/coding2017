package com.github.HarryHook.coding2017.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TwoStackInOneArrayTest {
    TwoStackInOneArray stack;

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
	stack = new TwoStackInOneArray();
	stack.push1(3);
	stack.push1(5);
	stack.push1(6);
	stack.push1(11);
	assertEquals(11, stack.peek1());
	assertEquals(11, stack.pop1());
	assertEquals(6, stack.pop1());
	stack.push1(61);
	assertEquals(61, stack.pop1());
	assertEquals(5, stack.pop1());
	assertEquals(3, stack.peek1());
	assertEquals(3, stack.pop1());
	// 栈1为空，抛出异常
	expectedEx.expect(Exception.class);
	stack.pop1();

	stack.push2(123);
	stack.push2(1);
	stack.push2(4);

	stack.push1(3);
	stack.push1(5);
	stack.push1(6);

	stack.push2(123);
	stack.push2(1);
	stack.push2(4);
	stack.push2(12);
	stack.push2(2);
	stack.push2(8);

	assertEquals(6, stack.pop1());
	assertEquals(5, stack.peek1());
	assertEquals(5, stack.pop1());
	assertEquals(3, stack.pop1());

	assertEquals(8, stack.pop2());
	assertEquals(2, stack.pop2());
	assertEquals(12, stack.pop2());
	assertEquals(4, stack.pop2());
	assertEquals(1, stack.pop2());
	assertEquals(123, stack.pop2());
	assertEquals(4, stack.pop2());
	assertEquals(1, stack.pop2());
	assertEquals(123, stack.pop2());
	// 栈2为空，抛出异常
	expectedEx.expect(Exception.class);
	stack.pop2();

    }

}
