package test.collection;

import java.util.EmptyStackException;
import static util.TestUtil.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import collection.concrete.Stack;
import junit.framework.TestCase;

public class StackTest extends TestCase {

	Stack<Integer> myStack;

	@Override
	@Before
	public void setUp() throws Exception {
		myStack = new Stack<Integer>();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		myStack = null;
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, myStack.isEmpty());
		myStack.push(getRandomNumber());
		assertEquals(false, myStack.isEmpty());
	}

	@Test
	public void testPush() {
		for (int i = 0; i < 10; i++) {
			assertEquals(i, myStack.size());
			myStack.push(i);
		}
	}

	@Test
	public void testPop() {
		testPush();
		int size = myStack.size();
		for (int i = size; i > 0; i--) {
			assertEquals(i, myStack.size());
			int expect = i - 1;
			assertEquals(i, myStack.size());
			int actual = myStack.pop();
			assertEquals(expect, actual);
		}

		try {
			myStack.pop();
			fail("no exception throw");
		} catch (Exception e) {
			assertEquals(EmptyStackException.class, e.getClass());
		}
	}

	@Test
	public void testPeek() {

		int expected = 0;
		int peek1 = 0;
		int repeated = 0;

		for (int i = 0; i < 10; i++) {
			myStack.push(i);
			expected = i;

			peek1 = myStack.peek();
			assertEquals(expected, peek1);

			for (int j = 0; j < i; j++) {
				repeated = myStack.peek();
				assertEquals(expected, repeated);
			}
		}

	}

	public void testGet() {

		try {
			myStack.get(getRandomNumber());
			fail("no exception throw");
		} catch (Exception e) {
			assertEquals(EmptyStackException.class, e.getClass());
		}

	}

	@Test
	public void testSize() {
		for (int i = 0; i < 10000; i++) {
			assertEquals(i, myStack.size());
			myStack.push(i);
		}
	}

	@Test
	public void testAdd() {

		int size = getRandomNumber();
		int[] expected = new int[size];
		int actual;
		for (int i = 0; i < size; i++) {
			actual = getRandomNumber();
			expected[i] = actual;
			myStack.add(actual);
		}

		int expectedInt;
		for (int i = 0; i < size; i++) {
			expectedInt = expected[size - i - 1];
			actual = myStack.pop();
			assertEquals(expectedInt, actual);
		}
	}

	@Test
	public void testPopPushAndPeek() {
		for (int i = 0; i < 10; i++) {
			int expected = i;
			myStack.push(i);
			int a = 0;
			myStack.push(a);
			myStack.pop();
			int actual = myStack.peek();
			assertEquals(expected, actual);
		}
	}

}
