package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import basicstruct.Stack;

public class StackTest {
	
    Stack s= new Stack();
	@Before
	public void setUp() throws Exception {
		s.push(11);
		s.push(22);
		s.push(33);
		s.push("44");
		s.push(55);
	}

	@Test
	public void testPop() {
	System.out.println(s.peek());	
	s.pop();
	System.out.println(s.peek());
	}

	@Test
	public void testIsEmpty() {
	System.out.println(s.isEmpty());
	}

	@Test
	public void testSize() {
		System.out.println(s.size());
	}

}
