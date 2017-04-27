package com.github.HarryHook.coding2017.stack;
import com.github.HarryHook.coding2017.basic.MyStack;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackUtilTest {
    private StackUtil sk;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReverse() {
	sk = new StackUtil();
	MyStack stack = new MyStack();
	stack.push(1);
	stack.push(2);
	stack.push(3);
	sk.reverse(stack);
	assertEquals(1, stack.pop());
	assertEquals(2, stack.pop());
	assertEquals(3, stack.pop());
    }
    @Test
    public void testRemove() {
	sk = new StackUtil();
	MyStack stack = new MyStack();
	stack.push(1);
	stack.push(2);
	stack.push(3);
	stack.push(2);
	stack.push(5);
	sk.remove(stack, 2);
	assertEquals(5, stack.pop());
	assertEquals(3, stack.pop());
	assertEquals(1, stack.pop());
    }
    @Test
    public void testGetTop() {
	sk = new StackUtil();
	MyStack stack = new MyStack();
	
	Object[] array = sk.getTop(stack, 3);
	assertNull(array);

	stack.push(1);
	stack.push(2);
	stack.push(3);
	stack.push(2);
	stack.push(5);
	array = sk.getTop(stack, 3);
	assertArrayEquals(array, new Object[] {5, 2, 3});
	array = sk.getTop(stack, 6);
	assertArrayEquals(array, new Object[] {5, 2, 3, 2, 1});
	array = sk.getTop(stack, -1);
	assertNull(array);
    }
    @Test
    public void testIsValidPairs() {
	sk = new StackUtil();
	String expr = null;
	assertEquals(false, sk.isValidPairs(expr));
	expr = "";
	assertEquals(false, sk.isValidPairs(expr));
	expr = "{xx[x]t)yyza]}";
	assertEquals(false, sk.isValidPairs(expr));
	expr = "asd{[(asds)]sx}";
	assertEquals(true, sk.isValidPairs(expr));
    }

}
