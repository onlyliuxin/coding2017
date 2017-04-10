package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Stack;

public class StackTest {
	private Stack stack;
	
	@Before
	public void init(){
		stack = new Stack();
	}
	
	@Test
	public void testPush() {
		stack.push("a");
		assertEquals("a", stack.peek());
		stack.push("b");
		assertEquals("b", stack.peek());
	}

	@Test
	public void testPop() {
		try{
			stack.pop();
			fail("empty stack hasn't element");
		}catch (Exception e) {
		}
		stack.push("a");
		stack.push("b");
		stack.push("c");
		assertEquals("c", stack.pop());
		assertEquals("b", stack.pop());
		assertEquals("a", stack.pop());
	}

	@Test
	public void testPeek() {
	}

	@Test
	public void testIsEmpty() {
		assertTrue(stack.isEmpty());
		stack.push("a");
		stack.push("b");
		assertFalse(stack.isEmpty());
		stack.pop();
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());
		
		
	}

	@Test
	public void testSize() {
		assertEquals(0, stack.size());
		stack.push("a");
		stack.push("b");
		assertEquals(2, stack.size());
	}

}
