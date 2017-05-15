package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Stack;
import main.StackUtil;

public class StackUtilTest {
	Stack stack;

	@Before
	public void setUp() throws Exception {
		stack = new Stack();
	}

	@Test
	public void testReverse() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		StackUtil.reverse(stack);
		int[] expected = { 1, 2, 3, 4, 5 };
		for (int i = 0; !stack.isEmpty(); i++) {
			assertEquals(expected[i], stack.pop());
		}
	}

	@Test
	public void testRemove() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		StackUtil.remove(stack, 9);
		int[] expected = { 5, 4, 3, 2, 1 };
		for (int i = 0; !stack.isEmpty(); i++) {
			assertEquals(expected[i], stack.pop());
		}

		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		StackUtil.remove(stack, 3);
		int[] expected1 = { 5, 4, 2, 1 };
		for (int i = 0; !stack.isEmpty(); i++) {
			assertEquals(expected1[i], stack.pop());
		}
	}

	@Test
	public void testGetTop() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		Object[] result = StackUtil.getTop(stack, 3);
		int[] expected = { 5, 4, 3 };
		for (int i = 0; i < result.length; i++) {
			assertEquals(expected[i], result[i]);
		}

		result = StackUtil.getTop(stack, 9);
		int[] expected1 = { 5, 4, 3, 2, 1 };
		for (int i = 0; i < result.length; i++) {
			assertEquals(expected1[i], result[i]);
		}

		result = StackUtil.getTop(stack, -1);
		assertEquals(0, result.length);
	}

	@Test
	public void testIsValidPairs() {
		assertTrue(StackUtil.isValidPairs("12wed333"));
		assertTrue(StackUtil.isValidPairs("1{2[3(4)5]6}"));
		assertTrue(StackUtil.isValidPairs("{{{[[[((()))]]]}}}"));
		assertTrue(StackUtil.isValidPairs("{}[]()([{}])"));
		assertFalse(StackUtil.isValidPairs(")abcd{}[]()"));
		assertFalse(StackUtil.isValidPairs("a{b[c}d]()"));
		assertFalse(StackUtil.isValidPairs("a{b)c}[]{}"));
		assertFalse(StackUtil.isValidPairs("{}[](()"));
	}

}
