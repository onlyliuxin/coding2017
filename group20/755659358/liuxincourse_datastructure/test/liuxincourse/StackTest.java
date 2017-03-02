package liuxincourse;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void testPush() {
		Stack stack=new Stack();
		stack.push(22);
		stack.push(33);
		assertEquals(33, stack.pop());
	}
	
	@Test
	public void testIsempty() {
		Stack stack=new Stack();
		stack.push(22);
		stack.push(33);
		stack.pop();
		assertEquals(false, stack.isEmpty());
	}

}
