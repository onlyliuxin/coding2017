package testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.basic.Stack;

public class TestStack
{
	@Test
	public void testStack() {
		Stack stack = new Stack();

		assertTrue(stack.isEmpty());

		for (int i = 10; i < 20; i++) {
			stack.push(i);
		}

		assertFalse(stack.isEmpty());
		assertTrue(stack.size() == 10);

		assertTrue(stack.peek().equals(19));
		assertFalse(stack.peek().equals(10));

		assertTrue(stack.pop().equals(19));
		assertTrue(stack.pop().equals(18));
	}

}
